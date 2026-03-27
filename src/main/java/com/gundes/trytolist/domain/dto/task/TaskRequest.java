package com.gundes.trytolist.domain.dto.task;

import com.gundes.trytolist.domain.enums.Priorities;

import java.sql.Date;

public record TaskRequest(
        String title,
        String content,
        Priorities priority,
        Boolean isConcluded
) {}
