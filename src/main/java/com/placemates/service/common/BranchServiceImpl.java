package com.placemates.service.common;

import com.placemates.dao.common.BranchDAO;
import com.placemates.dto.common.BranchDTO;
import com.placemates.exception.ResourceAlreadyExistsException;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.common.BranchRepository;
import com.placemates.service.user.UserService;
import com.placemates.util.logger.LoggerUtil;
import com.placemates.util.mapper.common.BranchMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.placemates.constant.AppConstants.*;

@Service
@Slf4j
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final UserService userService;

    public BranchServiceImpl(BranchRepository branchRepository, UserService userService) {
        this.branchRepository = branchRepository;
        this.userService = userService;
    }

    @Override
    public BranchDTO createBranch(BranchDTO branchDTO) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();
        double endTime, duration;

        if(branchRepository.findByName(branchDTO.getName()) != null){
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime) / 1000;
            log.warn(LoggerUtil.buildLog(BRANCH, CREATE, "Branch already exists with name: " + branchDTO.getName(), username, duration, FAIL));
            throw new ResourceAlreadyExistsException("Branch already exists with name: " + branchDTO.getName());
        }

        BranchDAO branchDAO = BranchMapper.INSTANCE.toBranchDAO(branchDTO);
        branchDAO.setBranchId(null);
        branchDAO = branchRepository.save(branchDAO);

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(BRANCH, CREATE, "Id- " + branchDAO.getBranchId(), username, duration, SUCCESS));

        return BranchMapper.INSTANCE.toBranchDTO(branchDAO);
    }

    @Override
    public BranchDTO getBranch(Integer branchId) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();

        BranchDAO branchDAO = branchRepository.findById(branchId).orElseThrow(() -> {
            log.error(LoggerUtil.buildLog(BRANCH, READ, "Branch not found with id- " + branchId, username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
            return new ResourceNotFoundException("Branch not found with id:" + branchId);
        });

        double endTime = System.currentTimeMillis();
        double duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(BRANCH, READ, "Branch found fetched id- " + branchId, username, duration, SUCCESS));

        return BranchMapper.INSTANCE.toBranchDTO(branchDAO);
    }

    @Override
    public List<BranchDTO> getAllBranches() {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();

        List<BranchDAO> branchDAOList = branchRepository.findAll();

        if(branchDAOList.isEmpty()){
            log.error(LoggerUtil.buildLog(BRANCH, READ, "Branches not found", username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
            return new ArrayList<>();
        } else {
            log.info(LoggerUtil.buildLog(BRANCH, READ, branchDAOList.size() + " Branches fetched", username, (System.currentTimeMillis() - startTime) / 1000, SUCCESS));
        }

        return BranchMapper.INSTANCE.toBranchDTOList(branchDAOList);
    }

    @Override
    public BranchDTO updateBranch(Integer branchId, BranchDTO branchDTO) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();
        double endTime, duration;

        if(!branchRepository.existsById(branchId)){
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime) / 1000;
            log.error(LoggerUtil.buildLog(BRANCH, UPDATE, "Branch not found with id- " + branchId, username, duration, FAIL));
            throw new ResourceNotFoundException("Branch not found with id:" + branchId);
        }

        BranchDAO existingByName = branchRepository.findByName(branchDTO.getName());
        if(existingByName != null && existingByName.getBranchId() != branchId){
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime) / 1000;
            log.warn(LoggerUtil.buildLog(BRANCH, UPDATE, "Branch already exists with name: " + branchDTO.getName(), username, duration, FAIL));
            throw new ResourceAlreadyExistsException("Branch already exists with name: " + branchDTO.getName());
        }

        BranchDAO branchDAO = BranchMapper.INSTANCE.toBranchDAO(branchDTO);
        branchDAO.setBranchId(branchId);
        branchDAO = branchRepository.save(branchDAO);

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(BRANCH, UPDATE, "Id- " + branchDAO.getBranchId(), username, duration, SUCCESS));

        return BranchMapper.INSTANCE.toBranchDTO(branchDAO);
    }

    @Override
    public void deleteBranch(Integer branchId) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();
        double endTime, duration;

        if(!branchRepository.existsById(branchId)){
            log.error(LoggerUtil.buildLog(BRANCH, DELETE, "Branch not found with id- " + branchId, username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
            throw new ResourceNotFoundException("Branch not found with id:" + branchId);
        }

        branchRepository.deleteById(branchId);

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(BRANCH, DELETE, "Id- " + branchId, username, duration, SUCCESS));
    }
}
