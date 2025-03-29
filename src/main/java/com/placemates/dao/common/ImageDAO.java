package com.placemates.dao.common;

import jakarta.persistence.*;

@Entity
@Table(name = "IMAGE")
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

    public ImageDAO() {
    }

    public ImageDAO(Integer imageId, String name, String type, String category, byte[] data) {
        this.imageId = imageId;
        this.name = name;
        this.type = type;
        this.category = category;
        this.data = data;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
