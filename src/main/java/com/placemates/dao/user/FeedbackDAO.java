package com.placemates.dao.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "FEEDBACK")
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
