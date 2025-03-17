package com.placemates.util.mapper.placedalums;

import com.placemates.dao.placedalums.PlacedAlumsDAO;
import com.placemates.dto.placedalums.PlacedAlumsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PlacedAlumsMapper {
    PlacedAlumsMapper placedAlumsMapper = Mappers.getMapper(PlacedAlumsMapper.class);

    PlacedAlumsDAO fromDTOToDAO(PlacedAlumsDTO placedAlumsDTO);
    PlacedAlumsDTO fromDAOToDTO(PlacedAlumsDAO placedAlumsDAO);
    List<PlacedAlumsDAO> fromDTOListToDAOList(List<PlacedAlumsDTO> placedAlumsDTOList);
    List<PlacedAlumsDTO> fromDAOListToDTOList(List<PlacedAlumsDAO> placedAlumsDAOList);
}
