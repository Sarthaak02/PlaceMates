package com.placemates.dao.blog;

import com.placemates.dao.user.UserDAO;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "BLOG_COMMENT")
public class BlogCommentDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CMT_ID")
    private Integer commentId;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "COMMENTED_AT")
    private LocalDateTime commentedAt;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "BLOG_ID")
    private BlogDAO blogDAO;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "USER_ID")
    private UserDAO commentByDAO;

    public BlogCommentDAO() {
    }

    public BlogCommentDAO(Integer commentId, String content, LocalDateTime commentedAt, BlogDAO blogDAO, UserDAO commentByDAO) {
        this.commentId = commentId;
        this.content = content;
        this.commentedAt = commentedAt;
        this.blogDAO = blogDAO;
        this.commentByDAO = commentByDAO;
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

    public BlogDAO getBlogDAO() {
        return blogDAO;
    }

    public void setBlogDAO(BlogDAO blogDAO) {
        this.blogDAO = blogDAO;
    }

    public UserDAO getCommentByDAO() {
        return commentByDAO;
    }

    public void setCommentByDAO(UserDAO commentByDAO) {
        this.commentByDAO = commentByDAO;
    }
}
