package com.placemates.repository.user;

import com.placemates.dao.user.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, Integer> {

    boolean existsByMail(String email);
    boolean existsByMobileNumber(String mobileNumber);
}
