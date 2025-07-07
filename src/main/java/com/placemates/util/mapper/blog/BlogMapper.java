package com.placemates.util.mapper.blog;

import com.placemates.dao.blog.BlogDAO;
import com.placemates.dto.blog.BlogDTO;
import com.placemates.util.mapper.user.UserInfoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {UserInfoMapper.class})
public interface BlogMapper {
    BlogMapper INSTANCE = Mappers.getMapper(BlogMapper.class);

    @Mapping(source = "createdByDTO", target = "createdByDAO")
    BlogDAO toBlogDAO(BlogDTO blogDTO);

    @Mapping(source = "createdByDAO", target = "createdByDTO")
    BlogDTO toBlogDTO(BlogDAO blogDAO);

    @Mapping(source = "createdByDTO", target = "createdByDAO")
    List<BlogDAO> toBlogDAOList(List<BlogDTO> blogDTOList);

    @Mapping(source = "createdByDAO", target = "createdByDTO")
    List<BlogDTO> toBlogDTOList(List<BlogDAO> blogDAOList);
}
