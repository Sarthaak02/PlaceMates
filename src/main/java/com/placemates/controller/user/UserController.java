package com.placemates.controller.user;

import com.placemates.dto.user.UserDTO;
import com.placemates.service.user.UserServiceImplementation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImplementation userServiceImplementation;

    public UserController(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @PostMapping("/register")
    UserDTO registerUser(@Valid @RequestBody UserDTO userDTO){
        return userServiceImplementation.createUser(userDTO);
    }

    @GetMapping("/{id}")
    UserDTO getUser(@PathVariable Integer id){
        return userServiceImplementation.getUserById(id);
    }

    @GetMapping("")
    List<UserDTO> getUsers(){
        return userServiceImplementation.getAllUsers();
    }

    @PutMapping("/{id}")
    UserDTO updateUser(@PathVariable Integer id, @Valid @RequestBody UserDTO userDTO){
        return userServiceImplementation.updateUserById(id, userDTO);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Integer id){
        userServiceImplementation.deleteUserById(id);
    }
}
