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
    UserDAO fromDTOToDAO(UserInfoDTO userInfoDTO);

    @Mapping(source = "roleDAO", target = "roleDTO")
    UserInfoDTO fromDAOToDTO(UserDAO userDAO);

    @Mapping(source = "roleDTO", target = "roleDAO")
    List<UserDAO> fromDTOListToDAOList(List<UserInfoDTO> userInfoDTOList);

    @Mapping(source = "roleDAO", target = "roleDTO")
    List<UserInfoDTO> fromDAOListToDTOList(List<UserDAO> userDAOList);
}
