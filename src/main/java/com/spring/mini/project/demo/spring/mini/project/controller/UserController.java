package com.spring.mini.project.demo.spring.mini.project.controller;

import com.spring.mini.project.demo.spring.mini.project.dto.Authentication.dtoAuthRequest.DTORegisterRequest;
import com.spring.mini.project.demo.spring.mini.project.dto.Authentication.dtoAuthResponse.DTORegisterResponse;
import com.spring.mini.project.demo.spring.mini.project.dto.apiResponse.APIResponse;
import com.spring.mini.project.demo.spring.mini.project.entity.User;
import com.spring.mini.project.demo.spring.mini.project.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<APIResponse<Object>> getUserDetails() {
        try {
            UserDetails userDetails = userService.getAuthenticatedUser();
            DTORegisterResponse dtoRegisterResponse = new DTORegisterResponse();
            dtoRegisterResponse.responseDTORegister((User) userDetails);
            APIResponse<Object> apiResponse = APIResponse.builder()
                    .message("Get current user information successfully.")
                    .payload(dtoRegisterResponse)
                    .status(HttpStatus.OK)
                    .time(LocalDateTime.now())
                    .build();

            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            APIResponse<Object> apiResponse = APIResponse.builder()
                    .message("Failed get current user details: " + e.getMessage())
                    .status(HttpStatus.BAD_REQUEST)
                    .time(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }

//    @PutMapping("/user")
//    public ResponseEntity<APIResponse<Object>> updateCurrentUser(@RequestBody DTORegisterRequest dtoRegisterRequest) throws Exception {
//            DTORegisterResponse dtoRegisterResponse = userService.updateCurrentUser(dtoRegisterRequest);
//            APIResponse<Object> apiResponse = APIResponse.builder()
//                    .message("User updated successfully.")
//                    .payload(dtoRegisterResponse)
//                    .status(HttpStatus.OK)
//                    .time(LocalDateTime.now())
//                    .build();
//            return ResponseEntity.ok(apiResponse);
//
//    }

}
