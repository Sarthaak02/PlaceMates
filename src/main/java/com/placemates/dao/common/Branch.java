package com.placemates.dao.common;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "BRANCH")
@Data
@AllArgsConstructor
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BR_ID")
    private Integer branchId;

    @Column(name = "NAME")
    private String name;
}
