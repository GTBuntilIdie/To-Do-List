package com.example.todoist.controller;

import com.example.todoist.dto.TaskDto;
import com.example.todoist.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@Tag(name = "API для задач", description = "Эндпоинты для работы с задачами")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(
            summary = "Создание задачи",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Задача создана успешно"),
                    @ApiResponse(responseCode = "400", description = "Некорректные значения в полях")
            }
    )
    @PostMapping
    public ResponseEntity<TaskDto> create(@Valid @RequestBody TaskDto taskDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(taskDto));
    }


    @Operation(
            summary = "Обновление задавчи",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Задача обновлена успешно"),
                    @ApiResponse(responseCode = "400", description = "Некорректные значения в полях"),
                    @ApiResponse(responseCode = "404", description = "Задача не найдена по id")
            }
    )
    @PutMapping("/{id}")
    public TaskDto update(@PathVariable long id,
                             @Valid @RequestBody TaskDto taskDto) {
        return taskService.update(id, taskDto);
    }


    @Operation(
            summary = "Удаление задачи",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Задача удалена успешно"),
                    @ApiResponse(responseCode = "404", description = "Задача не найдена по id")
            }
    )
    @DeleteMapping("/{id}")
    public TaskDto delete(@PathVariable long id) {
        return taskService.delete(id);
    }

    @GetMapping("/{id}")
    public TaskDto get(@PathVariable long id) {
        return taskService.get(id);
    }

}
