package com.placemates.repository.company;

import com.placemates.dao.company.CompanyDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyDAO,Integer> {
    boolean existsByName(String name);
}
