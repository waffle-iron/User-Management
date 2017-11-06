package org.scada_lts.user_management.model.acl;

public class Sid {

    private Integer principal;
    private String Sid;

    public Integer getPrincipal() {
        return principal;
    }

    public void setPrincipal(Integer principal) {
        this.principal = principal;
    }

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }
}
