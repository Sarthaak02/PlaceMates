package com.placemates.controller.user;

import com.placemates.dto.user.FeedbackDTO;
import com.placemates.service.user.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/create")
    public FeedbackDTO createFeedback(@Valid @RequestBody FeedbackDTO feedbackDTO){
        return feedbackService.createFeedback(feedbackDTO);
    }

    @GetMapping("")
    public List<FeedbackDTO> getAllFeedbacks(){
        return feedbackService.getAllFeedbacks();
    }
}
