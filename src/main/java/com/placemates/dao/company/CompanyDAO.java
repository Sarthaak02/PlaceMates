package com.placemates.dao.company;

import com.placemates.dao.common.ImageDAO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "COMPANY")
public class CompanyDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CMP_ID")
    private Integer companyId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMP_TYPE")
    private String employeeType;

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
}
