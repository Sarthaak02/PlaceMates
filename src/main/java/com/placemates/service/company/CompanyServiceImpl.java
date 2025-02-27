package com.placemates.service.company;

import com.placemates.dao.company.CompanyDAO;
import com.placemates.dto.company.CompanyDTO;
import com.placemates.exception.DataAlreadyExistsException;
import com.placemates.exception.DataNotFoundException;
import com.placemates.mapper.company.CompanyMapper;
import com.placemates.repository.company.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void createCompany(CompanyDTO companyDTO) throws DataAlreadyExistsException {
        if(companyRepository.existsByName(companyDTO.getName())){
            throw new DataAlreadyExistsException("Company '" + companyDTO.getName() + "' already exists.");
        }

        CompanyDAO companyDAO = CompanyMapper.mapCompanyDTOToCompanyDAO(companyDTO);
        companyRepository.save(companyDAO);
    }

    @Override
    public List<CompanyDTO> getCompanies() throws DataNotFoundException {
        List<CompanyDAO> companyDAOList = companyRepository.findAll();

        if (companyDAOList.isEmpty()) {
            throw new DataNotFoundException("No alumni found");
        }

        List<CompanyDTO> companyDTOList = new ArrayList<>();
        for (CompanyDAO companyDAO : companyDAOList) {
            companyDTOList.add(CompanyMapper.mapCompanyDAOToCompanyDTO(companyDAO));
        }

        return companyDTOList;
    }

    @Override
    public void updateCompany(Integer id, CompanyDTO companyDTO) throws DataNotFoundException, DataAlreadyExistsException {
        CompanyDAO companyDAO = companyRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Company with ID '" + id + "' not found"));

        String updatedName = companyDTO.getName();
        if (!updatedName.equals(companyDAO.getName())) {
            if (companyRepository.existsByName(updatedName)) {
                throw new DataAlreadyExistsException("Company '" + companyDTO.getName() + "' already exists.");
            }
            companyDAO.setName(updatedName);
        }

        if (companyDTO.getRole() != null) {
            companyDAO.setRole(companyDTO.getRole());
        }

        if (companyDTO.getCtc() != null) {
            companyDAO.setCtc(companyDTO.getCtc());
        }

        if (companyDTO.getJobDescription() != null) {
            companyDAO.setJobDescription(companyDTO.getJobDescription());
        }

        if (companyDTO.getLogoURL() != null) {
            companyDAO.setLogoURL(companyDTO.getLogoURL());
        }

        if (companyDTO.getWebURL() != null) {
            companyDAO.setWebURL(companyDTO.getWebURL());
        }

        if (companyDTO.getAllowBranch() != null) {
            companyDAO.setAllowBranch(companyDTO.getAllowBranch());
        }

        if (companyDTO.getRequireSkills() != null) {
            companyDAO.setRequireSkills(companyDTO.getRequireSkills());
        }

        if (companyDTO.getLocation() != null) {
            companyDAO.setLocation(companyDTO.getLocation());
        }

        companyRepository.save(companyDAO);
    }


    @Override
    public void deleteCompany(Integer id) throws DataNotFoundException {
        companyRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Company with ID '" + id + "' not found"));
        companyRepository.deleteById(id);
    }
}
