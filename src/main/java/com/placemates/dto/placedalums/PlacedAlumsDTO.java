package com.placemates.dto.placedalums;

import com.placemates.dto.common.BatchDTO;
import com.placemates.dto.common.BranchDTO;
import com.placemates.dto.common.ImageDTO;
import com.placemates.dto.company.CompanyDTO;
import com.placemates.enums.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PlacedAlumsDTO {

    private Integer placedAlumsId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String designation;

    private BigDecimal ctc;

    @Size(min=10,max=10)
    @Pattern(regexp="(^$|\\d{10})\n")
    private Integer mobileNumber;

    @Email
    @NotBlank
    private String email;

    private String linkText;

    @Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$|^(www\\.[a-zA-Z0-9-]+\\.[a-zA-Z]{2,})$")
    private String link;
    
    private Gender gender;
    private BranchDTO branchDTO;
    private BatchDTO batchDTO;
    private CompanyDTO companyDTO;
    private ImageDTO imageDTO;
}
