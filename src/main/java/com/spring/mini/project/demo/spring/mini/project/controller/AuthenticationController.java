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

//    @PostMapping("/login")
//    public ResponseEntity<DTOLoginResponse> authenticate(@RequestBody DTOLoginRequest dtoLoginRequest) throws Exception {
//        authenticate(dtoLoginRequest.getEmail(), dtoLoginRequest.getPassword());
//        final UserDetails userDetails = userService.loadUserByUsername(dtoLoginRequest.getEmail());
//        final String token = jwtService.generateToken(userDetails);
//        DTOLoginResponse authResponse = new DTOLoginResponse(token);
//        return ResponseEntity.ok(authResponse);
//    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<Object>> authenticate(@RequestBody DTOLoginRequest dtoLoginRequest) {
        try {
            authenticate(dtoLoginRequest.getEmail(), dtoLoginRequest.getPassword());
            final UserDetails userDetails = userService.loadUserByUsername(dtoLoginRequest.getEmail());
            final String token = jwtService.generateToken(userDetails);

            DTOLoginResponse authResponse = new DTOLoginResponse(token);
            APIResponse<Object> apiResponse = APIResponse.builder()
                    .message("Login successful.")
                    .payload(authResponse)
                    .status(HttpStatus.CREATED)
                    .time(LocalDateTime.now())
                    .build();

            return ResponseEntity.ok(apiResponse);
        } catch (UsernameNotFoundException e) {
            APIResponse<Object> apiResponse = APIResponse.builder()
                    .message("User not found with email: " + dtoLoginRequest.getEmail())
                    .status(HttpStatus.NOT_FOUND)
                    .time(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        } catch (Exception e) {
            APIResponse<Object> apiResponse = APIResponse.builder()
                    .message("Authentication failed.")
                    .status(HttpStatus.UNAUTHORIZED)
                    .time(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
        }
    }



    @PostMapping("/register")
    public ResponseEntity<DTORegisterResponse> registerUser(@RequestBody DTORegisterRequest dtoRegisterRequest) throws Exception {
        return ResponseEntity.ok(userService.registerUser(dtoRegisterRequest));
    }

}
