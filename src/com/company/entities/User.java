/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.entities;

/**
 *
 * @author daoid
 */
    public class User {
    private  int id;
    private String username;
    private String roles;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String voie;
    private String lienOpgg;
    private String description;
    private Integer solde;
    private Integer prix;
    private boolean disponibility;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getVoie() {
        return voie;
    }

    public String getLienOpgg() {
        return lienOpgg;
    }

    public String getDescription() {
        return description;
    }

    public Integer getSolde() {
        return solde;
    }

    public Integer getPrix() {
        return prix;
    }

    public boolean isDisponibility() {
        return disponibility;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setVoie(String voie) {
        this.voie = voie;
    }

    public void setLienOpgg(String lienOpgg) {
        this.lienOpgg = lienOpgg;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSolde(Integer solde) {
        this.solde = solde;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public void setDisponibility(boolean disponibility) {
        this.disponibility = disponibility;
    }
            
            
      

    public User(String username, String roles, String password, String firstname, String lastname, String email, String voie, String lienOpgg, String description, Integer solde, Integer prix, boolean disponibility) {
        this.username = username;
        this.roles = roles;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.voie = voie;
        this.lienOpgg = lienOpgg;
        this.description = description;
        this.solde = solde;
        this.prix = prix;
        this.disponibility = disponibility;
        this.id = 0;
    }

    public User(int id, String username, String roles, String password, String firstname, String lastname, String email, String voie, String lienOpgg, String description, Integer solde, Integer prix, boolean disponibility) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.voie = voie;
        this.lienOpgg = lienOpgg;
        this.description = description;
        this.solde = solde;
        this.prix = prix;
        this.disponibility = disponibility;
    }
    
    
}

