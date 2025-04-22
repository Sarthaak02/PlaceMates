package com.placemates.controller.user;

import com.placemates.dto.user.FeedbackDTO;
import com.placemates.service.user.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<FeedbackDTO> createFeedback(@Valid @RequestBody FeedbackDTO feedbackDTO){
        FeedbackDTO newFeedbackDTO = feedbackService.createFeedback(feedbackDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newFeedbackDTO);
    }

    @GetMapping
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbacks(){
        List<FeedbackDTO> feedbackDTOList = feedbackService.getAllFeedbacks();
        return ResponseEntity.status(HttpStatus.OK).body(feedbackDTOList);
    }
}
