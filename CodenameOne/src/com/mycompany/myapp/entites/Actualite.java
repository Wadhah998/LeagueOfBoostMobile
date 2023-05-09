/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entites;

/**
 *
 * @author imtinen
 */
public class Actualite {
    int id;
    String Titre,Description;

    public Actualite(int id, String Titre, String Description) {
        this.id = id;
        this.Titre = Titre;
        this.Description = Description;
    }

    
    public Actualite(){}

    public Actualite(int id, String Description) {
        this.id = id;
        this.Description = Description;
    }

    public Actualite(String Titre, String Description) {
        this.Titre = Titre;
        this.Description = Description;
    }
    

    public int getId() {
        return id;
    }

    public String getTitre() {
        return Titre;
    }

    public String getDescription() {
        return Description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "Actualite{" + "id=" + id + ", Titre=" + Titre + ", Description=" + Description + '}';
    }
    
   
  


    



  

    
}
