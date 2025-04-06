package com.placemates.controller.common;

import com.placemates.dto.common.LocationDTO;
import com.placemates.service.common.LocationService;
import jakarta.validation.Valid;
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
    public LocationDTO createLocation(@Valid @RequestBody LocationDTO locationDTO){
        return locationService.createLocation(locationDTO);
    }

    @GetMapping("/{id}")
    public LocationDTO getLocation(@PathVariable Integer id){
        return locationService.getLocation(id);
    }

    @GetMapping("")
    public List<LocationDTO> getAllLocations(){
        return locationService.getAllLocations();
    }

    @PutMapping("/{id}")
    public LocationDTO updateLocation(@PathVariable Integer id, @Valid @RequestBody LocationDTO locationDTO){
        return locationService.updateLocation(id, locationDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable Integer id){
        locationService.deleteLocation(id);
    }
}
