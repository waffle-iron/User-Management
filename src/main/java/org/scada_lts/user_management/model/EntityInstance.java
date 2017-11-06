package org.scada_lts.user_management.model;

import java.util.List;

public class EntityInstance {

    private String instanceId;
    private List<EntityClass> entitiesClass;
    private List<EntityInstance> entitiesInstance;

    private EntityInstance parent;
    private Sid owner;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public List<EntityClass> getEntitiesClass() {
        return entitiesClass;
    }

    public void setEntitiesClass(List<EntityClass> entitiesClass) {
        this.entitiesClass = entitiesClass;
    }

    public List<EntityInstance> getEntitiesInstance() {
        return entitiesInstance;
    }

    public void setEntitiesInstance(List<EntityInstance> entitiesInstance) {
        this.entitiesInstance = entitiesInstance;
    }

    public Sid getOwner() {
        return owner;
    }

    public void setOwner(Sid owner) {
        this.owner = owner;
    }

    public EntityInstance getParent() {
        return parent;
    }

    public void setParent(EntityInstance parent) {
        this.parent = parent;
    }
}
