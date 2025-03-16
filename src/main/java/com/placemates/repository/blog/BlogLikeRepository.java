package com.placemates.repository.blog;

import com.placemates.dao.blog.BlogLikeDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogLikeRepository extends JpaRepository<BlogLikeDAO,Integer> {
}
