package com.placemates.dao.blog;

import com.placemates.dao.user.UserDAO;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "BLOG_LIKE")
public class BlogLikeDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIKE_ID")
    private Integer likeID;

    @Column(name = "LIKED_AT")
    private LocalDateTime likedAt;

    @ManyToOne
    @JoinColumn(name = "BLOG_ID")
    private BlogDAO blogDAO;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserDAO likeByDAO;

    public BlogLikeDAO() {
    }

    public BlogLikeDAO(Integer likeID, LocalDateTime likedAt, BlogDAO blogDAO, UserDAO likeByDAO) {
        this.likeID = likeID;
        this.likedAt = likedAt;
        this.blogDAO = blogDAO;
        this.likeByDAO = likeByDAO;
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

    public BlogDAO getBlogDAO() {
        return blogDAO;
    }

    public void setBlogDAO(BlogDAO blogDAO) {
        this.blogDAO = blogDAO;
    }

    public UserDAO getLikeByDAO() {
        return likeByDAO;
    }

    public void setLikeByDAO(UserDAO likeByDAO) {
        this.likeByDAO = likeByDAO;
    }
}
