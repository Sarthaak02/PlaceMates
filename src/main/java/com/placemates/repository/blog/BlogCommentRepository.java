package com.placemates.repository.blog;

import com.placemates.dao.blog.BlogCommentDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogCommentRepository extends JpaRepository<BlogCommentDAO,Integer> {
    List<BlogCommentDAO> findAllByBlogDAO_BlogId(Integer id);
}
