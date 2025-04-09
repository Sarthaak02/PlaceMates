package com.placemates.service.blog;

import com.placemates.constant.AppConstants;
import com.placemates.dao.blog.BlogLikeDAO;
import com.placemates.dto.blog.BlogDTO;
import com.placemates.dto.blog.BlogLikeDTO;
import com.placemates.dto.user.UserDTO;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.blog.BlogLikeRepository;
import com.placemates.repository.blog.BlogRepository;
import com.placemates.repository.user.UserRepository;
import com.placemates.util.mapper.blog.BlogLikeMapper;
import com.placemates.util.mapper.blog.BlogMapper;
import com.placemates.util.mapper.user.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogLikeServiceImpl implements BlogLikeService{

    private static final Logger logger = LoggerFactory.getLogger(BlogLikeServiceImpl.class);
    private final BlogLikeRepository blogLikeRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    public BlogLikeServiceImpl(BlogLikeRepository blogLikeRepository, UserRepository userRepository, BlogRepository blogRepository) {
        this.blogLikeRepository = blogLikeRepository;
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
    }

    @Override
    public BlogLikeDTO createLikeByUserAndBlog(Integer userId, Integer blogId) {
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

        BlogLikeDAO blogLikeDAO = new BlogLikeDAO();

        LocalDateTime currentDateTime = LocalDateTime.now();
        blogLikeDAO.setLikedAt(currentDateTime);
        blogLikeDAO.setBlogDAO(BlogMapper.INSTANCE.fromDTOToDAO(blogDTO));
        blogLikeDAO.setLikeByDAO(UserMapper.INSTANCE.fromDTOToDAO(userDTO));

        blogLikeRepository.save(blogLikeDAO);
        logger.info("Blog with id: {}, liked by user with id:{}", blogDTO.getBlogId(), userDTO.getUserId());

        return BlogLikeMapper.INSTANCE.fromDAOToDTO(blogLikeDAO);
    }

    @Override
    public List<BlogLikeDTO> getAllLikesByBlog(Integer id) {
        List<BlogLikeDAO> blogLikeDAOList = blogLikeRepository.findAllByBlogDAO_BlogId(id);
        if(blogLikeDAOList.isEmpty()) logger.warn("Likes" + AppConstants.NO_RECORDS_FOUND);
        else logger.info("{} likes found", blogLikeDAOList.size());
        return BlogLikeMapper.INSTANCE.fromDAOListToDTOList(blogLikeDAOList);
    }

    @Transactional
    @Override
    public void deleteLikeByUserAndBlog(Integer userId, Integer blogId) {
        if(!blogLikeRepository.existsByLikeByDAO_UserIdAndBlogDAO_BlogId(userId,blogId)){
            logger.error("Like for blog with id: {} by user with id: {} not found", blogId, userId);
            throw new ResourceNotFoundException("Like for blog " + blogId + " by user " + userId + " not found");
        }
        blogLikeRepository.deleteByLikeByDAO_UserIdAndBlogDAO_BlogId(userId, blogId);
        logger.info("Blog with id: {} unliked by user with id: {}", blogId, userId);
    }
}
