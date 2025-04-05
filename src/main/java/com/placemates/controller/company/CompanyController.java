package com.placemates.controller.company;

import com.placemates.dto.company.CompanyDTO;
import com.placemates.service.company.CompanyServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyServiceImpl companyServiceImpl;

    public CompanyController(CompanyServiceImpl companyServiceImpl) {
        this.companyServiceImpl = companyServiceImpl;
    }

    @PostMapping("/create")
    public CompanyDTO createCompany(@Valid @RequestBody CompanyDTO companyDTO){
        return companyServiceImpl.createCompany(companyDTO);
    }

    @GetMapping("/{id}")
    public CompanyDTO getCompany(@PathVariable Integer id){
        return companyServiceImpl.getCompanyById(id);
    }

    @GetMapping("")
    public List<CompanyDTO> getAllCompanies(){
        return companyServiceImpl.getAllCompanies();
    }

    @PutMapping("/{id}")
    public CompanyDTO updateCompany(@PathVariable Integer id, @Valid @RequestBody CompanyDTO companyDTO){
        return companyServiceImpl.updateCompanyById(id,companyDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Integer id){
        companyServiceImpl.deleteCompanyById(id);
    }
}
