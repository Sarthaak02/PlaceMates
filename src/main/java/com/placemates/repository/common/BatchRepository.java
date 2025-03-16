package com.placemates.repository.common;

import com.placemates.dao.common.BatchDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<BatchDAO,Integer> {
}
