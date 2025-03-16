package com.placemates.dao.company;

import com.placemates.dao.common.BranchDAO;
import com.placemates.dao.common.LocationDAO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "CMP_BR_LOC")
@Data
@AllArgsConstructor
public class CompanyBranchLocationDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CBL_ID")
    private Integer companyBranchLocationId;

    @ManyToOne
    @JoinColumn(name = "CMP_ID")
    private CompanyDAO companyDAO;

    @ManyToOne
    @JoinColumn(name = "BR_ID")
    private BranchDAO branchDAO;

    @ManyToOne
    @JoinColumn(name = "LOC_ID")
    private LocationDAO locationDAO;
}
