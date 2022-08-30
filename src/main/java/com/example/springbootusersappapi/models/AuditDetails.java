package com.example.springbootusersappapi.models;

import java.io.Serializable;

public class AuditDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    private String cratedBy;
    private String roleName;

    public AuditDetails() {
    }

    public AuditDetails(String cratedBy, String roleName) {
        this.cratedBy = cratedBy;
        this.roleName = roleName;
    }

    public String getCratedBy() {
        return cratedBy;
    }

    public void setCratedBy(String cratedBy) {
        this.cratedBy = cratedBy;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "AuditDetails{" +
                "cratedBy='" + cratedBy + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
