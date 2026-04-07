package com.gundes.trytolist.service;

import com.gundes.trytolist.domain.dto.task.TaskRequest;
import com.gundes.trytolist.domain.dto.task.TaskResponse;
import com.gundes.trytolist.domain.dto.user.UserLogin;
import com.gundes.trytolist.domain.dto.user.UserRequest;
import com.gundes.trytolist.domain.dto.user.UserResponse;
import com.gundes.trytolist.domain.mapper.TaskMapper;
import com.gundes.trytolist.domain.mapper.UserMapper;
import com.gundes.trytolist.domain.model.User;
import com.gundes.trytolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final TaskMapper taskMapper;

    public UserResponse addUser(UserRequest req){
        return mapper.toResponse(
                repository.save(
                        mapper.toEntity(req)
                )
        );
    }

    public UserResponse getUserById(Long id){
        return mapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("User not found.")));
    }

    public UserResponse updateUser(Long id, UserRequest req){
        User user = mapper.toEntity(req);
        User fUser = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));

        fUser.setName(user.getName());
        fUser.setEmail(user.getEmail());
        fUser.setPassword(user.getPassword());
        // the user does not update all of his tasks, instead, we'll use other methods, this is only for editing his profile

        return mapper.toResponse(repository.save(fUser));
    }

    public HttpStatus deleteUser(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return HttpStatus.OK;
        }

        return HttpStatus.BAD_REQUEST;
    }

    public UserResponse login(UserLogin loginDTO) {
        User user = repository
                .findByEmailAndPassword(
                        loginDTO.email(),
                        loginDTO.password()
                )
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));

        return mapper.toResponse(user);
    }

}
