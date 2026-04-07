package com.gundes.trytolist.domain.mapper;

import com.gundes.trytolist.domain.dto.user.UserRequest;
import com.gundes.trytolist.domain.dto.user.UserResponse;
import com.gundes.trytolist.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper implements IUserMapper{

    @Override
    public User toEntity(UserRequest req){
        return new User(req.name(), req.email(), req.password());
    }

    @Override
    public UserResponse toResponse(User u){
        return new UserResponse(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getTasks());
    }

    @Override
    public List<UserResponse> toResponse(List<User> u){
        List<UserResponse> users = new ArrayList<>();

        u.forEach(user -> users.add(toResponse(user)));
        return users;
    }

}
