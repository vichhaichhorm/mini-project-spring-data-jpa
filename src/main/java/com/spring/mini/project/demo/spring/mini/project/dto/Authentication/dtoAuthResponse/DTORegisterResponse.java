package com.spring.mini.project.demo.spring.mini.project.dto.Authentication.dtoAuthResponse;

import com.spring.mini.project.demo.spring.mini.project.entity.User;
import com.spring.mini.project.demo.spring.mini.project.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DTORegisterResponse {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private String phoneNumber;
    public void responseDTORegister(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.phoneNumber = user.getPhoneNumber();
    }
}
