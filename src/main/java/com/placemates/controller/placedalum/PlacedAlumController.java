package com.placemates.controller.placedalum;

import com.placemates.dto.placedalum.PlacedAlumDTO;
import com.placemates.service.placedalum.PlacedAlumService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/placed-alum")
public class PlacedAlumController {

    private final PlacedAlumService placedAlumService;

    public PlacedAlumController(PlacedAlumService placedAlumService) {
        this.placedAlumService = placedAlumService;
    }

    @PostMapping("/create")
    PlacedAlumDTO createPlacedAlum(@Valid @RequestBody PlacedAlumDTO placedAlumDTO){
        return placedAlumService.createPlacedAlum(placedAlumDTO);
    }

    @GetMapping("/{id}")
    PlacedAlumDTO getPlacedAlum(@PathVariable Integer id){
        return placedAlumService.getPlacedAlum(id);
    }

    @GetMapping("")
    List<PlacedAlumDTO> getAllPlacedAlums(){
        return placedAlumService.getAllPlacedAlums();
    }

    @PutMapping("/{id}")
    PlacedAlumDTO updatePlacedAlum(@PathVariable Integer id, @Valid @RequestBody PlacedAlumDTO placedAlumDTO){
        return placedAlumService.updatePlacedAlum(id, placedAlumDTO);
    }

    @DeleteMapping("/{id}")
    void deletePlacedAlum(@PathVariable Integer id){
        placedAlumService.deletePlacedAlum(id);
    }
}
