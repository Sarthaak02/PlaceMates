package com.placemates.util.mapper.user;

import com.placemates.dao.user.UserDAO;
import com.placemates.dto.user.UserInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {RoleMapper.class})
public interface UserInfoMapper {

    UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);

    @Mapping(source = "roleDTO", target = "roleDAO")
    UserDAO toUserDAO(UserInfoDTO userInfoDTO);

    @Mapping(source = "roleDAO", target = "roleDTO")
    UserInfoDTO toUserDTO(UserDAO userDAO);

    @Mapping(source = "roleDTO", target = "roleDAO")
    List<UserDAO> toUserDAOList(List<UserInfoDTO> userInfoDTOList);

    @Mapping(source = "roleDAO", target = "roleDTO")
    List<UserInfoDTO> toUserDTOList(List<UserDAO> userDAOList);
}
