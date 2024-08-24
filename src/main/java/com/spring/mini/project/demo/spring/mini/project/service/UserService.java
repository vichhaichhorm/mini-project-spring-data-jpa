package com.spring.mini.project.demo.spring.mini.project.service;

import com.spring.mini.project.demo.spring.mini.project.dto.Authentication.dtoAuthRequest.DTORegisterRequest;
import com.spring.mini.project.demo.spring.mini.project.dto.Authentication.dtoAuthResponse.DTORegisterResponse;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    DTORegisterResponse registerUser(DTORegisterRequest dtoRegisterRequest) throws Exception;

}
