package org.scada_lts.user_management.model.acl;

import java.io.Serializable;

public class Entry implements Serializable {

    private static final long serialVersionUID = 2690417547404078167L;
    private Long id;
    private EntityIdentity entity;
    private Long order;
    private Sid sid;
    private Byte mask;

    public Entry() {
        //
    }

    public Entry(EntityIdentity entity, Long order, Sid sid, Byte mask) {
        this.entity = entity;
        this.order = order;
        this.sid = sid;
        this.mask = mask;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EntityIdentity getEntity() {
        return entity;
    }

    public void setEntity(EntityIdentity entity) {
        this.entity = entity;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public Sid getSid() {
        return sid;
    }

    public void setSid(Sid sid) {
        this.sid = sid;
    }

    public Byte getMask() {
        return mask;
    }

    public void setMask(Byte mask) {
        this.mask = mask;
    }

}
