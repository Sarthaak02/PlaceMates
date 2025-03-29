package com.placemates.service.user;

import com.placemates.dao.common.BranchDAO;
import com.placemates.dao.common.LocationDAO;
import com.placemates.dao.user.UserDAO;
import com.placemates.dto.user.UserDTO;
import com.placemates.exception.ResourceAlreadyExistsException;
import com.placemates.exception.ResourceNotFoundException;
import com.placemates.repository.common.BranchRepository;
import com.placemates.repository.common.LocationRepository;
import com.placemates.repository.user.UserRepository;
import com.placemates.util.mapper.user.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);

    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final LocationRepository locationRepository;

    public UserServiceImplementation(UserRepository userRepository, BranchRepository branchRepository, LocationRepository locationRepository) {
        this.userRepository = userRepository;
        this.branchRepository = branchRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
       if(userRepository.existsByMail(userDTO.getMail())){
            logger.warn("User already exists with email: {}", userDTO.getMail());
            throw new ResourceAlreadyExistsException("User already exists with mail : " + userDTO.getMail());
        }

        if(userRepository.existsByMobileNumber(userDTO.getMobileNumber())) {
            logger.warn("User already exists with mobile number: {}", userDTO.getMobileNumber());
            throw new ResourceAlreadyExistsException("User already exists with mobile number : " + userDTO.getMobileNumber());
        }

        UserDAO userDAO = UserMapper.INSTANCE.fromDTOToDAO(userDTO);

        if(userDTO.getBranchDTO() != null && userDTO.getBranchDTO().getBranchId() != null){
            BranchDAO branchDAO = branchRepository.findById(userDTO.getBranchDTO().getBranchId())
                    .orElseThrow(() -> {
                        logger.error("Branch not found with id: {}", userDTO.getBranchDTO().getBranchId());
                        return new ResourceNotFoundException("Branch not found with id : " + userDTO.getBranchDTO().getBranchId());
                    });
            userDAO.setBranchDAO(branchDAO);
        }
        else userDAO.setBranchDAO(null);

        if (userDTO.getLocationDTO() != null && userDTO.getLocationDTO().getLocationId() != null) {
            LocationDAO locationDAO = locationRepository.findById(userDTO.getLocationDTO().getLocationId())
                    .orElseThrow(() -> {
                        logger.error("Location not found with id: {}", userDTO.getLocationDTO().getLocationId());
                        return new ResourceNotFoundException("Location not found with id : " + userDTO.getLocationDTO().getLocationId());
                    });
            userDAO.setLocationDAO(locationDAO);
        } else {
            userDAO.setLocationDAO(null);
        }

        userDAO = userRepository.save(userDAO);
        logger.info("User saved successfully with id: {}", userDAO.getUserId());
        return UserMapper.INSTANCE.fromDAOToDTO(userDAO);
    }

    @Override
    public UserDTO getUserById(Integer id) {
        UserDAO userDAO = userRepository.findById(id).orElseThrow(() -> {
            logger.error("User not found with id: {}", id);
            return new ResourceNotFoundException("User not found with id :" + id);
        });
        logger.info("User found with id: {}", id);
        return UserMapper.INSTANCE.fromDAOToDTO(userDAO);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDAO> userDAOList = userRepository.findAll();
        if(userDAOList.isEmpty()) logger.warn("Users not found");

        logger.info("Total users found : {}", userDAOList.size());
        return UserMapper.INSTANCE.fromDAOListToDTOList(userDAOList);
    }

    @Override
    public UserDTO updateUserById(Integer id, UserDTO userDTO) {
        UserDAO userDAO = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));

        if (userDTO.getFirstName() != null) userDAO.setFirstName(userDTO.getFirstName());
        if (userDTO.getLastName() != null) userDAO.setLastName(userDTO.getLastName());
        if (userDTO.getMail() != null) userDAO.setMail(userDTO.getMail());
        if (userDTO.getMobileNumber() != null) userDAO.setMobileNumber(userDTO.getMobileNumber());
        if (userDTO.getPassword() != null) userDAO.setPassword(userDTO.getPassword());
        if (userDTO.getOrganisation() != null) userDAO.setOrganisation(userDTO.getOrganisation());
        if (userDTO.getDesignation() != null) userDAO.setDesignation(userDTO.getDesignation());
        if (userDTO.getLinkText() != null) userDAO.setLinkText(userDTO.getLinkText());
        if (userDTO.getLink() != null) userDAO.setLink(userDTO.getLink());
        if (userDTO.getGender() != null) userDAO.setGender(userDTO.getGender());
        if (userDTO.getBatch() != null) userDAO.setBatch(userDTO.getBatch());

        if(userDTO.getBranchDTO() != null && userDTO.getBranchDTO().getBranchId() != null){
            BranchDAO branchDAO = branchRepository.findById(userDTO.getBranchDTO().getBranchId())
                    .orElseThrow(() -> {
                        logger.error("Branch not found with id: {}", userDTO.getBranchDTO().getBranchId());
                        return new ResourceNotFoundException("Branch not found with id : " + userDTO.getBranchDTO().getBranchId());
                    });
            userDAO.setBranchDAO(branchDAO);
        }
        else userDAO.setBranchDAO(null);

        if (userDTO.getLocationDTO() != null && userDTO.getLocationDTO().getLocationId() != null) {
            LocationDAO locationDAO = locationRepository.findById(userDTO.getLocationDTO().getLocationId())
                    .orElseThrow(() -> {
                        logger.error("Location not found with id: {}", userDTO.getLocationDTO().getLocationId());
                        return new ResourceNotFoundException("Location not found with id : " + userDTO.getLocationDTO().getLocationId());
                    });
            userDAO.setLocationDAO(locationDAO);
        } else {
            userDAO.setLocationDAO(null);
        }

        userDAO = userRepository.save(userDAO);
        logger.info("User updated successfully with id : {}", id);
        return UserMapper.INSTANCE.fromDAOToDTO(userDAO);
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.findById(id).orElseThrow(() -> {
            logger.error("User not found with id: {}", id);
            return new ResourceNotFoundException("User not found with id : {}" + id);
        });
        userRepository.deleteById(id);
        logger.info("User deleted successfully with id: {}", id);
    }
}
