package com.gundes.trytolist.controller;

import com.gundes.trytolist.domain.dto.task.TaskRequest;
import com.gundes.trytolist.domain.dto.task.TaskResponse;
import com.gundes.trytolist.domain.dto.user.UserResponse;
import com.gundes.trytolist.service.ITaskService;
import com.gundes.trytolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final ITaskService service;

    @PostMapping("/user/{userId}")
    public UserResponse addTask(@PathVariable Long userId, @RequestBody List<TaskRequest> reqs){
        return service.addTasks(userId, reqs);
    }

    @GetMapping("/user/{userId}")
    public List<TaskResponse> getTasksFromUser(@PathVariable Long userId){
        return service.getTasks(userId);
    }

    @DeleteMapping("/user/{userId}/tasks/{taskId}")
    public UserResponse deleteTask(@PathVariable Long userId, @PathVariable Long taskId){
        return service.deleteTask(userId, taskId);
    }

    @PatchMapping("/{taskId}/conclude")
    public TaskResponse finishTask(@PathVariable Long taskId){
        return service.concludeTask(taskId);
    }
}
