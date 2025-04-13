package com.placemates.dao.blog;

import com.placemates.dao.user.UserDAO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "BLOG_ID")
    private BlogDAO blogDAO;

    @ManyToOne
    @JoinColumn(name = "COMMENT_BY")
    private UserDAO commentByDAO;
}
