package com.dictionary.models;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by home on 4/11/2020.
 */
@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "authority")
    private String authority;

    public Role() {
        //empty
    }


    public Role(Role role) {
        this.id = role.getId();
        this.username = role.getUsername();
        this.authority = role.getAuthority();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
