package com.placemates.service.common;

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

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public BranchDTO createBranch(BranchDTO branchDTO) {
        if(branchRepository.findByName(branchDTO.getName()) != null){
            logger.warn("Branch already exists with name: {}", branchDTO.getName());
            throw new ResourceAlreadyExistsException("Branch already exists with name: " + branchDTO.getName());
        }
        BranchDAO branchDAO = BranchMapper.INSTANCE.fromDTOToDAO(branchDTO);
        branchDAO.setBranchId(null);
        branchDAO = branchRepository.save(branchDAO);
        logger.info("Branch created successfully with id: {}", branchDAO.getBranchId());
        return BranchMapper.INSTANCE.fromDAOToDTO(branchDAO);
    }

    @Override
    public BranchDTO getBranch(Integer id) {
        BranchDAO branchDAO = branchRepository.findById(id).orElseThrow( () -> {
            logger.error("Branch not found with id : {} ",id);
            return new ResourceNotFoundException("Branch not found with id: {} " + id);
        });
        BranchDTO branchDTO = BranchMapper.INSTANCE.fromDAOToDTO(branchDAO);
        logger.info("Branch found with the id: {}", id);
        return branchDTO;
    }

    @Override
    public List<BranchDTO> getAllBranches() {
        List<BranchDAO> branchDAOList = branchRepository.findAll();
        if(branchDAOList.isEmpty()) logger.warn("No branches found !!!");
        else logger.info("{} branches found", branchDAOList.size());
        return BranchMapper.INSTANCE.fromDAOListToDTOList(branchDAOList);
    }

    @Override
    public BranchDTO updateBranch(Integer id, BranchDTO branchDTO) {
        if(!branchRepository.existsById(id)){
            logger.error("Branch not found with id: {}", id);
            throw new ResourceNotFoundException("Branch not found with id :" + id);
        }
        if(branchRepository.findByName(branchDTO.getName()) != null && branchRepository.findByName(branchDTO.getName()).getBranchId() != id){
            logger.warn("Branch already exists with name: {}", branchDTO.getName());
            throw new ResourceAlreadyExistsException("Branch already exists with name: " + branchDTO.getName());
        }
        BranchDAO branchDAO = BranchMapper.INSTANCE.fromDTOToDAO(branchDTO);
        branchDAO.setBranchId(id);
        branchRepository.save(branchDAO);
        logger.info("Branch updated successfully with id : {}", id);
        return BranchMapper.INSTANCE.fromDAOToDTO(branchDAO);
    }

    @Override
    public void deleteBranch(Integer id) {
        if(!branchRepository.existsById(id)){
            logger.error("Branch not found with id: {}", id);
            throw new ResourceNotFoundException("Branch not found with id :" + id);
        }
        branchRepository.deleteById(id);
        logger.info("Branch deleted successfully with id: {}", id);

    }
}
