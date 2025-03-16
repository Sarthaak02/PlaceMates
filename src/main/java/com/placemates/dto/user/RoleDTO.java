package com.placemates.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleDTO {

    private Integer roleId;

    @NotBlank
    private String name;
}
