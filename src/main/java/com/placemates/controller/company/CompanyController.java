package com.placemates.controller.company;

import com.placemates.response.APIResponse;
import com.placemates.dto.company.CompanyDTO;
import com.placemates.exception.DataAlreadyExistsException;
import com.placemates.exception.DataNotFoundException;
import com.placemates.service.company.CompanyServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyServiceImpl companyService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse> createCompany(@Valid @RequestBody CompanyDTO companyDTO) throws DataAlreadyExistsException {
        List<String> message = List.of("Company created successfully");
        companyService.createCompany(companyDTO);
        APIResponse successResponse = new APIResponse(
                LocalDateTime.now(),
                message
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    @GetMapping("/get")
    public ResponseEntity<List<CompanyDTO>> getCompanies() throws DataNotFoundException {
        List<CompanyDTO> companyDTOList = companyService.getCompanies();
        return ResponseEntity.status(HttpStatus.OK).body(companyDTOList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<APIResponse> updateCompany(@PathVariable Integer id, @Valid @RequestBody CompanyDTO companyDTO) throws DataNotFoundException, DataAlreadyExistsException {
        companyService.updateCompany(id,companyDTO);
        List<String> message = List.of("Company updated successfully");
        APIResponse successResponse = new APIResponse(
                LocalDateTime.now(),
                message
        );
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> deleteCompany(@PathVariable Integer id) throws DataNotFoundException {
        companyService.deleteCompany(id);
        List<String> message = List.of("Company deleted successfully");
        APIResponse successResponse = new APIResponse(
                LocalDateTime.now(),
                message
        );
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }
}
