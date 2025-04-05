package com.placemates.service.user;

import com.placemates.dao.user.UserDAO;
import com.placemates.dto.user.UserDTO;
import com.placemates.exception.ResourceAlreadyExistsException;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.user.UserRepository;
import com.placemates.util.mapper.user.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
       if(userRepository.findByMail(userDTO.getMail()) != null){
            logger.warn("User already exists with email: {}", userDTO.getMail());
            throw new ResourceAlreadyExistsException("User already exists with mail: " + userDTO.getMail());
        }

        if(userRepository.findByMobileNumber(userDTO.getMobileNumber()) != null) {
            logger.warn("User already exists with mobile number: {}", userDTO.getMobileNumber());
            throw new ResourceAlreadyExistsException("User already exists with mobile number: " + userDTO.getMobileNumber());
        }

        UserDAO userDAO = userRepository.save(UserMapper.INSTANCE.fromDTOToDAO(userDTO));
        logger.info("User created successfully with id: {}", userDAO.getUserId());
        return UserMapper.INSTANCE.fromDAOToDTO(userDAO);
    }

    @Override
    public UserDTO getUserById(Integer id) {
        UserDAO userDAO = userRepository.findById(id).orElseThrow(() -> {
            logger.error("User not found with id: {}", id);
            return new ResourceNotFoundException("User not found with id:" + id);
        });
        logger.info("User found with id: {}", id);
        return UserMapper.INSTANCE.fromDAOToDTO(userDAO);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDAO> userDAOList = userRepository.findAll();
        if(userDAOList.isEmpty()) logger.warn("Users not found");
        else logger.info("{} users found ", userDAOList.size());
        return UserMapper.INSTANCE.fromDAOListToDTOList(userDAOList);
    }

    @Override
    public UserDTO updateUserById(Integer id,UserDTO userDTO) {
        if(!userRepository.existsById(id)){
            logger.error("User not found with id : {}", id);
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        if(userRepository.findByMail(userDTO.getMail()) != null && userRepository.findByMail(userDTO.getMail()).getUserId() != id){
            logger.warn("User already exists with email: {}", userDTO.getMail());
            throw new ResourceAlreadyExistsException("User already exists with mail: " + userDTO.getMail());
        }

        if(userRepository.findByMobileNumber(userDTO.getMobileNumber()) != null && userRepository.findByMobileNumber(userDTO.getMobileNumber()).getUserId() != null ) {
            logger.warn("User already exists with mobile number: {}", userDTO.getMobileNumber());
            throw new ResourceAlreadyExistsException("User already exists with mobile number: " + userDTO.getMobileNumber());
        }

        UserDAO userDAO = UserMapper.INSTANCE.fromDTOToDAO(userDTO);
        userDAO.setUserId(id);
        userRepository.save(userDAO);
        logger.info("User updated successfully with id : {}", id);
        return UserMapper.INSTANCE.fromDAOToDTO(userDAO);
    }

    @Override
    public void deleteUserById(Integer id) {
        if(!userRepository.existsById(id)){
            logger.error("User not found with id: {}", id);
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
        logger.info("User deleted successfully with id: {}", id);
    }
}
