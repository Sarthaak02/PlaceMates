package com.placemates.dao.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "BRANCH")
public class BranchDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BR_ID")
    private Integer branchId;

    @Column(name = "NAME")
    private String name;
}
