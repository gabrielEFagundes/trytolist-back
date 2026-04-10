package com.gundes.trytolist.service;

import com.gundes.trytolist.domain.dto.task.TaskRequest;
import com.gundes.trytolist.domain.dto.task.TaskResponse;
import com.gundes.trytolist.domain.dto.user.UserResponse;
import com.gundes.trytolist.domain.model.Task;

import java.util.List;

public interface ITaskService {

    List<Task> createTasks(Long userId, List<TaskRequest> req);

    UserResponse addTasks(Long userId, List<TaskRequest> reqs);
    UserResponse deleteTask(Long userId, Long taskId);

    TaskResponse concludeTask(Long taskId);

    List<TaskResponse> getTasks(Long userId);
}
