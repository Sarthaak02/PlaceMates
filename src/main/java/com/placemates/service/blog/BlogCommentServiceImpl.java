package com.placemates.service.blog;

import com.placemates.constant.AppConstants;
import com.placemates.dao.blog.BlogCommentDAO;
import com.placemates.dto.blog.BlogCommentDTO;
import com.placemates.dto.blog.BlogDTO;
import com.placemates.dto.user.UserInfoDTO;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.blog.BlogCommentRepository;
import com.placemates.repository.blog.BlogRepository;
import com.placemates.repository.user.UserRepository;
import com.placemates.util.mapper.blog.BlogCommentMapper;
import com.placemates.util.mapper.blog.BlogMapper;
import com.placemates.util.mapper.user.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class BlogCommentServiceImpl implements BlogCommentService{

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
        UserInfoDTO userInfoDTO = UserInfoMapper.INSTANCE.fromDAOToDTO(
            userRepository.findById(userId).orElseThrow(() ->{
                log.info("User" + AppConstants.NOT_FOUND + "{}", userId);
                return new ResourceNotFoundException("User" + AppConstants.NOT_FOUND + userId);
            })
        );

        BlogDTO blogDTO = BlogMapper.INSTANCE.fromDAOToDTO(
            blogRepository.findById(blogId).orElseThrow(() -> {
                log.info("Blog" + AppConstants.NOT_FOUND + "{}", blogId);
                return new ResourceNotFoundException("Blog" + AppConstants.NOT_FOUND + blogId);
            })
        );

        BlogCommentDTO blogCommentDTO = new BlogCommentDTO();
        LocalDateTime currentDateTime = LocalDateTime.now();
        blogCommentDTO.setCommentedAt(currentDateTime);
        blogCommentDTO.setBlogDTO(blogDTO);
        blogCommentDTO.setCommentByDTO(userInfoDTO);
        blogCommentDTO.setContent(content);

        BlogCommentDAO blogCommentDAO = blogCommentRepository.save(BlogCommentMapper.INSTANCE.fromDTOToDAO(blogCommentDTO));
        log.info("User with id: {} commented on the blog with id: {}", userInfoDTO.getUserId(), blogDTO.getBlogId());

        return BlogCommentMapper.INSTANCE.fromDAOToDTO(blogCommentDAO);
    }

    @Override
    public List<BlogCommentDTO> getAllCommentsByBlog(Integer id) {
        List<BlogCommentDAO> blogCommentDAOList = blogCommentRepository.findAllByBlogDAO_BlogId(id);
        if(blogCommentDAOList.isEmpty()) log.warn("Comments" + AppConstants.NO_RECORDS_FOUND);
        else log.info("{} comments found", blogCommentDAOList.size());
        return BlogCommentMapper.INSTANCE.fromDAOListToDTOList(blogCommentDAOList);
    }

    @Transactional
    @Override
    public void deleteComment(Integer id) {
        if(!blogCommentRepository.existsById(id)){
            log.error("Comment" + AppConstants.NOT_FOUND + "{}", id);
            throw new ResourceNotFoundException("Comment" + AppConstants.NOT_FOUND + id);
        }
        blogCommentRepository.deleteById(id);
        log.info("Comment" + AppConstants.DELETED + "{}", id);
    }
}
