package com.gundes.trytolist.service;

import com.gundes.trytolist.domain.dto.task.TaskRequest;
import com.gundes.trytolist.domain.dto.task.TaskResponse;
import com.gundes.trytolist.domain.dto.user.UserLogin;
import com.gundes.trytolist.domain.dto.user.UserRequest;
import com.gundes.trytolist.domain.dto.user.UserResponse;
import com.gundes.trytolist.domain.mapper.ITaskMapper;
import com.gundes.trytolist.domain.mapper.IUserMapper;
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
public class UserService implements IUserService{

    private final UserRepository repository;
    private final IUserMapper mapper;
    private final ITaskMapper taskMapper;

    @Override
    public UserResponse addUser(UserRequest req){
        return mapper.toResponse(
                repository.save(
                        mapper.toEntity(req)
                )
        );
    }
    @Override
    public UserResponse getUserById(Long id){
        return mapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("User not found.")));
    }
    @Override
    public UserResponse updateUser(Long id, UserRequest req){
        User user = mapper.toEntity(req);
        User fUser = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));

        fUser.setName(user.getName());
        fUser.setEmail(user.getEmail());
        fUser.setPassword(user.getPassword());
        // the user does not update all of his tasks, instead, we'll use other methods, this is only for editing his profile

        return mapper.toResponse(repository.save(fUser));
    }
    @Override
    public HttpStatus deleteUser(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return HttpStatus.OK;
        }

        return HttpStatus.BAD_REQUEST;
    }
    @Override
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
