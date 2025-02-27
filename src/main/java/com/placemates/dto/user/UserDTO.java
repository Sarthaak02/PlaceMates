package com.placemates.dto.user;

import jakarta.validation.constraints.*;

public class UserDTO {

    private int id;

    @NotBlank(message = "User's First name can not be null or empty")
    private String firstName;

    @NotBlank(message = "User's Last name can not be null or empty")
    private String lastName;

    @NotBlank(message = "User's Email address can not be null or empty")
    @Email(message = "User's Email address is not valid")
    private String email;

    @NotBlank(message = "User's Password can not be null or empty")
    @Size(min = 8, message = "User's Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "User's Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character (@#$%^&+=!)")
    private String password;


    @NotNull(message = "User's User Type can not be null")
    private String userType;

    public UserDTO() {}

    public UserDTO(int id, String firstName, String lastName, String email, String password, String userType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
