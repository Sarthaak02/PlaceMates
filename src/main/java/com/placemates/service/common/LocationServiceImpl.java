package com.placemates.service.common;

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

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public LocationDTO createLocation(LocationDTO locationDTO) {
        if (locationRepository.findByCity(locationDTO.getCity()) != null) {
            log.warn("Location already exists with city: {}", locationDTO.getCity());
            throw new ResourceAlreadyExistsException("Location already exists with city: " + locationDTO.getCity());
        }
        LocationDAO locationDAO = LocationMapper.INSTANCE.fromDTOToDAO(locationDTO);
        locationDAO.setLocationId(null);
        locationDAO = locationRepository.save(locationDAO);
        log.info("Location successfully created with id: {}", locationDAO.getLocationId());
        return LocationMapper.INSTANCE.fromDAOToDTO(locationDAO);
    }

    @Override
    public LocationDTO getLocation(Integer id) {
        LocationDAO locationDAO = locationRepository.findById(id).orElseThrow(() -> {
            log.error("Location not found with id: {}", id);
            return new ResourceNotFoundException("Location not found with id: " + id);
        });
        LocationDTO locationDTO = LocationMapper.INSTANCE.fromDAOToDTO(locationDAO);
        log.info("Location found with id: {}",id);
        return locationDTO;
    }

    @Override
    public List<LocationDTO> getAllLocations() {
        List<LocationDAO> locationDAOList = locationRepository.findAll();
        if (locationDAOList.isEmpty()) log.warn("Locations not found !!!");
        else log.info("{} locations found", locationDAOList.size());
        return LocationMapper.INSTANCE.fromDAOListToDTOList(locationDAOList);
    }

    @Override
    public LocationDTO updateLocation(Integer id, LocationDTO locationDTO) {
        if (!locationRepository.existsById(id)) {
            log.error("Location not found with id: {}", id);
            throw new ResourceNotFoundException("Location not found with id: " + id);
        }
        if (locationRepository.findByCity(locationDTO.getCity()) != null && locationRepository.findByCity(locationDTO.getCity()).getLocationId() != id) {
            log.warn("Location already exists with city: {}", locationDTO.getCity());
            throw new ResourceAlreadyExistsException("Location already exists with city: " + locationDTO.getCity());
        }
        LocationDAO locationDAO = LocationMapper.INSTANCE.fromDTOToDAO(locationDTO);
        locationDAO.setLocationId(id);
        locationRepository.save(locationDAO);
        log.info("Location successfully updated with id: {}", locationDAO.getLocationId());
        return LocationMapper.INSTANCE.fromDAOToDTO(locationDAO);
    }

    @Override
    public void deleteLocation(Integer id) {
        if (!locationRepository.existsById(id)) {
            log.error("Location not found with id: {}", id);
            throw new ResourceNotFoundException("Location not found with id: " + id);
        }
        locationRepository.deleteById(id);
        log.info("Location successfully deleted  with id: {}", id);
    }
}
