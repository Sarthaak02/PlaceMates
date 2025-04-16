package com.placemates.controller.common;

import com.placemates.dto.common.BranchDTO;
import com.placemates.service.common.BranchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BranchDTO> createBranch(@Valid @RequestBody BranchDTO branchDTO){
        BranchDTO newBranchDTO = branchService.createBranch(branchDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBranchDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranch(@PathVariable Integer id){
        BranchDTO branchDTO = branchService.getBranch(id);
        return ResponseEntity.status(HttpStatus.OK).body(branchDTO);
    }

    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAllBranches(){
        List<BranchDTO> branchDTOList = branchService.getAllBranches();
        return ResponseEntity.status(HttpStatus.OK).body(branchDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable Integer id, @Valid @RequestBody BranchDTO branchDTO){
        BranchDTO updatedBranchDTO = branchService.updateBranch(id,branchDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBranchDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Integer id){
        branchService.deleteBranch(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
