package org.scada_lts.user_management.model.dto;

import java.io.Serializable;

public class UserDto implements Serializable {

    private Long id;
    private String name;

    public UserDto() {
        this.name="test";
    }

    public UserDto(String name) {
        this.name = name;
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

}
