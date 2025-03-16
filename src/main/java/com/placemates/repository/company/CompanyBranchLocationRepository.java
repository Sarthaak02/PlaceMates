package com.placemates.repository.company;

import com.placemates.dao.company.CompanyBranchLocationDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyBranchLocationRepository extends JpaRepository<CompanyBranchLocationDAO,Integer> {
}
