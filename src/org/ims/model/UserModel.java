/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ims.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author BENJIRO
 */
@Entity
@Table
public class UserModel {
    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column
    private String last_name;
    
    @Column
    private String first_name;
    
    @Column(nullable = true)
    private String other_name;
    
    @Column(unique = true)
    private String username;
    
    @Column
    private String password;
    

    public UserModel() {
    }
    
    //constructor with parameters
     public UserModel(String last_name, String first_name, String other_name, String username, String password) {
         this.last_name = last_name;
         this.first_name = first_name;
         this.other_name = other_name;
         this.username = username;
         this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getOther_name() {
        return other_name;
    }

    public void setOther_name(String other_name) {
        this.other_name = other_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }
    
    public String getFull_name() {
        if(this.other_name == null)
        {
            return this.first_name + " " + this.last_name;
        }
        return this.first_name + " " + this.other_name + " " + this.last_name;
    }

    
     
     
}
