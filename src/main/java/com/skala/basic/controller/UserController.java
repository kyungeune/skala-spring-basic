package com.skala.basic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skala.basic.data.UserRequest;
import com.skala.basic.data.UserResponse;


@RestController
public class UserController {

    // 임시 DataBase
    private final Map<String, UserRequest> userMap = new HashMap<>();
    
    @PostMapping("/users")
    public UserResponse postUsers(@RequestBody UserRequest requestBody) {
        // Post한게 여기로 들어온다.

        userMap.put(requestBody.getUserId(), requestBody);  // DB에 저장 <key, value>
        
        UserResponse responseBody = new UserResponse();
        responseBody.setResultCode(UserResponse.SUCCESS);
        responseBody.setResultMessage("사용자가 정상적으로 등록되었습니다.");
        responseBody.setRequest(requestBody);  // 그대로 다시 requestBody에 넣어주기

        return responseBody;
    }

    @GetMapping("/users")
    public List<UserRequest> getUsers() {
        List<UserRequest> list = new ArrayList<>(userMap.values());
        
        return list;
    }


    // 자체적으로 추가
   // ✅ PUT: 사용자 전체 수정(덮어쓰기)
    // PUT /users
    @PutMapping("/users")
    public UserResponse putUser(@RequestBody UserRequest requestBody) {
        UserResponse res = new UserResponse();

        String userId = requestBody.getUserId();
        if (userId == null || userId.isBlank()) {
            res.setResultCode(UserResponse.FAIL);
            res.setResultMessage("userId는 필수입니다.");
            return res;
        }

        if (!userMap.containsKey(userId)) {
            res.setResultCode(UserResponse.FAIL);
            res.setResultMessage("해당 userId의 사용자가 존재하지 않습니다.");
            res.setRequest(requestBody);
            return res;
        }

        userMap.put(userId, requestBody);

        res.setResultCode(UserResponse.SUCCESS);
        res.setResultMessage("사용자가 정상적으로 수정되었습니다.");
        res.setRequest(requestBody);
        return res;
    }


    // ✅ DELETE: 사용자 삭제
    // ✅ DELETE /users
    @DeleteMapping("/users")
    public UserResponse deleteUserByBody(@RequestBody UserRequest requestBody) {
        UserResponse responseBody = new UserResponse();

        String userId = requestBody.getUserId();

        if (userId == null || userId.isBlank()) {
            responseBody.setResultCode(UserResponse.FAIL);
            responseBody.setResultMessage("userId는 필수입니다.");
            return responseBody;
        }

        UserRequest removed = userMap.remove(userId);

        if (removed == null) {
            responseBody.setResultCode(UserResponse.FAIL);
            responseBody.setResultMessage("해당 userId의 사용자가 존재하지 않습니다.");
            return responseBody;
        }

        responseBody.setResultCode(UserResponse.SUCCESS);
        responseBody.setResultMessage("사용자가 정상적으로 삭제되었습니다.");
        responseBody.setRequest(removed);

        return responseBody;
    }
    
}
