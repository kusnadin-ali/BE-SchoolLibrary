package com.smbc.schoollibrary.constants;

public enum StatusBookEnum {
    AVAILABLE("Available"), NOT_AVAILABLE("Not Available");

    private String status;

    StatusBookEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
