package com.placemates.controller.company;

import com.placemates.dto.company.CompanyDTO;
import com.placemates.service.company.CompanyService;
import com.placemates.service.placedalum.PlacedAlumService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;
    private final PlacedAlumService placedAlumService;

    public CompanyController(CompanyService companyService, PlacedAlumService placedAlumService) {
        this.companyService = companyService;
        this.placedAlumService = placedAlumService;
    }

    @PostMapping("/create")
    public ResponseEntity<CompanyDTO> createCompany(@Valid @RequestBody CompanyDTO companyDTO){
        CompanyDTO newCompanyDTO = companyService.createCompany(companyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCompanyDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompany(@PathVariable Integer id){
        CompanyDTO companyDTO = companyService.getCompany(id);
        companyDTO.setPlacedAlumDTOs(placedAlumService.getAllPlacedAlumsByCompanyId(id));
        return ResponseEntity.status(HttpStatus.OK).body(companyDTO);
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanies(){
        List<CompanyDTO> companyDTOList = companyService.getAllCompanies();
        return ResponseEntity.status(HttpStatus.OK).body(companyDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Integer id, @Valid @RequestBody CompanyDTO companyDTO){
        CompanyDTO updatedCompany = companyService.updateCompany(id,companyDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCompany);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Integer id){
        companyService.deleteCompany(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
