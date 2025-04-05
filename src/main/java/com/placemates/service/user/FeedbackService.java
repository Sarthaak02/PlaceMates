package com.placemates.service.user;

import com.placemates.dto.user.FeedbackDTO;

import java.util.List;

public interface FeedbackService {
    FeedbackDTO createFeedback(FeedbackDTO feedbackDTO);
    List<FeedbackDTO> getAllFeedbacks();
}


