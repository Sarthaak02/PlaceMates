package com.placemates.service.company;

import com.placemates.dto.company.CompanyDTO;

import java.util.List;

public interface CompanyService {
    CompanyDTO createCompany(CompanyDTO companyDTO);
    CompanyDTO getCompanyById(Integer id);
    List<CompanyDTO> getAllCompanies();
    CompanyDTO updateCompanyById(Integer id, CompanyDTO companyDTO);
    void deleteCompanyById(Integer id);
}
