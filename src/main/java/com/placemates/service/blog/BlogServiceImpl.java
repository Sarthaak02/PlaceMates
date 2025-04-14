package com.placemates.service.blog;

import com.placemates.constant.AppConstants;
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

    private static final String BLOG = "Blog";

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
        log.info(BLOG + AppConstants.CREATED + "{}", blogDAO.getBlogId());

        return BlogMapper.INSTANCE.fromDAOToDTO(blogDAO);
    }

    @Override
    public BlogDTO getBlog(Integer id) {
        BlogDAO blogDAO = blogRepository.findById(id).orElseThrow( () -> {
            log.error(BLOG + AppConstants.NOT_FOUND + "{}", id);
            return new ResourceNotFoundException(BLOG + AppConstants.NOT_FOUND + id);
        });

        BlogDTO blogDTO = BlogMapper.INSTANCE.fromDAOToDTO(blogDAO);

        List<BlogLikeDTO> blogLikeDTOList = blogLikeService.getAllLikesByBlog(id);
        blogDTO.setBlogLikeDTOList(blogLikeDTOList);

        List<BlogCommentDTO> blogCommentDTOList = blogCommentService.getAllCommentsByBlog(id);
        blogDTO.setBlogCommentDTOList(blogCommentDTOList);

        log.info(BLOG + AppConstants.FOUND + "{}", id);

        return blogDTO;
    }

    @Override
    public List<BlogDTO> getAllBlogs() {
        List<BlogDAO> blogDAOList = blogRepository.findAll();

        if(blogDAOList.isEmpty()) log.warn(BLOG + AppConstants.NO_RECORDS_FOUND);
        else log.info("{} blogs" + AppConstants.RECORDS_FOUND, blogDAOList.size());

        return BlogMapper.INSTANCE.fromDAOListToDTOList(blogDAOList);
    }

    @Override
    public BlogDTO updateBlog(Integer id, BlogDTO blogDTO) {

        BlogDAO blogDAO = blogRepository.findById(id).orElseThrow( () -> {
            log.error(BLOG + AppConstants.NOT_FOUND + "{}", id);
            return new ResourceNotFoundException(BLOG + AppConstants.NOT_FOUND + id);
        });

        blogDTO.setCreatedAt(blogDAO.getCreatedAt());
        LocalDateTime currentDateTime = LocalDateTime.now();
        blogDTO.setUpdatedAt(currentDateTime);

        blogDAO = BlogMapper.INSTANCE.fromDTOToDAO(blogDTO);
        
        blogDAO.setBlogId(id);
        blogDAO = blogRepository.save(blogDAO);
        log.info(BLOG + AppConstants.UPDATED + "{}", blogDAO.getBlogId());
        return BlogMapper.INSTANCE.fromDAOToDTO(blogDAO);
    }

    @Override
    public void deleteBlog(Integer id) {
        if(!blogRepository.existsById(id)){
            log.error(BLOG + AppConstants.NOT_FOUND + "{}", id);
            throw new ResourceNotFoundException(BLOG + AppConstants.NOT_FOUND + id);
        }
        blogRepository.deleteById(id);
        log.info(BLOG + AppConstants.DELETED + "{}", id);
    }
}
