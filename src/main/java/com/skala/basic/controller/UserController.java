package com.skala.basic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skala.basic.data.UserRequest;
import com.skala.basic.data.UserResponse;
import com.skala.basic.repository.UserRepository;
import com.skala.basic.service.UserService;
import com.skala.basic.table.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public UserResponse postUsers(@Validated @RequestBody UserRequest requestBody) {
        return userService.createUser(requestBody);
    }

    @GetMapping("/users")
    public List<User> getUsers(@RequestParam(required = false) String name) {
        if (name == null || name.isBlank()) {
            return userService.getAllUsers();
        }
        return userService.getUsersByName(name);
    }

}
