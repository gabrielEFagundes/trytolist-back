package com.gundes.trytolist.domain.mapper;

import com.gundes.trytolist.domain.dto.task.TaskRequest;
import com.gundes.trytolist.domain.dto.task.TaskResponse;
import com.gundes.trytolist.domain.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper implements ITaskMapper{

    @Override
    public Task toEntity(TaskRequest req){
        return new Task(req.title(), req.content(), req.priority(), req.isConcluded());
    }
    @Override
    public List<Task> toEntity(List<TaskRequest> reqs){
        List<Task> tasks = new ArrayList<>();

        reqs.forEach(req -> tasks.add(toEntity(req)));
        return tasks;
    }
    @Override
    public TaskResponse toResponse(Task t){
        return new TaskResponse(t.getId(), t.getUser().getId(), t.getTitle(), t.getContent(), t.getPriority(), t.getIsConcluded(), t.getCreatedAt(), t.getFinishAt());
    }

    @Override
    public List<TaskResponse> toResponse(List<Task> t){
        List<TaskResponse> tasks = new ArrayList<>();

        t.forEach(task -> tasks.add(toResponse(task)));
        return tasks;
    }

}
