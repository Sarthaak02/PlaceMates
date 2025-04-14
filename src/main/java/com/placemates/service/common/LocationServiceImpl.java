package com.placemates.service.common;

import com.placemates.constant.AppConstants;
import com.placemates.dao.common.LocationDAO;
import com.placemates.dto.common.LocationDTO;
import com.placemates.exception.ResourceAlreadyExistsException;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.common.LocationRepository;
import com.placemates.util.mapper.common.LocationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

    private static final String LOCATION = "Location";
    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public LocationDTO createLocation(LocationDTO locationDTO) {
        if (locationRepository.findByCity(locationDTO.getCity()) != null) {
            log.warn(LOCATION + AppConstants.ALREADY_EXISTS + "city: {}", locationDTO.getCity());
            throw new ResourceAlreadyExistsException(LOCATION + AppConstants.ALREADY_EXISTS + "city: " + locationDTO.getCity());
        }
        LocationDAO locationDAO = LocationMapper.INSTANCE.fromDTOToDAO(locationDTO);
        locationDAO.setLocationId(null);
        locationDAO = locationRepository.save(locationDAO);
        log.info(LOCATION + AppConstants.CREATED + "{}", locationDAO.getLocationId());
        return LocationMapper.INSTANCE.fromDAOToDTO(locationDAO);
    }

    @Override
    public LocationDTO getLocation(Integer id) {
        LocationDAO locationDAO = locationRepository.findById(id).orElseThrow(() -> {
            log.error(LOCATION + AppConstants.NOT_FOUND + "{}", id);
            return new ResourceNotFoundException(LOCATION + AppConstants.NOT_FOUND + id);
        });
        LocationDTO locationDTO = LocationMapper.INSTANCE.fromDAOToDTO(locationDAO);
        log.info(LOCATION + AppConstants.FOUND + "{}",id);
        return locationDTO;
    }

    @Override
    public List<LocationDTO> getAllLocations() {
        List<LocationDAO> locationDAOList = locationRepository.findAll();
        if (locationDAOList.isEmpty()) log.warn("Locations" + AppConstants.NO_RECORDS_FOUND);
        else log.info("{} locations" + AppConstants.RECORDS_FOUND, locationDAOList.size());
        return LocationMapper.INSTANCE.fromDAOListToDTOList(locationDAOList);
    }

    @Override
    public LocationDTO updateLocation(Integer id, LocationDTO locationDTO) {
        if (!locationRepository.existsById(id)) {
            log.error(LOCATION + AppConstants.NOT_FOUND + "{}", id);
            throw new ResourceNotFoundException(LOCATION + AppConstants.NOT_FOUND + id);
        }
        if (locationRepository.findByCity(locationDTO.getCity()) != null && locationRepository.findByCity(locationDTO.getCity()).getLocationId() != id) {
            log.warn(LOCATION + AppConstants.ALREADY_EXISTS + "city: {}", locationDTO.getCity());
            throw new ResourceAlreadyExistsException(LOCATION + AppConstants.ALREADY_EXISTS + "city: " + locationDTO.getCity());
        }
        LocationDAO locationDAO = LocationMapper.INSTANCE.fromDTOToDAO(locationDTO);
        locationDAO.setLocationId(id);
        locationRepository.save(locationDAO);
        log.info(LOCATION + AppConstants.UPDATED + "{}", locationDAO.getLocationId());
        return LocationMapper.INSTANCE.fromDAOToDTO(locationDAO);
    }

    @Override
    public void deleteLocation(Integer id) {
        if (!locationRepository.existsById(id)) {
            log.error(LOCATION + AppConstants.NOT_FOUND + "{}", id);
            throw new ResourceNotFoundException(LOCATION + AppConstants.NOT_FOUND + id);
        }
        locationRepository.deleteById(id);
        log.info(LOCATION + AppConstants.DELETED + "{}", id);
    }
}
