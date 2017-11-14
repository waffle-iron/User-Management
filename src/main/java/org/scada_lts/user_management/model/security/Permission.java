package org.scada_lts.user_management.model.security;

public class Permission {

    // 1- read 1 - 2- write  etc
    private byte mask;

    public byte getMask() {
        return mask;
    }

    public void setMask(byte mask) {
        this.mask = mask;
    }
}
