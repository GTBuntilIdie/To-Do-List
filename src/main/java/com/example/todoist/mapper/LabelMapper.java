package com.example.todoist.mapper;

import com.example.todoist.dto.LabelDto;
import com.example.todoist.model.Label;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LabelMapper {

    LabelDto toDto(Label label);

    @Mapping(target = "id", ignore = true)
    Label toEntity(LabelDto labelDto);

}
