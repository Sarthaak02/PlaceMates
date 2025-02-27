package com.placemates.controller.user;

import com.placemates.dto.user.UserDTO;
import com.placemates.exception.DataAlreadyExistsException;
import com.placemates.exception.DataNotFoundException;
import com.placemates.service.user.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.placemates.response.APIResponse;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse> createUser(@Valid @RequestBody UserDTO userDTO) throws DataAlreadyExistsException {
        userService.createUser(userDTO);
        List<String> message = List.of("User created successfully");
        APIResponse successResponse = new APIResponse(
                LocalDateTime.now(),
                message
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    @PutMapping("/update/email/{id}")
    public ResponseEntity<APIResponse> updateUserEmail(@PathVariable Integer id, @Valid @RequestBody String email) throws DataNotFoundException, DataAlreadyExistsException {
        userService.updateUserEmail(id,email);
        List<String> message = List.of("Email successfully updated to " + email);
        APIResponse successResponse = new APIResponse(
                LocalDateTime.now(),
                message
        );
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }

    @PutMapping("/update/password/{id}")
    public ResponseEntity<APIResponse> updateUserPassword(@PathVariable Integer id, @Valid @RequestBody String password) throws DataNotFoundException {
        userService.updateUserPassword(id,password);
        List<String> message = List.of("Password updated successfully");
        APIResponse successResponse = new APIResponse(
                LocalDateTime.now(),
                message
        );
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> deleteUser(@PathVariable Integer id) throws DataNotFoundException {
        userService.deleteUser(id);
        List<String> message = List.of("User deleted successfully");
        APIResponse successResponse = new APIResponse(
                LocalDateTime.now(),
                message
        );
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }
}
