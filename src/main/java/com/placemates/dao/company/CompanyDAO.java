package com.placemates.dao.company;

import com.placemates.dao.common.ImageDAO;
import com.placemates.enums.EmployeeType;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "COMPANY")
public class CompanyDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CMP_ID")
    private Integer companyId;

    @Column(name = "NAME")
    private String name;

    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    @Column(name = "DESIGNATION")
    private String designation;

    @Column(name = "CTC")
    private BigDecimal ctc;

    @Column(name = "ELG_CT")
    private String eligibilityCriteria;

    @Column(name = "SKILL")
    private String skill;

    @Column(name = "JOB_DESC")
    private String jobDescription;

    @ManyToOne
    @JoinColumn(name = "IMG_ID")
    private ImageDAO imageDAO;

    public CompanyDAO() {
    }

    public CompanyDAO(Integer companyId, String name, EmployeeType employeeType, String designation, BigDecimal ctc, String eligibilityCriteria, String skill, String jobDescription, ImageDAO imageDAO) {
        this.companyId = companyId;
        this.name = name;
        this.employeeType = employeeType;
        this.designation = designation;
        this.ctc = ctc;
        this.eligibilityCriteria = eligibilityCriteria;
        this.skill = skill;
        this.jobDescription = jobDescription;
        this.imageDAO = imageDAO;
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

    public ImageDAO getImageDAO() {
        return imageDAO;
    }

    public void setImageDAO(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }
}
