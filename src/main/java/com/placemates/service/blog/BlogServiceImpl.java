package com.placemates.service.blog;

import com.placemates.dao.blog.BlogDAO;
import com.placemates.dao.user.UserDAO;
import com.placemates.dto.blog.BlogCommentDTO;
import com.placemates.dto.blog.BlogDTO;
import com.placemates.dto.blog.BlogLikeDTO;
import com.placemates.dto.user.UserInfoDTO;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.blog.BlogRepository;
import com.placemates.service.user.UserService;
import com.placemates.util.logger.LoggerUtil;
import com.placemates.util.mapper.blog.BlogMapper;
import com.placemates.util.mapper.user.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.placemates.constant.AppConstants.*;

@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final BlogLikeService blogLikeService;
    private final BlogCommentService blogCommentService;
    private final UserService userService;

    public BlogServiceImpl(BlogRepository blogRepository, BlogLikeService blogLikeService, BlogCommentService blogCommentService, UserService userService) {
        this.blogRepository = blogRepository;
        this.blogLikeService = blogLikeService;
        this.blogCommentService = blogCommentService;
        this.userService = userService;
    }

    @Override
    public BlogDTO createBlog(BlogDTO blogDTO) {
        UserDAO currentUser = userService.getCurrentUser();
        String username = currentUser.getMail();
        double startTime = System.currentTimeMillis();
        double endTime, duration;

        LocalDateTime currentDateTime = LocalDateTime.now();
        blogDTO.setCreatedAt(currentDateTime);
        blogDTO.setUpdatedAt(currentDateTime);

        UserInfoDTO userInfoDTO = UserInfoMapper.INSTANCE.toUserDTO(currentUser);
        blogDTO.setCreatedByDTO(userInfoDTO);

        BlogDAO blogDAO = BlogMapper.INSTANCE.toBlogDAO(blogDTO);
        blogDAO.setBlogId(null);
        blogDAO = blogRepository.save(blogDAO);

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(BLOG, CREATE, "Id- " + blogDAO.getBlogId(), username, duration, SUCCESS));

        return BlogMapper.INSTANCE.toBlogDTO(blogDAO);
    }

    @Override
    public BlogDTO getBlog(Integer id) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();

        BlogDAO blogDAO = blogRepository.findById(id).orElseThrow(() -> {
            log.error(LoggerUtil.buildLog(BLOG, READ, "Blog not found with id- " + id, username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
            return new ResourceNotFoundException("Blog not found with id:" + id);
        });

        BlogDTO blogDTO = BlogMapper.INSTANCE.toBlogDTO(blogDAO);

        List<BlogLikeDTO> blogLikeDTOList = blogLikeService.getAllLikesByBlog(id);
        blogDTO.setBlogLikeDTOs(blogLikeDTOList);

        List<BlogCommentDTO> blogCommentDTOList = blogCommentService.getAllCommentsByBlog(id);
        blogDTO.setBlogCommentDTOs(blogCommentDTOList);

        double endTime = System.currentTimeMillis();
        double duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(BLOG, READ, "Blog fetched with id- " + id, username, duration, SUCCESS));

        return blogDTO;
    }

    @Override
    public List<BlogDTO> getAllBlogs() {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();

        List<BlogDAO> blogDAOList = blogRepository.findAll();

        if(blogDAOList.isEmpty()) {
            log.error(LoggerUtil.buildLog(BLOG, READ, "Blogs not found", username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
        } else {
            log.info(LoggerUtil.buildLog(BLOG, READ, blogDAOList.size() + " Blogs fetched", username, (System.currentTimeMillis() - startTime) / 1000, SUCCESS));
        }

        return BlogMapper.INSTANCE.toBlogDTOList(blogDAOList);
    }

    @Override
    public BlogDTO updateBlog(Integer id, BlogDTO blogDTO) {
        UserDAO currentUser = userService.getCurrentUser();
        String username = currentUser.getMail();
        double startTime = System.currentTimeMillis();
        double endTime, duration;

        BlogDAO existing = blogRepository.findById(id).orElseThrow(() -> {
            log.error(LoggerUtil.buildLog(BLOG, UPDATE, "Blog not found with id- " + id, username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
            return new ResourceNotFoundException("Blog not found with id:" + id);
        });

        blogDTO.setCreatedAt(existing.getCreatedAt());
        blogDTO.setUpdatedAt(LocalDateTime.now());

        UserInfoDTO userInfoDTO = UserInfoMapper.INSTANCE.toUserDTO(currentUser);
        blogDTO.setCreatedByDTO(userInfoDTO);

        BlogDAO blogDAO = BlogMapper.INSTANCE.toBlogDAO(blogDTO);
        blogDAO.setBlogId(id);
        blogDAO = blogRepository.save(blogDAO);

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(BLOG, UPDATE, "Id- " + blogDAO.getBlogId(), username, duration, SUCCESS));

        return BlogMapper.INSTANCE.toBlogDTO(blogDAO);
    }

    @Override
    public void deleteBlog(Integer id) {
        String username = userService.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();

        if (!blogRepository.existsById(id)) {
            log.error(LoggerUtil.buildLog(BLOG, DELETE, "Blog not found with id- " + id, username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
            throw new ResourceNotFoundException("Blog not found with id:" + id);
        }

        blogRepository.deleteById(id);

        double endTime = System.currentTimeMillis();
        double duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(BLOG, DELETE, "Id- " + id, username, duration, SUCCESS));
    }
}
