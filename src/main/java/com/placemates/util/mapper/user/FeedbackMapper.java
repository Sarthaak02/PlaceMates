package com.placemates.util.mapper.user;

import com.placemates.dao.user.FeedbackDAO;
import com.placemates.dto.user.FeedbackDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = UserInfoMapper.class)
public interface FeedbackMapper {

    FeedbackMapper INSTANCE = Mappers.getMapper(FeedbackMapper.class);

    @Mapping(source = "userInfoDTO", target = "userDAO")
    FeedbackDAO toFeedbackDAO(FeedbackDTO feedbackDTO);

    @Mapping(source = "userDAO", target = "userInfoDTO")
    FeedbackDTO toFeedbackDTO(FeedbackDAO feedbackDAO);

    @Mapping(source = "userInfoDTO", target = "userDAO")
    List<FeedbackDAO> toFeedbackDAOList(List<FeedbackDTO> feedbackDTOList);

    @Mapping(source = "userDAO", target = "userInfoDTO")
    List<FeedbackDTO> toFeedbackDTOList(List<FeedbackDAO> feedbackDAOList);
}
