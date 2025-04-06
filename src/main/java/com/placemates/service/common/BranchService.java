package com.placemates.service.common;

import com.placemates.dto.common.BranchDTO;

import java.util.List;

public interface BranchService {
    BranchDTO createBranch(BranchDTO branchDTO);
    BranchDTO getBranch(Integer id);
    List<BranchDTO> getAllBranches();
    BranchDTO updateBranch(Integer id, BranchDTO branchDTO);
    void deleteBranch(Integer id);
}
