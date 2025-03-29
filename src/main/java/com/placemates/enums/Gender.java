package com.placemates.enums;

public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Gender fromValue(String value){
        for(Gender gender : Gender.values()){
            if(gender.value.equalsIgnoreCase(value)) return gender;
        }
        throw new IllegalArgumentException("Invalid gender value: " + value);
    }
}
