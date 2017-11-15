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

    @Override
    public String toString() {
        return "EntityClass{" +
                "id=" + id +
                ", className='" + className + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityClass that = (EntityClass) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return className != null ? className.equals(that.className) : that.className == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (className != null ? className.hashCode() : 0);
        return result;
    }
}
