package com.placemates.controller.blog;

import com.placemates.dto.blog.BlogCommentDTO;
import com.placemates.dto.blog.BlogDTO;
import com.placemates.dto.blog.BlogLikeDTO;
import com.placemates.service.blog.BlogCommentService;
import com.placemates.service.blog.BlogLikeService;
import com.placemates.service.blog.BlogService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;
    private final BlogLikeService blogLikeService;
    private final BlogCommentService blogCommentService;

    public BlogController(BlogService blogService, BlogLikeService blogLikeService, BlogCommentService blogCommentService) {
        this.blogService = blogService;
        this.blogLikeService = blogLikeService;
        this.blogCommentService = blogCommentService;
    }

    @PostMapping("/create")
    public BlogDTO createBlog(@Valid @RequestBody BlogDTO blogDTO){
        return blogService.createBlog(blogDTO);
    }

    @GetMapping("/{id}")
    public BlogDTO getBlog(@PathVariable Integer id){
        return blogService.getBlog(id);
    }

    @GetMapping("")
    public List<BlogDTO> getAllBlogs(){
        return blogService.getAllBlogs();
    }

    @PutMapping("/{id}")
    public BlogDTO updateBlog(@PathVariable Integer id,@Valid @RequestBody BlogDTO blogDTO){
        return blogService.updateBlog(id,blogDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBlog(@PathVariable Integer id){
        blogService.deleteBlog(id);
    }

    @PostMapping("like/user/{userId}/blog/{blogId}")
    public BlogLikeDTO likeBlog(@PathVariable Integer userId, @PathVariable Integer blogId){
        return blogLikeService.createLikeByUserAndBlog(userId, blogId);
    }

    @DeleteMapping("like/user/{userId}/blog/{blogId}")
    public void unlikeBlog(@PathVariable Integer userId, @PathVariable Integer blogId){
        blogLikeService.deleteLikeByUserAndBlog(userId,blogId);
    }

    @PostMapping("comment/user/{userId}/blog/{blogId}")
    public BlogCommentDTO addCommentOnBlog(@PathVariable Integer userId, @PathVariable Integer blogId, @Valid @RequestBody String content){
        return blogCommentService.createCommentByUserAndBlog(userId, blogId, content);
    }

    @DeleteMapping("comment/{id}")
    public void deleteCommentOfBlog(@PathVariable Integer id){
       blogCommentService.deleteComment(id);
    }
}
