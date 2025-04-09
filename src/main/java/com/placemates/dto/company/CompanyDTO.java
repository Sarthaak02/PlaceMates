package com.placemates.dto.company;

import com.placemates.dto.common.BranchDTO;
import com.placemates.dto.common.ImageDTO;
import com.placemates.dto.common.LocationDTO;
import com.placemates.dto.placedalum.PlacedAlumDTO;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.List;

public class CompanyDTO {
    private Integer companyId;

    @NotBlank
    private String name;

    private String employeeType;
    private String designation;
    private BigDecimal ctc;
    private String eligibilityCriteria;
    private String skill;
    private String jobDescription;
    private ImageDTO imageDTO;

    private List<PlacedAlumDTO> placedAlumDTOList;
    private List<BranchDTO> branchDTOList;
    private List<LocationDTO> locationDTOList;

    public CompanyDTO() {
    }

    public CompanyDTO(Integer companyId, String name, String employeeType, String designation, BigDecimal ctc, String eligibilityCriteria, String skill, String jobDescription, ImageDTO imageDTO, List<PlacedAlumDTO> placedAlumDTOList, List<BranchDTO> branchDTOList, List<LocationDTO> locationDTOList) {
        this.companyId = companyId;
        this.name = name;
        this.employeeType = employeeType;
        this.designation = designation;
        this.ctc = ctc;
        this.eligibilityCriteria = eligibilityCriteria;
        this.skill = skill;
        this.jobDescription = jobDescription;
        this.imageDTO = imageDTO;
        this.placedAlumDTOList = placedAlumDTOList;
        this.branchDTOList = branchDTOList;
        this.locationDTOList = locationDTOList;
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

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
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

    public List<PlacedAlumDTO> getPlacedAlumDTOList() {
        return placedAlumDTOList;
    }

    public void setPlacedAlumDTOList(List<PlacedAlumDTO> placedAlumDTOList) {
        this.placedAlumDTOList = placedAlumDTOList;
    }

    public List<BranchDTO> getBranchDTOList() {
        return branchDTOList;
    }

    public void setBranchDTOList(List<BranchDTO> branchDTOList) {
        this.branchDTOList = branchDTOList;
    }

    public List<LocationDTO> getLocationDTOList() {
        return locationDTOList;
    }

    public void setLocationDTOList(List<LocationDTO> locationDTOList) {
        this.locationDTOList = locationDTOList;
    }
}
