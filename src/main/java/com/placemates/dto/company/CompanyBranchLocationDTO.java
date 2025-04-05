package com.placemates.dto.company;

import com.placemates.dto.common.BranchDTO;
import com.placemates.dto.common.LocationDTO;

import jakarta.validation.constraints.NotNull;

public class CompanyBranchLocationDTO {
    private Integer companyBranchLocationId;

    @NotNull
    private CompanyDTO companyDTO;
    private BranchDTO branchDTO;
    private LocationDTO locationDTO;

    public CompanyBranchLocationDTO() {
    }

    public CompanyBranchLocationDTO(Integer companyBranchLocationId, CompanyDTO companyDTO, BranchDTO branchDTO, LocationDTO locationDTO) {
        this.companyBranchLocationId = companyBranchLocationId;
        this.companyDTO = companyDTO;
        this.branchDTO = branchDTO;
        this.locationDTO = locationDTO;
    }

    public Integer getCompanyBranchLocationId() {
        return companyBranchLocationId;
    }

    public void setCompanyBranchLocationId(Integer companyBranchLocationId) {
        this.companyBranchLocationId = companyBranchLocationId;
    }

    public CompanyDTO getCompanyDTO() {
        return companyDTO;
    }

    public void setCompanyDTO(CompanyDTO companyDTO) {
        this.companyDTO = companyDTO;
    }

    public BranchDTO getBranchDTO() {
        return branchDTO;
    }

    public void setBranchDTO(BranchDTO branchDTO) {
        this.branchDTO = branchDTO;
    }

    public LocationDTO getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(LocationDTO locationDTO) {
        this.locationDTO = locationDTO;
    }
}
