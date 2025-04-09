package com.placemates.util.mapper.placedalum;

import com.placemates.dao.placedalum.PlacedAlumDAO;
import com.placemates.dto.placedalum.PlacedAlumDTO;
import com.placemates.util.mapper.common.BranchMapper;
import com.placemates.util.mapper.compnay.CompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {CompanyMapper.class, BranchMapper.class})
public interface PlacedAlumMapper {
    PlacedAlumMapper INSTANCE = Mappers.getMapper(PlacedAlumMapper.class);

    @Mapping(source = "companyDTO", target = "companyDAO")
    @Mapping(source = "branchDTO", target = "branchDAO")
    PlacedAlumDAO fromDTOToDAO(PlacedAlumDTO placedAlumDTO);

    @Mapping(source = "companyDAO",target = "companyDTO")
    @Mapping(source = "branchDAO", target= " branchDTO")
    PlacedAlumDTO fromDAOToDTO(PlacedAlumDAO placedAlumDAO);

    @Mapping(source = "companyDTO", target = "companyDAO")
    @Mapping(source = "branchDTO", target = "branchDAO")
    List<PlacedAlumDAO> fromDTOListToDAOList(List<PlacedAlumDTO> placedAlumDTOList);

    @Mapping(source = "companyDAO",target = "companyDTO")
    @Mapping(source = "branchDAO", target= " branchDTO")
    List<PlacedAlumDTO> fromDAOListToDTOList(List<PlacedAlumDAO> placedAlumDAOList);
}
