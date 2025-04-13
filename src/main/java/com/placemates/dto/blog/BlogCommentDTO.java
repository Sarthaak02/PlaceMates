package com.placemates.dto.blog;

import com.placemates.dto.user.UserInfoDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class BlogCommentDTO {

    private Integer commentId;

    @NotBlank
    private String content;

    @NotNull
    private LocalDateTime commentedAt;

    @NotNull
    private BlogDTO blogDTO;

    @NotNull
    private UserInfoDTO commentByDTO;
}
