package com.placemates.util.mapper.user;

import com.placemates.dao.user.RoleDAO;
import com.placemates.dto.user.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper {
    RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);

    RoleDAO fromDTOToDAO(RoleDTO roleDTO);
    RoleDTO fromDAOTODTO(RoleDAO roleDAO);
    List<RoleDAO> fromDTOListToDAOList(List<RoleDTO> roleDTOList);
    List<RoleDTO> fromDAOListToDTOList(List<RoleDAO> roleDAOList);
}
