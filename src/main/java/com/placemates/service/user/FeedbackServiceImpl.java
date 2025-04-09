package com.placemates.service.user;

import com.placemates.constant.AppConstants;
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
        FeedbackDAO feedbackDAO = FeedbackMapper.INSTANCE.fromDTOToDAO(feedbackDTO);
        feedbackDAO.setFeedBackId(null);
        feedbackDAO = feedbackRepository.save(feedbackDAO);
        logger.info("Feedback" + AppConstants.CREATED + "{}", feedbackDAO.getFeedBackId());
        return FeedbackMapper.INSTANCE.fromDAOToDTO(feedbackDAO);
    }

    @Override
    public List<FeedbackDTO> getAllFeedbacks() {
        List<FeedbackDAO> feedbackDAOList = feedbackRepository.findAll();
        if(feedbackDAOList.isEmpty()) logger.warn("Feedbacks" + AppConstants.NO_RECORDS_FOUND);
        else logger.info("{} Feedbacks" + AppConstants.RECORDS_FOUND, feedbackDAOList.size());
        return FeedbackMapper.INSTANCE.fromDAOListToDTOList(feedbackDAOList);
    }
}
