package com.bytecraft.studentteachermanagement.model;

public enum CourseType {

    MAIN("Main"),
    SECONDARY("Secondary");

    private final String displayName;

    CourseType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
