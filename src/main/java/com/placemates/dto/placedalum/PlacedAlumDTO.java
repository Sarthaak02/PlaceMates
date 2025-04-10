package com.placemates.dto.placedalum;

import com.placemates.dto.common.BranchDTO;
import com.placemates.dto.common.ImageDTO;
import com.placemates.dto.company.CompanyDTO;
import com.placemates.enums.Gender;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class PlacedAlumDTO {

    private Integer placedAlumId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String designation;

    private BigDecimal ctc;

    @Pattern(regexp = "^\\d{10}$")
    private String mobileNumber;

    @Email
    @NotBlank
    private String mail;

    private String linkText;

    @Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$|^(www\\.[a-zA-Z0-9-]+\\.[a-zA-Z]{2,})$")
    private String link;
    
    private Gender gender;
    private BranchDTO branchDTO;
    private Integer batch;

    @NotNull
    private CompanyDTO companyDTO;

    private ImageDTO imageDTO;

    public PlacedAlumDTO() {
    }

    public PlacedAlumDTO(Integer placedAlumId, String firstName, String lastName, String designation, BigDecimal ctc, String mobileNumber, String mail, String linkText, String link, Gender gender, BranchDTO branchDTO, Integer batch, CompanyDTO companyDTO, ImageDTO imageDTO) {
        this.placedAlumId = placedAlumId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
        this.ctc = ctc;
        this.mobileNumber = mobileNumber;
        this.mail = mail;
        this.linkText = linkText;
        this.link = link;
        this.gender = gender;
        this.branchDTO = branchDTO;
        this.batch = batch;
        this.companyDTO = companyDTO;
        this.imageDTO = imageDTO;
    }

    public Integer getPlacedAlumId() {
        return placedAlumId;
    }

    public void setPlacedAlumId(Integer placedAlumId) {
        this.placedAlumId = placedAlumId;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public BigDecimal getCtc() {
        return ctc;
    }

    public void setCtc(BigDecimal ctc) {
        this.ctc = ctc;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public BranchDTO getBranchDTO() {
        return branchDTO;
    }

    public void setBranchDTO(BranchDTO branchDTO) {
        this.branchDTO = branchDTO;
    }

    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    public CompanyDTO getCompanyDTO() {
        return companyDTO;
    }

    public void setCompanyDTO(CompanyDTO companyDTO) {
        this.companyDTO = companyDTO;
    }

    public ImageDTO getImageDTO() {
        return imageDTO;
    }

    public void setImageDTO(ImageDTO imageDTO) {
        this.imageDTO = imageDTO;
    }
}
