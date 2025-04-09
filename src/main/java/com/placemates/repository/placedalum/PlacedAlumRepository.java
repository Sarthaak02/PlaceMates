package com.placemates.repository.placedalum;

import com.placemates.dao.placedalum.PlacedAlumDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlacedAlumRepository extends JpaRepository<PlacedAlumDAO,Integer> {
    PlacedAlumDAO findByMail(String email);
    PlacedAlumDAO findByMobileNumber(String mobileNumber);
    List<PlacedAlumDAO> findAllByCompanyDAO_CompanyId(Integer id);
}
