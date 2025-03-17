package com.placemates.util.mapper.common;

import com.placemates.dao.common.ImageDAO;
import com.placemates.dto.common.ImageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ImageMapper {
    ImageMapper imageMapper = Mappers.getMapper(ImageMapper.class);

    ImageDAO fromDTOToDAO(ImageDTO imageDTO);
    ImageDTO fromDAOToDTO(ImageDAO imageDAO);
    List<ImageDAO> fromDTOListToDAOList(List<ImageDTO> imageDTOList);
    List<ImageDTO> fromDAOListToDTOList(List<ImageDAO> imageDAOList);
}
