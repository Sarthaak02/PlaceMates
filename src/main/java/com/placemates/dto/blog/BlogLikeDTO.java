package com.placemates.dto.blog;

import com.placemates.dto.user.UserDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class BlogLikeDTO {

    private Integer likeID;

    @NotNull
    private LocalDateTime createdAt;

    private BlogDTO blogDTO;
    private UserDTO likeByDTO;
}
