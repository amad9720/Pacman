import edu.princeton.cs.introcs.StdDraw;

import java.awt.event.KeyEvent;

/**
 * Created by Amadou Ly,Cheickhouna Lo, et Ismael Goulani le 26-01-2017.
 */

public class GestionMap {
    private Thread thread;
    private int nbrColonnes;
    private int nbrLignes;

    private char direction;
    private boolean inGame = true;
    private int gestionTimer = 0;

    public GestionMap(Jeu jeu) {

        creationFenetre(jeu);

    }

    public void creationFenetre(Jeu jeu) {/* Permet de créer une fenetre de taille nbrecolonnes*nbrLignes
                                            *
                                            */

        nbrColonnes = jeu.labyrinthe.getNumColonnes();//nombre de colonnes du labyrinthe
        nbrLignes = jeu.labyrinthe.getNumLignes();//nombre de lignes du labyrinthe

        int echelleFenetre = 200;
        int tailleMaxFenetre = 700;
        while (nbrColonnes * echelleFenetre > tailleMaxFenetre || nbrLignes * echelleFenetre > tailleMaxFenetre) {
            echelleFenetre = echelleFenetre - 5;
            if (echelleFenetre <= 0) {
                echelleFenetre = 1;
                break;
            }
        }

        StdDraw.setCanvasSize(nbrColonnes * echelleFenetre, nbrLignes * echelleFenetre);
        StdDraw.setXscale(0, nbrColonnes);
        StdDraw.setYscale(0, nbrLignes);
        StdDraw.setPenColor(StdDraw.WHITE);

        StdDraw.clear(StdDraw.BLACK);
        StdDraw.enableDoubleBuffering();
        debutJeu(jeu);
    }

    public void debutJeu(Jeu jeu) {
        StdDraw.clear(StdDraw.BLACK);
        jeu.bougerFantomes(jeu);
        while (jeu.etatJeu == StatusJeu.CONTINUE) {
            StdDraw.clear(StdDraw.BLACK);
            controller(jeu);
            jeu.verificationStatut();
        }
    }

    //application des different elements qui constituent le jeu
    public void controller(Jeu jeu) {
        controllerTouche();
        bouger(jeu);
        dessiner(jeu);
    }

