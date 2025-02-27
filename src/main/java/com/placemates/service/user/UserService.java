package com.placemates.service.user;

import com.placemates.dto.user.UserDTO;
import com.placemates.exception.DataAlreadyExistsException;
import com.placemates.exception.DataNotFoundException;

public interface UserService {
    void createUser(UserDTO userDTO) throws DataAlreadyExistsException;
    void updateUserEmail(Integer id, String email) throws DataNotFoundException, DataAlreadyExistsException;
    void updateUserPassword(Integer id, String password) throws DataNotFoundException;
    void deleteUser(Integer id) throws DataNotFoundException;
}
