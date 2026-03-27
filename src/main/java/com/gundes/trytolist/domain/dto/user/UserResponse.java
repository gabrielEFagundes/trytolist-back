package com.gundes.trytolist.domain.dto.user;

import com.gundes.trytolist.domain.model.Task;

import java.util.List;

public record UserResponse(
        Long id,
        String name,
        String email,
        String password,
        List<Task> tasks
) {}
