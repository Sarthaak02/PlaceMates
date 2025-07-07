package com.placemates.service.common;

import com.placemates.dao.common.BranchDAO;
import com.placemates.dto.common.BranchDTO;
import com.placemates.exception.ResourceAlreadyExistsException;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.common.BranchRepository;
import com.placemates.util.mapper.common.BranchMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public  class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public BranchDTO createBranch(BranchDTO branchDTO) {
        if(branchRepository.findByName(branchDTO.getName()) != null){
            log.warn("Branch already exists with name: {}", branchDTO.getName());
            throw new ResourceAlreadyExistsException("Branch already exists with name: " + branchDTO.getName());
        }
        BranchDAO branchDAO = BranchMapper.INSTANCE.toBranchDAO(branchDTO);
        branchDAO.setBranchId(null);
        branchDAO = branchRepository.save(branchDAO);
        log.info("Branch successfully created with id: {}", branchDAO.getBranchId());
        return BranchMapper.INSTANCE.toBranchDTO(branchDAO);
    }

    @Override
    public BranchDTO getBranch(Integer id) {
        BranchDAO branchDAO = branchRepository.findById(id).orElseThrow( () -> {
            log.error("Branch not found with id: {}", id);
            return new ResourceNotFoundException("Branch not found with id:" + id);
        });
        BranchDTO branchDTO = BranchMapper.INSTANCE.toBranchDTO(branchDAO);
        log.info("Branch found with id: {}",id);
        return branchDTO;
    }

    @Override
    public List<BranchDTO> getAllBranches() {
        List<BranchDAO> branchDAOList = branchRepository.findAll();
        if(branchDAOList.isEmpty()) log.warn("Branches not found !!!");
        else log.info("{} branches found", branchDAOList.size());
        return BranchMapper.INSTANCE.toBranchDTOList(branchDAOList);
    }

    @Override
    public BranchDTO updateBranch(Integer id, BranchDTO branchDTO) {
        if(!branchRepository.existsById(id)){
            log.error("Branch not found with id: {}", id);
            throw new ResourceNotFoundException("Branch not found with id:" + id);
        }
        if(branchRepository.findByName(branchDTO.getName()) != null && branchRepository.findByName(branchDTO.getName()).getBranchId() != id){
            log.warn("Branch already exists with name: {}", branchDTO.getName());
            throw new ResourceAlreadyExistsException("Branch already exists with name: " + branchDTO.getName());
        }
        BranchDAO branchDAO = BranchMapper.INSTANCE.toBranchDAO(branchDTO);
        branchDAO.setBranchId(id);
        branchRepository.save(branchDAO);
        log.info("Branch successfully updated with id: {}", branchDAO.getBranchId());
        return BranchMapper.INSTANCE.toBranchDTO(branchDAO);
    }

    @Override
    public void deleteBranch(Integer id) {
        if(!branchRepository.existsById(id)){
            log.error("Branch not found with id: {}", id);
            throw new ResourceNotFoundException("Branch not found with id:" + id);
        }
        branchRepository.deleteById(id);
        log.info("Branch successfully deleted with id: {}", id);
    }
}
