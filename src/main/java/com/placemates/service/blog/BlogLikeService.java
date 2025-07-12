package com.placemates.service.blog;

import com.placemates.dto.blog.BlogLikeDTO;

import java.util.List;

public interface BlogLikeService {
    BlogLikeDTO createLike(Integer blogId);
    List<BlogLikeDTO> getAllLikesByBlog(Integer blogId);
    void deleteLike(Integer blogId);
}
