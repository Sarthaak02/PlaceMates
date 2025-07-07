package com.placemates.service.placedalum;

import com.placemates.dao.placedalum.PlacedAlumDAO;
import com.placemates.dto.placedalum.PlacedAlumDTO;
import com.placemates.exception.ResourceAlreadyExistsException;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.placedalum.PlacedAlumRepository;
import com.placemates.service.user.UserService;
import com.placemates.util.logger.LoggerUtil;
import com.placemates.util.mapper.placedalum.PlacedAlumMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.placemates.constant.AppConstants.*;

@Service
@Slf4j
public class PlacedAlumServiceImpl implements PlacedAlumService {

    private final PlacedAlumRepository placedAlumRepository;
    private final UserService userService;

    public PlacedAlumServiceImpl(PlacedAlumRepository placedAlumRepository, UserService userService) {
        this.placedAlumRepository = placedAlumRepository;
        this.userService = userService;
    }

    @Override
    public PlacedAlumDTO createPlacedAlum(PlacedAlumDTO placedAlumDTO) {
        if(placedAlumRepository.findByMail(placedAlumDTO.getMail()) != null){
            log.warn("Placed alum already exists with email: {}", placedAlumDTO.getMail());
            throw new ResourceAlreadyExistsException("Placed alum already exists with email:" + placedAlumDTO.getMail());
        }
        if(placedAlumRepository.findByMobileNumber(placedAlumDTO.getMobileNumber()) != null){
            log.warn("Placed alum already exists with mobile number: {}", placedAlumDTO.getMobileNumber());
            throw new ResourceAlreadyExistsException("Placed alum already exists with mobile number: " + placedAlumDTO.getMobileNumber());
        }

        PlacedAlumDAO placedAlumDAO = PlacedAlumMapper.INSTANCE.toPlacedAlumDAO(placedAlumDTO);
        placedAlumDAO.setPlacedAlumId(null);
        placedAlumDAO = placedAlumRepository.save(placedAlumDAO);
        log.info("Placed alum successfully created with id: {}", placedAlumDAO.getPlacedAlumId());
        return PlacedAlumMapper.INSTANCE.toPlacedAlumDTO(placedAlumDAO);
    }

    @Override
    public PlacedAlumDTO getPlacedAlum(Integer id) {
        PlacedAlumDAO placedAlumDAO = placedAlumRepository.findById(id).orElseThrow(() -> {
            log.error("Placed alum not found with id: {}", id);
            return new ResourceNotFoundException("Placed alum not found with id: " + id);
        });
        log.info("Placed alum found with id: {}", placedAlumDAO.getPlacedAlumId());
        return PlacedAlumMapper.INSTANCE.toPlacedAlumDTO(placedAlumDAO);
    }

    @Override
    public List<PlacedAlumDTO> getAllPlacedAlums() {
        List<PlacedAlumDAO> placedAlumDAOList = placedAlumRepository.findAll();

        if(placedAlumDAOList.isEmpty()) log.warn("Placed Alums not found !!!");
        else log.info("{} placed alums found", placedAlumDAOList.size());

        return PlacedAlumMapper.INSTANCE.toPlacedAlumDTOList(placedAlumDAOList);
    }

    @Override
    public PlacedAlumDTO updatePlacedAlum(Integer id, PlacedAlumDTO placedAlumDTO) {
        if(!placedAlumRepository.existsById(id)){
            log.error("Placed alum not found with id: {}", id);
            throw new ResourceNotFoundException("Placed alum not found with id: " + id);
        }

        if(placedAlumRepository.findByMail(placedAlumDTO.getMail()) != null && placedAlumRepository.findByMail(placedAlumDTO.getMail()).getPlacedAlumId() != id){
            log.warn("Placed alum already exists with email: {}", placedAlumDTO.getMail());
            throw new ResourceAlreadyExistsException("Placed alum already exists with email:" + placedAlumDTO.getMail());
        }

        if(placedAlumRepository.findByMobileNumber(placedAlumDTO.getMobileNumber()) != null && placedAlumRepository.findByMobileNumber(placedAlumDTO.getMobileNumber()).getPlacedAlumId() != id){
            log.warn("Placed alum already exists with mobile number: {}", placedAlumDTO.getMobileNumber());
            throw new ResourceAlreadyExistsException("Placed alum already exists with mobile number: " + placedAlumDTO.getMobileNumber());
        }

        PlacedAlumDAO placedAlumDAO = PlacedAlumMapper.INSTANCE.toPlacedAlumDAO(placedAlumDTO);
        placedAlumDAO.setPlacedAlumId(id);
        placedAlumDAO = placedAlumRepository.save(placedAlumDAO);
        log.info("Placed alum successfully updated with id: {}", placedAlumDAO.getPlacedAlumId());
        return PlacedAlumMapper.INSTANCE.toPlacedAlumDTO(placedAlumDAO);
    }

    @Override
    public void deletePlacedAlum(Integer id) {
        if(!placedAlumRepository.existsById(id)){
            log.error("Placed alum not found with id: {}", id);
            throw new ResourceNotFoundException("Placed alum not found with id: " + id);
        }
        placedAlumRepository.deleteById(id);
        log.info("Placed alum successfully deleted with id: {}", id);
    }
}
