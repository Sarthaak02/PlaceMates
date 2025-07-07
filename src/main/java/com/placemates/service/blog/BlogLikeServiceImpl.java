package com.placemates.service.blog;

import com.placemates.dao.blog.BlogLikeDAO;
import com.placemates.dto.blog.BlogDTO;
import com.placemates.dto.blog.BlogLikeDTO;
import com.placemates.dto.user.UserInfoDTO;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.blog.BlogLikeRepository;
import com.placemates.repository.blog.BlogRepository;
import com.placemates.repository.user.UserRepository;
import com.placemates.util.mapper.blog.BlogLikeMapper;
import com.placemates.util.mapper.blog.BlogMapper;
import com.placemates.util.mapper.user.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class BlogLikeServiceImpl implements BlogLikeService{
    
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
        UserInfoDTO userInfoDTO = UserInfoMapper.INSTANCE.toUserDTO(
            userRepository.findById(userId).orElseThrow(() ->{
                log.error("User not found with id: {}", userId);
                return new ResourceNotFoundException("User not found with id: " + userId);
            })
        );

        BlogDTO blogDTO = BlogMapper.INSTANCE.toBlogDTO(
            blogRepository.findById(blogId).orElseThrow(() -> {
                log.error("Blog not found with id: {}", blogId);
                return new ResourceNotFoundException("Blog not found with id:" + blogId);
            })
        );

        BlogLikeDAO blogLikeDAO = new BlogLikeDAO();

        LocalDateTime currentDateTime = LocalDateTime.now();
        blogLikeDAO.setLikedAt(currentDateTime);
        blogLikeDAO.setBlogDAO(BlogMapper.INSTANCE.toBlogDAO(blogDTO));
        blogLikeDAO.setLikedByDAO(UserInfoMapper.INSTANCE.toUserDAO(userInfoDTO));

        blogLikeRepository.save(blogLikeDAO);
        log.info("Blog with id: {}, liked by user with id:{}", blogDTO.getBlogId(), userInfoDTO.getUserId());

        return BlogLikeMapper.INSTANCE.toBlogLikeDTO(blogLikeDAO);
    }

    @Override
    public List<BlogLikeDTO> getAllLikesByBlog(Integer blogId) {
        List<BlogLikeDAO> blogLikeDAOList = blogLikeRepository.findAllByBlogDAO_BlogId(blogId);
        if(blogLikeDAOList.isEmpty()) log.warn("Likes for blog with id: {} not found !!!", blogId );
        else log.info("{} likes found", blogLikeDAOList.size());
        return BlogLikeMapper.INSTANCE.toBlogLikeDTOList(blogLikeDAOList);
    }

    @Transactional
    @Override
    public void deleteLikeByUserAndBlog(Integer userId, Integer blogId) {
        if(!blogLikeRepository.existsByLikedByDAO_UserIdAndBlogDAO_BlogId(userId,blogId)){
            log.error("Like for blog with id: {} by user with id: {} not found", blogId, userId);
            throw new ResourceNotFoundException("Like for blog " + blogId + " by user " + userId + " not found");
        }
        blogLikeRepository.deleteByLikedByDAO_UserIdAndBlogDAO_BlogId(userId, blogId);
        log.info("Blog with id: {} unliked by user with id: {}", blogId, userId);
    }
}
