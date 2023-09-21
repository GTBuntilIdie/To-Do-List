package com.example.todoist.security;

import com.example.todoist.exception.UserEmailNotFoundException;
import com.example.todoist.mapper.UserMapper;
import com.example.todoist.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ToDoListUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ToDoListUserDetails toDoListUserDetails;
    private final UserMapper userMapper;


    public ToDoListUserDetailsService(UserRepository userRepository, ToDoListUserDetails toDoListUserDetails, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.toDoListUserDetails = toDoListUserDetails;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(user -> {
                    toDoListUserDetails.setUserDto(userMapper.toDto(user));
                    return toDoListUserDetails;
                })
                .orElseThrow(() -> new UserEmailNotFoundException(
                                "Пользователь с логином " + email + " не найден!"
                        )
                );
    }
}
