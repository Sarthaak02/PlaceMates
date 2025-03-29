package com.placemates.service.user;

import com.placemates.dto.user.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Integer id);
    List<UserDTO> getAllUsers();
    UserDTO updateUserById(Integer id, UserDTO userDTO);
    void deleteUserById(Integer id);
}
