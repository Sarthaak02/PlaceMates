package com.placemates.dao.placedalums;

import com.placemates.dao.common.BatchDAO;
import com.placemates.dao.common.BranchDAO;
import com.placemates.dao.company.CompanyDAO;
import com.placemates.dao.common.ImageDAO;
import com.placemates.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "PLACED_ALUMS")
@Data
@AllArgsConstructor
public class PlacedAlumsDAO {

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
    private BranchDAO branchDAO;

    @ManyToOne
    @JoinColumn(name = "BAT_ID")
    private BatchDAO batchDAO;

    @ManyToOne
    @JoinColumn(name = "CMP_ID")
    private CompanyDAO companyDAO;

    @ManyToOne
    @JoinColumn(name = "IMG_ID")
    private ImageDAO imageDAO;
}
