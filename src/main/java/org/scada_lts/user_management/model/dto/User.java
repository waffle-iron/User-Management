package org.scada_lts.user_management.model.dto;

public class User {

    private String name;

    public User() {
        this.name="test";
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
