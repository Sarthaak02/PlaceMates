package com.placemates.dto.blog;

import com.placemates.dto.common.ImageDTO;
import com.placemates.dto.user.UserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BlogDTO {

    private Integer blogId;

    @NotBlank
    private String title;

    @NotBlank
    private String category;

    @NotBlank
    private String content;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;

    private UserDTO createdByDTO;
    private ImageDTO imageDTO;
}
