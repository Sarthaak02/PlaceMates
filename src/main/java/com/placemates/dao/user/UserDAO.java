package com.placemates.dao.user;

import com.placemates.dao.common.BatchDAO;
import com.placemates.dao.common.BranchDAO;
import com.placemates.dao.common.ImageDAO;
import com.placemates.dao.common.LocationDAO;
import com.placemates.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name="USER")
@Data
@AllArgsConstructor
public class UserDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "MOB_NO")
    private Integer mobileNumber;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ORGANIZATION")
    private String organization;

    @Column(name = "DESIGNATION")
    private String designation;

    @Column(name = "LINK_TXT")
    private String linkText;

    @Column(name = "LINK")
    private String link;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "BR_ID")
    private BranchDAO branchDAO;

    @ManyToOne
    @JoinColumn(name = "BAT_ID")
    private BatchDAO batchDAO;

    @ManyToOne
    @JoinColumn(name = "LOC_ID")
    private LocationDAO locationDAO;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private RoleDAO roleDAO;

    @ManyToOne
    @JoinColumn(name = "IMG_ID")
    private ImageDAO imageDAO;
}
