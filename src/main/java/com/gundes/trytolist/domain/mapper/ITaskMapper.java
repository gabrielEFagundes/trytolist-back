package com.gundes.trytolist.domain.mapper;

import com.gundes.trytolist.domain.dto.task.TaskRequest;
import com.gundes.trytolist.domain.dto.task.TaskResponse;
import com.gundes.trytolist.domain.model.Task;

import java.util.List;

public interface ITaskMapper {

    Task toEntity(TaskRequest dto);

    List<Task> toEntity(List<TaskRequest> reqs);

    TaskResponse toResponse(Task task);

    List<TaskResponse> toResponse(List<Task> t);
}
