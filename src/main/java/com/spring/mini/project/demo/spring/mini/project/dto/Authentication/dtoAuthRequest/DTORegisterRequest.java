package com.spring.mini.project.demo.spring.mini.project.dto.Authentication.dtoAuthRequest;

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
public class DTORegisterRequest {
    private String username;
    private String email;
    private String address;
    private String phoneNumber;
    private String password;
    private Role role;
    public void requestRegisterLogin(User user){
        user.setUsername(this.username);
        user.setEmail(this.email);
        user.setAddress(this.address);
        user.setPhoneNumber(this.phoneNumber);
        user.setRole(this.role);
        user.setPassword(this.password);
    }
}
