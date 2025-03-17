package com.placemates.util.mapper.common;

import com.placemates.dao.common.LocationDAO;
import com.placemates.dto.common.LocationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LocationMapper {
    LocationMapper locationMapper = Mappers.getMapper(LocationMapper.class);

    LocationDAO fromDTOToDAO(LocationDTO locationDTO);
    LocationDTO fromDAOToDTO(LocationDAO locationDAO);
    List<LocationDAO> fromDTOListToDAOList(List<LocationDTO> locationDTOList);
    List<LocationDTO> fromDAOListToDTOList(List<LocationDAO> locationDAOList);
}
