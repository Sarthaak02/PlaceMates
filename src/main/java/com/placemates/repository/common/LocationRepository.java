package com.placemates.repository.common;

import com.placemates.dao.common.LocationDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationDAO,Integer> {
    LocationDAO findByCity(String city);
}
