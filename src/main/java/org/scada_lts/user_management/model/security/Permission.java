package org.scada_lts.user_management.model.security;

import java.io.Serializable;

public class Permission implements Serializable{

    private static final long serialVersionUID = -1726921088053422438L;
    // 1- read 1 - 2- write  etc
    private byte mask;

    public Permission() {
        //
    }

    public Permission(byte mask) {
        this.mask = mask;
    }
    public byte getMask() {
        return mask;
    }

    public void setMask(byte mask) {
        this.mask = mask;
    }
}
