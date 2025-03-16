package com.placemates.enums;

public enum EmployeeType {
    FULL_TIME("Full Time"),
    PART_TIME("Part Time"),
    INTERN("Intern");

    private final String value;

    EmployeeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
