package com.placemates.util.mapper.blog;

import com.placemates.dao.blog.BlogCommentDAO;
import com.placemates.dto.blog.BlogCommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BlogCommentMapper {
    BlogCommentMapper blogCommentMapper = Mappers.getMapper(BlogCommentMapper.class);

    BlogCommentDAO fromDTOToDAO(BlogCommentDTO blogCommentDTO);
    BlogCommentDTO fromDAOToDTO(BlogCommentDAO blogCommentDAO);
    List<BlogCommentDAO> fromDTOListToDAOList(List<BlogCommentDTO> blogCommentDTOList);
    List<BlogCommentDTO> fromDAOListToDTOList(List<BlogCommentDAO> blogCommentDAOList);
}
