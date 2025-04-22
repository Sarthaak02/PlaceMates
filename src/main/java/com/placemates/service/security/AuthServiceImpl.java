package com.placemates.service.security;

import com.placemates.dto.security.AuthUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthUserDetails getAuthenticatedUser(AuthUserDetails authUserDetails){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authUserDetails.getUsername(),authUserDetails.getPassword()));
        return (AuthUserDetails) authentication.getPrincipal();
    }
}
