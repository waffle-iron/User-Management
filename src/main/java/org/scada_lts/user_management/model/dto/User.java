package org.scada_lts.user_management.model.dto;

public class User {

    private Long id;
    private String name;
    private String passwordMd5;

    public User() {
        this.name="test";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPasswordMd5() {
        return passwordMd5;
    }

    public void setPasswordMd5(String passwordmd5) {
        this.passwordMd5 = passwordmd5;
    }
}
