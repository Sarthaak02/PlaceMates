package com.placemates.dao.common;

import jakarta.persistence.*;

@Entity
@Table(name = "LOCATION")
public class LocationDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOC_ID")
    private Integer locationId;

    @Column(name = "CITY")
    private String city;

    public LocationDAO() {
    }

    public LocationDAO(Integer locationId, String city) {
        this.locationId = locationId;
        this.city = city;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
