package com.example.todoist.dto;

import com.example.todoist.model.Label;
import com.example.todoist.model.Status;
import com.example.todoist.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskDto {
    @Schema(name = "id", description = "id задачи", example = "1")
    private Long id;

    @Schema(name = "name", description = "Название задачи", example = "Домашнее задание")
    private String name;

    @Schema(name = "description", description = "Описание задачи", example = "Создать файл конфигурации changelog-master.xml")
    private String description;

    private Status status;

    @Schema(name = "date_of_creation", description = "Дата создания", pattern = "yyyy-MM-dd HH:mm:ss", example = "2016-11-09 11:44:44")
    @NotNull
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOfCreation;

    @Schema(name = "update_date", description = "Дата обновления", pattern = "yyyy-MM-dd HH:mm:ss", example = "2016-11-09 11:44:44")
    @NotNull
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    @Schema(name = "user_id", description = "id автора задачи", minimum = "1", example = "1")
    @NotBlank
    @Positive
    private User user;

    @Schema(name = "label_id", description = "id категории задачи", minimum = "1", example = "1")
    @NotBlank
    @Positive
    private Label label;

}
