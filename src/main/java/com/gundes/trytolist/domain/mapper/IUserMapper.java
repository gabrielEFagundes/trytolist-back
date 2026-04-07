package com.gundes.trytolist.domain.mapper;

import com.gundes.trytolist.domain.dto.user.UserRequest;
import com.gundes.trytolist.domain.dto.user.UserResponse;
import com.gundes.trytolist.domain.model.User;

import java.util.List;

public interface IUserMapper {

    User toEntity(UserRequest req);

    UserResponse toResponse(User u);

    List<UserResponse> toResponse(List<User> u);
}
