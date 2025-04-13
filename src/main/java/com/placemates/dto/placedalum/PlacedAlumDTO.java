package com.placemates.dto.placedalum;

import com.placemates.dto.common.BranchDTO;
import com.placemates.dto.common.ImageDTO;
import com.placemates.dto.company.CompanyDTO;
import com.placemates.enums.Gender;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class PlacedAlumDTO {

    private Integer placedAlumId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String designation;

    private BigDecimal ctc;

    @Pattern(regexp = "^\\d{10}$")
    private String mobileNumber;

    @Email
    @NotBlank
    private String mail;

    private String linkText;

    @Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$|^(www\\.[a-zA-Z0-9-]+\\.[a-zA-Z]{2,})$")
    private String link;
    
    private Gender gender;
    private BranchDTO branchDTO;
    private Integer batch;

    @NotNull
    private CompanyDTO companyDTO;

    private ImageDTO imageDTO;
}
