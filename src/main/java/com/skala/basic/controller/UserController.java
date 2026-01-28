package com.skala.basic.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skala.basic.data.UserRequest;
import com.skala.basic.data.UserResponse;

@RestController
public class UserController {
    
    @PostMapping("/users")
    public UserResponse postUsers(@RequestBody UserRequest requestBody) {
        // Post한게 여기로 들어온다.
        UserResponse responseBody = new UserResponse();

        responseBody.setResultCode(UserResponse.SUCCESS);
        responseBody.setResultMessage("사용자가 정상적으로 등록되었습니다.");
        responseBody.setRequest(requestBody);  // 그대로 다시 requestBody에 넣어주기

        return responseBody;
    }
}
