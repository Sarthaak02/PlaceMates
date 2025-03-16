package com.placemates.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeedbackDTO {
    private Integer feedBackId;
    private UserDTO userDTO;
    private Integer rating;
    private String message;
}
