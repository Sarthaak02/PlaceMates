package com.placemates.dto.common;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BranchDTO {

    private Integer branchId;

    @NotBlank
    private String name;
}
