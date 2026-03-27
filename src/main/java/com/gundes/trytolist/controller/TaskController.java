package com.gundes.trytolist.controller;

import com.gundes.trytolist.domain.dto.task.TaskRequest;
import com.gundes.trytolist.domain.dto.task.TaskResponse;
import com.gundes.trytolist.domain.dto.user.UserResponse;
import com.gundes.trytolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @PostMapping("/user/{userId}")
    public UserResponse addTask(@PathVariable Long userId, @RequestBody List<TaskRequest> reqs){
        return service.addTasks(userId, reqs);
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
