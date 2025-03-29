package com.placemates.dao.user;

import jakarta.persistence.*;

@Entity
@Table(name = "ROLE")
public class RoleDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Integer roleId;

    @Column(name = "NAME")
    private String name;

    public RoleDAO() {
    }

    public RoleDAO(Integer roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
