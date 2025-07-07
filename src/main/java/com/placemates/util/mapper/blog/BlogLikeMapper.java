package com.placemates.util.mapper.blog;


import com.placemates.dao.blog.BlogLikeDAO;
import com.placemates.dto.blog.BlogLikeDTO;
import com.placemates.util.mapper.user.UserInfoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {BlogMapper.class, UserInfoMapper.class})
public interface BlogLikeMapper {
    BlogLikeMapper INSTANCE = Mappers.getMapper(BlogLikeMapper.class);

    @Mapping(source = "likedByDTO", target = "likedByDAO")
    @Mapping(source = "blogDTO", target = "blogDAO")
    BlogLikeDAO toBlogLikeDAO(BlogLikeDTO blogLikeDTO);

    @Mapping(source = "likedByDAO", target = "likedByDTO")
    @Mapping(source = "blogDAO", target = "blogDTO")
    BlogLikeDTO toBlogLikeDTO(BlogLikeDAO blogLikeDAO);

    @Mapping(source = "likedByDTO", target = "likedByDAO")
    @Mapping(source = "blogDTO", target = "blogDAO")
    List<BlogLikeDAO> toBlogLikeDAOList(List<BlogLikeDTO> blogLikeDTOList);

    @Mapping(source = "likedByDAO", target = "likedByDTO")
    @Mapping(source = "blogDAO", target = "blogDTO")
    List<BlogLikeDTO> toBlogLikeDTOList(List<BlogLikeDAO> blogLikeDAOList);
}
