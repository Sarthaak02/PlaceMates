package com.placemates.dao.company;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Company")
public class CompanyDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private int id;

    @NotBlank(message = "Company's name can not be null or empty")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Company's Role can not be null or empty")
    @Column(name = "role")
    private String role;

    @NotNull(message = "Company's CTC can not be null or empty")
    @Column(name = "ctc")
    private String ctc;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "logo_url")
    private String logoURL;

    @Column(name = "web_url")
    private String webURL;

    @Column(name = "allow_branch")
    private String allowBranch;

    @Column(name = "require_skills")
    private String requireSkills;

    @Column(name = "location")
    private String location;

    public CompanyDAO() {}

    public CompanyDAO(int id, String name, String role, String ctc, String jobDescription, String logoURL, String webURL, String allowBranch, String requireSkills, String location) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.ctc = ctc;
        this.jobDescription = jobDescription;
        this.logoURL = logoURL;
        this.webURL = webURL;
        this.allowBranch = allowBranch;
        this.requireSkills = requireSkills;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getCtc() {
        return ctc;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public String getWebURL() {
        return webURL;
    }

    public String getAllowBranch() {
        return allowBranch;
    }

    public String getRequireSkills() {
        return requireSkills;
    }

    public String getLocation() {
        return location;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCtc(String ctc) {
        this.ctc = ctc;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public void setWebURL(String webURL) {
        this.webURL = webURL;
    }

    public void setAllowBranch(String allowBranch) {
        this.allowBranch = allowBranch;
    }

    public void setRequireSkills(String requireSkills) {
        this.requireSkills = requireSkills;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
