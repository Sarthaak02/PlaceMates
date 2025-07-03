package com.placemates.dto.blog;

import com.placemates.dto.common.ImageDTO;
import com.placemates.dto.user.UserInfoDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class BlogDTO {

    private Integer blogId;

    @NotBlank
    private String title;

    @NotBlank
    private String category;

    @NotBlank
    private String content;

    private int likeCount;
    private List<BlogLikeDTO> blogLikeDTOs;

    private int commentCount;
    private List<BlogCommentDTO> blogCommentDTOs;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @NotNull
    private UserInfoDTO createdByDTO;

    private ImageDTO imageDTO;
}
