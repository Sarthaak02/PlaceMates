package com.placemates.dao.blog;

import com.placemates.dao.user.UserDAO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "BLOG_LIKE")
@Data
@AllArgsConstructor
public class BlogLikeDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIKE_ID")
    private Integer likeID;

    @Column(name = "CREATED_ID")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "BLOG_ID")
    private BlogDAO blogDAO;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserDAO likeByDAO;
}
