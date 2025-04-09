package com.placemates.service.common;

import com.placemates.constant.AppConstants;
import com.placemates.dao.common.BranchDAO;
import com.placemates.dto.common.BranchDTO;
import com.placemates.exception.ResourceAlreadyExistsException;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.common.BranchRepository;
import com.placemates.util.mapper.common.BranchMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class BranchServiceImpl implements BranchService {

    private static final Logger logger = LoggerFactory.getLogger(BranchServiceImpl.class);
    private final BranchRepository branchRepository;
    private static final String BRANCH = "Branch";

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public BranchDTO createBranch(BranchDTO branchDTO) {
        if(branchRepository.findByName(branchDTO.getName()) != null){
            logger.warn(BRANCH + AppConstants.ALREADY_EXISTS + "name: {}", branchDTO.getName());
            throw new ResourceAlreadyExistsException(BRANCH + AppConstants.ALREADY_EXISTS + "name: " + branchDTO.getName());
        }
        BranchDAO branchDAO = BranchMapper.INSTANCE.fromDTOToDAO(branchDTO);
        branchDAO.setBranchId(null);
        branchDAO = branchRepository.save(branchDAO);
        logger.info(BRANCH + AppConstants.CREATED + "{}", branchDAO.getBranchId());
        return BranchMapper.INSTANCE.fromDAOToDTO(branchDAO);
    }

    @Override
    public BranchDTO getBranch(Integer id) {
        BranchDAO branchDAO = branchRepository.findById(id).orElseThrow( () -> {
            logger.error(BRANCH + AppConstants.NOT_FOUND + "{} ",id);
            return new ResourceNotFoundException(BRANCH + AppConstants.NOT_FOUND + id);
        });
        BranchDTO branchDTO = BranchMapper.INSTANCE.fromDAOToDTO(branchDAO);
        logger.info(BRANCH + AppConstants.FOUND + "{}", id);
        return branchDTO;
    }

    @Override
    public List<BranchDTO> getAllBranches() {
        List<BranchDAO> branchDAOList = branchRepository.findAll();
        if(branchDAOList.isEmpty()) logger.warn("Branches" + AppConstants.NO_RECORDS_FOUND);
        else logger.info("{} branches found", branchDAOList.size());
        return BranchMapper.INSTANCE.fromDAOListToDTOList(branchDAOList);
    }

    @Override
    public BranchDTO updateBranch(Integer id, BranchDTO branchDTO) {
        if(!branchRepository.existsById(id)){
            logger.error(BRANCH + AppConstants.NOT_FOUND + "{} ",id);
            throw new ResourceNotFoundException(BRANCH + AppConstants.NOT_FOUND + id);
        }
        if(branchRepository.findByName(branchDTO.getName()) != null && branchRepository.findByName(branchDTO.getName()).getBranchId() != id){
            logger.warn(BRANCH + AppConstants.ALREADY_EXISTS + "name: {}", branchDTO.getName());
            throw new ResourceAlreadyExistsException(BRANCH + AppConstants.ALREADY_EXISTS + "name: " + branchDTO.getName());
        }
        BranchDAO branchDAO = BranchMapper.INSTANCE.fromDTOToDAO(branchDTO);
        branchDAO.setBranchId(id);
        branchRepository.save(branchDAO);
        logger.info(BRANCH + AppConstants.UPDATED + "{}", branchDAO.getBranchId());
        return BranchMapper.INSTANCE.fromDAOToDTO(branchDAO);
    }

    @Override
    public void deleteBranch(Integer id) {
        if(!branchRepository.existsById(id)){
            logger.error(BRANCH + AppConstants.NOT_FOUND + "{} ",id);
            throw new ResourceNotFoundException(BRANCH + AppConstants.NOT_FOUND + id);
        }
        branchRepository.deleteById(id);
        logger.info(BRANCH + AppConstants.DELETED + "{}", id);
    }
}
