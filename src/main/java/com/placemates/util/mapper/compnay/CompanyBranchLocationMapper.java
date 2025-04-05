package com.placemates.util.mapper.compnay;


import com.placemates.dao.company.CompanyBranchLocationDAO;
import com.placemates.dto.company.CompanyBranchLocationDTO;
import com.placemates.util.mapper.common.BranchMapper;
import com.placemates.util.mapper.common.LocationMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {CompanyMapper.class, BranchMapper.class, LocationMapper.class})
public interface CompanyBranchLocationMapper {
    CompanyBranchLocationMapper INSTANCE = Mappers.getMapper(CompanyBranchLocationMapper.class);

    @Mapping(source = "companyDTO", target = "companyDAO")
    @Mapping(source = "branchDTO", target = "branchDAO")
    @Mapping(source = "locationDTO", target = "locationDAO")
    CompanyBranchLocationDAO fromDTOToDAO(CompanyBranchLocationDTO companyBranchLocationDTO);

    @Mapping(source = "companyDAO",target = "companyDTO")
    @Mapping(source = "branchDAO", target= " branchDTO")
    @Mapping(source = "locationDAO", target= " locationDTO")
    CompanyBranchLocationDTO fromDAOToDTO(CompanyBranchLocationDAO companyBranchLocationDAO);

    @Mapping(source = "companyDTO", target = "companyDAO")
    @Mapping(source = "branchDTO", target = "branchDAO")
    @Mapping(source = "locationDTO", target = "locationDAO")
    List<CompanyBranchLocationDAO> fromDTOListToDAOList(List<CompanyBranchLocationDTO> companyBranchLocationDTOList);

    @Mapping(source = "companyDAO",target = "companyDTO")
    @Mapping(source = "branchDAO", target= " branchDTO")
    @Mapping(source = "locationDAO", target= " locationDTO")
    List<CompanyBranchLocationDTO> fromDAOListToDTOList(List<CompanyBranchLocationDAO> companyBranchLocationDAOList);
}
