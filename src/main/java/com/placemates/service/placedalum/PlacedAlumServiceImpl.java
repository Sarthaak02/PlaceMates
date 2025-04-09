package com.placemates.service.placedalum;

import com.placemates.constant.AppConstants;
import com.placemates.dao.placedalum.PlacedAlumDAO;
import com.placemates.dto.placedalum.PlacedAlumDTO;
import com.placemates.exception.ResourceAlreadyExistsException;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.placedalum.PlacedAlumRepository;
import com.placemates.util.mapper.placedalum.PlacedAlumMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlacedAlumServiceImpl implements PlacedAlumService {

    private static final Logger logger = LoggerFactory.getLogger(PlacedAlumServiceImpl.class);
    private final PlacedAlumRepository placedAlumRepository;
    private static final String PLACED_ALUM = "Placed alum";

    public PlacedAlumServiceImpl(PlacedAlumRepository placedAlumRepository) {
        this.placedAlumRepository = placedAlumRepository;
    }

    @Override
    public PlacedAlumDTO createPlacedAlum(PlacedAlumDTO placedAlumDTO) {
        if(placedAlumRepository.findByMail(placedAlumDTO.getMail()) != null){
            logger.warn(PLACED_ALUM + AppConstants.ALREADY_EXISTS + "mail: {}", placedAlumDTO.getMail());
            throw new ResourceAlreadyExistsException(PLACED_ALUM + AppConstants.ALREADY_EXISTS + "mail: " + placedAlumDTO.getMail());
        }
        if(placedAlumRepository.findByMobileNumber(placedAlumDTO.getMobileNumber()) != null){
            logger.warn(PLACED_ALUM + AppConstants.ALREADY_EXISTS + "mobile number: {}", placedAlumDTO.getMobileNumber());
            throw new ResourceAlreadyExistsException(PLACED_ALUM + AppConstants.ALREADY_EXISTS + "mobile number: " + placedAlumDTO.getMobileNumber());
        }

        PlacedAlumDAO placedAlumDAO = PlacedAlumMapper.INSTANCE.fromDTOToDAO(placedAlumDTO);
        placedAlumDAO.setPlacedAlumId(null);
        placedAlumDAO = placedAlumRepository.save(placedAlumDAO);
        logger.info(PLACED_ALUM + AppConstants.CREATED + "{}", placedAlumDAO.getPlacedAlumId());
        return PlacedAlumMapper.INSTANCE.fromDAOToDTO(placedAlumDAO);
    }

    @Override
    public PlacedAlumDTO getPlacedAlum(Integer id) {
        PlacedAlumDAO placedAlumDAO = placedAlumRepository.findById(id).orElseThrow(() -> {
            logger.error(PLACED_ALUM + AppConstants.NOT_FOUND + "{}", id);
            return new ResourceNotFoundException(PLACED_ALUM + AppConstants.NOT_FOUND + id);
        });
        logger.info(PLACED_ALUM + AppConstants.FOUND + "{}",id);
        return PlacedAlumMapper.INSTANCE.fromDAOToDTO(placedAlumDAO);
    }

    @Override
    public List<PlacedAlumDTO> getAllPlacedAlums() {
        List<PlacedAlumDAO> placedAlumDAOList = placedAlumRepository.findAll();

        if(placedAlumDAOList.isEmpty()) logger.warn("Placed Alums" + AppConstants.NO_RECORDS_FOUND);
        else logger.info("{} Placed alums" + AppConstants.RECORDS_FOUND, placedAlumDAOList.size());

        return PlacedAlumMapper.INSTANCE.fromDAOListToDTOList(placedAlumDAOList);
    }

    @Override
    public PlacedAlumDTO updatePlacedAlum(Integer id, PlacedAlumDTO placedAlumDTO) {
        if(!placedAlumRepository.existsById(id)){
            logger.error(PLACED_ALUM + AppConstants.NOT_FOUND + "{}", id);
            throw new ResourceNotFoundException(PLACED_ALUM + AppConstants.NOT_FOUND + id);
        }

        if(placedAlumRepository.findByMail(placedAlumDTO.getMail()) != null && placedAlumRepository.findByMail(placedAlumDTO.getMail()).getPlacedAlumId() != id){
            logger.warn(PLACED_ALUM + AppConstants.ALREADY_EXISTS + "mail: {}", placedAlumDTO.getMail());
            throw new ResourceAlreadyExistsException(PLACED_ALUM + AppConstants.ALREADY_EXISTS + "mail: " + placedAlumDTO.getMail());
        }

        if(placedAlumRepository.findByMobileNumber(placedAlumDTO.getMobileNumber()) != null && placedAlumRepository.findByMobileNumber(placedAlumDTO.getMobileNumber()).getPlacedAlumId() != id){
            logger.warn(PLACED_ALUM + AppConstants.ALREADY_EXISTS + "mobile number: {}", placedAlumDTO.getMobileNumber());
            throw new ResourceAlreadyExistsException(PLACED_ALUM + AppConstants.ALREADY_EXISTS + "mobile number: " + placedAlumDTO.getMobileNumber());
        }

        PlacedAlumDAO placedAlumDAO = PlacedAlumMapper.INSTANCE.fromDTOToDAO(placedAlumDTO);
        placedAlumDAO.setPlacedAlumId(id);
        placedAlumDAO = placedAlumRepository.save(placedAlumDAO);
        logger.info( PLACED_ALUM + AppConstants.UPDATED + "{}", placedAlumDAO.getPlacedAlumId());
        return PlacedAlumMapper.INSTANCE.fromDAOToDTO(placedAlumDAO);
    }

    @Override
    public void deletePlacedAlum(Integer id) {
        if(!placedAlumRepository.existsById(id)){
            logger.error(PLACED_ALUM + AppConstants.NOT_FOUND + "{}", id);
            throw new ResourceNotFoundException(PLACED_ALUM + AppConstants.NOT_FOUND + id);
        }
        placedAlumRepository.deleteById(id);
        logger.info(PLACED_ALUM + AppConstants.DELETED + "{}", id);
    }
}
