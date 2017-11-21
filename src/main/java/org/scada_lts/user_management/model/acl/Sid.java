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
package org.scada_lts.user_management.model.acl;

import java.io.Serializable;

/**
 * Sid Security Identifier
 * @author  grzegorz bylica grzegorz.bylica@gmail.com
 */
public class Sid implements Serializable{

    private static final long serialVersionUID = -4605852659773676229L;

    private Long id;
    private Long principal;
    private String sid;

    public Sid() {
        //
    }

    public Sid(Long principal, String sid) {
        this.principal = principal;
        this.sid = sid;
    }

    public Sid(Sid sid) {
        this.id = sid.getId();
        this.principal = sid.getPrincipal();
        this.sid = sid.getSid();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrincipal() {
        return principal;
    }

    public void setPrincipal(Long principal) {
        this.principal = principal;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "Sid{" +
                "id=" + id +
                ", principal=" + principal +
                ", sid='" + sid + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sid sid1 = (Sid) o;

        if (id != null ? !id.equals(sid1.id) : sid1.id != null) return false;
        if (principal != null ? !principal.equals(sid1.principal) : sid1.principal != null) return false;
        return sid != null ? sid.equals(sid1.sid) : sid1.sid == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (principal != null ? principal.hashCode() : 0);
        result = 31 * result + (sid != null ? sid.hashCode() : 0);
        return result;
    }
}
