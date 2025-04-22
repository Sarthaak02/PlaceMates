package com.placemates.service.company;

import com.placemates.dao.company.CompanyBranchLocationDAO;
import com.placemates.dao.company.CompanyDAO;
import com.placemates.dto.common.BranchDTO;
import com.placemates.dto.common.LocationDTO;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.company.CompanyBranchLocationRepository;
import com.placemates.repository.company.CompanyRepository;
import com.placemates.util.mapper.common.BranchMapper;
import com.placemates.util.mapper.common.LocationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CompanyBranchLocationServiceImpl implements CompanyBranchLocationService{
    private final CompanyBranchLocationRepository companyBranchLocationRepository;
    private final CompanyRepository companyRepository;

    public CompanyBranchLocationServiceImpl(CompanyBranchLocationRepository companyBranchLocationRepository, CompanyRepository companyRepository) {
        this.companyBranchLocationRepository = companyBranchLocationRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<BranchDTO> saveAllBranchesByCompany(List<BranchDTO> branchDTOList, Integer companyId) {
        CompanyDAO companyDAO = companyRepository.findById(companyId).orElseThrow( () -> {
            log.error("Company not found with id: {}", companyId);
            return new ResourceNotFoundException("Company not found with id:" + companyId);
        });

        for(BranchDTO branchDTO : branchDTOList){
            CompanyBranchLocationDAO companyBranchLocationDAO = new CompanyBranchLocationDAO();
            companyBranchLocationDAO.setCompanyDAO(companyDAO);
            companyBranchLocationDAO.setBranchDAO(BranchMapper.INSTANCE.fromDTOToDAO(branchDTO));
            companyBranchLocationRepository.save(companyBranchLocationDAO);
        }
        log.info("Company created with {} branches", branchDTOList.size());
        return branchDTOList;
    }

    @Override
    public List<LocationDTO> saveAllLocationsByCompany(List<LocationDTO> locationDTOList, Integer companyId) {
        CompanyDAO companyDAO = companyRepository.findById(companyId).orElseThrow( () -> {
            log.error("Company not found with id: {}", companyId);
            return new ResourceNotFoundException("Company not found with id:" + companyId);
        });

        for(LocationDTO locationDTO : locationDTOList){
            CompanyBranchLocationDAO companyBranchLocationDAO = new CompanyBranchLocationDAO();
            companyBranchLocationDAO.setCompanyDAO(companyDAO);
            companyBranchLocationDAO.setLocationDAO(LocationMapper.INSTANCE.fromDTOToDAO(locationDTO));
            companyBranchLocationRepository.save(companyBranchLocationDAO);
        }
        log.info("Company created with {} locations", locationDTOList.size());
        return locationDTOList;
    }

    @Override
    public List<BranchDTO> getAllBranchesByCompany(Integer id) {
        List<CompanyBranchLocationDAO> companyBranchLocationDAOList = companyBranchLocationRepository.findAllByCompanyDAO_CompanyId(id);
        List<BranchDTO> branchDTOList = new ArrayList<>();
        if(!companyBranchLocationDAOList.isEmpty()){
            for(CompanyBranchLocationDAO companyBranchLocationDAO : companyBranchLocationDAOList){
                if(companyBranchLocationDAO.getBranchDAO() != null) branchDTOList.add(BranchMapper.INSTANCE.fromDAOToDTO(companyBranchLocationDAO.getBranchDAO()));
            }
        }
        log.info("Company found with {} branches", branchDTOList.size());
        return branchDTOList;
    }

    @Override
    public List<LocationDTO> getAllLocationsByCompany(Integer id) {
        List<CompanyBranchLocationDAO> companyBranchLocationDAOList = companyBranchLocationRepository.findAllByCompanyDAO_CompanyId(id);
        List<LocationDTO> locationDTOList = new ArrayList<>();
        if(!companyBranchLocationDAOList.isEmpty()){
            for(CompanyBranchLocationDAO companyBranchLocationDAO : companyBranchLocationDAOList){
                if(companyBranchLocationDAO.getLocationDAO() != null) locationDTOList.add(LocationMapper.INSTANCE.fromDAOToDTO(companyBranchLocationDAO.getLocationDAO()));
            }
        }
        log.info("Company found with {} locations", locationDTOList.size());
        return locationDTOList;
    }


    @Override
    public void deleteAllByCompany(Integer id) {
        companyBranchLocationRepository.deleteAllByCompanyDAO_CompanyId(id);
    }
}
