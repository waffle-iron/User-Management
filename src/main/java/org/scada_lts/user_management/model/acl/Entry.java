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

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", entity=" + entity +
                ", order=" + order +
                ", sid=" + sid +
                ", mask=" + mask +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        if (id != null ? !id.equals(entry.id) : entry.id != null) return false;
        if (entity != null ? !entity.equals(entry.entity) : entry.entity != null) return false;
        if (order != null ? !order.equals(entry.order) : entry.order != null) return false;
        if (sid != null ? !sid.equals(entry.sid) : entry.sid != null) return false;
        return mask != null ? mask.equals(entry.mask) : entry.mask == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (entity != null ? entity.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (sid != null ? sid.hashCode() : 0);
        result = 31 * result + (mask != null ? mask.hashCode() : 0);
        return result;
    }
}
