package com.placemates.repository.company;

import com.placemates.dao.company.CompanyBranchLocationDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyBranchLocationRepository extends JpaRepository<CompanyBranchLocationDAO,Integer> {
    List<CompanyBranchLocationDAO> findAllByCompanyDAO_CompanyId(Integer id);
    void deleteAllByCompanyDAO_CompanyId(Integer id);
}
