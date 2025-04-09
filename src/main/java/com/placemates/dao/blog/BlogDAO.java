package com.placemates.dao.blog;

import com.placemates.dao.common.ImageDAO;
import com.placemates.dao.user.UserDAO;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.time.LocalDateTime;

@Entity
@Table(name = "BLOG")
public class BlogDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BLOG_ID")
    private Integer blogId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "CONTENT")
    private String content;

    @Formula("(SELECT COUNT(*) FROM BLOG_LIKE l WHERE l.BLOG_ID = BLOG_ID)")
    private int likeCount;

    @Formula("(SELECT COUNT(*) FROM BLOG_COMMENT c WHERE c.BLOG_ID = BLOG_ID)")
    private int commentCount;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    private UserDAO createdByDAO;

    @OneToOne
    @JoinColumn(name = "IMG_ID")
    private ImageDAO imageDAO;

    public BlogDAO() {
    }

    public BlogDAO(Integer blogId, String title, String category, String content, int likeCount, int commentCount, LocalDateTime createdAt, LocalDateTime updatedAt, UserDAO createdByDAO, ImageDAO imageDAO) {
        this.blogId = blogId;
        this.title = title;
        this.category = category;
        this.content = content;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdByDAO = createdByDAO;
        this.imageDAO = imageDAO;
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

    public UserDAO getCreatedByDAO() {
        return createdByDAO;
    }

    public void setCreatedByDAO(UserDAO createdByDAO) {
        this.createdByDAO = createdByDAO;
    }

    public ImageDAO getImageDAO() {
        return imageDAO;
    }

    public void setImageDAO(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }
}
