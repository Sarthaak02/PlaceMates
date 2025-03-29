package com.placemates.dto.blog;

import com.placemates.dto.user.UserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class BlogCommentDTO {

    private Integer commentId;

    @NotBlank
    private String content;

    @NotNull
    private LocalDateTime commentedAt;

    @NotNull
    private BlogDTO blogDTO;

    @NotNull
    private UserDTO commentByDTO;

    public BlogCommentDTO() {
    }

    public BlogCommentDTO(Integer commentId, String content, LocalDateTime commentedAt, BlogDTO blogDTO, UserDTO commentByDTO) {
        this.commentId = commentId;
        this.content = content;
        this.commentedAt = commentedAt;
        this.blogDTO = blogDTO;
        this.commentByDTO = commentByDTO;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCommentedAt() {
        return commentedAt;
    }

    public void setCommentedAt(LocalDateTime commentedAt) {
        this.commentedAt = commentedAt;
    }

    public BlogDTO getBlogDTO() {
        return blogDTO;
    }

    public void setBlogDTO(BlogDTO blogDTO) {
        this.blogDTO = blogDTO;
    }

    public UserDTO getCommentByDTO() {
        return commentByDTO;
    }

    public void setCommentByDTO(UserDTO commentByDTO) {
        this.commentByDTO = commentByDTO;
    }
}
