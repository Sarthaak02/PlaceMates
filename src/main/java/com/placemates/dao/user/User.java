package com.placemates.dao.user;

import com.placemates.dao.common.Batch;
import com.placemates.dao.common.Branch;
import com.placemates.dao.common.Image;
import com.placemates.dao.common.Location;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name="USER")
@Data
@AllArgsConstructor
public class User {

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
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "BAT_ID")
    private Batch batch;

    @ManyToOne
    @JoinColumn(name = "LOC_ID")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "IMG_ID")
    private Image image;
}
