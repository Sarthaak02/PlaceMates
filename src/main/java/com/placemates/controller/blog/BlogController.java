package com.placemates.controller.blog;

import com.placemates.dto.blog.BlogCommentDTO;
import com.placemates.dto.blog.BlogDTO;
import com.placemates.dto.blog.BlogLikeDTO;
import com.placemates.service.blog.BlogCommentService;
import com.placemates.service.blog.BlogLikeService;
import com.placemates.service.blog.BlogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BlogDTO> createBlog(@Valid @RequestBody BlogDTO blogDTO){
        BlogDTO newBlogDTO = blogService.createBlog(blogDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBlogDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDTO> getBlog(@PathVariable Integer id){
        BlogDTO blogDTO = blogService.getBlog(id);
        return ResponseEntity.status(HttpStatus.OK).body(blogDTO);
    }

    @GetMapping
    public ResponseEntity<List<BlogDTO>> getAllBlogs(){
        List<BlogDTO> blogDTOList = blogService.getAllBlogs();
        return ResponseEntity.status(HttpStatus.OK).body(blogDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogDTO> updateBlog(@PathVariable Integer id,@Valid @RequestBody BlogDTO blogDTO){
        BlogDTO updatedBlog = blogService.updateBlog(id,blogDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBlog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Integer id){
        blogService.deleteBlog(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("like/user/{userId}/blog/{blogId}")
    public ResponseEntity<BlogLikeDTO> likeBlog(@PathVariable Integer userId, @PathVariable Integer blogId){
        BlogLikeDTO newBlogLikeDTO = blogLikeService.createLike(blogId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBlogLikeDTO);
    }

    @DeleteMapping("like/user/{userId}/blog/{blogId}")
    public ResponseEntity<Void> unlikeBlog(@PathVariable Integer userId, @PathVariable Integer blogId){
        blogLikeService.deleteLike(blogId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("comment/user/{userId}/blog/{blogId}")
    public ResponseEntity<BlogCommentDTO> addCommentOnBlog(@PathVariable Integer userId, @PathVariable Integer blogId, @Valid @RequestBody String content){
//        Hndle that commetn cannot be empty chekck for is it empty or not and also ids can be added in the model so pass model class in body
        BlogCommentDTO newBlogCommentDTO = blogCommentService.createComment(blogId, content);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBlogCommentDTO);
    }

    @DeleteMapping("comment/{id}")
    public ResponseEntity<Void> deleteCommentOfBlog(@PathVariable Integer id){
       blogCommentService.deleteComment(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
