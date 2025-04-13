package com.placemates.dao.blog;

import com.placemates.dao.common.ImageDAO;
import com.placemates.dao.user.UserDAO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
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
}
