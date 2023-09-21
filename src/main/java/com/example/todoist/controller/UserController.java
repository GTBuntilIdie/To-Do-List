package com.example.todoist.controller;

import com.example.todoist.dto.UserDto;
import com.example.todoist.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Удаление пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь удалён успешно"),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден по id")
            }
    )
    @DeleteMapping("/{id}")
    public UserDto delete(@PathVariable long id) {
        return userService.delete(id);
    }

}
