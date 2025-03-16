package com.placemates.repository.blog;

import com.placemates.dao.blog.BlogDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<BlogDAO,Integer> {
}
