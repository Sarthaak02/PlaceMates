package com.placemates.controller.common;

import com.placemates.dto.common.BranchDTO;
import com.placemates.service.common.BranchService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branch")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping("/create")
    public BranchDTO createBranch(@Valid @RequestBody BranchDTO branchDTO){
        return branchService.createBranch(branchDTO);
    }

    @GetMapping("/{id}")
    public BranchDTO getBranch(@PathVariable Integer id){
        return branchService.getBranch(id);
    }

    @GetMapping("")
    public List<BranchDTO> getAllBranches(){
        return branchService.getAllBranches();
    }

    @PutMapping("/{id}")
    public BranchDTO updateBranch(@PathVariable Integer id, @Valid @RequestBody BranchDTO branchDTO){
        return branchService.updateBranch(id,branchDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBranch(@PathVariable Integer id){
        branchService.deleteBranch(id);
    }
}
