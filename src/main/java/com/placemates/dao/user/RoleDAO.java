package com.placemates.dao.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "ROLE")
@Data
@AllArgsConstructor
public class RoleDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Integer roleId;

    @Column(name = "NAME")
    private String name;
}
