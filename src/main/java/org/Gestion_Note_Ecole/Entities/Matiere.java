package org.Gestion_Note_Ecole.Entities;

public class Matiere {
    private int id;
    private String nom;

    public Matiere(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    // Getters et setters

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    // Getters et setters
}
