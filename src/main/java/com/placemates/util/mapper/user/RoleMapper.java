package com.placemates.util.mapper.user;

import com.placemates.dao.user.RoleDAO;
import com.placemates.dto.user.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper {
    RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);

    RoleDAO toRoleDAO(RoleDTO roleDTO);
    RoleDTO tORoleDTO(RoleDAO roleDAO);
    List<RoleDAO> toRoleDAOList(List<RoleDTO> roleDTOList);
    List<RoleDTO> toRoleDTOList(List<RoleDAO> roleDAOList);
}
