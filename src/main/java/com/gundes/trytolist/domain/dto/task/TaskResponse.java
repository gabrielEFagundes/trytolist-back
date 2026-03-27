package com.gundes.trytolist.domain.dto.task;

import com.gundes.trytolist.domain.enums.Priorities;

import java.sql.Date;

public record TaskResponse(
        Long id,
        Long userId,
        String title,
        String content,
        Priorities priority,
        Boolean isConcluded,
        Date createdAt,
        Date finishAt
) {}
