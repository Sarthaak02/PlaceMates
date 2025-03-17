package com.placemates.util.mapper.blog;

import com.placemates.dao.blog.BlogDAO;
import com.placemates.dto.blog.BlogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BlogMapper {
    BlogMapper blogMapper = Mappers.getMapper(BlogMapper.class);

    BlogDAO fromDTOToDAO(BlogDTO blogDTO);
    BlogDTO fromDAOToDTO(BlogDAO blogDAO);
    List<BlogDAO> fromDTOListToDAOList(List<BlogDTO> blogDTOList);
    List<BlogDTO> fromDAOListToDTOList(List<BlogDAO> blogDAOList);
}
