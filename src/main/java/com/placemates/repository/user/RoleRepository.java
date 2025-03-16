package com.placemates.repository.user;

import com.placemates.dao.user.RoleDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleDAO,Integer> {
}
