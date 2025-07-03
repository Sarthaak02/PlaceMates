package com.placemates.repository.blog;

import com.placemates.dao.blog.BlogLikeDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogLikeRepository extends JpaRepository<BlogLikeDAO,Integer> {
    List<BlogLikeDAO> findAllByBlogDAO_BlogId(Integer id);
    void deleteByLikedByDAO_UserIdAndBlogDAO_BlogId(Integer userID, Integer blogID);
    boolean existsByLikedByDAO_UserIdAndBlogDAO_BlogId(Integer userID, Integer blogID);
}
