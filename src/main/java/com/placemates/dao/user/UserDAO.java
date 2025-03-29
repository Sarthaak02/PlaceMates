package com.placemates.dao.user;

import com.placemates.dao.common.BranchDAO;
import com.placemates.dao.common.ImageDAO;
import com.placemates.dao.common.LocationDAO;
import com.placemates.enums.Gender;
import com.placemates.util.convertor.GenderConvertor;
import jakarta.persistence.*;

@Entity
@Table(name="USER")
public class UserDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MAIL")
    private String mail;

    @Column(name = "MOB_NO")
    private String mobileNumber;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ORGANISATION")
    private String organisation;

    @Column(name = "DESIGNATION")
    private String designation;

    @Column(name = "LINK_TXT")
    private String linkText;

    @Column(name = "LINK")
    private String link;

    @Convert(converter = GenderConvertor.class)
    @Column(name = "GENDER")
    private Gender gender;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "BR_ID")
    private BranchDAO branchDAO;

    private Integer batch;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "LOC_ID")
    private LocationDAO locationDAO;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID")
    private RoleDAO roleDAO;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "IMG_ID")
    private ImageDAO imageDAO;

    public UserDAO() {
    }

    public UserDAO(Integer userId, String firstName, String lastName, String mail, String mobileNumber, String password, String organisation, String designation, String linkText, String link, Gender gender, BranchDAO branchDAO, Integer batch, LocationDAO locationDAO, RoleDAO roleDAO, ImageDAO imageDAO) {
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
        this.branchDAO = branchDAO;
        this.batch = batch;
        this.locationDAO = locationDAO;
        this.roleDAO = roleDAO;
        this.imageDAO = imageDAO;
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

    public LocationDAO getLocationDAO() {
        return locationDAO;
    }

    public void setLocationDAO(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    public RoleDAO getRoleDAO() {
        return roleDAO;
    }

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public ImageDAO getImageDAO() {
        return imageDAO;
    }

    public void setImageDAO(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }
}
