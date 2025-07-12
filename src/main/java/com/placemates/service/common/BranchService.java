package com.placemates.service.common;

import com.placemates.dto.common.BranchDTO;

import java.util.List;

public interface BranchService {
    BranchDTO createBranch(BranchDTO branchDTO);
    BranchDTO getBranch(Integer branchId);
    List<BranchDTO> getAllBranches();
    BranchDTO updateBranch(Integer branchId, BranchDTO branchDTO);
    void deleteBranch(Integer branchId);
}
