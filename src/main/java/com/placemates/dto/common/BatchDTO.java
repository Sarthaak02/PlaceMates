package com.placemates.dto.common;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BatchDTO {

    private Integer batchId;

    @NotNull
    @Min(1000)
    @Max(9999)
    @Digits(integer = 4, fraction = 0)
    private Integer batchYear;

}
