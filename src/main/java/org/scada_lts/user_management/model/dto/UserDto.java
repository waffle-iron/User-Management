package org.scada_lts.user_management.model.dto;

import java.io.Serializable;

public class UserDto implements Serializable {

    private Long id;
    private String name;
    private String password;

    public UserDto() {
        this.name="test";
    }

    public UserDto(String name, String password) {
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
