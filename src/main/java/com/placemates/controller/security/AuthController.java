package com.placemates.controller.security;

import com.placemates.dto.security.AuthUserDetails;
import com.placemates.dto.user.UserDTO;
import com.placemates.service.security.AuthService;
import com.placemates.service.security.JWTService;
import com.placemates.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
    private final JWTService jwtService;

    public AuthController(UserService userService, AuthService authService, JWTService jwtService) {
        this.userService = userService;
        this.authService = authService;
        this.jwtService = jwtService;
    }


    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUpUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO newUserDTO = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUserDTO);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody AuthUserDetails authUserDetails){
        AuthUserDetails authenticatedUser = authService.getAuthenticatedUser(authUserDetails);
        if(authenticatedUser != null){
            return jwtService.generateToken(authenticatedUser);
        }
        return "Fail";
    }
}
