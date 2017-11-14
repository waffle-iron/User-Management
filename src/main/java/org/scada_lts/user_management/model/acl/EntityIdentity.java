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
}
