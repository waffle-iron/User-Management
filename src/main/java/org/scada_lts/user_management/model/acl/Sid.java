package org.scada_lts.user_management.model.acl;

import java.io.Serializable;

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
