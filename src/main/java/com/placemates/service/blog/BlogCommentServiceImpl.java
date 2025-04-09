package com.placemates.service.blog;

import com.placemates.constant.AppConstants;
import com.placemates.dao.blog.BlogCommentDAO;
import com.placemates.dto.blog.BlogCommentDTO;
import com.placemates.dto.blog.BlogDTO;
import com.placemates.dto.user.UserDTO;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.blog.BlogCommentRepository;
import com.placemates.repository.blog.BlogRepository;
import com.placemates.repository.user.UserRepository;
import com.placemates.util.mapper.blog.BlogCommentMapper;
import com.placemates.util.mapper.blog.BlogMapper;
import com.placemates.util.mapper.user.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogCommentServiceImpl implements BlogCommentService{

    private static final Logger logger = LoggerFactory.getLogger(BlogCommentServiceImpl.class);
    private final BlogCommentRepository blogCommentRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    public BlogCommentServiceImpl(BlogCommentRepository blogCommentRepository, UserRepository userRepository, BlogRepository blogRepository) {
        this.blogCommentRepository = blogCommentRepository;
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
    }

    @Override
    public BlogCommentDTO createCommentByUserAndBlog(Integer userId, Integer blogId, String content) {
        UserDTO userDTO = UserMapper.INSTANCE.fromDAOToDTO(
            userRepository.findById(userId).orElseThrow(() ->{
                logger.info("User" + AppConstants.NOT_FOUND + "{}", userId);
                return new ResourceNotFoundException("User" + AppConstants.NOT_FOUND + userId);
            })
        );

        BlogDTO blogDTO = BlogMapper.INSTANCE.fromDAOToDTO(
            blogRepository.findById(blogId).orElseThrow(() -> {
                logger.info("Blog" + AppConstants.NOT_FOUND + "{}", blogId);
                return new ResourceNotFoundException("Blog" + AppConstants.NOT_FOUND + blogId);
            })
        );

        BlogCommentDTO blogCommentDTO = new BlogCommentDTO();
        LocalDateTime currentDateTime = LocalDateTime.now();
        blogCommentDTO.setCommentedAt(currentDateTime);
        blogCommentDTO.setBlogDTO(blogDTO);
        blogCommentDTO.setCommentByDTO(userDTO);
        blogCommentDTO.setContent(content);

        BlogCommentDAO blogCommentDAO = blogCommentRepository.save(BlogCommentMapper.INSTANCE.fromDTOToDAO(blogCommentDTO));
        logger.info("User with id: {} commented on the blog with id: {}", userDTO.getUserId(), blogDTO.getBlogId());

        return BlogCommentMapper.INSTANCE.fromDAOToDTO(blogCommentDAO);
    }

    @Override
    public List<BlogCommentDTO> getAllCommentsByBlog(Integer id) {
        List<BlogCommentDAO> blogCommentDAOList = blogCommentRepository.findAllByBlogDAO_BlogId(id);
        if(blogCommentDAOList.isEmpty()) logger.warn("Comments" + AppConstants.NO_RECORDS_FOUND);
        else logger.info("{} comments found", blogCommentDAOList.size());
        return BlogCommentMapper.INSTANCE.fromDAOListToDTOList(blogCommentDAOList);
    }

    @Transactional
    @Override
    public void deleteComment(Integer id) {
        if(!blogCommentRepository.existsById(id)){
            logger.error("Comment" + AppConstants.NOT_FOUND + "{}", id);
            throw new ResourceNotFoundException("Comment" + AppConstants.NOT_FOUND + "{}" + id);
        }
        blogCommentRepository.deleteById(id);
        logger.info("Comment" + AppConstants.DELETED + "{}", id);
    }
}
