package com.placemates.dto.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
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
}
