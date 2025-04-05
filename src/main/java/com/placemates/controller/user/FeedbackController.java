package com.placemates.controller.user;

import com.placemates.dto.user.FeedbackDTO;
import com.placemates.service.user.FeedbackServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackServiceImpl feedbackServiceImpl;

    public FeedbackController(FeedbackServiceImpl feedbackServiceImpl) {
        this.feedbackServiceImpl = feedbackServiceImpl;
    }

    @PostMapping("/create")
    public FeedbackDTO createFeedback(@Valid @RequestBody FeedbackDTO feedbackDTO){
        return feedbackServiceImpl.createFeedback(feedbackDTO);
    }

    @GetMapping("")
    public List<FeedbackDTO> getAllFeedbacks(){
        return feedbackServiceImpl.getAllFeedbacks();
    }
}
