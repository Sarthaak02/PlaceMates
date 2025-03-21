package com.placemates.dao.blog;

import com.placemates.dao.common.ImageDAO;
import com.placemates.dao.user.UserDAO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "BLOG")
@Data
@AllArgsConstructor
public class BlogDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BLOG_ID")
    private Integer blogId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "content")
    private String content;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserDAO createdByDAO;

    @OneToOne
    @JoinColumn(name = "IMG_ID")
    private ImageDAO imageDAO;
}
