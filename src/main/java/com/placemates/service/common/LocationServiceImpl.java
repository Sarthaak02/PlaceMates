package com.placemates.service.common;

import com.placemates.dao.common.LocationDAO;
import com.placemates.dto.common.LocationDTO;
import com.placemates.exception.ResourceAlreadyExistsException;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.common.LocationRepository;
import com.placemates.service.user.UserService;
import com.placemates.util.logger.LoggerUtil;
import com.placemates.util.mapper.common.LocationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.placemates.constant.AppConstants.*;

@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final UserService userService;

    public LocationServiceImpl(LocationRepository locationRepository, UserService userService) {
        this.locationRepository = locationRepository;
        this.userService = userService;
    }

    @Override
    public LocationDTO createLocation(LocationDTO locationDTO) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();
        double endTime, duration;

        if (locationRepository.findByCity(locationDTO.getCity()) != null) {
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime) / 1000;
            log.warn(LoggerUtil.buildLog(LOCATION, CREATE, "Location already exists with city: " + locationDTO.getCity(), username, duration, FAIL));
            throw new ResourceAlreadyExistsException("Location already exists with city: " + locationDTO.getCity());
        }

        LocationDAO locationDAO = LocationMapper.INSTANCE.toLocationDAO(locationDTO);
        locationDAO.setLocationId(null);
        locationDAO = locationRepository.save(locationDAO);

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(LOCATION, CREATE, "Id- " + locationDAO.getLocationId(), username, duration, SUCCESS));

        return LocationMapper.INSTANCE.toLocationDTO(locationDAO);
    }

    @Override
    public LocationDTO getLocation(Integer locationId) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();

        LocationDAO locationDAO = locationRepository.findById(locationId).orElseThrow(() -> {
            log.error(LoggerUtil.buildLog(LOCATION, READ, "Location not found with id- " + locationId, username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
            return new ResourceNotFoundException("Location not found with id: " + locationId);
        });

        double endTime = System.currentTimeMillis();
        double duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(LOCATION, READ, "Location fetched with id- " + locationId, username, duration, SUCCESS));

        return LocationMapper.INSTANCE.toLocationDTO(locationDAO);
    }

    @Override
    public List<LocationDTO> getAllLocations() {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();

        List<LocationDAO> locationDAOList = locationRepository.findAll();

        if (locationDAOList.isEmpty()) {
            log.error(LoggerUtil.buildLog(LOCATION, READ, "Locations not found", username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
            return new ArrayList<>();
        } else {
            log.info(LoggerUtil.buildLog(LOCATION, READ, locationDAOList.size() + " Locations fetched", username, (System.currentTimeMillis() - startTime) / 1000, SUCCESS));
        }

        return LocationMapper.INSTANCE.toLocationDTOList(locationDAOList);
    }

    @Override
    public LocationDTO updateLocation(Integer locationId, LocationDTO locationDTO) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();
        double endTime, duration;

        if (!locationRepository.existsById(locationId)) {
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime) / 1000;
            log.error(LoggerUtil.buildLog(LOCATION, UPDATE, "Location not found with id- " + locationId, username, duration, FAIL));
            throw new ResourceNotFoundException("Location not found with id: " + locationId);
        }

        LocationDAO existingByCity = locationRepository.findByCity(locationDTO.getCity());
        if (existingByCity != null && existingByCity.getLocationId() != locationId) {
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime) / 1000;
            log.warn(LoggerUtil.buildLog(LOCATION, UPDATE, "Location already exists with city: " + locationDTO.getCity(), username, duration, FAIL));
            throw new ResourceAlreadyExistsException("Location already exists with city: " + locationDTO.getCity());
        }

        LocationDAO locationDAO = LocationMapper.INSTANCE.toLocationDAO(locationDTO);
        locationDAO.setLocationId(locationId);
        locationDAO = locationRepository.save(locationDAO);

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(LOCATION, UPDATE, "Id- " + locationDAO.getLocationId(), username, duration, SUCCESS));

        return LocationMapper.INSTANCE.toLocationDTO(locationDAO);
    }

    @Override
    public void deleteLocation(Integer locationId) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();
        double endTime, duration;

        if (!locationRepository.existsById(locationId)) {
            log.error(LoggerUtil.buildLog(LOCATION, DELETE, "Location not found with id- " + locationId, username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
            throw new ResourceNotFoundException("Location not found with id: " + locationId);
        }

        locationRepository.deleteById(locationId);

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(LOCATION, DELETE, "Id- " + locationId, username, duration, SUCCESS));
    }
}
