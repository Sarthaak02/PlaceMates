package com.placemates.service.company;

import com.placemates.dao.company.CompanyDAO;
import com.placemates.dto.common.BranchDTO;
import com.placemates.dto.common.LocationDTO;

import java.util.List;

public interface CompanyBranchLocationService {
    List<BranchDTO> saveBranchList(List<BranchDTO> branchDTOList, CompanyDAO companyDAO);
    List<LocationDTO> saveLocationList(List<LocationDTO> locationDTOList, CompanyDAO companyDAO);
    List<BranchDTO> getBranchList(Integer id);
    List<LocationDTO> getLocationList(Integer id);
    void deleteAllByCompany(Integer id);
}
