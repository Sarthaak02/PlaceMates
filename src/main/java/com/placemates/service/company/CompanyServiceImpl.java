package com.placemates.service.company;

import com.placemates.dao.company.CompanyDAO;
import com.placemates.dao.placedalum.PlacedAlumDAO;
import com.placemates.dto.common.BranchDTO;
import com.placemates.dto.common.LocationDTO;
import com.placemates.dto.company.CompanyDTO;
import com.placemates.exception.ResourceAlreadyExistsException;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.company.CompanyRepository;
import com.placemates.repository.placedalum.PlacedAlumRepository;
import com.placemates.util.mapper.compnay.CompanyMapper;
import com.placemates.util.mapper.placedalum.PlacedAlumMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    private final PlacedAlumRepository placedAlumRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, PlacedAlumRepository placedAlumRepository) {
        this.companyRepository = companyRepository;
        this.placedAlumRepository = placedAlumRepository;
    }

    @Transactional
    @Override
    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        if(companyRepository.findByName(companyDTO.getName()) != null){
            log.warn("Company already exists with name: {}", companyDTO.getName());
            throw new ResourceAlreadyExistsException("Company already exists with name: " + companyDTO.getName());
        }
        CompanyDAO companyDAO = CompanyMapper.INSTANCE.fromDTOToDAO(companyDTO);
        companyDAO.setCompanyId(null);
        companyDAO = companyRepository.save(companyDAO);
        log.info("Company successfully created with id: {}", companyDAO.getCompanyId());

        companyDTO = CompanyMapper.INSTANCE.fromDAOToDTO(companyDAO);

        return companyDTO;
    }

    @Override
    public CompanyDTO getCompany(Integer id) {
        CompanyDAO companyDAO = companyRepository.findById(id).orElseThrow( () -> {
            log.error("Company not found with id: {}", id);
            return new ResourceNotFoundException("Company not found with id:" + id);
        });
        log.info("Company found with id: {}",id);

        CompanyDTO companyDTO = CompanyMapper.INSTANCE.fromDAOToDTO(companyDAO);

        List<PlacedAlumDAO> placedAlumDAOList = placedAlumRepository.findAllByCompanyDAO_CompanyId(id);
        companyDTO.setPlacedAlumDTOs(PlacedAlumMapper.INSTANCE.fromDAOListToDTOList(placedAlumDAOList));

        return companyDTO;
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        List<CompanyDAO> companyDAOList = companyRepository.findAll();

        List<CompanyDTO> companyDTOList = CompanyMapper.INSTANCE.fromDAOListToDTOList(companyDAOList);
        if(companyDAOList.isEmpty()) log.warn("Companies not found !!!");
        else{
            log.info("{} companies found", companyDAOList.size());
        }
        return companyDTOList;
    }

    @Transactional
    @Override
    public CompanyDTO updateCompany(Integer id, CompanyDTO companyDTO) {
        if(!companyRepository.existsById(id)){
            log.error("Company not found with id: {}", id);
            throw new ResourceNotFoundException("Company not found with id:" + id);
        }
        if(companyRepository.findByName(companyDTO.getName()) != null && companyRepository.findByName(companyDTO.getName()).getCompanyId() != id){
            log.warn("Company already exists with name: {}", companyDTO.getName());
            throw new ResourceAlreadyExistsException("Company already exists with name: " + companyDTO.getName());
        }
        CompanyDAO companyDAO = CompanyMapper.INSTANCE.fromDTOToDAO(companyDTO);
        companyDAO.setCompanyId(id);
        companyRepository.save(companyDAO);
        log.info("Company successfully updated with id: {}", id);

        companyDTO = CompanyMapper.INSTANCE.fromDAOToDTO(companyDAO);

        return companyDTO;
    }

    @Override
    public void deleteCompany(Integer id) {
        if(!companyRepository.existsById(id)){
            log.error("Company not found with id: {}", id);
            throw new ResourceNotFoundException("Company not found with id:" + id);
        }
        companyRepository.deleteById(id);
        log.info("Company successfully deleted with id: {}", id);
    }
}
