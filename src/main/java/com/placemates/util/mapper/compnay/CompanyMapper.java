package com.placemates.util.mapper.compnay;

import com.placemates.dao.company.CompanyDAO;
import com.placemates.dto.company.CompanyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CompanyMapper {
   CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

   @Mapping(source = "branchDTOs", target = "branchDAOs")
   @Mapping(source = "locationDTOs", target = "locationDAOs")
   CompanyDAO fromDTOToDAO(CompanyDTO companyDTO);

   @Mapping(source = "branchDAOs", target = "branchDTOs")
   @Mapping(source = "locationDAOs", target = "locationDTOs")
   CompanyDTO fromDAOToDTO(CompanyDAO companyDAO);

   @Mapping(source = "branchDTOs", target = "branchDAOs")
   @Mapping(source = "locationDTOs", target = "locationDAOs")
   List<CompanyDAO> fromDTOListToDAOList(List<CompanyDTO> companyDTOList);

   @Mapping(source = "branchDAOs", target = "branchDTOs")
   @Mapping(source = "locationDAOs", target = "locationDTOs")
   List<CompanyDTO> fromDAOListToDTOList(List<CompanyDAO> companyDAOList);
}
