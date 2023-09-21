package com.example.todoist.dto;

import com.example.todoist.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String password;

    private Role role;

}
