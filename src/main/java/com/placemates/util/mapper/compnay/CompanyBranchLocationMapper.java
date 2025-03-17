package com.placemates.util.mapper.compnay;


import com.placemates.dao.company.CompanyBranchLocationDAO;
import com.placemates.dto.company.CompanyBranchLocationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CompanyBranchLocationMapper {
    CompanyBranchLocationMapper companyBranchLocationMapper = Mappers.getMapper(CompanyBranchLocationMapper.class);

    CompanyBranchLocationDAO fromDTOToDAO(CompanyBranchLocationDTO companyBranchLocationDTO);
    CompanyBranchLocationDTO fromDAOToDTO(CompanyBranchLocationDAO companyBranchLocationDAO);
    List<CompanyBranchLocationDAO> fromDTOListToDAOList(List<CompanyBranchLocationDTO> companyBranchLocationDTOList);
    List<CompanyBranchLocationDTO> fromDAOListToDTOList(List<CompanyBranchLocationDAO> companyBranchLocationDAOList);
}
