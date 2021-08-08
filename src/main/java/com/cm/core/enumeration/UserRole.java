package com.cm.core.enumeration;

public enum UserRole {
    TEACHER("teacher"),
    STUDENT("student"),
    GUEST("guest"),
    SYS("system_admin");

    public String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }
}
