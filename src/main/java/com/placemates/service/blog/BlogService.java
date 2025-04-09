package com.placemates.service.blog;

import com.placemates.dto.blog.BlogDTO;

import java.util.List;

public interface BlogService {
    BlogDTO createBlog(BlogDTO blogDTO);
    BlogDTO getBlog(Integer id);
    List<BlogDTO> getAllBlogs();
    BlogDTO updateBlog(Integer id, BlogDTO blogDTO);
    void deleteBlog(Integer id);
}
