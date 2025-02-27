package com.placemates.dao.alumni;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Alumni")
public class AlumniDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alumni_id")
    private int id;

    @NotBlank(message = "Alumni's first name can not be null or empty")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Alumni's last name can not be null or empty")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Alumni's branch can not be null or empty")
    @Column(name = "branch")
    private String branch;

    @NotNull(message = "Alumni's graduation year can not be null or empty")
    @Column(name = "graduation_year")
    private String graduationYear;

    @NotBlank(message = "Alumni's company name can not be null or empty")
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "designation")
    private String designation;

    @Column(name = "ctc")
    private String ctc;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank(message = "Alumni's email name can not be null or empty")
    @Column(name = "email")
    private String email;

    @Column(name = "social_link")
    private String socialLink;

    public AlumniDAO() {}

    public AlumniDAO(int id, String firstName, String lastName, String branch, String graduationYear, String companyName, String designation, String ctc, String phoneNumber, String email, String socialLink) {
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
