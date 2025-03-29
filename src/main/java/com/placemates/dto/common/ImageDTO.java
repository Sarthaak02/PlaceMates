package com.placemates.dto.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ImageDTO {

    private Integer imageId;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotBlank
    private String category;

    @NotNull
    private byte[] data;

    public ImageDTO() {
    }

    public ImageDTO(Integer imageId, String name, String type, String category, @NotNull byte[] data) {
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
