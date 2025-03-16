package com.placemates.repository.common;

import com.placemates.dao.common.ImageDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageDAO,Integer> {
}
