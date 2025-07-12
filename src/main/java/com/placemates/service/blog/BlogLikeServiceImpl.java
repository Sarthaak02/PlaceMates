package com.placemates.service.blog;

import com.placemates.dao.blog.BlogLikeDAO;
import com.placemates.dao.user.UserDAO;
import com.placemates.dto.blog.BlogDTO;
import com.placemates.dto.blog.BlogLikeDTO;
import com.placemates.dto.user.UserInfoDTO;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.blog.BlogLikeRepository;
import com.placemates.repository.blog.BlogRepository;
import com.placemates.repository.user.UserRepository;
import com.placemates.service.user.UserService;
import com.placemates.util.logger.LoggerUtil;
import com.placemates.util.mapper.blog.BlogLikeMapper;
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
public class BlogLikeServiceImpl implements BlogLikeService {

    private final BlogLikeRepository blogLikeRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final UserService userService;

    public BlogLikeServiceImpl(BlogLikeRepository blogLikeRepository, UserRepository userRepository, BlogRepository blogRepository, UserService userService) {
        this.blogLikeRepository = blogLikeRepository;
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
        this.userService = userService;
    }

    @Override
    public BlogLikeDTO createLike(Integer blogId) {
        UserDAO currentUser = userService.getCurrentUser();
        String username = currentUser.getMail();
        double startTime = System.currentTimeMillis();
        double endTime;
        double duration;

        UserInfoDTO userInfoDTO = UserInfoMapper.INSTANCE.toUserDTO(
                userRepository.findById(currentUser.getUserId()).orElseThrow(() -> {
                    log.error(LoggerUtil.buildLog(BLOG_LIKE, CREATE, "User not found with id: " + currentUser.getUserId(), username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
                    return new ResourceNotFoundException("User not found with id: " + currentUser.getUserId());
                })
        );

        BlogDTO blogDTO = BlogMapper.INSTANCE.toBlogDTO(
                blogRepository.findById(blogId).orElseThrow(() -> {
                    log.error(LoggerUtil.buildLog(BLOG_LIKE, CREATE, "Blog not found with id: " + blogId, username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
                    return new ResourceNotFoundException("Blog not found with id:" + blogId);
                })
        );

        BlogLikeDAO blogLikeDAO = new BlogLikeDAO();
        blogLikeDAO.setLikedAt(LocalDateTime.now());
        blogLikeDAO.setBlogDAO(BlogMapper.INSTANCE.toBlogDAO(blogDTO));
        blogLikeDAO.setLikedByDAO(UserInfoMapper.INSTANCE.toUserDAO(userInfoDTO));

        blogLikeRepository.save(blogLikeDAO);

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(BLOG_LIKE, CREATE, "BlogId- " + blogDTO.getBlogId() + ", Liked by UserId- " + userInfoDTO.getUserId(), username, duration, SUCCESS));

        return BlogLikeMapper.INSTANCE.toBlogLikeDTO(blogLikeDAO);
    }

    @Override
    public List<BlogLikeDTO> getAllLikesByBlog(Integer blogId) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();

        List<BlogLikeDAO> blogLikeDAOList = blogLikeRepository.findAllByBlogDAO_BlogId(blogId);

        if (blogLikeDAOList.isEmpty()) {
            log.error(LoggerUtil.buildLog(BLOG_LIKE, READ, "Likes not found for blogId- " + blogId, username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
        } else {
            log.info(LoggerUtil.buildLog(BLOG_LIKE, READ, blogLikeDAOList.size() + " Likes fetched for blogId- " + blogId, username, (System.currentTimeMillis() - startTime) / 1000, SUCCESS));
        }

        return BlogLikeMapper.INSTANCE.toBlogLikeDTOList(blogLikeDAOList);
    }

    @Transactional
    @Override
    public void deleteLike(Integer blogId) {
        UserDAO currentUser = userService.getCurrentUser();
        String username = currentUser.getMail();
        double startTime = System.currentTimeMillis();
        double endTime;
        double duration;

        if (!blogLikeRepository.existsByLikedByDAO_UserIdAndBlogDAO_BlogId(currentUser.getUserId(), blogId)) {
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime) / 1000;
            log.error(LoggerUtil.buildLog(BLOG_LIKE, DELETE, "Like not found for blogId- " + blogId + " by userId- " + currentUser.getUserId(), username, duration, FAIL));
            throw new ResourceNotFoundException("Like for blog " + blogId + " by user " + currentUser.getUserId() + " not found");
        }

        blogLikeRepository.deleteByLikedByDAO_UserIdAndBlogDAO_BlogId(currentUser.getUserId(), blogId);

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(BLOG_LIKE, DELETE, "Unliked blogId- " + blogId + " by userId- " + currentUser.getUserId(), username, duration, SUCCESS));
    }
}
