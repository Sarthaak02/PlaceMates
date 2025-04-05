package com.placemates.service.user;

import com.placemates.dao.user.FeedbackDAO;
import com.placemates.dto.user.FeedbackDTO;
import com.placemates.repository.user.FeedbackRepository;
import com.placemates.util.mapper.user.FeedbackMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService{

    private static final Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);
    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO) {
        FeedbackDAO feedbackDAO = feedbackRepository.save(FeedbackMapper.INSTANCE.fromDTOToDAO(feedbackDTO));
        logger.info("Feedback saved successfully");
        return FeedbackMapper.INSTANCE.fromDAOToDTO(feedbackDAO);
    }

    @Override
    public List<FeedbackDTO> getAllFeedbacks() {
        List<FeedbackDAO> feedbackDAOList = feedbackRepository.findAll();
        if(feedbackDAOList.isEmpty()) logger.warn("No feedbacks found!!!");
        else logger.info("{} feedbacks found ", feedbackDAOList.size());
        return FeedbackMapper.INSTANCE.fromDAOListToDTOList(feedbackDAOList);
    }
}
