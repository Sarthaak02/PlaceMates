package com.placemates.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoDTO {

    private Integer userId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private RoleDTO roleDTO;
}
