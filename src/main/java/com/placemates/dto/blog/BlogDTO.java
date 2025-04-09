package com.placemates.dto.blog;

import com.placemates.dto.common.ImageDTO;
import com.placemates.dto.user.UserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class BlogDTO {

    private Integer blogId;

    @NotBlank
    private String title;

    @NotBlank
    private String category;

    @NotBlank
    private String content;

    private int likeCount;
    private List<BlogLikeDTO> blogLikeDTOList;

    private int commentCount;
    private List<BlogCommentDTO> blogCommentDTOList;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @NotNull
    private UserDTO createdByDTO;


    private ImageDTO imageDTO;

    public BlogDTO() {
    }

    public BlogDTO(Integer blogId, String title, String category, String content, int likeCount, List<BlogLikeDTO> blogLikeDTOList, int commentCount, List<BlogCommentDTO> blogCommentDTOList, LocalDateTime createdAt, LocalDateTime updatedAt, UserDTO createdByDTO, ImageDTO imageDTO) {
        this.blogId = blogId;
        this.title = title;
        this.category = category;
        this.content = content;
        this.likeCount = likeCount;
        this.blogLikeDTOList = blogLikeDTOList;
        this.commentCount = commentCount;
        this.blogCommentDTOList = blogCommentDTOList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdByDTO = createdByDTO;
        this.imageDTO = imageDTO;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public List<BlogLikeDTO> getBlogLikeDTOList() {
        return blogLikeDTOList;
    }

    public void setBlogLikeDTOList(List<BlogLikeDTO> blogLikeDTOList) {
        this.blogLikeDTOList = blogLikeDTOList;
    }

    public List<BlogCommentDTO> getBlogCommentDTOList() {
        return blogCommentDTOList;
    }

    public void setBlogCommentDTOList(List<BlogCommentDTO> blogCommentDTOList) {
        this.blogCommentDTOList = blogCommentDTOList;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserDTO getCreatedByDTO() {
        return createdByDTO;
    }

    public void setCreatedByDTO(UserDTO createdByDTO) {
        this.createdByDTO = createdByDTO;
    }

    public ImageDTO getImageDTO() {
        return imageDTO;
    }

    public void setImageDTO(ImageDTO imageDTO) {
        this.imageDTO = imageDTO;
    }
}
