package com.placemates.dto.alumni;

import jakarta.validation.constraints.*;

public class AlumniDTO {

    private int id;

    @NotBlank(message = "Alumni's first name can not be null or empty")
    private String firstName;

    @NotBlank(message = "Alumni's last name can not be null or empty")
    private String lastName;

    @NotBlank(message = "Alumni's branch can not be null or empty")
    private String branch;

    @NotNull(message = "Alumni's graduation year can not be null or empty")
    private String graduationYear;

    @NotBlank(message = "Alumni's company name can not be null or empty")
    private String companyName;

    private String designation;

    private String ctc;

    private String phoneNumber;

    @NotBlank(message = "Alumni's email name can not be null or empty")
    @Email(message = "Alumni's Email address is not valid")
    private String email;

    private String socialLink;

    public AlumniDTO() {}

    public AlumniDTO(int id, String firstName, String lastName, String branch, String graduationYear, String companyName, String designation, String ctc, String phoneNumber, String email, String socialLink) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.branch = branch;
        this.graduationYear = graduationYear;
        this.companyName = companyName;
        this.designation = designation;
        this.ctc = ctc;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.socialLink = socialLink;
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

    public String getBranch() {
        return branch;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getDesignation() {
        return designation;
    }

    public String getCtc() {
        return ctc;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getSocialLink() {
        return socialLink;
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

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setCtc(String ctc) {
        this.ctc = ctc;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSocialLink(String socialLink) {
        this.socialLink = socialLink;
    }
}
