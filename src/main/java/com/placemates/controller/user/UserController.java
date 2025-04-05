package com.placemates.controller.user;

import com.placemates.dto.user.UserDTO;
import com.placemates.service.user.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/register")
    UserDTO registerUser(@Valid @RequestBody UserDTO userDTO){
        return userServiceImpl.createUser(userDTO);
    }

    @GetMapping("/{id}")
    UserDTO getUser(@PathVariable Integer id){
        return userServiceImpl.getUserById(id);
    }

    @GetMapping("")
    List<UserDTO> getUsers(){
        return userServiceImpl.getAllUsers();
    }

    @PutMapping("/{id}")
    UserDTO updateUser(@PathVariable Integer id, @Valid @RequestBody UserDTO userDTO){
        return userServiceImpl.updateUserById(id, userDTO);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Integer id){
        userServiceImpl.deleteUserById(id);
    }
}
