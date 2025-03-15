package com.placemates.dao.company;

import com.placemates.dao.common.Image;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "COMPANY")
@Data
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CMP_ID")
    private Integer companyId;

    @Column(name = "NAME")
    private String name;

    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

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
    private Image image;
}
