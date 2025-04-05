package com.placemates.repository.placedalums;

import com.placemates.dao.placedalums.PlacedAlumsDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacedAlumsRepository extends JpaRepository<PlacedAlumsDAO,Integer> {
    PlacedAlumsDAO findByMail(String email);
    PlacedAlumsDAO findByMobileNumber(String mobileNumber);
}
