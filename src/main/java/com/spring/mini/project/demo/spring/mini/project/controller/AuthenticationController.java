package com.spring.mini.project.demo.spring.mini.project.controller;

import com.spring.mini.project.demo.spring.mini.project.dto.Authentication.dtoAuthRequest.DTOLoginRequest;
import com.spring.mini.project.demo.spring.mini.project.dto.Authentication.dtoAuthRequest.DTORegisterRequest;
import com.spring.mini.project.demo.spring.mini.project.dto.Authentication.dtoAuthResponse.DTOLoginResponse;
import com.spring.mini.project.demo.spring.mini.project.dto.Authentication.dtoAuthResponse.DTORegisterResponse;
import com.spring.mini.project.demo.spring.mini.project.dto.apiResponse.APIResponse;
import com.spring.mini.project.demo.spring.mini.project.jwt.JwtService;
import com.spring.mini.project.demo.spring.mini.project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User is disabled", e);
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials", e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<Object>> authenticate(@RequestBody DTOLoginRequest dtoLoginRequest) {
        try {
            authenticate(dtoLoginRequest.getEmail(), dtoLoginRequest.getPassword());
            final UserDetails userDetails = userService.loadUserByUsername(dtoLoginRequest.getEmail());
            final String token = jwtService.generateToken(userDetails);

            DTOLoginResponse authResponse = new DTOLoginResponse(token);
            APIResponse<Object> apiResponse = APIResponse.builder()
                    .status(HttpStatus.OK)
                    .message("You have logged in to the system successfully.")
                    .payload(authResponse)
                    .time(LocalDateTime.now())
                    .build();

            return ResponseEntity.ok(apiResponse);
        } catch (UsernameNotFoundException e) {
            APIResponse<Object> apiResponse = APIResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("User not found with email: " + dtoLoginRequest.getEmail())
                    .time(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        } catch (Exception e) {
            APIResponse<Object> apiResponse = APIResponse.builder()
                    .status(HttpStatus.UNAUTHORIZED)
                    .message("Authentication failed.")
                    .time(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse<Object>> registerUser(@RequestBody DTORegisterRequest dtoRegisterRequest) throws Exception {
        try {
            DTORegisterResponse registerResponse = userService.registerUser(dtoRegisterRequest);

            APIResponse<Object> apiResponse = APIResponse.builder()
                    .status(HttpStatus.CREATED)
                    .message("Registered successfully.")
                    .payload(registerResponse)
                    .time(LocalDateTime.now())
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        } catch (Exception e) {
            APIResponse<Object> apiResponse = APIResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("User registration failed: " + e.getMessage())
                    .time(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }

}
