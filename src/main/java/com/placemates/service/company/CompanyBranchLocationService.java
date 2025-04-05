package com.placemates.service.company;

import com.placemates.dao.company.CompanyDAO;
import com.placemates.dto.common.BranchDTO;
import com.placemates.dto.common.LocationDTO;

import java.util.List;

public interface CompanyBranchLocationService {
    List<BranchDTO> saveAllBranches(List<BranchDTO> branchDTOList, CompanyDAO companyDAO);
    List<LocationDTO> saveAllLocations(List<LocationDTO> locationDTOList, CompanyDAO companyDAO);
    List<BranchDTO> getAllBranches(Integer id);
    List<LocationDTO> getAllLocations(Integer id);
    void deleteAllByCompany(Integer id);
}
