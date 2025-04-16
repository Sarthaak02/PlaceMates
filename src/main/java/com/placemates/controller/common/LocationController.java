package com.placemates.controller.common;

import com.placemates.dto.common.LocationDTO;
import com.placemates.service.common.LocationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/create")
    public ResponseEntity<LocationDTO> createLocation(@Valid @RequestBody LocationDTO locationDTO){
        LocationDTO newLocationDTO = locationService.createLocation(locationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newLocationDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getLocation(@PathVariable Integer id){
        LocationDTO locationDTO = locationService.getLocation(id);
        return ResponseEntity.status(HttpStatus.OK).body(locationDTO);
    }

    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAllLocations(){
        List<LocationDTO> locationDTOList = locationService.getAllLocations();
        return ResponseEntity.status(HttpStatus.OK).body(locationDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationDTO> updateLocation(@PathVariable Integer id, @Valid @RequestBody LocationDTO locationDTO){
        LocationDTO updatedLocationDTO = locationService.updateLocation(id, locationDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedLocationDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Integer id){
        locationService.deleteLocation(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
