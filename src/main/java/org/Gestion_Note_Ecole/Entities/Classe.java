package org.Gestion_Note_Ecole.Entities;

import java.util.ArrayList;
import java.util.List;

public class Classe {
    private int id;
    private String nom;
    private List<Etudiant> etudiants;
    private List<Matiere> matieres;

    public Classe(int id, String nom) {
        this.id = id;
        this.nom = nom;
        this.etudiants = new ArrayList<>();
        this.matieres = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    // Getters et setters
    // Getters et setters
    public int getId() {
        return id;
    }

    public List<Matiere> getMatieres() {
        return matieres;
    }
}

