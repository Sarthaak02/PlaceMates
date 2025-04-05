package com.placemates.util.mapper.common;

import com.placemates.dao.common.BranchDAO;
import com.placemates.dto.common.BranchDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BranchMapper {
    BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);

    BranchDAO fromDTOToDAO(BranchDTO branchDTO);
    BranchDTO fromDAOToDTO(BranchDAO branchDAO);
    List<BranchDAO> fromDTOListToDAOList(List<BranchDTO> branchDTOList);
    List<BranchDTO> fromDAOListToDTOList(List<BranchDAO> branchDAOList);
}
