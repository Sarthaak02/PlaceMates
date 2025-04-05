package com.placemates.util.mapper.compnay;

import com.placemates.dao.company.CompanyDAO;
import com.placemates.dto.company.CompanyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CompanyMapper {
   CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

   CompanyDAO fromDTOToDAO(CompanyDTO companyDTO);
   CompanyDTO fromDAOToDTO(CompanyDAO companyDAO);
   List<CompanyDAO> fromDTOListToDAOList(List<CompanyDTO> companyDTOList);
   List<CompanyDTO> fromDAOListToDTOList(List<CompanyDAO> companyDAOList);
}
