package com.placemates.dao.blog;

import com.placemates.dao.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "BLOG_COMMENT")
@Data
@AllArgsConstructor
public class BlogComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CMT_ID")
    private Integer commentId;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CREATED_AT")
    private String createdAt;

    @ManyToOne
    @JoinColumn(name = "BLOG_ID")
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User commentBy;
}
