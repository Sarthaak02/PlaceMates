package com.placemates.util.mapper.common;

import com.placemates.dao.common.BatchDAO;
import com.placemates.dto.common.BatchDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BatchMapper {
    BatchMapper batchMapper = Mappers.getMapper(BatchMapper.class);

    BatchDAO fromDTOToDAO(BatchDTO batchDTO);
    BatchDTO fromDAOToDTO(BatchDAO batchDAO);
    List<BatchDAO> fromDTOListToDAOList(List<BatchDTO> batchDTOList);
    List<BatchDTO> fromDAOListToDTOList(List<BatchDAO> batchDAOList);
}
