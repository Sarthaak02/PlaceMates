package com.placemates.util.mapper.common;

import com.placemates.dao.common.LocationDAO;
import com.placemates.dto.common.LocationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    LocationDAO toLocationDAO(LocationDTO locationDTO);
    LocationDTO toLocationDTO(LocationDAO locationDAO);
    List<LocationDAO> toLocationDAOList(List<LocationDTO> locationDTOList);
    List<LocationDTO> toLocationDTOList(List<LocationDAO> locationDAOList);
}
