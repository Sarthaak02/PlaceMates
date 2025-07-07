package com.placemates.repository.user;

import com.placemates.dao.user.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, Integer> {
    UserDAO findByMail(String email);
    UserDAO findByMobileNumber(String mobileNumber);

    @Query("SELECT u.mail FROM UserDAO u WHERE u.mobileNumber = :mobileNumber")
    String findEmailByMobileNumber(@Param("mobileNumber") String mobileNumber);

    @Query("SELECT u FROM UserDAO u WHERE u.mail = :username OR u.mobileNumber = :username")
    UserDAO findByUsername(@Param("username") String username);
}
