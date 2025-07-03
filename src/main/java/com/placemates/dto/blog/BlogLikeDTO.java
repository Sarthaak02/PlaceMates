package com.placemates.dto.blog;

import com.placemates.dto.user.UserInfoDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class BlogLikeDTO {

    private Integer likeID;

    @NotNull
    private LocalDateTime likedAt;

    @NotNull
    private BlogDTO blogDTO;

    @NotNull
    private UserInfoDTO likedByDTO;
}
