package com.spring.mini.project.demo.spring.mini.project.dto.Authentication.dtoAuthRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOLoginRequest {
    private String email;
    private String password;
}
