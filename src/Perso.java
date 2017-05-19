/**
 * Created by Amadou Ly,Cheickhouna Lo, et Ismael Goulani le 26-01-2017.
 */

import java.util.*;

public class Perso {

    public void deplacer(char direction, Jeu jeu){

        xPrecedent = jeu.joueur.getX();
        yPrecedent = jeu.joueur.getY();

        switch (direction) {
            case 'N':
                if (verification(xPrecedent, yPrecedent-1, jeu)) {
                    jeu.joueur.setY(yPrecedent-1);
                    miseAJourJoueur(jeu);
                }
                break;
            case 'S':
                if (verification(xPrecedent, yPrecedent+1, jeu)) {
                    jeu.joueur.setY(yPrecedent+1);
                    miseAJourJoueur(jeu);
                }
                break;
            case 'W':
                if (verification(xPrecedent-1, yPrecedent, jeu)) {
                    jeu.joueur.setX(xPrecedent-1);
                    miseAJourJoueur(jeu);
                }
                break;
            case 'E':
                if (verification(xPrecedent+1, yPrecedent, jeu)) {
                    jeu.joueur.setX(xPrecedent+1);
                    miseAJourJoueur(jeu);
                }
                break;
            default:
                break;
        }

    }

    public boolean verification(int x, int y, Jeu jeu){
        if (Verification.coordValides(jeu.labyrinthe.getNumLignes(), jeu.labyrinthe.getNumColonnes(), y, x)){
            if (Verification.isPacManOccupable(jeu, y, x)) return true;
            else return false;
        }
        else return false;
    }


