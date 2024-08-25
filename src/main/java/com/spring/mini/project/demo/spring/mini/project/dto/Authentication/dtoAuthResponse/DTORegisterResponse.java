package com.spring.mini.project.demo.spring.mini.project.dto.Authentication.dtoAuthResponse;

import com.spring.mini.project.demo.spring.mini.project.entity.User;
import com.spring.mini.project.demo.spring.mini.project.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DTORegisterResponse {
    private Long id;
    private String username;
    private String email;
    private String address;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private Role role;

    public void responseDTORegister(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.phoneNumber = user.getPhoneNumber();
        this.createdAt = user.getCreatedAt();
        this.role = user.getRole();
    }
}
