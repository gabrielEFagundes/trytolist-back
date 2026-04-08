package com.gundes.trytolist;



import com.gundes.trytolist.TaskRepository;
import com.gundes.trytolist.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api")
public class Projeto {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    // ================= USER =================

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody Map<String, String> body) {

        User user = new User();
        user.setName(body.get("name"));
        user.setEmail(body.get("email"));
        user.setPassword(body.get("password"));

        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));

        return ResponseEntity.ok(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Map<String, String> body) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));

        user.setName(body.get("name"));
        user.setEmail(body.get("email"));
        user.setPassword(body.get("password"));

        return ResponseEntity.ok(userRepository.save(user));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {

        if (!userRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("User not found");
        }

        userRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {

        User user = userRepository
                .findByEmailAndPassword(body.get("email"), body.get("password"))
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));

        return ResponseEntity.ok(user);
    }

    // ================= TASK =================

    @PostMapping("/task/user/{userId}")
    public ResponseEntity<?> addTask(@PathVariable Long userId,
                                     @RequestBody List<Map<String, Object>> body) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        List<Task> tasks = new ArrayList<>();

        for (Map<String, Object> t : body) {

            Task task = new Task();

            task.setTitle((String) t.get("title"));
            task.setContent((String) t.get("content"));

            if (t.get("priority") != null) {
                task.setPriority(Priorities.valueOf((String) t.get("priority")));
            } else {
                task.setPriority(Priorities.LOW);
            }

            task.setIsConcluded(false);
            task.setCreatedAt(Date.valueOf(LocalDate.now()));
            task.setUser(user);

            tasks.add(task);
        }

        taskRepository.saveAll(tasks);
        user.getTasks().addAll(tasks);

        return ResponseEntity.ok(userRepository.save(user));
    }

    @DeleteMapping("/task/user/{userId}/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long userId,
                                        @PathVariable Long taskId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        taskRepository.deleteById(taskId);

        user.getTasks().removeIf(task -> task.getId().equals(taskId));

        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

    @PatchMapping("/task/{taskId}/conclude")
    public ResponseEntity<?> finishTask(@PathVariable Long taskId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found."));

        task.setIsConcluded(true);
        task.setFinishAt(Date.valueOf(LocalDate.now()));

        return ResponseEntity.ok(taskRepository.save(task));
    }
}