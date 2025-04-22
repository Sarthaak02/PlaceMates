package com.placemates.service.security;

import com.placemates.dto.security.AuthUserDetails;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;

public interface JWTService {
    SecretKey generateKey();
    String generateToken(AuthUserDetails authenticatedUser);
    Claims extractAllClaims(String token);
    String extractUsername(String token);
    String extractAuthority(String token);
    Date extractExpiration(String token);
    boolean validateToken(String token, UserDetails userDetails);
}
