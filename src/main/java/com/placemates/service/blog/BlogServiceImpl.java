package com.placemates.service.blog;

import com.placemates.dao.blog.BlogDAO;
import com.placemates.dto.blog.BlogCommentDTO;
import com.placemates.dto.blog.BlogDTO;
import com.placemates.dto.blog.BlogLikeDTO;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.blog.BlogRepository;
import com.placemates.util.mapper.blog.BlogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final BlogLikeService blogLikeService;
    private final BlogCommentService blogCommentService;


    public BlogServiceImpl(BlogRepository blogRepository, BlogLikeService blogLikeService, BlogCommentService blogCommentService) {
        this.blogRepository = blogRepository;
        this.blogLikeService = blogLikeService;
        this.blogCommentService = blogCommentService;
    }

    @Override
    public BlogDTO createBlog(BlogDTO blogDTO) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        blogDTO.setCreatedAt(currentDateTime);
        blogDTO.setUpdatedAt(currentDateTime);

        BlogDAO blogDAO = BlogMapper.INSTANCE.fromDTOToDAO(blogDTO);
        blogDAO.setBlogId(null);
        blogDAO = blogRepository.save(blogDAO);
        log.info("Blog successfully created with id: {}", blogDAO.getBlogId());

        return BlogMapper.INSTANCE.fromDAOToDTO(blogDAO);
    }

    @Override
    public BlogDTO getBlog(Integer id) {
        BlogDAO blogDAO = blogRepository.findById(id).orElseThrow( () -> {
            log.error("Blog not found with id: {}", id);
            return new ResourceNotFoundException("Blog not found with id:" + id);
        });

        BlogDTO blogDTO = BlogMapper.INSTANCE.fromDAOToDTO(blogDAO);

        List<BlogLikeDTO> blogLikeDTOList = blogLikeService.getAllLikesByBlog(id);
        blogDTO.setBlogLikeDTOList(blogLikeDTOList);

        List<BlogCommentDTO> blogCommentDTOList = blogCommentService.getAllCommentsByBlog(id);
        blogDTO.setBlogCommentDTOList(blogCommentDTOList);

        log.info("Blog found with id: {}", id);

        return blogDTO;
    }

    @Override
    public List<BlogDTO> getAllBlogs() {
        List<BlogDAO> blogDAOList = blogRepository.findAll();

        if(blogDAOList.isEmpty()) log.warn("Blog not found !!!");
        else log.info("{} blogs found", blogDAOList.size());

        return BlogMapper.INSTANCE.fromDAOListToDTOList(blogDAOList);
    }

    @Override
    public BlogDTO updateBlog(Integer id, BlogDTO blogDTO) {

        BlogDAO blogDAO = blogRepository.findById(id).orElseThrow( () -> {
            log.error("Blog not found with id: {}", id);
            return new ResourceNotFoundException("Blog not found with id:" + id);
        });

        blogDTO.setCreatedAt(blogDAO.getCreatedAt());
        LocalDateTime currentDateTime = LocalDateTime.now();
        blogDTO.setUpdatedAt(currentDateTime);

        blogDAO = BlogMapper.INSTANCE.fromDTOToDAO(blogDTO);
        
        blogDAO.setBlogId(id);
        blogDAO = blogRepository.save(blogDAO);
        log.info("Blog successfully updated with id: {}", blogDAO.getBlogId());
        return BlogMapper.INSTANCE.fromDAOToDTO(blogDAO);
    }

    @Override
    public void deleteBlog(Integer id) {
        if(!blogRepository.existsById(id)){
            log.error("Blog not found with id: {}", id);
            throw new ResourceNotFoundException("Blog not found with id:" + id);
        }
        blogRepository.deleteById(id);
        log.info("Blog successfully deleted with id: {}", id);
    }
}
