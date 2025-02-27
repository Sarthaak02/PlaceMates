package com.placemates.service.company;

import com.placemates.dto.company.CompanyDTO;
import com.placemates.exception.DataAlreadyExistsException;
import com.placemates.exception.DataNotFoundException;

import java.util.List;

public interface CompanyService {

    void createCompany(CompanyDTO companyDTO) throws DataAlreadyExistsException;
    List<CompanyDTO> getCompanies() throws DataNotFoundException;
    void updateCompany(Integer id, CompanyDTO companyDTO) throws DataNotFoundException, DataAlreadyExistsException;
    void deleteCompany(Integer id) throws DataNotFoundException;
}
