package com.placemates.service.blog;

import com.placemates.dto.blog.BlogCommentDTO;

import java.util.List;

public interface BlogCommentService {
    BlogCommentDTO createComment(Integer blogId, String comment);
    List<BlogCommentDTO> getAllCommentsByBlog(Integer blogId);
    void deleteComment(Integer blogId);
}
