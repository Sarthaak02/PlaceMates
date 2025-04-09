package com.placemates.util.mapper.blog;

import com.placemates.dao.blog.BlogDAO;
import com.placemates.dto.blog.BlogDTO;
import com.placemates.util.mapper.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {UserMapper.class})
public interface BlogMapper {
    BlogMapper INSTANCE = Mappers.getMapper(BlogMapper.class);

    @Mapping(source = "createdByDTO", target = "createdByDAO")
    BlogDAO fromDTOToDAO(BlogDTO blogDTO);

    @Mapping(source = "createdByDAO", target = "createdByDTO")
    BlogDTO fromDAOToDTO(BlogDAO blogDAO);

    @Mapping(source = "createdByDTO", target = "createdByDAO")
    List<BlogDAO> fromDTOListToDAOList(List<BlogDTO> blogDTOList);

    @Mapping(source = "createdByDAO", target = "createdByDTO")
    List<BlogDTO> fromDAOListToDTOList(List<BlogDAO> blogDAOList);
}
