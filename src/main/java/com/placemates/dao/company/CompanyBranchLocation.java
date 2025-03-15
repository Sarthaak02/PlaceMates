package com.placemates.dao.company;

import com.placemates.dao.common.Branch;
import com.placemates.dao.common.Location;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "CMP_BR_LOC")
@Data
@AllArgsConstructor
public class CompanyBranchLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CBL_ID")
    private Integer companyBranchLocationId;

    @ManyToOne
    @JoinColumn(name = "CMP_ID")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "BR_ID")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "LOC_ID")
    private Location location;
}
