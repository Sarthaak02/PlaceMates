package com.placemates.util.convertor;

import com.placemates.enums.Gender;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenderConvertor implements AttributeConverter<Gender,String> {

    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return (gender != null) ? gender.getValue() : null;
    }

    @Override
    public Gender convertToEntityAttribute(String value) {
        return (value != null) ? Gender.fromValue(value) : null;
    }
}
