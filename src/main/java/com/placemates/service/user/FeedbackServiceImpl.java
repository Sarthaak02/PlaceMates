package com.placemates.service.user;

import com.placemates.dao.user.FeedbackDAO;
import com.placemates.dto.user.FeedbackDTO;
import com.placemates.repository.user.FeedbackRepository;
import com.placemates.util.mapper.user.FeedbackMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FeedbackServiceImpl implements FeedbackService{
    
    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO) {
        FeedbackDAO feedbackDAO = FeedbackMapper.INSTANCE.fromDTOToDAO(feedbackDTO);
        feedbackDAO.setFeedBackId(null);
        feedbackDAO = feedbackRepository.save(feedbackDAO);
        log.info("Feedback successfully created with id: {}", feedbackDAO.getFeedBackId());
        return FeedbackMapper.INSTANCE.fromDAOToDTO(feedbackDAO);
    }

    @Override
    public List<FeedbackDTO> getAllFeedbacks() {
        List<FeedbackDAO> feedbackDAOList = feedbackRepository.findAll();
        if(feedbackDAOList.isEmpty()) log.warn("Feedbacks not found !!!");
        else log.info("{} feedbacks found", feedbackDAOList.size());
        return FeedbackMapper.INSTANCE.fromDAOListToDTOList(feedbackDAOList);
    }
}
