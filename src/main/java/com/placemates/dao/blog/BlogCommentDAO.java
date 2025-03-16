package com.placemates.dao.blog;

import com.placemates.dao.user.UserDAO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "BLOG_COMMENT")
@Data
@AllArgsConstructor
public class BlogCommentDAO {

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
    private BlogDAO blogDAO;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserDAO commentByDAO;
}
