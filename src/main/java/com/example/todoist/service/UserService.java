package com.example.todoist.service;


import com.example.todoist.dto.UserDto;
import com.example.todoist.mapper.UserMapper;
import com.example.todoist.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto delete(long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    return entity;
                }).map(userMapper::toDto)
                .orElse(null);
    }
}
