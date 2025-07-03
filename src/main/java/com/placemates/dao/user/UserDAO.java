package com.placemates.dao.user;

import com.placemates.dao.common.BranchDAO;
import com.placemates.dao.common.ImageDAO;
import com.placemates.dao.common.LocationDAO;
import com.placemates.enums.Gender;
import com.placemates.util.convertor.GenderConvertor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
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

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ORGANISATION")
    private String organisation;

    @Column(name = "DESIGNATION")
    private String designation;

    @Column(name = "LINK_TEXT")
    private String linkText;

    @Column(name = "LINK")
    private String link;

    @Convert(converter = GenderConvertor.class)
    @Column(name = "GENDER")
    private Gender gender;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "BRANCH_ID")
    private BranchDAO branchDAO;

    @Column(name = "BATCH_YEAR")
    private Integer batchYear;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "LOCATION_ID")
    private LocationDAO locationDAO;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID")
    private RoleDAO roleDAO;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "IMAGE_ID")
    private ImageDAO imageDAO;
}
