package com.placemates.dao.company;

import com.placemates.dao.common.BranchDAO;
import com.placemates.dao.common.ImageDAO;
import com.placemates.dao.common.LocationDAO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "COMPANY")
public class CompanyDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPANY_ID")
    private Integer companyId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMPLOYEE_TYPE")
    private String employeeType;

    @Column(name = "DESIGNATION")
    private String designation;

    @Column(name = "CTC")
    private BigDecimal ctc;

    @Column(name = "ELIGIBILITY_CRITERIA")
    private String eligibilityCriteria;

    @Column(name = "SKILL")
    private String skill;

    @Column(name = "JOB_DESCRIPTION")
    private String jobDescription;

    @ManyToOne
    @JoinColumn(name = "IMAGE_ID")
    private ImageDAO imageDAO;

    @ManyToMany
    @JoinTable(
            name = "COMPANY_BRANCH",
            joinColumns = @JoinColumn(name = "COMPANY_ID"),
            inverseJoinColumns = @JoinColumn(name = "BRANCH_ID")
    )
    private List<BranchDAO> branchDAOs;

    @ManyToMany
    @JoinTable(
            name = "COMPANY_LOCATION",
            joinColumns = @JoinColumn(name = "COMPANY_ID"),
            inverseJoinColumns = @JoinColumn(name = "LOCATION_ID")
    )
    private List<LocationDAO> locationDAOs;
}
