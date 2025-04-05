package com.placemates.service.placedalums;

import com.placemates.dao.placedalums.PlacedAlumsDAO;
import com.placemates.dto.placedalums.PlacedAlumsDTO;
import com.placemates.exception.ResourceAlreadyExistsException;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.placedalums.PlacedAlumsRepository;
import com.placemates.util.mapper.placedalums.PlacedAlumsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlacedAlumsServiceImpl implements PlacedAlumsService{

    private static final Logger logger = LoggerFactory.getLogger(PlacedAlumsServiceImpl.class);
    private final PlacedAlumsRepository placedAlumsRepository;

    public PlacedAlumsServiceImpl(PlacedAlumsRepository placedAlumsRepository) {
        this.placedAlumsRepository = placedAlumsRepository;
    }

    @Override
    public PlacedAlumsDTO createPlacedAlums(PlacedAlumsDTO placedAlumsDTO) {
        if(placedAlumsRepository.findByMail(placedAlumsDTO.getMail()) != null){
            logger.warn("Placed alums already exists with email: {}", placedAlumsDTO.getMail());
            throw new ResourceAlreadyExistsException("Placed alums already exists with mail: " + placedAlumsDTO.getMail());
        }

        if(placedAlumsRepository.findByMobileNumber(placedAlumsDTO.getMobileNumber()) != null){
            logger.warn("Placed alums exists with mobile number: {}", placedAlumsDTO.getMobileNumber());
            throw new ResourceAlreadyExistsException("Placed alums already exists with mobile number: " + placedAlumsDTO.getMobileNumber());
        }

        PlacedAlumsDAO placedAlumsDAO =  placedAlumsRepository.save(PlacedAlumsMapper.INSTANCE.fromDTOToDAO(placedAlumsDTO));
        logger.info("Placed alums created successfully with id: {}", placedAlumsDAO.getPlacedAlumsId());
        return PlacedAlumsMapper.INSTANCE.fromDAOToDTO(placedAlumsDAO);
    }

    @Override
    public PlacedAlumsDTO getPlacedAlumsById(Integer id) {
        PlacedAlumsDAO placedAlumsDAO = placedAlumsRepository.findById(id).orElseThrow(() -> {
            logger.error("Placed alums not found with id: {}", id);
            return new ResourceNotFoundException("Placed alums not found with id: " + id);
        });
        logger.info("Placed alums found with id: {}",id);
        return PlacedAlumsMapper.INSTANCE.fromDAOToDTO(placedAlumsDAO);
    }

    @Override
    public List<PlacedAlumsDTO> getAllPlacedAlumni() {
        List<PlacedAlumsDAO> placedAlumsDAOList = placedAlumsRepository.findAll();

        if(placedAlumsDAOList.isEmpty()) logger.warn("Placed Alumni not found");
        else logger.info("{} Placed alums found", placedAlumsDAOList.size());

        return PlacedAlumsMapper.INSTANCE.fromDAOListToDTOList(placedAlumsDAOList);
    }

    @Override
    public PlacedAlumsDTO updatePlacedAlumsById(Integer id,PlacedAlumsDTO placedAlumsDTO) {
        if(!placedAlumsRepository.existsById(id)){
            logger.error("Placed alums not found with id: {}", id);
            throw new ResourceNotFoundException("Placed alums not found with id: " + id);
        }

        if(placedAlumsRepository.findByMail(placedAlumsDTO.getMail()) != null && placedAlumsRepository.findByMail(placedAlumsDTO.getMail()).getPlacedAlumsId() != id){
            logger.warn("Placed alums already exists with email: {}", placedAlumsDTO.getMail());
            throw new ResourceAlreadyExistsException("Placed alums already exists with mail: " + placedAlumsDTO.getMail());
        }

        if(placedAlumsRepository.findByMobileNumber(placedAlumsDTO.getMobileNumber()) != null && placedAlumsRepository.findByMobileNumber(placedAlumsDTO.getMobileNumber()).getPlacedAlumsId() != id){
            logger.warn("Placed alums exists with mobile number: {}", placedAlumsDTO.getMobileNumber());
            throw new ResourceAlreadyExistsException("Placed alums already exists with mobile number: " + placedAlumsDTO.getMobileNumber());
        }

        PlacedAlumsDAO placedAlumsDAO = PlacedAlumsMapper.INSTANCE.fromDTOToDAO(placedAlumsDTO);
        placedAlumsDAO.setPlacedAlumsId(id);
        placedAlumsRepository.save(placedAlumsDAO);
        logger.info("Placed alums updated successfully with id: {}", id);
        return PlacedAlumsMapper.INSTANCE.fromDAOToDTO(placedAlumsDAO);
    }

    @Override
    public void deletePlacedAlumsById(Integer id) {
        if(!placedAlumsRepository.existsById(id)){
            logger.error("Placed alums not found with id: {}", id);
            throw new ResourceNotFoundException("Placed alums not found with id: " + id);
        }
        placedAlumsRepository.deleteById(id);
        logger.info("Placed alums deleted successfully with id: {}", id);
    }
}
