package org.Gestion_Note_Ecole;

import org.Gestion_Note_Ecole.Entities.Classe;
import org.Gestion_Note_Ecole.Entities.Etudiant;
import org.Gestion_Note_Ecole.Entities.Matiere;
import org.Gestion_Note_Ecole.Manager.GestionClasses;
import org.Gestion_Note_Ecole.Manager.GestionEtudiants;
import org.Gestion_Note_Ecole.Manager.GestionMatieres;
import org.Gestion_Note_Ecole.Manager.GestionNotes;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static GestionClasses gestionClasses = new GestionClasses();
    private static GestionEtudiants gestionEtudiants = new GestionEtudiants();
    private static GestionMatieres gestionMatieres = new GestionMatieres();
    private static GestionNotes gestionNotes = new GestionNotes();

    public static void main(String[] args) {
        while (true) {
            afficherMenu();
            try {
                Scanner scanner = new Scanner(System.in);
                int choix = scanner.nextInt();
                scanner.nextLine();  // Consomme la nouvelle ligne
                switch (choix) {
                    case 1:
                        ajouterClasse(scanner);
                        break;
                    case 2:
                        ajouterEtudiant(scanner);
                        break;
                    case 3:
                        ajouterMatiere(scanner);
                        break;
                    case 4:
                        enregistrerNotes(scanner);
                        break;
                    case 5:
                        calculerMoyennes(scanner);
                        break;
                    case 6:
                        établirClassement(scanner);
                        break;
                    case 0:
                        System.out.println("Au revoir !");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Choix invalide. Veuillez entrer une option comprise entre 1 et 6 ou 0 pour Quitter.");
                }
            }catch (Exception e){
                System.out.println("L'Option que vous entrez n'est pas correct");
                System.out.println("Veuillez entrer une bonne option");

            }
        }
    }

    private static void afficherMenu() {
        System.out.println("\nMenu de Gestion des Notes");
        System.out.println("1. Ajouter une classe");
        System.out.println("2. Ajouter un étudiant");
        System.out.println("3. Ajouter une matière");
        System.out.println("4. Enregistrer des notes");
        System.out.println("5. Calculer les moyennes");
        System.out.println("6. Établir le classement");
        System.out.println("0. Quitter");
        System.out.print("Veuillez choisir une option : ");
    }

    private static void ajouterClasse(Scanner scanner) {
        // Recuperation de toute les classes
        List<Classe> ListClasses = gestionClasses.getClasses();
        if(ListClasses.isEmpty()){
            System.out.println("Aucunes classes existante || Creation d'une nouvelle classe ");
        }else{
            System.out.println("Voici la Liste des classes existantes dans le systeme");
            for(Classe c : ListClasses){
                System.out.println((ListClasses.indexOf(c)+1)+" ) CLASSE DE : "+c.getNom());
            }

        }
        System.out.print("Entrez le nom de la nouvelle classe : ");
        String nomClasse = scanner.nextLine();
        Classe classe = new Classe(gestionClasses.getClasses().size() + 1, nomClasse);
        if(!VerificationClasseExiste(ListClasses,nomClasse)){
            gestionClasses.ajouterClasse(classe);
            System.out.println("Classe ajoutée avec succès !");
        }

    }
    private static boolean VerificationClasseExiste(List<Classe>listeClasse,String NewClasseName){
        for(Classe c :listeClasse ){
            if(c.getNom().equalsIgnoreCase(NewClasseName)){
                System.out.println("La Classe "+NewClasseName+" existe deja dans le systeme");
                return true;
            }
        }
        return false;
    }

    private static void ajouterEtudiant(Scanner scanner) {
        System.out.print("Entrez le nom de la classe a laquelle l'etudiant appartiendra: ");
        String nomClasse = scanner.nextLine();
        Classe classe = gestionClasses.getClasseParNom(nomClasse);
        if (classe == null) {
            System.out.println("La Classe "+nomClasse.toUpperCase()+"  n'existe pas dans le systeme.");
            return;
        }
        System.out.print("Entrez le nom de l'étudiant : ");
        String nomEtudiant = scanner.nextLine();
        // Recuperation de l' etudiant de la classe
        Etudiant etudiant = gestionEtudiants.getEtudiantParNom(classe,nomEtudiant);
        if (etudiant != null) {
            gestionEtudiants.AfficheListEtudiants(classe);
            System.out.println("L'Etudiant "+etudiant.getNom()+" existe deja dans la classe "+nomClasse.toUpperCase());
        }else{
            Etudiant Student = new Etudiant(classe.getEtudiants().size() + 1, nomEtudiant, classe);
            gestionEtudiants.ajouterÉtudiant(classe, Student);
            System.out.println("Étudiant ajouté avec succès !");
        }
    }

    private static void ajouterMatiere(Scanner scanner) {
        System.out.print("Entrez le nom de la matière : ");
        String nomMatiere = scanner.nextLine();
        Matiere matiere = new Matiere(gestionMatieres.getMatières().size() + 1, nomMatiere);
        gestionMatieres.ajouterMatiere(matiere);
        System.out.println("Matière ajoutée avec succès !");
    }

    private static void enregistrerNotes(Scanner scanner) {
        System.out.print("Entrez le nom de la classe : ");
        String nomClasse = scanner.nextLine();
        Classe classe = gestionClasses.getClasseParNom(nomClasse);
        if (classe == null) {
            System.out.println("Classe non trouvée.");
            return;
        }

        System.out.print("Entrez le nom de la matière : ");
        String nomMatiere = scanner.nextLine();
        Matiere matiere = gestionMatieres.getMatiereParNom(nomMatiere);
        if (matiere == null) {
            System.out.println("Matière non trouvée.");
            return;
        }
        System.out.print("Entrez le nom de l'etudiant: ");
        String nomEtudiant = scanner.nextLine();
        // Recuperation de l' etudiant de la classe
        Etudiant etudiant = gestionEtudiants.getEtudiantParNom(classe,nomEtudiant);
        if (etudiant != null) {
            System.out.print("Entrez la note pour  l'Etudiant" + etudiant.getNom() + " : ");
            double valeur = scanner.nextDouble();
            gestionNotes.enregistrerNote(classe, matiere, etudiant, valeur);
            System.out.println("Notes enregistrées avec succès !");
        }else{
            System.out.println("Cet etudiant n'existe pas dans la classe "+classe.getNom());
        }

    }

    private static void calculerMoyennes(Scanner scanner) {
        System.out.print("Entrez le nom de la classe : ");
        String nomClasse = scanner.nextLine();
        Classe classe = gestionClasses.getClasseParNom(nomClasse);
        if (classe == null) {
            System.out.println("Classe non trouvée.");
            return;
        }

        for (Etudiant etudiant : classe.getEtudiants()) {
            double moyenneGenerale = gestionNotes.calculerMoyenneGénérale(etudiant);
            System.out.println("Moyenne générale de " + etudiant.getNom() + " : " + moyenneGenerale);
        }
    }

    private static void établirClassement(Scanner scanner) {
        System.out.print("Entrez le nom de la classe : ");
        String nomClasse = scanner.nextLine();
        Classe classe = gestionClasses.getClasseParNom(nomClasse);
        if (classe == null) {
            System.out.println("Classe non trouvée.");
            return;
        }

        System.out.print("Entrez le nom de la matière : ");
        String nomMatiere = scanner.nextLine();
        Matiere matiere = gestionMatieres.getMatiereParNom(nomMatiere);
        if (matiere == null) {
            System.out.println("Matière non trouvée.");
            return;
        }

        List<Etudiant> classement = gestionNotes.EtablirClassement(classe, matiere);
        System.out.println("Classement en " + matiere.getNom() + " :");
        for (Etudiant etudiant : classement) {
            double moyenne = gestionNotes.calculerMoyenne(etudiant, matiere);
            System.out.println(etudiant.getNom() + " : " + moyenne);
        }
    }
}
