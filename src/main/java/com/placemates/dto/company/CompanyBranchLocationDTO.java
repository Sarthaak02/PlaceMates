package com.placemates.dto.company;

import com.placemates.dto.common.BranchDTO;
import com.placemates.dto.common.LocationDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyBranchLocationDTO {
    private Integer companyBranchLocationId;
    private CompanyDTO companyDTO;
    private BranchDTO branchDTO;
    private LocationDTO locationDTO;
}
