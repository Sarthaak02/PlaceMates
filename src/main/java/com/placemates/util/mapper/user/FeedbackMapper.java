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
    FeedbackDAO fromDTOToDAO(FeedbackDTO feedbackDTO);

    @Mapping(source = "userDAO", target = "userInfoDTO")
    FeedbackDTO fromDAOToDTO(FeedbackDAO feedbackDAO);

    @Mapping(source = "userInfoDTO", target = "userDAO")
    List<FeedbackDAO> fromDTOListToDAOList(List<FeedbackDTO> feedbackDTOList);

    @Mapping(source = "userDAO", target = "userInfoDTO")
    List<FeedbackDTO> fromDAOListToDTOList(List<FeedbackDAO> feedbackDAOList);
}
