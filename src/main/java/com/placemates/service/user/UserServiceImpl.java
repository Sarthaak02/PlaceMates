package com.placemates.service.user;

import com.placemates.constant.AppConstants;
import com.placemates.dao.user.UserDAO;
import com.placemates.dto.user.RoleDTO;
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
    private static final String USER = "User";

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
       if(userRepository.findByMail(userDTO.getMail()) != null){
           logger.warn(USER + AppConstants.ALREADY_EXISTS + "email: {}", userDTO.getMail());
           throw new ResourceAlreadyExistsException(USER + AppConstants.ALREADY_EXISTS + "mail: " + userDTO.getMail());
        }

        if(userRepository.findByMobileNumber(userDTO.getMobileNumber()) != null) {
            logger.warn(USER + AppConstants.ALREADY_EXISTS + "mobile number: {}", userDTO.getMobileNumber());
            throw new ResourceAlreadyExistsException(USER + AppConstants.ALREADY_EXISTS + "mobile number: " + userDTO.getMobileNumber());
        }

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(AppConstants.DEFAULT_ROLE);
        userDTO.setRoleDTO(roleDTO);

        UserDAO userDAO = userRepository.save(UserMapper.INSTANCE.fromDTOToDAO(userDTO));
        logger.info(USER + AppConstants.CREATED + "{}", userDAO.getUserId());
        return UserMapper.INSTANCE.fromDAOToDTO(userDAO);
    }

    @Override
    public UserDTO getUser(Integer id) {
        UserDAO userDAO = userRepository.findById(id).orElseThrow(() -> {
            logger.error(USER + AppConstants.NOT_FOUND + "{}", id);
            return new ResourceNotFoundException(USER + AppConstants.NOT_FOUND + id);
        });
        logger.info(USER + AppConstants.FOUND + "{}", userDAO.getUserId());
        return UserMapper.INSTANCE.fromDAOToDTO(userDAO);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDAO> userDAOList = userRepository.findAll();
        if(userDAOList.isEmpty()) logger.warn("Users" + AppConstants.NO_RECORDS_FOUND);
        else logger.info("{} users" + AppConstants.RECORDS_FOUND, userDAOList.size());
        return UserMapper.INSTANCE.fromDAOListToDTOList(userDAOList);
    }

    @Override
    public UserDTO updateUser(Integer id, UserDTO userDTO) {
        if(!userRepository.existsById(id)){
            logger.error(USER + AppConstants.NOT_FOUND + "{}", id);
            throw new ResourceNotFoundException(USER + AppConstants.NOT_FOUND + id);
        }

        if(userRepository.findByMail(userDTO.getMail()) != null && userRepository.findByMail(userDTO.getMail()).getUserId() != id) {
            logger.warn(USER + AppConstants.ALREADY_EXISTS + "email: {}", userDTO.getMail());
            throw new ResourceAlreadyExistsException(USER + AppConstants.ALREADY_EXISTS + "mail: " + userDTO.getMail());
        }

        if(userRepository.findByMobileNumber(userDTO.getMobileNumber()) != null && userRepository.findByMobileNumber(userDTO.getMobileNumber()).getUserId() != id ) {
            logger.warn(USER + AppConstants.ALREADY_EXISTS + "mobile number: {}", userDTO.getMobileNumber());
            throw new ResourceAlreadyExistsException(USER + AppConstants.ALREADY_EXISTS + "mobile number: " + userDTO.getMobileNumber());
        }

        //If the role is not passed from ui i.e. not in dto then set role here
        UserDAO userDAO = UserMapper.INSTANCE.fromDTOToDAO(userDTO);
        userDAO.setUserId(id);
        userRepository.save(userDAO);
        logger.info(USER + AppConstants.UPDATED + "{}", userDAO.getUserId());
        return UserMapper.INSTANCE.fromDAOToDTO(userDAO);
    }

    @Override
    public void deleteUser(Integer id) {
        if(!userRepository.existsById(id)){
            logger.error(USER + AppConstants.NOT_FOUND + "{}", id);
            throw new ResourceNotFoundException(USER + AppConstants.NOT_FOUND + id);
        }
        userRepository.deleteById(id);
        logger.info(USER + AppConstants.DELETED + "{}", id);
    }
}
