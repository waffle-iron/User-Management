package org.scada_lts.user_management.model;

import java.util.ArrayList;
import java.util.List;

public class Acl {

    private String name;

    private List<EntityClass> entityClasses = new ArrayList<EntityClass>();

    private List<EntityInstance> entityInstances = new ArrayList<EntityInstance>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EntityClass> getEntityClasses() {
        return entityClasses;
    }

    public void setEntityClasses(List<EntityClass> entityClasses) {
        this.entityClasses = entityClasses;
    }

    public List<EntityInstance> getEntityInstances() {
        return entityInstances;
    }

    public void setEntityInstances(List<EntityInstance> entityInstances) {
        this.entityInstances = entityInstances;
    }
}
