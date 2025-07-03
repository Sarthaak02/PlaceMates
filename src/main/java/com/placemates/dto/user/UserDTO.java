package com.placemates.dto.user;

import com.placemates.dto.common.ImageDTO;
import com.placemates.dto.common.LocationDTO;
import com.placemates.dto.common.BranchDTO;
import com.placemates.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Integer userId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String mail;

    @Pattern(regexp = "^\\d{10}$")
    private String mobileNumber;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@#$%^&(){}\\[\\]:;<>,/~_+\\-=|\\\\]).{8,32}$")
    private String password;

    private String organisation;
    private String designation;
    private String linkText;

    @Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$|^(www\\.[a-zA-Z0-9-]+\\.[a-zA-Z]{2,})$")
    private String link;

    private Gender gender;
    private Integer batch;
    private BranchDTO branchDTO;
    private LocationDTO locationDTO;
    private RoleDTO roleDTO;
    private ImageDTO imageDTO;
}