    public void miseAJourJoueur(Jeu jeu){

        boolean teleporteur = false;
        if (Verification.isTeleportation(jeu, jeu.joueur.getY(), jeu.joueur.getX()))
            teleporteur = true;

        //verifie si la case est occupee
        if (Verification.occuped(jeu, jeu.joueur.getY(), jeu.joueur.getX())) {
            for (int i = 0; i < jeu.fantomes.size(); i++) {

                //verifie s'il ya collision ou superposition (joueur ---> fantome)
                if (Verification.croisement(jeu.joueur, jeu.fantomes.get(i))) {


                    //etude de cas en fonction du fantome croisee
                    if ((Verification.isF_Rouge(jeu.fantomes.get(i)))) {

                        //mode effraye apres que pacman ai consomme energie il peu eliminer les fantomes en les ramenants a leurs case piege (K)
                        if (jeu.modeEffraye == true) {
                            for (int n = 0; n < jeu.labyrinthe.getCoords().size(); n++) {
                                if(jeu.labyrinthe.getCoords().get(n).presence.equals("K")) {
                                    jeu.fantomes.get(i).setX(jeu.labyrinthe.getCoords().get(n).x);
                                    jeu.fantomes.get(i).setY(jeu.labyrinthe.getCoords().get(n).y);
                                    miseAJourFantome(jeu, i);
                                    jeu.joueur.incrementeScore(50);
                                    //return;
                                }
                            }
                        }else { // il retourne dans sa case de depart le cas contraire
                            for (int n = 0; n < jeu.labyrinthe.getJoueurs().size(); n++) {
                                if(jeu.labyrinthe.getJoueurs().get(n).presence.equals("J0")) {
                                    jeu.joueur.setX(jeu.labyrinthe.getJoueurs().get(n).x);
                                    jeu.joueur.setY(jeu.labyrinthe.getJoueurs().get(n).y);
                                    //on diminue la vie
                                    jeu.joueur.decrementerVie();
                                    //jeu.joueur.incrementeScore();
                                    //return;
                                }
                            }
                        }


                    }

                    if (Verification.isF_Bleu(jeu.fantomes.get(i))) {

                        if (jeu.modeEffraye == true) {
                            for (int n = 0; n < jeu.labyrinthe.getCoords().size(); n++) {
                                if(jeu.labyrinthe.getCoords().get(n).presence.equals("K")) {
                                    jeu.fantomes.get(i).setX(jeu.labyrinthe.getCoords().get(n).x);
                                    jeu.fantomes.get(i).setY(jeu.labyrinthe.getCoords().get(n).y);
                                    miseAJourFantome(jeu, i);
                                    jeu.joueur.incrementeScore(50);
                                    //return;
                                }
                            }
                        }else {
                            for (int n = 0; n < jeu.labyrinthe.getJoueurs().size(); n++) {
                                if(jeu.labyrinthe.getJoueurs().get(n).presence.equals("J0")) {
                                    jeu.joueur.setX(jeu.labyrinthe.getJoueurs().get(n).x);
                                    jeu.joueur.setY(jeu.labyrinthe.getJoueurs().get(n).y);
                                    jeu.joueur.decrementerVie();
                                    //return;
                                }
                            }
                        }

                    }

                    if (Verification.isF_Rose(jeu.fantomes.get(i))) {

                        if (jeu.modeEffraye == true) {
                            for (int n = 0; n < jeu.labyrinthe.getCoords().size(); n++) {
                                if(jeu.labyrinthe.getCoords().get(n).presence.equals("K")) {
                                    jeu.fantomes.get(i).setX(jeu.labyrinthe.getCoords().get(n).x);
                                    jeu.fantomes.get(i).setY(jeu.labyrinthe.getCoords().get(n).y);
                                    miseAJourFantome(jeu, i);
                                    jeu.joueur.incrementeScore(50);
                                    //return;
                                }
                            }
                        }else {
                            for (int n = 0; n < jeu.labyrinthe.getJoueurs().size(); n++) {
                                if(jeu.labyrinthe.getJoueurs().get(n).presence.equals("K")) {
                                    jeu.joueur.setX(jeu.labyrinthe.getJoueurs().get(n).x);
                                    jeu.joueur.setY(jeu.labyrinthe.getJoueurs().get(n).y);
                                    jeu.joueur.decrementerVie();
                                    //return;
                                }
                            }
                        }

                    }

                    if (Verification.isF_Orange(jeu.fantomes.get(i))) {

                        if (jeu.modeEffraye == true) {
                            for (int n = 0; n < jeu.labyrinthe.getCoords().size(); n++) {
                                if(jeu.labyrinthe.getCoords().get(n).presence.equals("K")) {
                                    jeu.fantomes.get(i).setX(jeu.labyrinthe.getCoords().get(n).x);
                                    jeu.fantomes.get(i).setY(jeu.labyrinthe.getCoords().get(n).y);
                                    miseAJourFantome(jeu, i);
                                    jeu.joueur.incrementeScore(50);
                                    //return;
                                }
                            }
                        }else { // il retourne dans sa case de depart le cas contraire
                            for (int n = 0; n < jeu.labyrinthe.getJoueurs().size(); n++) {
                                if(jeu.labyrinthe.getJoueurs().get(n).presence.equals("K")) {
                                    jeu.joueur.setX(jeu.labyrinthe.getJoueurs().get(n).x);
                                    jeu.joueur.setY(jeu.labyrinthe.getJoueurs().get(n).y);
                                    jeu.joueur.decrementerVie();
                                    //return;
                                }
                            }
                        }

                    }

                }
            }
        }
        else {
            //la case actuel
            Etat cse = jeu.labyrinthe.getGrilleForme()[jeu.joueur.getY()][jeu.joueur.getX()];

            if (!teleporteur) {
                jeu.labyrinthe.getGrilleInfo()[jeu.joueur.getY()][jeu.joueur.getX()].occuped = true;
                jeu.labyrinthe.getGrilleInfo()[jeu.joueur.getY()][jeu.joueur.getX()].presence = 'J';
                jeu.labyrinthe.getGrilleForme()[jeu.joueur.getY()][jeu.joueur.getX()]  = Etat.joueur;

                if (cse.isFruit() | cse.isEnergie() | cse.isEnergieEtFantome() | cse.isFruitEtFantome())
                    jeu.joueur.incrementeScore(1);
                jeu.nbrFruit++;
            }
        }

        //si pacman prend l'energie
        if (Verification.prendEnergie(jeu)) {
            //faire quelque chose ... exemple changer le mode des fantomes (effraye/ plus agressifs ...)
            for (int i = 0; i < jeu.labyrinthe.getPortes().size(); i++)
                jeu.labyrinthe.getGrilleForme()[jeu.labyrinthe.getPortes().get(i).y][jeu.labyrinthe.getPortes().get(i).x] = Etat.vide;

            jeu.labyrinthe.getGrilleForme()[jeu.joueur.getY()][jeu.joueur.getX()] = Etat.vide;
            jeu.modeEffraye = true;

        }

        if (Verification.isTeleportation(jeu, jeu.joueur.getY(), jeu.joueur.getX())) {
            for (int i = 0; i < jeu.labyrinthe.getTeleporteurs().size() - 1 ; i++) {
                if (Verification.superposition(jeu.joueur, jeu.labyrinthe.getTeleporteurs().get(i).y, jeu.labyrinthe.getTeleporteurs().get(i).x)) {
                    jeu.joueur.setX(jeu.labyrinthe.getTeleporteurs().get(i+1).x);
                    jeu.joueur.setY(jeu.labyrinthe.getTeleporteurs().get(i+1).y);
                } else if (Verification.superposition(jeu.joueur, jeu.labyrinthe.getTeleporteurs().get(i+1).y, jeu.labyrinthe.getTeleporteurs().get(i+1).x)) {
                    jeu.joueur.setX(jeu.labyrinthe.getTeleporteurs().get(i).x);
                    jeu.joueur.setY(jeu.labyrinthe.getTeleporteurs().get(i).y);
                }
            }
        }

        if (!(Verification.isTeleportation(jeu, yPrecedent, xPrecedent))) {
            jeu.labyrinthe.getGrilleInfo()[yPrecedent][xPrecedent].occuped = false;
            jeu.labyrinthe.getGrilleInfo()[yPrecedent][xPrecedent].presence = 'V';
            jeu.labyrinthe.getGrilleForme()[yPrecedent][xPrecedent]  = Etat.vide;
        }
    }

