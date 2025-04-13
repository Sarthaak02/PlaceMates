package com.placemates.dto.company;

import com.placemates.dto.common.BranchDTO;
import com.placemates.dto.common.LocationDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyBranchLocationDTO {
    private Integer companyBranchLocationId;

    @NotNull
    private CompanyDTO companyDTO;
    private BranchDTO branchDTO;
    private LocationDTO locationDTO;
}
