package com.placemates.service.company;

import com.placemates.dao.company.CompanyDAO;
import com.placemates.dto.common.BranchDTO;
import com.placemates.dto.common.LocationDTO;
import com.placemates.dto.company.CompanyDTO;
import com.placemates.exception.ResourceAlreadyExistsException;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.company.CompanyRepository;
import com.placemates.util.mapper.compnay.CompanyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{
    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);
    private final CompanyRepository companyRepository;
    private final CompanyBranchLocationServiceImpl companyBranchLocationServiceImpl;

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyBranchLocationServiceImpl companyBranchLocationServiceImpl) {
        this.companyRepository = companyRepository;
        this.companyBranchLocationServiceImpl = companyBranchLocationServiceImpl;
    }

    @Override
    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        if(companyRepository.findByName(companyDTO.getName()) != null){
            logger.warn("Company already exists with name: {}", companyDTO.getName());
            throw new ResourceAlreadyExistsException("Company already exists with name: " + companyDTO.getName());
        }
        CompanyDAO companyDAO = CompanyMapper.INSTANCE.fromDTOToDAO(companyDTO);
//        if passed from api/ui so set null
        companyDAO.setCompanyId(null);

        companyDAO = companyRepository.save(companyDAO);
        logger.info("Company created successfully with id: {}", companyDAO.getCompanyId());

        List<BranchDTO> branchDTOList = new ArrayList<>();
        if (companyDTO.getBranchDTOList() != null && !companyDTO.getBranchDTOList().isEmpty()) {
            branchDTOList = companyBranchLocationServiceImpl.saveBranchList(companyDTO.getBranchDTOList(), companyDAO);
        }

        List<LocationDTO> locationDTOList = new ArrayList<>();
        if (companyDTO.getLocationDTOList() != null && !companyDTO.getLocationDTOList().isEmpty()) {
            locationDTOList = companyBranchLocationServiceImpl.saveLocationList(companyDTO.getLocationDTOList(), companyDAO);
        }

        companyDTO = CompanyMapper.INSTANCE.fromDAOToDTO(companyDAO);
        companyDTO.setBranchDTOList(branchDTOList);
        companyDTO.setLocationDTOList(locationDTOList);

        return companyDTO;
    }

    @Override
    public CompanyDTO getCompanyById(Integer id) {
        CompanyDAO companyDAO = companyRepository.findById(id).orElseThrow( () -> {
            logger.error("Company not found with id: {}", id);
            return new ResourceNotFoundException("Company not found with id: " + id);
        });
        logger.info("Company found with id: {}", id);

        CompanyDTO companyDTO = CompanyMapper.INSTANCE.fromDAOToDTO(companyDAO);

        List<BranchDTO> branchDTOList = companyBranchLocationServiceImpl.getBranchList(id);
        companyDTO.setBranchDTOList(branchDTOList);

        List<LocationDTO> locationDTOList = companyBranchLocationServiceImpl.getLocationList(id);
        companyDTO.setLocationDTOList(locationDTOList);

        return companyDTO;
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        List<CompanyDAO> companyDAOList = companyRepository.findAll();

        List<CompanyDTO> companyDTOList = CompanyMapper.INSTANCE.fromDAOListToDTOList(companyDAOList);
        if(companyDAOList.isEmpty()) logger.warn("Companies not found");
        else{
            logger.info("{} companies found", companyDAOList.size());
            for(CompanyDTO companyDTO : companyDTOList){
                List<BranchDTO> branchDTOList = companyBranchLocationServiceImpl.getBranchList(companyDTO.getCompanyId());
                companyDTO.setBranchDTOList(branchDTOList);
                List<LocationDTO> locationDTOList = companyBranchLocationServiceImpl.getLocationList(companyDTO.getCompanyId());
                companyDTO.setLocationDTOList(locationDTOList);
            }
        }
        return companyDTOList;
    }

    @Transactional
    @Override
    public CompanyDTO updateCompanyById(Integer id, CompanyDTO companyDTO) {
        if(!companyRepository.existsById(id)){
            logger.error("Company not found with id: {}", id);
            throw new ResourceNotFoundException("Company not found with id: " + id);
        }
        if(companyRepository.findByName(companyDTO.getName()) != null && companyRepository.findByName(companyDTO.getName()).getCompanyId() != id){
            logger.warn("Company already exists with name: {}", companyDTO.getName());
            throw new ResourceAlreadyExistsException("Company already exists with name: " + companyDTO.getName());
        }
        CompanyDAO companyDAO = CompanyMapper.INSTANCE.fromDTOToDAO(companyDTO);
        companyDAO.setCompanyId(id);
        companyRepository.save(companyDAO);
        logger.info("Company updated successfully with id: {}", id);

        companyBranchLocationServiceImpl.deleteAllByCompany(id);

        List<BranchDTO> branchDTOList = new ArrayList<>();
        if (companyDTO.getBranchDTOList() != null && !companyDTO.getBranchDTOList().isEmpty()) {
            branchDTOList = companyBranchLocationServiceImpl.saveBranchList(companyDTO.getBranchDTOList(), companyDAO);
        }

        List<LocationDTO> locationDTOList = new ArrayList<>();
        if (companyDTO.getLocationDTOList() != null && !companyDTO.getLocationDTOList().isEmpty()) {
            locationDTOList = companyBranchLocationServiceImpl.saveLocationList(companyDTO.getLocationDTOList(), companyDAO);
        }

        companyDTO = CompanyMapper.INSTANCE.fromDAOToDTO(companyDAO);
        companyDTO.setBranchDTOList(branchDTOList);
        companyDTO.setLocationDTOList(locationDTOList);

        return companyDTO;
    }

    @Override
    public void deleteCompanyById(Integer id) {
        if(!companyRepository.existsById(id)){
            logger.error("Company not found with id: {}", id);
            throw new ResourceNotFoundException("Company not found with id: " + id);
        }
        companyRepository.deleteById(id);
        logger.info("Company deleted successfully with id: {}", id);

        companyBranchLocationServiceImpl.deleteAllByCompany(id);
        logger.info("Company's branches and locations deleted successfully with id: {}", id);
    }
}
