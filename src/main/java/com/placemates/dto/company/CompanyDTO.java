package com.placemates.dto.company;

import com.placemates.dto.common.BranchDTO;
import com.placemates.dto.common.ImageDTO;
import com.placemates.dto.common.LocationDTO;
import com.placemates.dto.placedalum.PlacedAlumDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class CompanyDTO {
    private Integer companyId;

    @NotBlank
    private String name;

    private String employeeType;
    private String designation;
    private BigDecimal ctc;
    private String eligibilityCriteria;
    private String skill;
    private String jobDescription;
    private ImageDTO imageDTO;

    private List<PlacedAlumDTO> placedAlumDTOList;
    private List<BranchDTO> branchDTOList;
    private List<LocationDTO> locationDTOList;
}
