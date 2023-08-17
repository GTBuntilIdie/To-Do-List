package com.example.todoist.security;

import com.example.todoist.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
@RequestScope
public class ToDoListUserDetails {

    private UserDto userDto;

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(userDto)
                .map(UserDto::getRole)
                .map(role -> "ROLE_" + role.name())
                .map(SimpleGrantedAuthority::new)
                .map(Collections::singleton)
                .orElseGet(Collections::emptySet);
    }

    public String getPassword() {
        return Optional.ofNullable(userDto)
                .map(UserDto::getPassword)
                .orElse(null);
    }


}
