package com.placemates.service.user;

import com.placemates.dao.user.UserDAO;
import com.placemates.dto.user.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUser(Integer userId);
    UserDAO getCurrentUser();
    String getCurrentUserUsername();
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Integer userId, UserDTO userDTO);
    void deleteUser(Integer userId);
}
