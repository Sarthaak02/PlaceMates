package com.placemates.service.user;

import com.placemates.constant.AppConstants;
import com.placemates.dao.user.RoleDAO;
import com.placemates.dao.user.UserDAO;
import com.placemates.dto.user.RoleDTO;
import com.placemates.dto.user.UserDTO;
import com.placemates.exception.ResourceAlreadyExistsException;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.user.UserRepository;
import com.placemates.util.logger.LoggerUtil;
import com.placemates.util.mapper.user.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.placemates.constant.AppConstants.*;

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
        String username = this.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();
        double endTime;
        double duration;

        if(userRepository.findByMail(userDTO.getMail()) != null){
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime) / 1000;
            log.warn(LoggerUtil.buildLog(USER,CREATE,"User already exists with email:- " + userDTO.getMail(), username, duration, FAIL));
            throw new ResourceAlreadyExistsException("User already exists with email:" + userDTO.getMail());
        }

        if(userRepository.findByMobileNumber(userDTO.getMobileNumber()) != null) {
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime) / 1000;
            log.warn(LoggerUtil.buildLog(USER,CREATE,"User already exists with mobile number- " + userDTO.getMobileNumber(), username, duration, FAIL));
            throw new ResourceAlreadyExistsException("User already exists with mobile number: " + userDTO.getMobileNumber());
        }

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(AppConstants.DEFAULT_ROLE);
        userDTO.setRoleDTO(roleDTO);

        UserDAO userDAO = UserMapper.INSTANCE.toUserDAO(userDTO);

        userDAO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userDAO = userRepository.save(userDAO);

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;

        log.info(LoggerUtil.buildLog(USER,CREATE,"Email- " + userDAO.getMail(), username, duration, SUCCESS));

        return UserMapper.INSTANCE.toUserDTO(userDAO);
    }

    @Override
    public UserDTO getUser(Integer userId) {
        String username = this.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();
        double endTime;
        double duration;

        UserDAO userDAO = userRepository.findById(userId).orElseThrow(() -> {
            log.error(LoggerUtil.buildLog(USER,READ,"User not found with Id- " + userId, username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
            return new ResourceNotFoundException("User not found with Id: " + userId);
        });

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(USER,READ,"User fetched with Id- " + userDAO.getUserId(), username, duration, SUCCESS));

        return UserMapper.INSTANCE.toUserDTO(userDAO);
    }

    @Override
    public UserDAO getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDAO userDAO = userRepository.findByUsername(username);

        if(userDAO != null) return userDAO;

        return new UserDAO();
    }

    @Override
    public String getCurrentUserUsername() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if(!username.contains("@")) username = userRepository.findEmailByMobileNumber(username);

        return username != null ? username : "Anonymous";
    }

    @Override
    public List<UserDTO> getAllUsers() {
        String username = this.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();

        List<UserDAO> userDAOList = userRepository.findAll();

        if(userDAOList.isEmpty()){
            log.warn(LoggerUtil.buildLog(USER,READ,"Users not found", username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
            new ArrayList<>();
        }
        else log.info(LoggerUtil.buildLog(USER,READ,userDAOList.size() + " Users fetched", username, (System.currentTimeMillis() - startTime) / 1000, SUCCESS));

        return UserMapper.INSTANCE.toUserDTOList(userDAOList);
    }

    @Override
    public UserDTO updateUser(Integer userId, UserDTO userDTO) {
        String username = this.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();
        double endTime;
        double duration;

        UserDAO userDAO = userRepository.findById(userId).orElseThrow(() -> {
            log.error(LoggerUtil.buildLog(USER,UPDATE,"User not found with Id- " + userId, username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
            return new ResourceNotFoundException("User not found with Id: " + userId);
        });

        if(userRepository.findByMail(userDTO.getMail()) != null && userRepository.findByMail(userDTO.getMail()).getUserId() != userId) {
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime) / 1000;
            log.warn(LoggerUtil.buildLog(USER,UPDATE,"User already exists with email- " + userDTO.getMail(), username, duration, FAIL));
            throw new ResourceAlreadyExistsException("User already exists with email:" + userDTO.getMail());
        }

        if(userRepository.findByMobileNumber(userDTO.getMobileNumber()) != null && userRepository.findByMobileNumber(userDTO.getMobileNumber()).getUserId() != userId) {
            endTime = System.currentTimeMillis();
            duration = (endTime - startTime) / 1000;
            log.warn(LoggerUtil.buildLog(USER,UPDATE,"User already exists with mobile number- " + userDTO.getMobileNumber(), username, duration, FAIL));
            throw new ResourceAlreadyExistsException("User already exists with mobile number: " + userDTO.getMobileNumber());
        }

        //If the role is not passed from ui i.e. not in dto then set role here
//        May be changed for the admin dashboard he has access to change the role of the user then for that implement the patch method
        RoleDAO roleDAO = userDAO.getRoleDAO();

        userDAO = UserMapper.INSTANCE.toUserDAO(userDTO);
        userDAO.setUserId(userId);
        userDAO.setRoleDAO(roleDAO);
        userDAO.setPassword(passwordEncoder.encode(userDAO.getPassword()));
        userDAO = userRepository.save(userDAO);

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(USER,UPDATE,"Id- " + userDAO.getUserId(), username, duration, SUCCESS));

        return UserMapper.INSTANCE.toUserDTO(userDAO);
    }

    @Override
    public void deleteUser(Integer userId) {
        String username = this.getCurrentUserUsername();
        double startTime = System.currentTimeMillis();
        double endTime;
        double duration;

        if(!userRepository.existsById(userId)){
            log.error(LoggerUtil.buildLog(USER,DELETE,"User not found with Id- " + userId, username, (System.currentTimeMillis() - startTime) / 1000, FAIL));
            throw new ResourceNotFoundException("User not found with Id: " + userId);
        }

        userRepository.deleteById(userId);

        endTime = System.currentTimeMillis();
        duration = (endTime - startTime) / 1000;
        log.info(LoggerUtil.buildLog(USER,DELETE,"Id- " + userId, username, duration, SUCCESS));
    }
}
