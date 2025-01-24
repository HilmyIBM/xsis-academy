package com.xsis.bc345.fe.models;

public class RoleView {
    private String id;
    private String roleName;

    

    public RoleView(String id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
