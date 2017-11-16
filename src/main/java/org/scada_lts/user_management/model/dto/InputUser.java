package org.scada_lts.user_management.model.dto;

import java.io.Serializable;

public class InputUser implements Serializable{

    private static final long serialVersionUID = -2645134875025168842L;
    private String name;
    private String passwd;

    public InputUser() {
        //
    }

    public InputUser(String name, String passwd) {
        this.name = name;
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "InputUser{" +
                "name='" + name + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InputUser inputUser = (InputUser) o;

        if (name != null ? !name.equals(inputUser.name) : inputUser.name != null) return false;
        return passwd != null ? passwd.equals(inputUser.passwd) : inputUser.passwd == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (passwd != null ? passwd.hashCode() : 0);
        return result;
    }
}
