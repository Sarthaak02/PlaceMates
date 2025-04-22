package com.placemates.service.security;

import com.placemates.dto.security.AuthUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class JWTServiceImpl implements JWTService{

    private String secretKey = "";

    public JWTServiceImpl() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        SecretKey key = keyGenerator.generateKey();
        secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
    }

    @Override
    public SecretKey generateKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String generateToken(AuthUserDetails authenticatedUser) {

        Map<String,Object> claims = new HashMap<>();

        Collection<? extends GrantedAuthority> authorities = authenticatedUser.getAuthorities();

        if (!authorities.isEmpty()) {
            String role = authorities.iterator().next().getAuthority();
            claims.put("role", role);
        }

        return Jwts
                .builder()
                .claims()
                .add(claims)
                .subject(authenticatedUser.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .and()
                .signWith(generateKey())
                .compact();
    }

    @Override
    public Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    @Override
    public String extractAuthority(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    @Override
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        String authority = extractAuthority(token);
        Date expiration = extractExpiration(token);

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        String role = "";
        if (!authorities.isEmpty()) {
            role = authorities.iterator().next().getAuthority();
        }

//        Maye be check for ROLE_ as prefix in authority for role based authentication

        if(username.equals(userDetails.getUsername())){
            if(authority.equals(role)){
                if(!expiration.before(new Date())){
                    return true;
                }
            }
        }
        return false;
    }
}
