package com.spring.mini.project.demo.spring.mini.project.dto.Authentication.dtoAuthResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DTOLoginResponse {
    private String token;
    public DTOLoginResponse(String token) {
        this.token = token;
    }
}
