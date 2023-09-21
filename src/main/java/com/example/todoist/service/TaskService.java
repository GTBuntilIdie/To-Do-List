package com.example.todoist.service;

import com.example.todoist.dto.TaskDto;
import com.example.todoist.exception.TaskNotFoundException;
import com.example.todoist.mapper.TaskMapper;
import com.example.todoist.model.Task;
import com.example.todoist.repository.TaskRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public TaskDto create(TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        Task saved = taskRepository.save(task);
        return taskMapper.toDto(saved);
    }

    public TaskDto update(long id, TaskDto taskDto) {
        return taskRepository.findById(id)
                .map(entity -> {
                    var updated = taskMapper.toEntity(taskDto);
                    updated.setId(entity.getId());
                    return taskRepository.save(updated);
                })
                .map(taskMapper::toDto)
                .orElse(null);
    }

    public TaskDto delete(long id) {
        return taskRepository.findById(id)
                .map(entity -> {
                    taskRepository.delete(entity);
                    return entity;
                }).map(taskMapper::toDto)
                .orElse(null);
    }

    public TaskDto get(long id) {
        return taskRepository.findById(id).map(taskMapper::toDto).orElse(null);

    }
}
