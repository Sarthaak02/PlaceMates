package com.placemates.dto.user;

import com.placemates.dto.common.BatchDTO;
import com.placemates.dto.common.ImageDTO;
import com.placemates.dto.common.LocationDTO;
import com.placemates.dto.common.BranchDTO;
import com.placemates.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    private Integer userId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @Size(min=10,max=10)
    @Pattern(regexp="(^$|\\d{10})\n")
    private Integer mobileNumber;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@#$%^&(){}\\[\\]:;<>,/~_+\\-=|\\\\]).{8,32}$\n")
    private String password;

    private String organization;
    private String designation;
    private String linkText;

    @Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$|^(www\\.[a-zA-Z0-9-]+\\.[a-zA-Z]{2,})$")
    private String link;

    private Gender gender;
    private BranchDTO branchDTO;
    private BatchDTO batchDTO;
    private LocationDTO locationDTO;
    private RoleDTO roleDTO;
    private ImageDTO imageDTO;
}
