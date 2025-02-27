package com.placemates.mapper.user;

import com.placemates.dao.user.UserDAO;
import com.placemates.dto.user.UserDTO;

public class UserMapper {

    public static UserDAO mapUserDTOToUserDAO(UserDTO userDTO){
        UserDAO userDAO = new UserDAO();
        userDAO.setId(userDTO.getId());
        userDAO.setFirstName(userDTO.getFirstName());
        userDAO.setLastName(userDTO.getLastName());
        userDAO.setEmail(userDTO.getEmail());
        userDAO.setPassword(userDTO.getPassword());
        userDAO.setUserType(userDTO.getUserType());
        return userDAO;
    }

    public static UserDTO mapUserDAOToUserDTO(UserDAO userDAO){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userDAO.getId());
        userDTO.setFirstName(userDAO.getFirstName());
        userDTO.setLastName(userDAO.getLastName());
        userDTO.setEmail(userDAO.getEmail());
        userDTO.setPassword(userDAO.getPassword());
        userDTO.setUserType(userDAO.getUserType());
        return userDTO;
    }
}
