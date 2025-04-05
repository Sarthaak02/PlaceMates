package com.placemates.controller.placedalums;

import com.placemates.dto.placedalums.PlacedAlumsDTO;
import com.placemates.service.placedalums.PlacedAlumsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/placed-alums")
public class PlacedAlumsController {

    private final PlacedAlumsServiceImpl placedAlumsServiceImpl;

    public PlacedAlumsController(PlacedAlumsServiceImpl placedAlumsServiceImpl) {
        this.placedAlumsServiceImpl = placedAlumsServiceImpl;
    }

    @PostMapping("/create")
    PlacedAlumsDTO createPlacedAlums(@Valid @RequestBody PlacedAlumsDTO placedAlumsDTO){
        return placedAlumsServiceImpl.createPlacedAlums(placedAlumsDTO);
    }

    @GetMapping("/{id}")
    PlacedAlumsDTO getPlacedAlums(@PathVariable Integer id){
        return placedAlumsServiceImpl.getPlacedAlumsById(id);
    }

    @GetMapping("")
    List<PlacedAlumsDTO> getAllPlacedAlumni(){
        return placedAlumsServiceImpl.getAllPlacedAlumni();
    }

    @PutMapping("/{id}")
    PlacedAlumsDTO updatePlacedAlums(@PathVariable Integer id, @Valid @RequestBody PlacedAlumsDTO placedAlumsDTO){
        return placedAlumsServiceImpl.updatePlacedAlumsById(id,placedAlumsDTO);
    }

    @DeleteMapping("/{id}")
    void deletePlacedAlums(@PathVariable Integer id){
        placedAlumsServiceImpl.deletePlacedAlumsById(id);
    }
}
