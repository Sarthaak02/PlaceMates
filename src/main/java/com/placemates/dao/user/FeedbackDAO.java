package com.placemates.dao.user;

import jakarta.persistence.*;

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

    public FeedbackDAO() {
    }

    public FeedbackDAO(Integer feedBackId, UserDAO userDAO, Integer rating, String message) {
        this.feedBackId = feedBackId;
        this.userDAO = userDAO;
        this.rating = rating;
        this.message = message;
    }

    public Integer getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(Integer feedBackId) {
        this.feedBackId = feedBackId;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
