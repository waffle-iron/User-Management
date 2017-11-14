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

}
