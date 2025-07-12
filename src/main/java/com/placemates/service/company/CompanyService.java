package com.placemates.service.company;

import com.placemates.dto.company.CompanyDTO;

import java.util.List;

public interface CompanyService {
    CompanyDTO createCompany(CompanyDTO companyDTO);
    CompanyDTO getCompany(Integer companyId);
    List<CompanyDTO> getAllCompanies();
    CompanyDTO updateCompany(Integer companyId, CompanyDTO companyDTO);
    void deleteCompany(Integer companyId);
}

