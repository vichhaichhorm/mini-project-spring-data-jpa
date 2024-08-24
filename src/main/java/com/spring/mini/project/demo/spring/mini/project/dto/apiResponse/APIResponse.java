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
    private String message;
    private T payload;
    private HttpStatus status;
    private LocalDateTime time;
}
