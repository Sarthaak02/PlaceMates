package com.placemates.dao.company;

import com.placemates.dao.common.BranchDAO;
import com.placemates.dao.common.LocationDAO;
import jakarta.persistence.*;

@Entity
@Table(name = "CMP_BR_LOC")
public class CompanyBranchLocationDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CBL_ID")
    private Integer companyBranchLocationId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "CMP_ID")
    private CompanyDAO companyDAO;

    @ManyToOne
    @JoinColumn(name = "BR_ID")
    private BranchDAO branchDAO;

    @ManyToOne
    @JoinColumn(name = "LOC_ID")
    private LocationDAO locationDAO;

    public CompanyBranchLocationDAO() {
    }

    public CompanyBranchLocationDAO(Integer companyBranchLocationId, CompanyDAO companyDAO, BranchDAO branchDAO, LocationDAO locationDAO) {
        this.companyBranchLocationId = companyBranchLocationId;
        this.companyDAO = companyDAO;
        this.branchDAO = branchDAO;
        this.locationDAO = locationDAO;
    }

    public Integer getCompanyBranchLocationId() {
        return companyBranchLocationId;
    }

    public void setCompanyBranchLocationId(Integer companyBranchLocationId) {
        this.companyBranchLocationId = companyBranchLocationId;
    }

    public CompanyDAO getCompanyDAO() {
        return companyDAO;
    }

    public void setCompanyDAO(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }

    public BranchDAO getBranchDAO() {
        return branchDAO;
    }

    public void setBranchDAO(BranchDAO branchDAO) {
        this.branchDAO = branchDAO;
    }

    public LocationDAO getLocationDAO() {
        return locationDAO;
    }

    public void setLocationDAO(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }
}
