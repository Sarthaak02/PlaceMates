package com.placemates.util.mapper.user;

import com.placemates.dao.user.UserDAO;
import com.placemates.dto.user.UserDTO;
import com.placemates.util.mapper.common.BranchMapper;
import com.placemates.util.mapper.common.LocationMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {BranchMapper.class, LocationMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "branchDTO", target = "branchDAO")
    @Mapping(source = "locationDTO", target = "locationDAO")
    UserDAO fromDTOToDAO(UserDTO userDTO);

    @Mapping(source = "branchDAO", target = "branchDTO")
    @Mapping(source = "locationDAO", target = "locationDTO")
    UserDTO fromDAOToDTO(UserDAO userDAO);

    @Mapping(source = "branchDTO", target = "branchDAO")
    @Mapping(source = "locationDTO", target = "locationDAO")
    List<UserDAO> fromDTOListToDAOList(List<UserDTO> userDTOList);

    @Mapping(source = "branchDAO", target = "branchDTO")
    @Mapping(source = "locationDAO", target = "locationDTO")
    List<UserDTO> fromDAOListToDTOList(List<UserDAO> userDAOList);
}