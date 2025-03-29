package com.placemates.dto.common;

import jakarta.validation.constraints.NotBlank;

public class BranchDTO {

    private Integer branchId;

    @NotBlank
    private String name;

    public BranchDTO() {
    }

    public BranchDTO(Integer branchId, String name) {
        this.branchId = branchId;
        this.name = name;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
