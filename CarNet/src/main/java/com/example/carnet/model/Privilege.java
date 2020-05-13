package com.example.carnet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Collection;
@Table(name = "privileges")
@Entity
public class Privilege {
    @Id
    private int privilege_id;

    private String privilege_name;
    @JsonBackReference
    @ManyToMany
    private Collection<Role> roles;

    public int getPrivilegeId() {
        return privilege_id;
    }

    public void setPrivilegeId(int privilege_id) {
        this.privilege_id = privilege_id;
    }

    public String getPrivilegeName() {
        return privilege_name;
    }

    public void setPrivilegeName(String privilege_name) {
        this.privilege_name = privilege_name;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}

