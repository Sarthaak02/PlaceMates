package com.placemates.repository.alumni;

import com.placemates.dao.alumni.AlumniDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumniRepository extends JpaRepository<AlumniDAO,Integer> {
    boolean existsByEmail(String email);
}
