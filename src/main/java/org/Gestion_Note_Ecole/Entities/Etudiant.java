package org.Gestion_Note_Ecole.Entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Etudiant {
    private int id;
    private String nom;
    private Classe classe;
    private Map<Matiere, List<Note>> notesParMatiere;

    public Etudiant(int id, String nom, Classe classe) {
        this.id = id;
        this.nom = nom;
        this.classe = classe;
        this.notesParMatiere = new HashMap<>();
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public Classe getClasse() {
        return classe;
    }

    public Map<Matiere, List<Note>> getNotesParMatiere() {
        return notesParMatiere;
    }

    // Getters et setters
}

