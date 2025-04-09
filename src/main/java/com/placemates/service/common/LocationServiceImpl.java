package com.placemates.service.common;

import com.placemates.constant.AppConstants;
import com.placemates.dao.common.LocationDAO;
import com.placemates.dto.common.LocationDTO;
import com.placemates.exception.ResourceAlreadyExistsException;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.common.LocationRepository;
import com.placemates.util.mapper.common.LocationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private static final Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);
    private static final String LOCATION = "Location";
    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public LocationDTO createLocation(LocationDTO locationDTO) {
        if (locationRepository.findByCity(locationDTO.getCity()) != null) {
            logger.warn(LOCATION + AppConstants.ALREADY_EXISTS + "city: {}", locationDTO.getCity());
            throw new ResourceAlreadyExistsException(LOCATION + AppConstants.ALREADY_EXISTS + "city: " + locationDTO.getCity());
        }
        LocationDAO locationDAO = LocationMapper.INSTANCE.fromDTOToDAO(locationDTO);
        locationDAO.setLocationId(null);
        locationDAO = locationRepository.save(locationDAO);
        logger.info(LOCATION + AppConstants.CREATED + "{}", locationDAO.getLocationId());
        return LocationMapper.INSTANCE.fromDAOToDTO(locationDAO);
    }

    @Override
    public LocationDTO getLocation(Integer id) {
        LocationDAO locationDAO = locationRepository.findById(id).orElseThrow(() -> {
            logger.error(LOCATION + AppConstants.NOT_FOUND + "{}", id);
            return new ResourceNotFoundException(LOCATION + AppConstants.NOT_FOUND + id);
        });
        LocationDTO locationDTO = LocationMapper.INSTANCE.fromDAOToDTO(locationDAO);
        logger.info(LOCATION + AppConstants.FOUND + "{}",id);
        return locationDTO;
    }

    @Override
    public List<LocationDTO> getAllLocations() {
        List<LocationDAO> locationDAOList = locationRepository.findAll();
        if (locationDAOList.isEmpty()) logger.warn("Locations" + AppConstants.NO_RECORDS_FOUND);
        else logger.info("{} locations" + AppConstants.RECORDS_FOUND, locationDAOList.size());
        return LocationMapper.INSTANCE.fromDAOListToDTOList(locationDAOList);
    }

    @Override
    public LocationDTO updateLocation(Integer id, LocationDTO locationDTO) {
        if (!locationRepository.existsById(id)) {
            logger.error(LOCATION + AppConstants.NOT_FOUND + "{}", id);
            throw new ResourceNotFoundException(LOCATION + AppConstants.NOT_FOUND + id);
        }
        if (locationRepository.findByCity(locationDTO.getCity()) != null && locationRepository.findByCity(locationDTO.getCity()).getLocationId() != id) {
            logger.warn(LOCATION + AppConstants.ALREADY_EXISTS + "city: {}", locationDTO.getCity());
            throw new ResourceAlreadyExistsException(LOCATION + AppConstants.ALREADY_EXISTS + "city: " + locationDTO.getCity());
        }
        LocationDAO locationDAO = LocationMapper.INSTANCE.fromDTOToDAO(locationDTO);
        locationDAO.setLocationId(id);
        locationRepository.save(locationDAO);
        logger.info(LOCATION + AppConstants.UPDATED + "{}", locationDAO.getLocationId());
        return LocationMapper.INSTANCE.fromDAOToDTO(locationDAO);
    }

    @Override
    public void deleteLocation(Integer id) {
        if (!locationRepository.existsById(id)) {
            logger.error(LOCATION + AppConstants.NOT_FOUND + "{}", id);
            throw new ResourceNotFoundException(LOCATION + AppConstants.NOT_FOUND + id);
        }
        locationRepository.deleteById(id);
        logger.info(LOCATION + AppConstants.DELETED + "{}", id);
    }
}
