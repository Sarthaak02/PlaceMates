package com.placemates.service.blog;

import com.placemates.dto.blog.BlogCommentDTO;

import java.util.List;

public interface BlogCommentService {
    BlogCommentDTO createCommentByUserAndBlog(Integer userId, Integer blogId, String content);
    List<BlogCommentDTO> getAllCommentsByBlog(Integer id);
    void deleteComment(Integer id);
}