    public void deplacerFantomeAleatoire(int indiceFantome, Jeu jeu){
        xPrecedent = jeu.fantomes.get(indiceFantome).getX();
        yPrecedent = jeu.fantomes.get(indiceFantome).getY();

        int tempsDonne = 100;

        boolean deplacementFait = false;
        Random rand2 = new Random(System.currentTimeMillis());

        do {
            switch (rand2.nextInt(4)) {
                case 0:
                    if (verificationFantome(xPrecedent+1, yPrecedent, jeu)) { // a modifiere enlever jeu.Laby tu peux y acceder avec jeu
                        jeu.fantomes.get(indiceFantome).setX(xPrecedent+1);
                        deplacementFait = true;
                        miseAJourFantome(jeu, indiceFantome);
                    }
                    break;
                case 1:
                    if (verificationFantome(xPrecedent, yPrecedent-1, jeu)) {
                        jeu.fantomes.get(indiceFantome).setY(yPrecedent-1);
                        deplacementFait = true;
                        miseAJourFantome(jeu, indiceFantome);
                    }
                    break;
                case 2:
                    if (verificationFantome(xPrecedent, yPrecedent+1, jeu)) {
                        jeu.fantomes.get(indiceFantome).setY(yPrecedent+1);
                        deplacementFait = true;
                        miseAJourFantome(jeu, indiceFantome);
                    }
                    break;
                case 3:
                    if (verificationFantome(xPrecedent-1, yPrecedent, jeu)) {
                        jeu.fantomes.get(indiceFantome).setX(xPrecedent-1);
                        deplacementFait = true;
                        miseAJourFantome(jeu, indiceFantome);
                    }
                    break;
                default:
                    break;
            }
            tempsDonne--;
            if (tempsDonne < 0) {
                break;
            }
        } while (!deplacementFait);
    }

    public boolean verificationFantome(int x, int y, Jeu jeu){
        if (Verification.coordValides(jeu.labyrinthe.getNumLignes(), jeu.labyrinthe.getNumColonnes(), y, x)){
            if (Verification.isAllOccupable(jeu, y, x) && (Verification.absenceFantome(jeu, y, x)))
                return true;
            else return false;
        }
        else return false;
    }

