package com.placemates.dao.placedalum;

import com.placemates.dao.common.BranchDAO;
import com.placemates.dao.common.ImageDAO;
import com.placemates.dao.company.CompanyDAO;
import com.placemates.enums.Gender;
import com.placemates.util.convertor.GenderConvertor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "PLACED_ALUM")
public class PlacedAlumDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLACED_ALUM_ID")
    private Integer placedAlumId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "DESIGNATION")
    private String designation;

    @Column(name = "CTC")
    private BigDecimal ctc;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "MAIL")
    private String mail;

    @Column(name = "LINK_TEXT")
    private String linkText;

    @Column(name = "LINK")
    private String link;

    @Convert(converter = GenderConvertor.class)
    @Column(name = "GENDER")
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID")
    private BranchDAO branchDAO;

    @Column(name = "BATCH_YEAR")
    private Integer batchYear;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private CompanyDAO companyDAO;

    @ManyToOne
    @JoinColumn(name = "IMAGE_ID")
    private ImageDAO imageDAO;
}
