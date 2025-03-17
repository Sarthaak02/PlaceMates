package com.placemates.util.mapper.user;

import com.placemates.dao.user.UserDAO;
import com.placemates.dto.user.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    UserDAO fromDTOToDAO(UserDTO userDTO);
    UserDTO fromDAOToDTO(UserDAO userDAO);
    List<UserDAO> fromDTOListToDAOList(List<UserDTO> userDTOList);
    List<UserDTO> fromDAOListToDTOList(List<UserDAO> userDAOList);
}