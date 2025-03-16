package com.placemates.dto.company;

import com.placemates.dto.common.ImageDTO;
import com.placemates.enums.EmployeeType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CompanyDTO {
    private Integer companyId;

    @NotBlank
    private String name;

    private EmployeeType employeeType;
    private String designation;
    private BigDecimal ctc;
    private String eligibilityCriteria;
    private String skill;
    private String jobDescription;
    private ImageDTO imageDTO;
}
