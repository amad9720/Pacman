import java.io.*;
import java.util.*;

/**
 * Created by Amadou Ly,Cheickhouna Lo, et Ismael Goulani le 26-01-2017.
 */

public class LecteurFichier {

    public static int[][] grille;
    public static int numColonnes, numLignes;

    public LecteurFichier() {

    }

    public static void lireFichier(String fichier) {
        try {
            Scanner sc = new Scanner(new File(fichier));
            numLignes = sc.nextInt();
            numColonnes = sc.nextInt();
            sc.nextLine();
            grille = new int[numLignes][numColonnes];

            int ligneCourante = 0;
            char tempChar;
            String tempLigne;
            while (sc.hasNextLine()) {
                tempLigne = sc.nextLine();
                for (int colonne = 0; colonne < numColonnes; colonne++) {
                    tempChar = tempLigne.charAt(colonne);
                    switch (tempChar) {
                        case '%': // mur
                            grille[ligneCourante][colonne] = 1;
                            break;
                        case '|': // teleportation
                            grille[ligneCourante][colonne] = 2;
                            break;
                        case '*': // porte
                            grille[ligneCourante][colonne] = 3;
                            break;
                        case 'R':
                            grille[ligneCourante][colonne] = 4;
                            break;
                        case 'P':
                            grille[ligneCourante][colonne] = 5;
                            break;
                        case 'O':
                            grille[ligneCourante][colonne] = 6;
                            break;
                        case 'B':
                            grille[ligneCourante][colonne] = 7;
                            break;
                        case 'E':
                            grille[ligneCourante][colonne] = 8;
                            break;
                        case 'J':
                            grille[ligneCourante][colonne] = 9;
                            break;
                        case 'K':
                            grille[ligneCourante][colonne] = 10;
                            break;
                        case '.':
                            grille[ligneCourante][colonne] = 11;
                            break;
                        default: // espace vide (fruit)
                            grille[ligneCourante][colonne] = 0;
                            break;
                    }
                }
                ligneCourante++;
                if (ligneCourante >= numLignes) {
                    break;
                }
            }
            sc.close();
        }

        catch (Exception e) {
            System.out.println("Erreur: Lecture impossible du fichier.");
        }
    }

}