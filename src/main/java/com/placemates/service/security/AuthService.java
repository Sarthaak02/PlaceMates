package com.placemates.service.security;

import com.placemates.dto.security.AuthUserDetails;

public interface AuthService {
    AuthUserDetails getAuthenticatedUser(AuthUserDetails authUserDetails);
}
