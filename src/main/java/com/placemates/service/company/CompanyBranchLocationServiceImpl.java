package com.placemates.service.company;

import com.placemates.dao.company.CompanyBranchLocationDAO;
import com.placemates.dao.company.CompanyDAO;
import com.placemates.dto.common.BranchDTO;
import com.placemates.dto.common.LocationDTO;
import com.placemates.repository.company.CompanyBranchLocationRepository;
import com.placemates.util.mapper.common.BranchMapper;
import com.placemates.util.mapper.common.LocationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyBranchLocationServiceImpl implements CompanyBranchLocationService{
    private static final Logger logger = LoggerFactory.getLogger(CompanyBranchLocationServiceImpl.class);
    private final CompanyBranchLocationRepository companyBranchLocationRepository;

    public CompanyBranchLocationServiceImpl(CompanyBranchLocationRepository companyBranchLocationRepository) {
        this.companyBranchLocationRepository = companyBranchLocationRepository;
    }

    @Override
    public List<BranchDTO> saveBranchList(List<BranchDTO> branchDTOList, CompanyDAO companyDAO) {
        for(BranchDTO branchDTO : branchDTOList){
            CompanyBranchLocationDAO companyBranchLocationDAO = new CompanyBranchLocationDAO();
            companyBranchLocationDAO.setCompanyDAO(companyDAO);
            companyBranchLocationDAO.setBranchDAO(BranchMapper.INSTANCE.fromDTOToDAO(branchDTO));
            companyBranchLocationRepository.save(companyBranchLocationDAO);
        }
        logger.info("Company created successfully with branches: {}", branchDTOList.size());
        return branchDTOList;
    }

    @Override
    public List<LocationDTO> saveLocationList(List<LocationDTO> locationDTOList, CompanyDAO companyDAO) {
        for(LocationDTO locationDTO : locationDTOList){
            CompanyBranchLocationDAO companyBranchLocationDAO = new CompanyBranchLocationDAO();
            companyBranchLocationDAO.setCompanyDAO(companyDAO);
            companyBranchLocationDAO.setLocationDAO(LocationMapper.INSTANCE.fromDTOToDAO(locationDTO));
            companyBranchLocationRepository.save(companyBranchLocationDAO);
        }
        logger.info("Company created successfully with locations: {}", locationDTOList.size());
        return locationDTOList;
    }

    @Override
    public List<BranchDTO> getBranchList(Integer id) {
        List<CompanyBranchLocationDAO> companyBranchLocationDAOList = companyBranchLocationRepository.findAllByCompanyDAO_CompanyId(id);
        List<BranchDTO> branchDTOList = new ArrayList<>();
        if(!companyBranchLocationDAOList.isEmpty()){
            for(CompanyBranchLocationDAO companyBranchLocationDAO : companyBranchLocationDAOList){
                if(companyBranchLocationDAO.getBranchDAO() != null) branchDTOList.add(BranchMapper.INSTANCE.fromDAOToDTO(companyBranchLocationDAO.getBranchDAO()));
            }
        }
        logger.info("Company found with branches: {}", branchDTOList.size());
        return branchDTOList;
    }

    @Override
    public List<LocationDTO> getLocationList(Integer id) {
        List<CompanyBranchLocationDAO> companyBranchLocationDAOList = companyBranchLocationRepository.findAllByCompanyDAO_CompanyId(id);
        List<LocationDTO> locationDTOList = new ArrayList<>();
        if(!companyBranchLocationDAOList.isEmpty()){
            for(CompanyBranchLocationDAO companyBranchLocationDAO : companyBranchLocationDAOList){
                if(companyBranchLocationDAO.getLocationDAO() != null) locationDTOList.add(LocationMapper.INSTANCE.fromDAOToDTO(companyBranchLocationDAO.getLocationDAO()));
            }
        }
        logger.info("Company found with locations: {}", locationDTOList.size());
        return locationDTOList;
    }

    @Override
    public void deleteAllByCompany(Integer id) {
        companyBranchLocationRepository.deleteAllByCompanyDAO_CompanyId(id);
    }
}
