package com.placemates.dto.user;

import jakarta.validation.constraints.NotBlank;

public class RoleDTO {

    private Integer roleId;

    @NotBlank
    private String name;

    public RoleDTO() {
    }

    public RoleDTO(Integer roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
