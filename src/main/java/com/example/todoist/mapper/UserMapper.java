package com.example.todoist.mapper;

import com.example.todoist.dto.UserDto;
import com.example.todoist.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserDto toDto(User user);

}
