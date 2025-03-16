package com.placemates.dto.blog;

import com.placemates.dto.user.UserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlogCommentDTO {

    private Integer commentId;

    @NotBlank
    private String content;

    @NotNull
    private String createdAt;

    private BlogDTO blogDTO;
    private UserDTO commentByDTO;
}
