package com.gundes.trytolist.domain.dto.user;

public record UserRequest(
        String name,
        String email,
        String password
) {}
