package com.placemates.service.user;

import com.placemates.constant.AppConstants;
import com.placemates.dao.user.RoleDAO;
import com.placemates.dao.user.UserDAO;
import com.placemates.dto.user.RoleDTO;
import com.placemates.dto.user.UserDTO;
import com.placemates.exception.ResourceAlreadyExistsException;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.user.UserRepository;
import com.placemates.util.mapper.user.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
       if(userRepository.findByMail(userDTO.getMail()) != null){
           log.warn("User already exists with email: {}", userDTO.getMail());
           throw new ResourceAlreadyExistsException("User already exists with email:" + userDTO.getMail());
        }

        if(userRepository.findByMobileNumber(userDTO.getMobileNumber()) != null) {
            log.warn("User already exists with mobile number: {}", userDTO.getMobileNumber());
            throw new ResourceAlreadyExistsException("User already exists with mobile number: " + userDTO.getMobileNumber());
        }

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(AppConstants.DEFAULT_ROLE);
        userDTO.setRoleDTO(roleDTO);
        UserDAO userDAO = UserMapper.INSTANCE.toUserDAO(userDTO);
        userDAO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDAO = userRepository.save(userDAO);
        log.info("User successfully created with id: {}", userDAO.getUserId());
        return UserMapper.INSTANCE.toUserDTO(userDAO);
    }

    @Override
    public UserDTO getUser(Integer id) {
        UserDAO userDAO = userRepository.findById(id).orElseThrow(() -> {
            log.error("User not found with id: {}", id);
            return new ResourceNotFoundException("User not found with id: " + id);
        });
        log.info("User found with id: {}", userDAO.getUserId());
        return UserMapper.INSTANCE.toUserDTO(userDAO);
    }

    @Override
    public String getCurrentUserUsername() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if(!username.contains("@")) username = userRepository.findEmailByMobileNumber(username);

        return username;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDAO> userDAOList = userRepository.findAll();
        if(userDAOList.isEmpty()) log.warn("Users not found !!!");
        else log.info("{} users found", userDAOList.size());
        return UserMapper.INSTANCE.toUserDTOList(userDAOList);
    }

    @Override
    public UserDTO updateUser(Integer id, UserDTO userDTO) {
        UserDAO userDAO = userRepository.findById(id).orElseThrow(() -> {
            log.error("User not found with id: {}", id);
            return new ResourceNotFoundException("User not found with id: " + id);
        });

        if(userRepository.findByMail(userDTO.getMail()) != null && userRepository.findByMail(userDTO.getMail()).getUserId() != id) {
            log.warn("User already exists with email: {}", userDTO.getMail());
            throw new ResourceAlreadyExistsException("User already exists with email:" + userDTO.getMail());
        }

        if(userRepository.findByMobileNumber(userDTO.getMobileNumber()) != null && userRepository.findByMobileNumber(userDTO.getMobileNumber()).getUserId() != id ) {
            log.warn("User already exists with mobile number: {}", userDTO.getMobileNumber());
            throw new ResourceAlreadyExistsException("User already exists with mobile number: " + userDTO.getMobileNumber());
        }

        //If the role is not passed from ui i.e. not in dto then set role here
//        May be changed for the admin dashboard he has access to change the role of the user then for that implement the patch method
        RoleDAO roleDAO = userDAO.getRoleDAO();

        userDAO = UserMapper.INSTANCE.toUserDAO(userDTO);
        userDAO.setUserId(id);
        userDAO.setRoleDAO(roleDAO);
        userDAO.setPassword(passwordEncoder.encode(userDAO.getPassword()));
        userDAO = userRepository.save(userDAO);
        log.info("User successfully updated with id: {}", userDAO.getUserId());
        return UserMapper.INSTANCE.toUserDTO(userDAO);
    }

    @Override
    public void deleteUser(Integer id) {
        if(!userRepository.existsById(id)){
            log.error("User not found with id: {}", id);
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
        log.info("User successfully deleted with id: {}", id);
    }
}
