package com.placemates.controller.placedalum;

import com.placemates.dto.placedalum.PlacedAlumDTO;
import com.placemates.service.placedalum.PlacedAlumService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PlacedAlumDTO> createPlacedAlum(@Valid @RequestBody PlacedAlumDTO placedAlumDTO){
        PlacedAlumDTO newPlacedAlumDTO = placedAlumService.createPlacedAlum(placedAlumDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPlacedAlumDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlacedAlumDTO> getPlacedAlum(@PathVariable Integer id){
        PlacedAlumDTO placedAlumDTO = placedAlumService.getPlacedAlum(id);
        return ResponseEntity.status(HttpStatus.OK).body(placedAlumDTO);
    }

    @GetMapping
    public ResponseEntity<List<PlacedAlumDTO>> getAllPlacedAlums(){
        List<PlacedAlumDTO> placedAlumDTOList = placedAlumService.getAllPlacedAlums();
        return ResponseEntity.status(HttpStatus.OK).body(placedAlumDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlacedAlumDTO> updatePlacedAlum(@PathVariable Integer id, @Valid @RequestBody PlacedAlumDTO placedAlumDTO){
        PlacedAlumDTO updatedPlacedAlumDTO = placedAlumService.updatePlacedAlum(id, placedAlumDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPlacedAlumDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlacedAlum(@PathVariable Integer id){
        placedAlumService.deletePlacedAlum(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
