package com.placemates.dao.placedalum;

import com.placemates.dao.common.BranchDAO;
import com.placemates.dao.common.ImageDAO;
import com.placemates.dao.company.CompanyDAO;
import com.placemates.enums.Gender;
import com.placemates.util.convertor.GenderConvertor;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PLACED_ALUM")
public class PlacedAlumDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PA_ID")
    private Integer placedAlumId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "DESIGNATION")
    private String designation;

    @Column(name = "CTC")
    private BigDecimal ctc;

    @Column(name = "MOB_NO")
    private String mobileNumber;

    @Column(name = "MAIL")
    private String mail;

    @Column(name = "LINK_TXT")
    private String linkText;

    @Column(name = "LINK")
    private String link;

    @Convert(converter = GenderConvertor.class)
    @Column(name = "GENDER")
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "BR_ID")
    private BranchDAO branchDAO;

    private Integer batch;

    @ManyToOne
    @JoinColumn(name = "CMP_ID")
    private CompanyDAO companyDAO;

    @ManyToOne
    @JoinColumn(name = "IMG_ID")
    private ImageDAO imageDAO;

    public PlacedAlumDAO() {
    }

    public PlacedAlumDAO(Integer placedAlumId, String firstName, String lastName, String designation, BigDecimal ctc, String mobileNumber, String mail, String linkText, String link, Gender gender, BranchDAO branchDAO, Integer batch, CompanyDAO companyDAO, ImageDAO imageDAO) {
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
        this.branchDAO = branchDAO;
        this.batch = batch;
        this.companyDAO = companyDAO;
        this.imageDAO = imageDAO;
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

    public BranchDAO getBranchDAO() {
        return branchDAO;
    }

    public void setBranchDAO(BranchDAO branchDAO) {
        this.branchDAO = branchDAO;
    }

    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    public CompanyDAO getCompanyDAO() {
        return companyDAO;
    }

    public void setCompanyDAO(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }

    public ImageDAO getImageDAO() {
        return imageDAO;
    }

    public void setImageDAO(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }
}
