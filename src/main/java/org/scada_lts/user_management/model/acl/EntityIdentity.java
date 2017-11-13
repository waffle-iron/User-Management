package org.scada_lts.user_management.model.acl;

import java.util.List;

public class EntityIdentity {

    private Long id;
    private String identityId;
    private List<EntityClass> entitiesClass;
    private List<EntityIdentity> entitiesIdentity;

    private EntityIdentity parent;
    private Sid owner;

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

    public List<EntityClass> getEntitiesClass() {
        return entitiesClass;
    }

    public void setEntitiesClass(List<EntityClass> entitiesClass) {
        this.entitiesClass = entitiesClass;
    }

    public List<EntityIdentity> getEntitiesInstance() {
        return entitiesIdentity;
    }

    public void setEntitiesIdentity(List<EntityIdentity> entitiesIdentity) {
        this.entitiesIdentity = entitiesIdentity;
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

    public List<EntityIdentity> getEntitiesIdentity() {
        return entitiesIdentity;
    }
}
