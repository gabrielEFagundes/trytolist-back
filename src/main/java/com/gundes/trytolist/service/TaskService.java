package com.gundes.trytolist.service;

import com.gundes.trytolist.domain.dto.task.TaskRequest;
import com.gundes.trytolist.domain.dto.task.TaskResponse;
import com.gundes.trytolist.domain.dto.user.UserResponse;
import com.gundes.trytolist.domain.mapper.ITaskMapper;
import com.gundes.trytolist.domain.mapper.IUserMapper;
import com.gundes.trytolist.domain.mapper.TaskMapper;
import com.gundes.trytolist.domain.mapper.UserMapper;
import com.gundes.trytolist.domain.model.Task;
import com.gundes.trytolist.domain.model.User;
import com.gundes.trytolist.repository.TaskRepository;
import com.gundes.trytolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService{

    private final TaskRepository repository;
    private final UserRepository userRepository;
    private final ITaskMapper mapper;
    private final IUserMapper userMapper;

    @Override
    public List<Task> createTasks(Long userId, List<TaskRequest> req){
        List<Task> tasks = mapper.toEntity(req);

        User fUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));

        tasks.forEach(task -> task.setUser(fUser));
        return repository.saveAll(tasks);
    }

    @Override
    public UserResponse addTasks(Long userId, List<TaskRequest> reqs){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));

        List<Task> tasks = createTasks(userId, reqs);

        user.getTasks().addAll(tasks);
        return userMapper.toResponse(userRepository.save(user));
    }
    @Override
    public UserResponse deleteTask(Long userId, Long taskId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));

        repository.deleteById(taskId);
        user.getTasks().removeIf(task -> task.getId().equals(taskId));
        return userMapper.toResponse(userRepository.save(user));
    }
    @Override
    public TaskResponse concludeTask(Long taskId){
        Task task = repository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found."));

        task.setIsConcluded(true);
        task.setFinishAt(Date.valueOf(LocalDate.now()));
        return mapper.toResponse(repository.save(task));
    }

    @Override
    public List<TaskResponse> getTasks(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));

        return mapper.toResponse(user.getTasks());
    }

}
