package com.placemates.dto.user;

import jakarta.validation.constraints.NotBlank;


public class UserInfoDTO {

    private Integer userId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private RoleDTO roleDTO;

    public UserInfoDTO() {
    }

    public UserInfoDTO(Integer userId, String firstName, String lastName, RoleDTO roleDTO) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleDTO = roleDTO;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RoleDTO getRoleDTO() {
        return roleDTO;
    }

    public void setRoleDTO(RoleDTO roleDTO) {
        this.roleDTO = roleDTO;
    }
}