    // direction du joueur
    public void controllerTouche() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) { // Left arrow
            direction = 'W';
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) { // right arrow
            direction = 'E';
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) { // up arrow
            direction = 'S';
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) { // down arrow
            direction = 'N';
        }
    }

    public void bouger(Jeu jeu) {

        jeu.joueur.deplacer(direction, jeu);

    }

    //met a jour la fenetre apres chaque mouvement
    public void dessiner(Jeu jeu) {

        dessinerLabyrinthe(jeu);
        dessinerFantomes(jeu);
        dessinerJoueur(jeu);
        dessinerScore(jeu);
        dessinerVie(jeu);
        StdDraw.show(1000/30);
        StdDraw.pause(100);

    }

    public void dessinerMur(int ligne, int colonne) {
        StdDraw.picture(colonne+0.5, ligne+0.5, "pacman_icons/mur1.png", 1, 1);
    }

    public void dessinerEnergie(int ligne, int colonne) {
        StdDraw.picture(colonne+0.5, ligne+0.5, "pacman_icons/energie.png", 0.9, 0.9);
    }

    public void dessinerFruit(int ligne, int colonne) {
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.filledCircle(colonne+0.5, ligne+0.5, 0.1);
    }

    public void dessinerPorte(int ligne, int colonne) {
        StdDraw.picture(colonne+0.5, ligne+0.5, "pacman_icons/porte.png", 1, 1);
    }

    public void dessinerTeleportation(int ligne, int colonne) {
        StdDraw.picture(colonne+0.5, ligne+0.5, "pacman_icons/debut2.png", 1, 1);
    }

    public void dessinerLabyrinthe(Jeu jeu) {
        for (int i = 0; i < jeu.labyrinthe.getNumLignes(); i++) {
            for (int j = 0; j < jeu.labyrinthe.getNumColonnes(); j++) {
                if (jeu.labyrinthe.getGrilleForme()[i][j].isMur()) dessinerMur(i, j);
                if (jeu.labyrinthe.getGrilleForme()[i][j].isEnergie()) dessinerEnergie(i, j);
                if (jeu.labyrinthe.getGrilleForme()[i][j].isFruit()) dessinerFruit(i, j);
                if (jeu.labyrinthe.getGrilleForme()[i][j].isPorte()) dessinerPorte(i, j);
                if (jeu.labyrinthe.getGrilleForme()[i][j].isTeleportation()) dessinerTeleportation(i, j);
            }
        }
    }



    public void dessinerJoueur(Jeu jeu){
        switch (direction){
            case 'W':
                StdDraw.picture(jeu.joueur.getX()+0.5, jeu.joueur.getY()+0.5, jeu.imagePerso, 1, 1,-180);

                break;
            case 'N':
                StdDraw.picture(jeu.joueur.getX()+0.5, jeu.joueur.getY()+0.5, jeu.imagePerso, 1, 1,-90);
                break;
            case 'S':
                StdDraw.picture(jeu.joueur.getX()+0.5, jeu.joueur.getY()+0.5, jeu.imagePerso, 1, 1,+90);
                break;
            case 'E':
                StdDraw.picture(jeu.joueur.getX()+0.5, jeu.joueur.getY()+0.5, jeu.imagePerso, 1, 1,0);
                break;
        }

    }

    public void dessinerFantomes(Jeu jeu) {

        if (jeu.modeEffraye == false) {
            StdDraw.picture(jeu.fantomes.get(0).getX()+0.5, jeu.fantomes.get(0).getY()+0.5, jeu.imageFRouge, 1, 1);
            StdDraw.picture(jeu.fantomes.get(1).getX()+0.5, jeu.fantomes.get(1).getY()+0.5, jeu.imageFRose, 1, 1);
            StdDraw.picture(jeu.fantomes.get(2).getX()+0.5, jeu.fantomes.get(2).getY()+0.5, jeu.imageFBleu, 1, 1);
            StdDraw.picture(jeu.fantomes.get(3).getX()+0.5, jeu.fantomes.get(3).getY()+0.5, jeu.imageFOrange, 1, 1);
        } else {
            for (int i = 0; i < jeu.fantomes.size(); i++)
                StdDraw.picture(jeu.fantomes.get(i).getX()+0.5, jeu.fantomes.get(i).getY()+0.5, jeu.imageFEffraye, 1, 1);

            gestionTimer++;

            if (gestionTimer == jeu.TIMER) {
                gestionTimer = 0;
                jeu.modeEffraye = false;

            }
        }

    }

    public void dessinerScore(Jeu jeu){

        int  score=jeu.joueur.getScore();
        StdDraw.text(2, 3 * nbrLignes / 4, "Score:"  );
        StdDraw.text(2, 3 * nbrLignes / 4-1, ""+score  );

    }

    public void dessinerVie(Jeu jeu){
        int vie = jeu.joueur.getVie();
        StdDraw.text(1.5,nbrLignes/2+1,"VIES :");
        switch(vie){
            case 1:
                StdDraw.picture(1,nbrLignes/2,jeu.imageVie,0.5,0.5);
                break;
            case 2:
                StdDraw.picture(1,nbrLignes/2,jeu.imageVie,0.5,0.5);
                StdDraw.picture(2,nbrLignes/2,jeu.imageVie,0.5,0.5);
                break;
            case 3:
                StdDraw.picture(1,nbrLignes/2,jeu.imageVie,0.5,0.5);
                StdDraw.picture(2,nbrLignes/2,jeu.imageVie,0.5,0.5);
                StdDraw.picture(3,nbrLignes/2,jeu.imageVie,0.5,0.5);
                break;
            default:
                break;

        }
    }
}
