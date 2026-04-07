package com.gundes.trytolist.controller;

import com.gundes.trytolist.domain.dto.user.UserLogin;
import com.gundes.trytolist.domain.dto.user.UserRequest;
import com.gundes.trytolist.domain.dto.user.UserResponse;
import com.gundes.trytolist.service.IUserService;
import com.gundes.trytolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService service;

    @PostMapping
    public UserResponse addNewUser(@RequestBody UserRequest req){
        return service.addUser(req);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id){
        return service.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest req){
        return service.updateUser(id, req);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUser(@PathVariable Long id){
        return service.deleteUser(id);
    }


    @PostMapping("/login")
    public UserResponse login(@RequestBody UserLogin loginDTO) {
        return service.login(loginDTO);
    }
}
