package org.Gestion_Note_Ecole.Manager;

import org.Gestion_Note_Ecole.Entities.Classe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GestionClasses {
    private List<Classe> classes;

    public GestionClasses() {
        this.classes = new ArrayList<>();
    }

    public void ajouterClasse(Classe classe) {
        classes.add(classe);
    }

    public Classe getClasseParNom(String nom) {
        for (Classe classe : classes) {
            if (classe.getNom().equals(nom)) {
                return classe;
            }
        }
        return null;
    }

    // Getters

    public List<Classe> getClasses() {
        return classes;
    }
}

