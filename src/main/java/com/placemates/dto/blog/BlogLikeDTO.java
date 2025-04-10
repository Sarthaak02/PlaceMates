package com.placemates.dto.blog;

import com.placemates.dto.user.UserInfoDTO;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class BlogLikeDTO {

    private Integer likeID;

    @NotNull
    private LocalDateTime likedAt;

    @NotNull
    private BlogDTO blogDTO;

    @NotNull
    private UserInfoDTO likeByDTO;

    public BlogLikeDTO() {
    }

    public BlogLikeDTO(Integer likeID, LocalDateTime likedAt, BlogDTO blogDTO, UserInfoDTO likeByDTO) {
        this.likeID = likeID;
        this.likedAt = likedAt;
        this.blogDTO = blogDTO;
        this.likeByDTO = likeByDTO;
    }

    public Integer getLikeID() {
        return likeID;
    }

    public void setLikeID(Integer likeID) {
        this.likeID = likeID;
    }

    public LocalDateTime getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(LocalDateTime likedAt) {
        this.likedAt = likedAt;
    }

    public BlogDTO getBlogDTO() {
        return blogDTO;
    }

    public void setBlogDTO(BlogDTO blogDTO) {
        this.blogDTO = blogDTO;
    }

    public UserInfoDTO getLikeByDTO() {
        return likeByDTO;
    }

    public void setLikeByDTO(UserInfoDTO likeByDTO) {
        this.likeByDTO = likeByDTO;
    }
}
