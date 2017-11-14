package org.scada_lts.user_management.model.acl;

import java.io.Serializable;

public class EntityClass implements Serializable {

    private static final long serialVersionUID = -5556324863284690511L;
    private Long id;
    private String className;

    public EntityClass() {
        //
    }

    public EntityClass(String className) {
        this.className = className;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
