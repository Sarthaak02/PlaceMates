package com.placemates.util.mapper.blog;

import com.placemates.dao.blog.BlogCommentDAO;
import com.placemates.dto.blog.BlogCommentDTO;
import com.placemates.util.mapper.user.UserInfoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {BlogMapper.class, UserInfoMapper.class})
public interface BlogCommentMapper {
    BlogCommentMapper INSTANCE = Mappers.getMapper(BlogCommentMapper.class);

    @Mapping(source = "commentedByDTO", target = "commentedByDAO")
    @Mapping(source = "blogDTO", target = "blogDAO")
    BlogCommentDAO toBlogCommentDAO(BlogCommentDTO blogCommentDTO);

    @Mapping(source = "commentedByDAO", target = "commentedByDTO")
    @Mapping(source = "blogDAO", target = "blogDTO")
    BlogCommentDTO toBlogCommentDTO(BlogCommentDAO blogCommentDAO);

    @Mapping(source = "commentedByDTO", target = "commentedByDAO")
    @Mapping(source = "blogDTO", target = "blogDAO")
    List<BlogCommentDAO> toBlogCommentDAOList(List<BlogCommentDTO> blogCommentDTOList);

    @Mapping(source = "commentedByDAO", target = "commentedByDTO")
    @Mapping(source = "blogDAO", target = "blogDTO")
    List<BlogCommentDTO> toBlogCommentDTOList(List<BlogCommentDAO> blogCommentDAOList);
}
