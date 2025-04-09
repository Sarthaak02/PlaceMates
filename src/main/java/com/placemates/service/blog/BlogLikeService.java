package com.placemates.service.blog;

import com.placemates.dto.blog.BlogLikeDTO;

import java.util.List;

public interface BlogLikeService {
    BlogLikeDTO createLikeByUserAndBlog(Integer userId, Integer blogId);
    List<BlogLikeDTO> getAllLikesByBlog(Integer id);
    void deleteLikeByUserAndBlog(Integer userID, Integer blogId);
}
