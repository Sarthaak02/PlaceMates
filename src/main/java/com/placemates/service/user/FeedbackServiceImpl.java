package com.placemates.service.user;

import com.placemates.dao.user.FeedbackDAO;
import com.placemates.dao.user.UserDAO;
import com.placemates.dto.user.FeedbackDTO;
import com.placemates.dto.user.UserInfoDTO;
import com.placemates.repository.user.FeedbackRepository;
import com.placemates.util.logger.LoggerUtil;
import com.placemates.util.mapper.user.FeedbackMapper;
import com.placemates.util.mapper.user.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.placemates.constant.AppConstants.*;

@Service
@Slf4j
public class FeedbackServiceImpl implements FeedbackService{
    
    private final FeedbackRepository feedbackRepository;
    private final UserService userService;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, UserService userService) {
        this.feedbackRepository = feedbackRepository;
        this.userService = userService;
    }

    @Override
    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO) {
        UserDAO currentUser = userService.getCurrentUser();
        String username = currentUser.getMail();

        double startTime = System.currentTimeMillis();

        UserInfoDTO userInfoDTO = UserInfoMapper.INSTANCE.toUserDTO(currentUser);
        feedbackDTO.setUserInfoDTO(userInfoDTO);

        FeedbackDAO feedbackDAO = FeedbackMapper.INSTANCE.toFeedbackDAO(feedbackDTO);
        feedbackDAO = feedbackRepository.save(feedbackDAO);

        double endTime = System.currentTimeMillis();
        double duration = (endTime - startTime) / 1000;

        log.info(LoggerUtil.buildLog(FEEDBACK,CREATE,"Id- " + feedbackDAO.getFeedBackId(), username, duration, SUCCESS));

        return FeedbackMapper.INSTANCE.toFeedbackDTO(feedbackDAO);
    }

    @Override
    public List<FeedbackDTO> getAllFeedbacks() {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();

        List<FeedbackDAO> feedbackDAOList = feedbackRepository.findAll();

        double endTime = System.currentTimeMillis();
        double duration = (endTime - startTime) / 1000;

        if(feedbackDAOList.isEmpty()){
            log.warn(LoggerUtil.buildLog(FEEDBACK,READ,"Feedbacks not found" , username, duration, FAIL));
            new ArrayList<>();
        }
        else log.info(LoggerUtil.buildLog(FEEDBACK,READ,feedbackDAOList.size() + " feedbacks fetched" , username, duration, SUCCESS));

        return FeedbackMapper.INSTANCE.toFeedbackDTOList(feedbackDAOList);
    }
}
