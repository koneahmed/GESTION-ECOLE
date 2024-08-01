package org.Gestion_Note_Ecole.Entities;

public class Note {
    private int id;
    private double valeur;
    private Matiere matiere;
    private Etudiant etudiant;

    public Note(int id, double valeur, Matiere matiere, Etudiant etudiant) {
        this.id = id;
        this.valeur = valeur;
        this.matiere = matiere;
        this.etudiant = etudiant;
    }

    // Getters et setters

    public int getId() {
        return id;
    }

    public double getValeur() {
        return valeur;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }
}

