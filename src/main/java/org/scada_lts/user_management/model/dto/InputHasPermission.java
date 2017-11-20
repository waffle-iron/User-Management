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

import org.scada_lts.user_management.model.acl.EntityIdentity;
import org.scada_lts.user_management.model.acl.Sid;
import org.scada_lts.user_management.model.security.Permission;

/**
 * @author Grzegorz Bylica grzegorz.bylica@gmail.com
 **/
public class InputHasPermission {

    private Sid sid;
    private EntityIdentity entityIdentity;
    private Permission permission;

    public InputHasPermission() {
        //
    }

    public InputHasPermission(Sid sid, EntityIdentity entityIdentity, Permission permission) {
        this.sid = sid;
        this.entityIdentity = entityIdentity;
        this.permission = permission;
    }

    public Sid getSid() {
        return sid;
    }

    public void setSid(Sid sid) {
        this.sid = sid;
    }

    public EntityIdentity getEntityIdentity() {
        return entityIdentity;
    }

    public void setEntityIdentity(EntityIdentity entityIdentity) {
        this.entityIdentity = entityIdentity;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "InputHasPermission{" +
                "sid=" + sid +
                ", entityIdentity=" + entityIdentity +
                ", permission=" + permission +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InputHasPermission that = (InputHasPermission) o;

        if (sid != null ? !sid.equals(that.sid) : that.sid != null) return false;
        if (entityIdentity != null ? !entityIdentity.equals(that.entityIdentity) : that.entityIdentity != null)
            return false;
        return permission != null ? permission.equals(that.permission) : that.permission == null;
    }

    @Override
    public int hashCode() {
        int result = sid != null ? sid.hashCode() : 0;
        result = 31 * result + (entityIdentity != null ? entityIdentity.hashCode() : 0);
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        return result;
    }
}
