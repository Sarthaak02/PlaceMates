package com.placemates.dto.user;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FeedbackDTO {
    private Integer feedBackId;
    private UserInfoDTO userInfoDTO;

    @NotNull
    private Integer rating;

    @NotBlank
    private String message;
}
