package com.smbc.schoollibrary.constants;

public enum GenderEnum {
    MALE("M"), FEMALE("F");

    private final String code;

    GenderEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}