package com.placemates.dao.common;

import jakarta.persistence.*;

@Entity
@Table(name = "BRANCH")
public class BranchDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BR_ID")
    private Integer branchId;

    @Column(name = "NAME")
    private String name;

    public BranchDAO() {
    }

    public BranchDAO(Integer branchId, String name) {
        this.branchId = branchId;
        this.name = name;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
