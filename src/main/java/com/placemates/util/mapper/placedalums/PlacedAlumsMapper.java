package com.placemates.util.mapper.placedalums;

import com.placemates.dao.placedalums.PlacedAlumsDAO;
import com.placemates.dto.placedalums.PlacedAlumsDTO;
import com.placemates.util.mapper.common.BranchMapper;
import com.placemates.util.mapper.compnay.CompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {CompanyMapper.class, BranchMapper.class})
public interface PlacedAlumsMapper {
    PlacedAlumsMapper INSTANCE = Mappers.getMapper(PlacedAlumsMapper.class);

    @Mapping(source = "companyDTO", target = "companyDAO")
    @Mapping(source = "branchDTO", target = "branchDAO")
    PlacedAlumsDAO fromDTOToDAO(PlacedAlumsDTO placedAlumsDTO);

    @Mapping(source = "companyDAO",target = "companyDTO")
    @Mapping(source = "branchDAO", target= " branchDTO")
    PlacedAlumsDTO fromDAOToDTO(PlacedAlumsDAO placedAlumsDAO);

    @Mapping(source = "companyDTO", target = "companyDAO")
    @Mapping(source = "branchDTO", target = "branchDAO")
    List<PlacedAlumsDAO> fromDTOListToDAOList(List<PlacedAlumsDTO> placedAlumsDTOList);

    @Mapping(source = "companyDAO",target = "companyDTO")
    @Mapping(source = "branchDAO", target= " branchDTO")
    List<PlacedAlumsDTO> fromDAOListToDTOList(List<PlacedAlumsDAO> placedAlumsDAOList);
}
