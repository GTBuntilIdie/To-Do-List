package com.example.todoist.mapper;

import com.example.todoist.dto.TaskDto;
import com.example.todoist.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {

    TaskDto toDto(Task task);

    @Mapping(target = "id", ignore = true)
    Task toEntity(TaskDto taskDto);
}