    //de la meme maniere qu'on la fait pour le pacman, on met a jour l'etat du fantome de de la map
    public void miseAJourFantome(Jeu jeu, int indiceFantome){

        boolean teleporteur = false;
        if (Verification.isTeleportation(jeu, jeu.fantomes.get(indiceFantome).getY(), jeu.fantomes.get(indiceFantome).getX()))
            teleporteur = true;

        if (Verification.occuped(jeu, jeu.fantomes.get(indiceFantome).getY(), jeu.fantomes.get(indiceFantome).getX())) {
            if (Verification.croisement(jeu.fantomes.get(indiceFantome), jeu.joueur)) {


                if (Verification.isF_Rouge(jeu.fantomes.get(indiceFantome))) {

                    //si le fantome croise le pacman alors quil est effrayee il revient a son point initial
                    if (jeu.modeEffraye == true) {
                        for (int n = 0; n < jeu.labyrinthe.getCoords().size(); n++) {
                            if(jeu.labyrinthe.getCoords().get(n).presence.equals("K")) {
                                jeu.fantomes.get(indiceFantome).setX(jeu.labyrinthe.getCoords().get(n).x);
                                jeu.fantomes.get(indiceFantome).setY(jeu.labyrinthe.getCoords().get(n).y);
                                jeu.joueur.incrementeScore(50);
                                //return;
                            }
                        }//miseAJourFantome(, ) ...
                        //il est bien de mettre une verification ici. Fais cela provoque une surcharge de la pile d'appel (exception stackoverflow)
                    }else {
                        for (int n = 0; n < jeu.labyrinthe.getJoueurs().size(); n++) {
                            if(jeu.labyrinthe.getJoueurs().get(n).presence.equals("J0")) {
                                jeu.joueur.setX(jeu.labyrinthe.getJoueurs().get(n).x);
                                jeu.joueur.setY(jeu.labyrinthe.getJoueurs().get(n).y);
                                //on diminue la vie
                                jeu.joueur.decrementerVie();

                                //return;
                            }
                        }
                    }
                }

                if (Verification.isF_Bleu(jeu.fantomes.get(indiceFantome))) {

                    if (jeu.modeEffraye == true) {
                        for (int n = 0; n < jeu.labyrinthe.getCoords().size(); n++) {
                            if(jeu.labyrinthe.getCoords().get(n).presence.equals("K")) {
                                jeu.fantomes.get(indiceFantome).setX(jeu.labyrinthe.getCoords().get(n).x);
                                jeu.fantomes.get(indiceFantome).setY(jeu.labyrinthe.getCoords().get(n).y);
                                jeu.joueur.incrementeScore(50);
                                //return;
                            }
                        }
                    }else {
                        for (int n = 0; n < jeu.labyrinthe.getJoueurs().size(); n++) {
                            if(jeu.labyrinthe.getJoueurs().get(n).presence.equals("J0")) {
                                jeu.joueur.setX(jeu.labyrinthe.getJoueurs().get(n).x);
                                jeu.joueur.setY(jeu.labyrinthe.getJoueurs().get(n).y);
                                jeu.joueur.decrementerVie();

                                //return;
                            }
                        }
                    }




                }
                if (Verification.isF_Rose(jeu.fantomes.get(indiceFantome))) {

                    if (jeu.modeEffraye == true) {
                        for (int n = 0; n < jeu.labyrinthe.getCoords().size(); n++) {
                            if(jeu.labyrinthe.getCoords().get(n).presence.equals("K")) {
                                jeu.fantomes.get(indiceFantome).setX(jeu.labyrinthe.getCoords().get(n).x);
                                jeu.fantomes.get(indiceFantome).setY(jeu.labyrinthe.getCoords().get(n).y);
                                jeu.joueur.incrementeScore(50);
                                //return;
                            }
                        }
                    }else {
                        for (int n = 0; n < jeu.labyrinthe.getJoueurs().size(); n++) {
                            if(jeu.labyrinthe.getJoueurs().get(n).presence.equals("J0")) {
                                jeu.joueur.setX(jeu.labyrinthe.getJoueurs().get(n).x);
                                jeu.joueur.setY(jeu.labyrinthe.getJoueurs().get(n).y);
                                jeu.joueur.decrementerVie();

                                //return;
                            }
                        }
                    }



                }
                if (Verification.isF_Orange(jeu.fantomes.get(indiceFantome))) {

                    if (jeu.modeEffraye == true) {
                        for (int n = 0; n < jeu.labyrinthe.getCoords().size(); n++) {
                            if(jeu.labyrinthe.getCoords().get(n).presence.equals("K")) {
                                jeu.fantomes.get(indiceFantome).setX(jeu.labyrinthe.getCoords().get(n).x);
                                jeu.fantomes.get(indiceFantome).setY(jeu.labyrinthe.getCoords().get(n).y);
                                jeu.joueur.incrementeScore(50);
                                //return;
                            }
                        }
                    }else {
                        for (int n = 0; n < jeu.labyrinthe.getJoueurs().size(); n++) {
                            if(jeu.labyrinthe.getJoueurs().get(n).presence.equals("J0")) {
                                jeu.joueur.setX(jeu.labyrinthe.getJoueurs().get(n).x);
                                jeu.joueur.setY(jeu.labyrinthe.getJoueurs().get(n).y);
                                jeu.joueur.decrementerVie();

                                //return;
                            }
                        }
                    }



                }
            }
        }
        else {
            //le teleporteur ne change jamais d'etat, il garde aucun perso... il les teleportes instantanement
            if (!teleporteur) {

                jeu.labyrinthe.getGrilleInfo()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()].occuped = true;

                if ((Verification.isF_Rouge(jeu.fantomes.get(indiceFantome)))) {

                    jeu.labyrinthe.getGrilleInfo()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()].presence = 'R';

                    if (jeu.labyrinthe.getGrilleForme()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()] == Etat.fruit)
                        jeu.labyrinthe.getGrilleForme()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()] = Etat.fruitEtFantome;
                    else if (jeu.labyrinthe.getGrilleForme()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()] == Etat.energie)
                        jeu.labyrinthe.getGrilleForme()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()] = Etat.energieEtFantome;
                    else
                        jeu.labyrinthe.getGrilleForme()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()] = Etat.f_Rouge;
                }

                if (Verification.isF_Bleu(jeu.fantomes.get(indiceFantome))) {

                    jeu.labyrinthe.getGrilleInfo()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()].presence = 'B';

                    if (jeu.labyrinthe.getGrilleForme()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()] == Etat.fruit)
                        jeu.labyrinthe.getGrilleForme()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()] = Etat.fruitEtFantome;
                    else if (jeu.labyrinthe.getGrilleForme()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()] == Etat.energie)
                        jeu.labyrinthe.getGrilleForme()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()] = Etat.energieEtFantome;
                    else
                        jeu.labyrinthe.getGrilleForme()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()] = Etat.f_Bleu;
                }

                if (Verification.isF_Rose(jeu.fantomes.get(indiceFantome))) {

                    jeu.labyrinthe.getGrilleInfo()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()].presence = 'P';

                    if (jeu.labyrinthe.getGrilleForme()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()] == Etat.fruit)
                        jeu.labyrinthe.getGrilleForme()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()] = Etat.fruitEtFantome;
                    else if (jeu.labyrinthe.getGrilleForme()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()] == Etat.energie)
                        jeu.labyrinthe.getGrilleForme()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()] = Etat.energieEtFantome;
                    else
                        jeu.labyrinthe.getGrilleForme()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()] = Etat.f_Rose;
                }

                if (Verification.isF_Orange(jeu.fantomes.get(indiceFantome))) {

                    jeu.labyrinthe.getGrilleInfo()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()].presence = 'O';

                    if (jeu.labyrinthe.getGrilleForme()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()] == Etat.fruit)
                        jeu.labyrinthe.getGrilleForme()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()] = Etat.fruitEtFantome;
                    else if (jeu.labyrinthe.getGrilleForme()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()] == Etat.energie)
                        jeu.labyrinthe.getGrilleForme()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()] = Etat.energieEtFantome;
                    else
                        jeu.labyrinthe.getGrilleForme()[jeu.fantomes.get(indiceFantome).getY()][jeu.fantomes.get(indiceFantome).getX()] = Etat.f_Orange;
                }
            }
        }

        if (jeu.modeEffraye == false) {
            for (int i = 0; i < jeu.labyrinthe.getPortes().size(); i++)
                jeu.labyrinthe.getGrilleForme()[jeu.labyrinthe.getPortes().get(i).y][jeu.labyrinthe.getPortes().get(i).x] = Etat.porte;
        }

        jeu.labyrinthe.getGrilleInfo()[yPrecedent][xPrecedent].occuped = false;

        //verificaition de la case occupee precedement pour faire la reinitialisation de son etat
        if (!(Verification.isTeleportation(jeu, yPrecedent, xPrecedent))) {
            if (Verification.isFruitEtFantome(jeu, yPrecedent, xPrecedent)) {
                jeu.labyrinthe.getGrilleInfo()[yPrecedent][xPrecedent].presence = ' ';
                jeu.labyrinthe.getGrilleForme()[yPrecedent][xPrecedent] = Etat.fruit;
            } else if (Verification.isEnergieEtFantome(jeu, yPrecedent, xPrecedent)) {
                jeu.labyrinthe.getGrilleInfo()[yPrecedent][xPrecedent].presence = ' ';
                jeu.labyrinthe.getGrilleForme()[yPrecedent][xPrecedent] = Etat.energie;
            } else {
                jeu.labyrinthe.getGrilleInfo()[yPrecedent][xPrecedent].presence = 'V';
                jeu.labyrinthe.getGrilleForme()[yPrecedent][xPrecedent] = Etat.vide;

            }
        }
    }

    //gerer les deplacement des fantomes pour qu'ils nous suivent
    public void deplacerFantome(Jeu jeu, int indiceFantome) {
        class Pas {
            int x;
            int y;

            public Pas(int fantomeX, int fantomeY) {
                x = fantomeX;
                y = fantomeY;
            }
        }

        Fantome fantome = jeu.fantomes.get(indiceFantome);

        int fantomeX = fantome.getX();
        int fantomeY = fantome.getY();

        int joueurX = 0;
        int joueurY = 0;

        for(int m = 0; m < jeu.labyrinthe.getNumLignes(); m++) {
            for(int n = 0; n < jeu.labyrinthe.getNumColonnes(); n++) {
                if (jeu.labyrinthe.getGrilleForme()[m][n].isJoueur()) {
                    joueurX = n;
                    joueurY = m;
                }
            }
        }

        double minDistance = Double.MAX_VALUE;
        Pas pas = new Pas(fantomeX,fantomeY);

        List<Pas> directions= new ArrayList<Pas>();
        directions.add(new Pas(-1, 0));
        directions.add(new Pas(1, 0));
        directions.add(new Pas(0, -1));
        directions.add(new Pas(0, 1));
        for (Pas direction : directions) {

            int newX = fantomeX + direction.x;
            int newY = fantomeY + direction.y;

            if (Verification.coordValides(jeu.labyrinthe.getNumLignes(), jeu.labyrinthe.getNumColonnes(), newY, newX)) {

                if (jeu.labyrinthe.getGrilleForme()[newY][newX].isJoueur()) {
                    pas = new Pas(newX, newY);
                }

                if (!(jeu.labyrinthe.getGrilleForme()[newY][newX].isAllOccupable()))
                    continue;

                double distance = calculDictance(newX, newY, joueurX, joueurY);
                if (distance < minDistance) {
                    minDistance = distance;
                    pas = new Pas(newX, newY);
                }
            }
        }

        xPrecedent = fantomeX;
        yPrecedent = fantomeY;

        fantome.setX(pas.x);
        fantome.setY(pas.y);
        miseAJourFantome(jeu, indiceFantome);
    }

    public double calculDictance(int fantomeX, int fantomeY, int joueurX, int joueurY) {
        return Math.sqrt(Math.pow(fantomeX - joueurX, 2) + Math.pow(fantomeY - joueurY, 2));
    }


    public int getXPrecedent(){
        return xPrecedent;
    }

    public int getYPrecedent(){
        return yPrecedent;
    }

    private int xPrecedent;
    private int yPrecedent;
}

