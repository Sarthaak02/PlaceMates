package com.placemates.controller.placedalums;

import com.placemates.dto.placedalums.PlacedAlumsDTO;
import com.placemates.service.placedalums.PlacedAlumsService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/placed-alum")
public class PlacedAlumsController {

    private final PlacedAlumsService placedAlumsService;

    public PlacedAlumsController(PlacedAlumsService placedAlumsService) {
        this.placedAlumsService = placedAlumsService;
    }

    @PostMapping("/create")
    PlacedAlumsDTO createPlacedAlums(@Valid @RequestBody PlacedAlumsDTO placedAlumsDTO){
        return placedAlumsService.createPlacedAlums(placedAlumsDTO);
    }

    @GetMapping("/{id}")
    PlacedAlumsDTO getPlacedAlums(@PathVariable Integer id){
        return placedAlumsService.getPlacedAlumsById(id);
    }

    @GetMapping("")
    List<PlacedAlumsDTO> getAllPlacedAlumni(){
        return placedAlumsService.getAllPlacedAlumni();
    }

    @PutMapping("/{id}")
    PlacedAlumsDTO updatePlacedAlums(@PathVariable Integer id, @Valid @RequestBody PlacedAlumsDTO placedAlumsDTO){
        return placedAlumsService.updatePlacedAlumsById(id,placedAlumsDTO);
    }

    @DeleteMapping("/{id}")
    void deletePlacedAlums(@PathVariable Integer id){
        placedAlumsService.deletePlacedAlumsById(id);
    }
}
