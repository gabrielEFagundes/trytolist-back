package com.gundes.trytolist.service;

import com.gundes.trytolist.domain.dto.user.UserLogin;
import com.gundes.trytolist.domain.dto.user.UserRequest;
import com.gundes.trytolist.domain.dto.user.UserResponse;
import org.springframework.http.HttpStatus;

public interface IUserService {

    UserResponse addUser(UserRequest req);

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, UserRequest req);

    HttpStatus deleteUser(Long id);

    UserResponse login(UserLogin loginDTO);
}
