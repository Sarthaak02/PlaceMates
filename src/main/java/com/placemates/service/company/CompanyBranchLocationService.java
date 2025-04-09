package com.placemates.service.company;

import com.placemates.dto.common.BranchDTO;
import com.placemates.dto.common.LocationDTO;

import java.util.List;

public interface CompanyBranchLocationService {
    List<BranchDTO> saveAllBranchesByCompany(List<BranchDTO> branchDTOList, Integer companyId);
    List<LocationDTO> saveAllLocationsByCompany(List<LocationDTO> locationDTOList, Integer companyId);
    List<BranchDTO> getAllBranchesByCompany(Integer id);
    List<LocationDTO> getAllLocationsByCompany(Integer id);
    void deleteAllByCompany(Integer id);
}
