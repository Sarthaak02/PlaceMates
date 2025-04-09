package com.placemates.controller.user;

import com.placemates.dto.user.UserDTO;
import com.placemates.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    UserDTO registerUser(@Valid @RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @GetMapping("/{id}")
    UserDTO getUser(@PathVariable Integer id){
        return userService.getUser(id);
    }

    @GetMapping("")
    List<UserDTO> getUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    UserDTO updateUser(@PathVariable Integer id, @Valid @RequestBody UserDTO userDTO){
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }
}
