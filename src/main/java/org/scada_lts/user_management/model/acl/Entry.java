package org.scada_lts.user_management.model.acl;

import java.util.List;

public class Entry {

    private List<EntityIdentity> entities;
    private Byte mask;
    private Sid sid;

    public List<EntityIdentity> getEntities() {
        return entities;
    }

    public void setEntities(List<EntityIdentity> entities) {
        this.entities = entities;
    }

    public Byte getMask() {
        return mask;
    }

    public void setMask(Byte mask) {
        this.mask = mask;
    }

    public Sid getSid() {
        return sid;
    }

    public void setSid(Sid sid) {
        this.sid = sid;
    }
}
