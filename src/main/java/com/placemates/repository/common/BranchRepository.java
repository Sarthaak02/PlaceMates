package com.placemates.repository.common;

import com.placemates.dao.common.BranchDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<BranchDAO,Integer> {
    BranchDAO findByName(String name);
}
