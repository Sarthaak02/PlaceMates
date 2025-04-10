package com.placemates.dto.user;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FeedbackDTO {
    private Integer feedBackId;
    private UserInfoDTO userInfoDTO;

    @NotNull
    private Integer rating;

    @NotBlank
    private String message;

    public FeedbackDTO() {
    }

    public FeedbackDTO(Integer feedBackId, UserInfoDTO userInfoDTO, Integer rating, String message) {
        this.feedBackId = feedBackId;
        this.userInfoDTO = userInfoDTO;
        this.rating = rating;
        this.message = message;
    }

    public Integer getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(Integer feedBackId) {
        this.feedBackId = feedBackId;
    }

    public UserInfoDTO getUserInfoDTO() {
        return userInfoDTO;
    }

    public void setUserInfoDTO(UserInfoDTO userInfoDTO) {
        this.userInfoDTO = userInfoDTO;
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
