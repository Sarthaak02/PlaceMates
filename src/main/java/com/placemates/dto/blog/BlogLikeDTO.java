package com.placemates.dto.blog;

import com.placemates.dto.user.UserDTO;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class BlogLikeDTO {

    private Integer likeID;

    @NotNull
    private LocalDateTime likedAt;

    @NotNull
    private BlogDTO blogDTO;

    @NotNull
    private UserDTO likeByDTO;

    public BlogLikeDTO() {
    }

    public BlogLikeDTO(Integer likeID, LocalDateTime likedAt, BlogDTO blogDTO, UserDTO likeByDTO) {
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

    public UserDTO getLikeByDTO() {
        return likeByDTO;
    }

    public void setLikeByDTO(UserDTO likeByDTO) {
        this.likeByDTO = likeByDTO;
    }
}
