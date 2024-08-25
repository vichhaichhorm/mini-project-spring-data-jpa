package com.spring.mini.project.demo.spring.mini.project.service.serviceImp;

import com.spring.mini.project.demo.spring.mini.project.entity.User;
import com.spring.mini.project.demo.spring.mini.project.dto.Authentication.dtoAuthRequest.DTORegisterRequest;
import com.spring.mini.project.demo.spring.mini.project.dto.Authentication.dtoAuthResponse.DTORegisterResponse;
import com.spring.mini.project.demo.spring.mini.project.jwt.JwtService;
import com.spring.mini.project.demo.spring.mini.project.repository.UserRepository;
import com.spring.mini.project.demo.spring.mini.project.service.UserService;
import com.spring.mini.project.demo.spring.mini.project.validation.ExceptionValidation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserService {
    private JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(JwtService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return user;
    }

    @Override
    public DTORegisterResponse registerUser(DTORegisterRequest dtoRegisterRequest) throws Exception {
        User existingUser = userRepository.findByEmail(dtoRegisterRequest.getEmail());
//        if (existingUser != null) {
//            throw new Exception("You cannot use duplicate email");
//        }
//        if (!ExceptionValidation.isValidName(dtoRegisterRequest.getUsername())) {
//            throw new Exception("You cannot input number or special symbol in username");
//        }
//        if (!ExceptionValidation.isValidPhoneNumber(dtoRegisterRequest.getPhoneNumber())) {
//            throw new Exception("Invalid phone number: it should be 8-10 digits long and contain only numbers");
//        }
        User newUser = new User();
        dtoRegisterRequest.requestRegisterLogin(newUser);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        DTORegisterResponse dtoRegisterResponse = new DTORegisterResponse();
        dtoRegisterResponse.responseDTORegister(userRepository.save(newUser));
        return dtoRegisterResponse;
    }

    //    @Override
//    public UserDetails getAuthenticatedUser() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            return (UserDetails) principal;
//        } else {
//            throw new UsernameNotFoundException("User not authenticated");
//        }
//    }
    @Override
    public UserDetails getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null; // Or throw an exception if you prefer
    }

//
//    public DTORegisterResponse updateCurrentUser(DTORegisterRequest dtoRegisterRequest) {
//        UserDetails userDetails = getAuthenticatedUser();
//
//        User user = userRepository.findByEmail(userDetails.getUsername());
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with email: " + userDetails.getUsername());
//        }
//
//        user.setUsername(dtoRegisterRequest.getUsername());
//        user.setEmail(dtoRegisterRequest.getEmail());
//        user.setAddress(dtoRegisterRequest.getAddress());
//        user.setPhoneNumber(dtoRegisterRequest.getPhoneNumber());
//
//        if (dtoRegisterRequest.getPassword() != null && !dtoRegisterRequest.getPassword().isEmpty()) {
//            user.setPassword(passwordEncoder.encode(dtoRegisterRequest.getPassword()));
//        }
//
//        User updatedUser = userRepository.save(user);
//
//        DTORegisterResponse dtoRegisterResponse = new DTORegisterResponse();
//        dtoRegisterResponse.responseDTORegister(updatedUser);
//
//        // Logging for debugging
//        System.out.println("User updated: " + updatedUser);
//
//        return dtoRegisterResponse;
//    }


}
