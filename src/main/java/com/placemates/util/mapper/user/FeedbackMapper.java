package com.placemates.util.mapper.user;

import com.placemates.dao.user.FeedbackDAO;
import com.placemates.dto.user.FeedbackDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FeedbackMapper {

    FeedbackMapper feedbackMapper = Mappers.getMapper(FeedbackMapper.class);

    FeedbackDAO fromDTOToDAO(FeedbackDTO feedbackDTO);
    FeedbackDTO fromDAOToDTO(FeedbackDAO feedbackDAO);
    List<FeedbackDAO> fromDTOListToDAOList(List<FeedbackDTO> feedbackDTOList);
    List<FeedbackDTO> fromDAOListToDTOList(List<FeedbackDAO> feedbackDAOList);
}
