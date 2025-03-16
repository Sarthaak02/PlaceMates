package com.placemates.repository.user;

import com.placemates.dao.user.FeedbackDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackDAO,Integer> {
}
