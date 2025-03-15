package com.placemates.dao.blog;

import com.placemates.dao.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "BLOG_LIKE")
@Data
@AllArgsConstructor
public class BlogLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIKE_ID")
    private Integer likeID;

    @Column(name = "CREATED_ID")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "BLOG_ID")
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User likeBy;
}
