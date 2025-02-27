package com.placemates.controller.alumni;

import com.placemates.response.APIResponse;
import com.placemates.dto.alumni.AlumniDTO;
import com.placemates.exception.DataAlreadyExistsException;
import com.placemates.exception.DataNotFoundException;
import com.placemates.service.alumni.AlumniServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/alumni")
public class AlumniController {

    @Autowired
    private AlumniServiceImpl alumniService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse> createAlumni(@Valid @RequestBody AlumniDTO alumniDTO) throws DataAlreadyExistsException {
        alumniService.createAlumni(alumniDTO);
        List<String> message = List.of("Alumni created successfully");
        APIResponse successResponse = new APIResponse(
                LocalDateTime.now(),
                message
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    @GetMapping("/get")
    public ResponseEntity<List<AlumniDTO>> getAlumni() throws DataNotFoundException {
        List<AlumniDTO> alumniDTOList = alumniService.getAlumni();
        return ResponseEntity.status(HttpStatus.OK).body(alumniDTOList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<APIResponse> updateAlumni(@PathVariable Integer id, @Valid @RequestBody AlumniDTO alumniDTO) throws DataNotFoundException, DataAlreadyExistsException {
        alumniService.updateAlumni(id,alumniDTO);
        List<String> message = List.of("Alumni updated successfully");
        APIResponse successResponse = new APIResponse(
                LocalDateTime.now(),
                message
        );
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> deleteAlumni(@PathVariable Integer id) throws DataNotFoundException {
        alumniService.deleteAlumni(id);
        List<String> message = List.of("Alumni deleted successfully");
        APIResponse successResponse = new APIResponse(
                LocalDateTime.now(),
                message
        );
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }
}
