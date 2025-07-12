package com.placemates.service.blog;

import com.placemates.dao.blog.BlogCommentDAO;
import com.placemates.dao.user.UserDAO;
import com.placemates.dto.blog.BlogCommentDTO;
import com.placemates.dto.blog.BlogDTO;
import com.placemates.dto.user.UserInfoDTO;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.blog.BlogCommentRepository;
import com.placemates.repository.blog.BlogRepository;
import com.placemates.repository.user.UserRepository;
import com.placemates.service.user.UserService;
import com.placemates.util.logger.LoggerUtil;
import com.placemates.util.mapper.blog.BlogCommentMapper;
import com.placemates.util.mapper.blog.BlogMapper;
import com.placemates.util.mapper.user.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.placemates.constant.AppConstants.*;

@Service
@Slf4j
public class BlogCommentServiceImpl implements BlogCommentService {

    private final BlogCommentRepository blogCommentRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final UserService userService;

    public BlogCommentServiceImpl(BlogCommentRepository blogCommentRepository, UserRepository userRepository, BlogRepository blogRepository, UserService userService) {
        this.blogCommentRepository = blogCommentRepository;
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
        this.userService = userService;
    }

    @Override
    public BlogCommentDTO createComment(Integer blogId, String comment) {
        UserDAO currentUser = userService.getCurrentUser();
        String username = currentUser.getMail();
        double startTime = System.currentTimeMillis();
        double endTime;
        double duration;

        UserInfoDTO userInfoDTO = UserInfoMapper.INSTANCE.toUserDTO(
                userRepository.findById(currentUser.getUserId()).orElseThrow(() -> {
                    log.error(LoggerUtil.buildLog(BLOG_COMMENT, CREATE, "User not found with id: " + currentUser.getUserId(), username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
                    return new ResourceNotFoundException("User not found with id: " + currentUser.getUserId());
                })
        );

        BlogDTO blogDTO = BlogMapper.INSTANCE.toBlogDTO(
                blogRepository.findById(blogId).orElseThrow(() -> {
                    log.error(LoggerUtil.buildLog(BLOG_COMMENT, CREATE, "Blog not found with id: " + blogId, username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
                    return new ResourceNotFoundException("Blog not found with id:" + blogId);
                })
        );

        BlogCommentDTO blogCommentDTO = new BlogCommentDTO();
        blogCommentDTO.setCommentedAt(LocalDateTime.now());
        blogCommentDTO.setBlogDTO(blogDTO);
        blogCommentDTO.setCommentedByDTO(userInfoDTO);
        blogCommentDTO.setComment(comment);

        BlogCommentDAO blogCommentDAO = blogCommentRepository.save(BlogCommentMapper.INSTANCE.toBlogCommentDAO(blogCommentDTO));

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(BLOG_COMMENT, CREATE, "Commented on blogId- " + blogDTO.getBlogId() + " by userId- " + userInfoDTO.getUserId(), username, duration, SUCCESS));

        return BlogCommentMapper.INSTANCE.toBlogCommentDTO(blogCommentDAO);
    }

    @Override
    public List<BlogCommentDTO> getAllCommentsByBlog(Integer blogId) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();

        List<BlogCommentDAO> blogCommentDAOList = blogCommentRepository.findAllByBlogDAO_BlogId(blogId);

        double duration = (System.currentTimeMillis() - startTime) / 1000;
        if (blogCommentDAOList.isEmpty()) {
            log.error(LoggerUtil.buildLog(BLOG_COMMENT, READ, "Comments not found for blogId- " + blogId, username, duration, FAIL));
        } else {
            log.info(LoggerUtil.buildLog(BLOG_COMMENT, READ, blogCommentDAOList.size() + " comments fetched for blogId- " + blogId, username, duration, SUCCESS));
        }

        return BlogCommentMapper.INSTANCE.toBlogCommentDTOList(blogCommentDAOList);
    }

    @Transactional
    @Override
    public void deleteComment(Integer blogId) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();
        double endTime;
        double duration;

        if (!blogCommentRepository.existsById(blogId)) {
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime) / 1000;
            log.error(LoggerUtil.buildLog(BLOG_COMMENT, DELETE, "Comment not found with blogId- " + blogId, username, duration, FAIL));
            throw new ResourceNotFoundException("Comment not found with blogId: " + blogId);
        }

        blogCommentRepository.deleteById(blogId);
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(BLOG_COMMENT, DELETE, "Comment deleted with blogId- " + blogId, username, duration, SUCCESS));
    }
}
