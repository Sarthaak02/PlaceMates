package com.placemates.repository.user;

import com.placemates.dao.user.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDAO,Integer> {
    boolean existsByEmail(String email);
}
