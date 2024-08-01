package org.Gestion_Note_Ecole.Manager;
import org.Gestion_Note_Ecole.Entities.Classe;
import org.Gestion_Note_Ecole.Entities.Etudiant;
import org.Gestion_Note_Ecole.Entities.Matiere;
import org.Gestion_Note_Ecole.Entities.Note;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GestionNotes {
    public void enregistrerNote(Classe classe, Matiere matiere, Etudiant etudiant, double valeur) {
        Note note = new Note(0, valeur, matiere, etudiant);
        etudiant.getNotesParMatiere().computeIfAbsent(matiere, k -> new ArrayList<>()).add(note);
    }

    public double calculerMoyenne(Etudiant etudiant, Matiere matiere) {
        List<Note> notes = etudiant.getNotesParMatiere().get(matiere);
        if (notes == null || notes.isEmpty()) {
            return 0.0;
        }
        double somme = 0.0;
        for (Note note : notes) {
            somme += note.getValeur();
        }
        return somme / notes.size();
    }

    public double calculerMoyenneGénérale(Etudiant etudiant) {
        double somme = 0.0;
        int count = 0;
        for (List<Note> notes : etudiant.getNotesParMatiere().values()) {
            for (Note note : notes) {
                somme += note.getValeur();
                count++;
            }
        }
        return (count == 0) ? 0.0 : somme / count;
    }

    public List<Etudiant> EtablirClassement(Classe classe, Matiere matiere) {
        List<Etudiant> etudiants = new ArrayList<>(classe.getEtudiants());
        etudiants.sort(Comparator.comparingDouble(e -> -calculerMoyenne(e, matiere)));
        return etudiants;
    }
}
