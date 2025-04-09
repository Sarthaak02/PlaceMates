package com.placemates.util.mapper.blog;


import com.placemates.dao.blog.BlogLikeDAO;
import com.placemates.dto.blog.BlogLikeDTO;
import com.placemates.util.mapper.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {BlogMapper.class, UserMapper.class})
public interface BlogLikeMapper {
    BlogLikeMapper INSTANCE = Mappers.getMapper(BlogLikeMapper.class);

    @Mapping(source = "likeByDTO", target = "likeByDAO")
    @Mapping(source = "blogDTO", target = "blogDAO")
    BlogLikeDAO fromDTOToDAO(BlogLikeDTO blogLikeDTO);

    @Mapping(source = "likeByDAO", target = "likeByDTO")
    @Mapping(source = "blogDAO", target = "blogDTO")
    BlogLikeDTO fromDAOToDTO(BlogLikeDAO blogLikeDAO);

    @Mapping(source = "likeByDTO", target = "likeByDAO")
    @Mapping(source = "blogDTO", target = "blogDAO")
    List<BlogLikeDAO> fromDTOListToDAOList(List<BlogLikeDTO> blogLikeDTOList);

    @Mapping(source = "likeByDAO", target = "likeByDTO")
    @Mapping(source = "blogDAO", target = "blogDTO")
    List<BlogLikeDTO> fromDAOListToDTOList(List<BlogLikeDAO> blogLikeDAOList);
}
