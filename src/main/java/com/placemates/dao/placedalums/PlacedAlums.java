package com.placemates.dao.placedalums;

import com.placemates.dao.common.Batch;
import com.placemates.dao.common.Branch;
import com.placemates.dao.company.Company;
import com.placemates.dao.common.Image;
import com.placemates.dao.user.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "PLACED_ALUMS")
@Data
@AllArgsConstructor
public class PlacedAlums {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PA_ID")
    private Integer placedAlumsId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "DESIGNATION")
    private String designation;

    @Column(name = "CTC")
    private BigDecimal ctc;

    @Column(name = "MOB_NO")
    private Integer mobileNumber;

    @Column(name = "EMAIL")
    private String email;

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
    @JoinColumn(name = "CMP_ID")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "IMG_ID")
    private Image image;
}
