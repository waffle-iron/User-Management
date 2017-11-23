/*
 * (c) 2017 Abil'I.T. http://abilit.eu/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.scada_lts.user_management.model.dto;

import org.scada_lts.user_management.model.acl.EntityClass;
import org.scada_lts.user_management.model.acl.Sid;
import org.scada_lts.user_management.model.security.Permission;

import java.io.Serializable;

/**
 * @author Grzegorz Bylica grzegorz.bylica@gmail.com
 **/
public class InputFilterAcl implements Serializable{

    private static final long serialVersionUID = -6800144472009108246L;
    private Sid sid;
    private EntityClass entityClass;
    private Permission permision;

    /**
     * Constructor maker "InputFilterAcl"
     */
    public InputFilterAcl() {
        //
    }

    /**
     * Constructor maker "InputFilterAcl"
     * @param sid
     * @param entityClass
     * @param permision
     */
    public InputFilterAcl(Sid sid, EntityClass entityClass, Permission permision) {
        this.sid = sid;
        this.entityClass = entityClass;
        this.permision = permision;
    }

    public Sid getSid() {
        return sid;
    }

    public void setSid(Sid sid) {
        this.sid = sid;
    }

    public EntityClass getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(EntityClass entityClass) {
        this.entityClass = entityClass;
    }

    public Permission getPermision() {
        return permision;
    }

    public void setPermision(Permission permision) {
        this.permision = permision;
    }

    @Override
    public String toString() {
        return "InputFilterAcl{" +
                "sid=" + sid +
                ", entityClass=" + entityClass +
                ", permision=" + permision +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InputFilterAcl that = (InputFilterAcl) o;

        if (sid != null ? !sid.equals(that.sid) : that.sid != null) return false;
        if (entityClass != null ? !entityClass.equals(that.entityClass) : that.entityClass != null) return false;
        return permision != null ? permision.equals(that.permision) : that.permision == null;
    }

    @Override
    public int hashCode() {
        int result = sid != null ? sid.hashCode() : 0;
        result = 31 * result + (entityClass != null ? entityClass.hashCode() : 0);
        result = 31 * result + (permision != null ? permision.hashCode() : 0);
        return result;
    }
}
