package com.placemates.util.mapper.blog;

import com.placemates.dao.blog.BlogCommentDAO;
import com.placemates.dto.blog.BlogCommentDTO;
import com.placemates.util.mapper.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {BlogMapper.class, UserMapper.class})
public interface BlogCommentMapper {
    BlogCommentMapper INSTANCE = Mappers.getMapper(BlogCommentMapper.class);

    @Mapping(source = "commentByDTO", target = "commentByDAO")
    @Mapping(source = "blogDTO", target = "blogDAO")
    BlogCommentDAO fromDTOToDAO(BlogCommentDTO blogCommentDTO);

    @Mapping(source = "commentByDAO", target = "commentByDTO")
    @Mapping(source = "blogDAO", target = "blogDTO")
    BlogCommentDTO fromDAOToDTO(BlogCommentDAO blogCommentDAO);

    @Mapping(source = "commentByDTO", target = "commentByDAO")
    @Mapping(source = "blogDTO", target = "blogDAO")
    List<BlogCommentDAO> fromDTOListToDAOList(List<BlogCommentDTO> blogCommentDTOList);

    @Mapping(source = "commentByDAO", target = "commentByDTO")
    @Mapping(source = "blogDAO", target = "blogDTO")
    List<BlogCommentDTO> fromDAOListToDTOList(List<BlogCommentDAO> blogCommentDAOList);
}
