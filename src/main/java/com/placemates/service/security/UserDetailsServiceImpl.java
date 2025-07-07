package com.placemates.service.security;

import com.placemates.dao.user.UserDAO;
import com.placemates.dto.security.AuthUserDetails;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        UserDAO userDAO = userRepository.findByUsername(username);

        if(userDAO == null){
            log.error("User not found with username: {}",username);
            throw new ResourceNotFoundException("User not found with username: " + username);
        }

        return new AuthUserDetails(username, userDAO.getPassword(), userDAO.getRoleDAO().getName());
    }

}
