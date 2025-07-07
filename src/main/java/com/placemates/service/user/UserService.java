package com.placemates.service.user;

import com.placemates.dto.user.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUser(Integer id);
    String getCurrentUserUsername();
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Integer id, UserDTO userDTO);
    void deleteUser(Integer id);
}
