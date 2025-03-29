package com.placemates.dto.company;

import com.placemates.dto.common.ImageDTO;
import com.placemates.enums.EmployeeType;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class CompanyDTO {
    private Integer companyId;

    @NotBlank
    private String name;

    private EmployeeType employeeType;
    private String designation;
    private BigDecimal ctc;
    private String eligibilityCriteria;
    private String skill;
    private String jobDescription;
    private ImageDTO imageDTO;

    public CompanyDTO() {
    }

    public CompanyDTO(Integer companyId, String name, EmployeeType employeeType, String designation, BigDecimal ctc, String eligibilityCriteria, String skill, String jobDescription, ImageDTO imageDTO) {
        this.companyId = companyId;
        this.name = name;
        this.employeeType = employeeType;
        this.designation = designation;
        this.ctc = ctc;
        this.eligibilityCriteria = eligibilityCriteria;
        this.skill = skill;
        this.jobDescription = jobDescription;
        this.imageDTO = imageDTO;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
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

    public String getEligibilityCriteria() {
        return eligibilityCriteria;
    }

    public void setEligibilityCriteria(String eligibilityCriteria) {
        this.eligibilityCriteria = eligibilityCriteria;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public ImageDTO getImageDTO() {
        return imageDTO;
    }

    public void setImageDTO(ImageDTO imageDTO) {
        this.imageDTO = imageDTO;
    }
}
