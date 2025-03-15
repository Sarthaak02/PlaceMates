package com.placemates.dao.common;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "LOCATION")
@Data
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOC_ID")
    private Integer locationId;

    @Column(name = "CITY")
    private String city;
}
