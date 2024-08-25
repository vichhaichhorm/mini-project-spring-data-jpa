package com.spring.mini.project.demo.spring.mini.project.dto.apiResponse;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse <T>{
    private HttpStatus status;
    private String message;
    private T payload;
    private LocalDateTime time;
}
