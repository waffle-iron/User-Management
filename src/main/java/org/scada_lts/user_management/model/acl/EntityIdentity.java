package org.scada_lts.user_management.model.acl;

import java.io.Serializable;

public class EntityIdentity implements Serializable {

    private static final long serialVersionUID = 7079348216095537647L;
    private Long id;
    private String identityId;
    private EntityClass entityClass;
    private EntityIdentity entityIdentity;

    private EntityIdentity parent;
    private Sid owner;

    public EntityIdentity() {
        //
    }

    public EntityIdentity(String identityId, EntityClass entityClass, EntityIdentity entityIdentity, EntityIdentity parent, Sid owner) {
        this.identityId = identityId;
        this.entityClass = entityClass;
        this.entityIdentity = entityIdentity;
        this.parent = parent;
        this.owner = owner;
    }

    public EntityIdentity(String identityId, EntityClass entityClass, EntityIdentity parent, Sid owner) {
        this.identityId = identityId;
        this.entityClass = entityClass;
        this.entityIdentity = this;
        this.parent = parent;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public EntityClass getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(EntityClass entityClass) {
        this.entityClass = entityClass;
    }

    public EntityIdentity getEntityInstance() {
        return entityIdentity;
    }

    public void setEntityIdentity(EntityIdentity entityIdentity) {
        this.entityIdentity = entityIdentity;
    }

    public Sid getOwner() {
        return owner;
    }

    public void setOwner(Sid owner) {
        this.owner = owner;
    }

    public EntityIdentity getParent() {
        return parent;
    }

    public void setParent(EntityIdentity parent) {
        this.parent = parent;
    }

    public EntityIdentity getEntityIdentity() {
        return entityIdentity;
    }

    @Override
    public String toString() {
        return "EntityIdentity{" +
                "id=" + id +
                ", identityId='" + identityId + '\'' +
                ", entityClass=" + entityClass +
                ", entityIdentity=" + entityIdentity +
                ", parent=" + parent +
                ", owner=" + owner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityIdentity that = (EntityIdentity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (identityId != null ? !identityId.equals(that.identityId) : that.identityId != null) return false;
        if (entityClass != null ? !entityClass.equals(that.entityClass) : that.entityClass != null) return false;
        if (entityIdentity != null ? !entityIdentity.equals(that.entityIdentity) : that.entityIdentity != null)
            return false;
        if (parent != null ? !parent.equals(that.parent) : that.parent != null) return false;
        return owner != null ? owner.equals(that.owner) : that.owner == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (identityId != null ? identityId.hashCode() : 0);
        result = 31 * result + (entityClass != null ? entityClass.hashCode() : 0);
        result = 31 * result + (entityIdentity != null ? entityIdentity.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }
}
