package com.placemates.util.mapper.blog;


import com.placemates.dao.blog.BlogLikeDAO;
import com.placemates.dto.blog.BlogLikeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BlogLikeMapper {
    BlogLikeMapper blogLikeMapper = Mappers.getMapper(BlogLikeMapper.class);

    BlogLikeDAO fromDTOToDAO(BlogLikeDTO blogLikeDTO);
    BlogLikeDTO fromDAOToDTO(BlogLikeDAO blogLikeDAO);
    List<BlogLikeDAO> fromDTOListToDAOList(List<BlogLikeDTO> blogLikeDTOList);
    List<BlogLikeDTO> fromDAOListToDTOList(List<BlogLikeDAO> blogLikeDAOList);
}
