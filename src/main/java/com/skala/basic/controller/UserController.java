package com.skala.basic.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skala.basic.data.UserRequest;
import com.skala.basic.data.UserResponse;
import com.skala.basic.service.UserService;
import com.skala.basic.table.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 사용자 생성
    @PostMapping("/users")
    public UserResponse postUsers(@Validated @RequestBody UserRequest requestBody) {
        return userService.createUser(requestBody);
    }

    // 사용자 조회 (전체 or 이름 검색)
    @GetMapping("/users")
    public List<User> getUsers(@RequestParam(required = false) String name) {
        if (name == null || name.isBlank()) {
            return userService.getAllUsers();
        }
        return userService.getUsersByName(name);
    }
}
