package com.placemates.dto.user;

import com.placemates.dto.common.ImageDTO;
import com.placemates.dto.common.LocationDTO;
import com.placemates.dto.common.BranchDTO;
import com.placemates.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserDTO {

    private Integer userId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String mail;

    private String mobileNumber;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@#$%^&(){}\\[\\]:;<>,/~_+\\-=|\\\\]).{8,32}$")
    private String password;

    private String organisation;
    private String designation;
    private String linkText;

    @Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$|^(www\\.[a-zA-Z0-9-]+\\.[a-zA-Z]{2,})$")
    private String link;

    private Gender gender;
    private Integer batch;
    private BranchDTO branchDTO;
    private LocationDTO locationDTO;
    private RoleDTO roleDTO;
    private ImageDTO imageDTO;

    public UserDTO() {
    }

    public UserDTO(Integer userId, String firstName, String lastName, String mail, String mobileNumber, String password, String organisation, String designation, String linkText, String link, Gender gender, Integer batch, BranchDTO branchDTO, LocationDTO locationDTO, RoleDTO roleDTO, ImageDTO imageDTO) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.organisation = organisation;
        this.designation = designation;
        this.linkText = linkText;
        this.link = link;
        this.gender = gender;
        this.batch = batch;
        this.branchDTO = branchDTO;
        this.locationDTO = locationDTO;
        this.roleDTO = roleDTO;
        this.imageDTO = imageDTO;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    public BranchDTO getBranchDTO() {
        return branchDTO;
    }

    public void setBranchDTO(BranchDTO branchDTO) {
        this.branchDTO = branchDTO;
    }

    public LocationDTO getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(LocationDTO locationDTO) {
        this.locationDTO = locationDTO;
    }

    public RoleDTO getRoleDTO() {
        return roleDTO;
    }

    public void setRoleDTO(RoleDTO roleDTO) {
        this.roleDTO = roleDTO;
    }

    public ImageDTO getImageDTO() {
        return imageDTO;
    }

    public void setImageDTO(ImageDTO imageDTO) {
        this.imageDTO = imageDTO;
    }
}
