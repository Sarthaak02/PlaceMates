package com.placemates.util.mapper.common;

import com.placemates.dao.common.ImageDAO;
import com.placemates.dto.common.ImageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ImageMapper {
    ImageMapper imageMapper = Mappers.getMapper(ImageMapper.class);

    ImageDAO toImageDAO(ImageDTO imageDTO);
    ImageDTO toImageDTO(ImageDAO imageDAO);
    List<ImageDAO> toImageDAOList(List<ImageDTO> imageDTOList);
    List<ImageDTO> toImageDTOList(List<ImageDAO> imageDAOList);
}
