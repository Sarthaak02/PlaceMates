package com.placemates.dao.common;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "IMAGE")
@Data
@AllArgsConstructor
public class ImageDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMG_ID")
    private Integer imageId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "CATEGORY")
    private String category;

    @Lob
    @Column(name = "DATA")
    private byte[] data;
}
