package com.placemates.service.user;

import com.placemates.dao.user.UserDAO;
import com.placemates.dto.user.UserDTO;
import com.placemates.exception.DataAlreadyExistsException;
import com.placemates.exception.DataNotFoundException;
import com.placemates.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.placemates.mapper.user.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(UserDTO userDTO) throws DataAlreadyExistsException {
        if(userRepository.existsByEmail(userDTO.getEmail())) {
            throw new DataAlreadyExistsException("User with '" + userDTO.getEmail() + "' already exists");
        }
        UserDAO userDAO = UserMapper.mapUserDTOToUserDAO(userDTO);
        userRepository.save(userDAO);
    }

    @Override
    public void updateUserEmail(Integer id, String email) throws DataNotFoundException, DataAlreadyExistsException {
        if(userRepository.existsByEmail(email)) {
            throw new DataAlreadyExistsException("User with '" + email + "' already exists");
        }
        UserDAO userDAO = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User with ID '" + id + "' not found"));
        userDAO.setEmail(email);
        userRepository.save(userDAO);
    }

    @Override
    public void updateUserPassword(Integer id, String password) throws DataNotFoundException {
        UserDAO userDAO = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User with ID '" + id + "' not found"));
        userDAO.setPassword(password);
        userRepository.save(userDAO);
    }

    @Override
    public void deleteUser(Integer id) throws DataNotFoundException {
        userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User with ID '" + id + "' not found"));
        userRepository.deleteById(id);
    }
}
