/**
 * Created by Amadou Ly,Cheickhouna Lo, et Ismael Goulani le 26-01-2017.
 */

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.awt.event.KeyEvent;


public class FenetreDeLancement {

    private int choixNiveau;
    private int choixLaby;

    private final Font standard = new Font("Times New Roman", Font.BOLD, 12);
    private final Font FinJeu = new Font("Times New Roman", Font.BOLD, 16);

    private final int HAUTEUR = 40;
    private final int LARGEUR = 40;


    public FenetreDeLancement() {
        creationFenetre();
    }

    public void creationFenetre() {
        int echelleFenetre = 200;
        int tailleMaxFenetre = 700;
        while (LARGEUR * echelleFenetre > tailleMaxFenetre || HAUTEUR * echelleFenetre > tailleMaxFenetre) {
            echelleFenetre = echelleFenetre - 5;
            if (echelleFenetre <= 0) {
                echelleFenetre = 1;
                break;
            }
        }

        StdDraw.setCanvasSize(LARGEUR * echelleFenetre, HAUTEUR * echelleFenetre);
        StdDraw.setXscale(0, LARGEUR);
        StdDraw.setYscale(0, HAUTEUR);
        StdDraw.setPenColor(StdDraw.WHITE);

        StdDraw.clear(StdDraw.BLACK);

        menuPrincipale();
    }


    public void menuPrincipale() {

        StdDraw.clear(StdDraw.BLACK);

        boolean choisie = false;
        int option = 0;

        while (!choisie) {
            // Dessin du menu principal
            StdDraw.text(LARGEUR/2, HAUTEUR/2 + 4, "1. Start");
            StdDraw.text(LARGEUR/2, HAUTEUR/2 + 2, "2. Control");
            StdDraw.text(LARGEUR/2, HAUTEUR/2 , "3. Exit");
            StdDraw.show();

            // Lecture clavier .
            if (StdDraw.isKeyPressed(KeyEvent.VK_1) || StdDraw.isKeyPressed(KeyEvent.VK_D)) {
                option = 1;
                choisie = true;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_2) || StdDraw.isKeyPressed(KeyEvent.VK_M)) {
                option = 2;
                choisie = true;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_3) || StdDraw.isKeyPressed(KeyEvent.VK_E)) {
                option = 3;
                choisie = true;
            }
        }

        // Va à l'écran souhaiter en fonction de la touche selecctionné
        switch (option) {
            case 1:
                menuDemarrer();
                break;
            case 2:
                EcranControle();
                break;
            case 3:
                EcranSortie();
                break;

            default:
                break;
        }
    }


    public void menuDemarrer() {
        StdDraw.clear(StdDraw.BLACK);

        boolean choisie = false;
        int option = 0;

        //Augmente la vitesse es fantomes en foction de la difficulté choisie
        while (!choisie) {
            StdDraw.text(LARGEUR/2 , HAUTEUR/2 + 12, "press ESC to return");
            StdDraw.text(LARGEUR/2, HAUTEUR/2 + 3, "4. Easy");
            StdDraw.text(LARGEUR/2, HAUTEUR/2, "5. Medium");
            StdDraw.text(LARGEUR/2, HAUTEUR/2 - 3, "6. Hard");
            StdDraw.show();

            if (StdDraw.isKeyPressed(KeyEvent.VK_4)) {
                choisie = true;
                option = 1;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_5)) {
                choisie = true;
                option = 2;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_6)) {
                choisie = true;
                option = 3;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE)) {
                choisie = true;
                option = -1;
            }
        }

        switch (option) {
            case 1:
                choixNiveau = 4;
                break;
            case 2:
                choixNiveau = 2;
                break;
            case 3:
                choixNiveau = 1;
                break;
            case -1:
                menuPrincipale();
                break;
            default:
                break;
        }

        lancerJeu();

    }

    // Affiche les controles
    public void EcranControle() {
        StdDraw.clear(StdDraw.BLACK);
        boolean choisie = false;

        while (!choisie) {
            StdDraw.picture(LARGEUR/2, HAUTEUR/2, "pacman_icons/control2.png");
            StdDraw.text(LARGEUR/2 , HAUTEUR/2 + 12, "press ESC to return");
            StdDraw.show();
            // Escape pour retourner au menu.
            if (StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE)) {
                choisie = true;
            }
        }
        menuPrincipale();
    }


    public void lancerJeu() {

        Jeu jeu = new Jeu(choixLaby, choixNiveau);
        GestionMap gm = new GestionMap(jeu);

        switch (jeu.etatJeu) {
            case GAGNE:
                fenetreGagne(jeu);
                break;
            case PERDUE:
                fenetreEchec(jeu);
                break;
            default:
                System.out.println("il ya un probleme avec etatJeu");
                break;
        }

    }

    //Afficher la fenetre de sortie
    public void EcranSortie() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.text(LARGEUR/2, HAUTEUR/2, "Merci");
        StdDraw.show();
        StdDraw.pause(300);
        System.exit(0);
    }

    public void fenetreGagne(Jeu jeu) {//Affiche le fenetre de fin de jeu
        StdDraw.clear(StdDraw.BLACK);
        boolean choisie = false;

        while (!choisie) {

            StdDraw.text(jeu.labyrinthe.getNumColonnes()/2, jeu.labyrinthe.getNumLignes()/2 + 5, "FELICITATION VOUS AVEZ GAGNEZ ");
            StdDraw.text(jeu.labyrinthe.getNumColonnes()/2, jeu.labyrinthe.getNumLignes()/2 + 2, "VOTRE SCORE : " + Integer.toString(jeu.joueur.getScore()));

            StdDraw.text(jeu.labyrinthe.getNumColonnes()/2, jeu.labyrinthe.getNumLignes()/2, "1. REJOUER");
            StdDraw.text(jeu.labyrinthe.getNumColonnes()/2, jeu.labyrinthe.getNumLignes()/2 - 2, "2. QUITTER");
            StdDraw.show();
            // Escape  pour retourner au menu.
            if (StdDraw.isKeyPressed(KeyEvent.VK_1)) {
                //choisie = true;
                StdDraw.pause(300);
                lancerJeu();
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_2)) {
                //choisie = true;
                StdDraw.pause(300);
                EcranSortie();
            }
        }
    }

    public void fenetreEchec(Jeu jeu) {//affiche la fenetre de fin de jeu e
        StdDraw.clear(StdDraw.BLACK);
        boolean choisie = false;

        while (!choisie) {

            StdDraw.text(jeu.labyrinthe.getNumColonnes()/2, jeu.labyrinthe.getNumLignes()/2 + 5, "VOUS AVEZ PERDUE ");
            StdDraw.text(jeu.labyrinthe.getNumColonnes()/2, jeu.labyrinthe.getNumLignes()/2 + 2, "VOTRE SCORE : " + Integer.toString(jeu.joueur.getScore()));

            StdDraw.text(jeu.labyrinthe.getNumColonnes()/2, jeu.labyrinthe.getNumLignes()/2, "1. REJOUER");
            StdDraw.text(jeu.labyrinthe.getNumColonnes()/2, jeu.labyrinthe.getNumLignes()/2 - 2, "2. QUITTER");
            StdDraw.show();
            // Escape  pour retourner au menu.
            if (StdDraw.isKeyPressed(KeyEvent.VK_1)) {
                //choisie = true;
                StdDraw.pause(300);
                lancerJeu();
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_2)) {
                //choisie = true;
                StdDraw.pause(300);
                EcranSortie();
            }
        }
    }
}

