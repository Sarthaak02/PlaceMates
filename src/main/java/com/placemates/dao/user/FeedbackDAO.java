package com.placemates.dao.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "FEEDBACK")
@Data
@AllArgsConstructor
public class FeedbackDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FB_ID")
    private Integer feedBackId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserDAO userDAO;

    @Column(name = "RATING")
    private Integer rating;

    @Column(name = "MESSAGE")
    private String message;
}
