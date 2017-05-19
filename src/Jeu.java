
/**
 * Created by Amadou Ly,Cheickhouna Lo, et Ismael Goulani le 26-01-2017.
 */

import java.util.ArrayList;

public class Jeu {

    //chargemennt des images
    String imageFRouge = "pacman_icons/rouge.png";
    String imageFRose = "pacman_icons/rose.png";
    String imageFBleu = "pacman_icons/bleu.png";
    String imageVie = "pacman_icons/vie.png";

    String imageFOrange = "pacman_icons/orange.png";
    String imageFEffraye = "pacman_icons/effraye.png";
    String imagePerso = "pacman_icons/perso.png";


    //mode des fantomes
    //enclachement dans le labyrinthe



    public Jeu(int map, int niveau){
        labyrinthe = new Labyrinthe(map);

        joueur = new Joueur(labyrinthe, 3);

        fantomes = new ArrayList<Fantome>(NBF_INITIAL);

        fantomes.add(new Fantome(labyrinthe, "FR0")); // R pour ombre (le fantome rouge)
        fantomes.add(new Fantome(labyrinthe, "FP0")); // P pour rapide (le fantome rose)
        fantomes.add(new Fantome(labyrinthe, "FB0")); // B pour timide (le fantome bleu)
        fantomes.add(new Fantome(labyrinthe, "FO0")); // O pour indiferent (le fantome orange)

        this.niveau = niveau;
    }

    public void bougerFantomes(Jeu jeu) {
        final Thread thread = new Thread() {
            public void run() {
                while (true) {
                    try {
                        for (int i = 0; i < fantomes.size(); i++) {

                            if (jeu.fantomes.get(i).getType().equals("FO0") || jeu.fantomes.get(i).getType().equals("FB0"))
                                jeu.fantomes.get(i).deplacerFantomeAleatoire(i, jeu);
                            else
                                jeu.fantomes.get(i).deplacerFantome(jeu, i);

                            if (etatJeu.getStatusJeu() != StatusJeu.CONTINUE) {
                                interrupt();
                                return;
                            }
                        }
                        sleep(niveau*100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();

    }

    public void verificationStatut() {
        if (this.joueur.getVie() == 0) {
            etatJeu = StatusJeu.PERDUE;
        }
        if (this.nbrFruit ==  NBRFRUIT) {
            etatJeu = StatusJeu.GAGNE;
        }
    }

    ArrayList<Fantome> fantomes;
    Labyrinthe labyrinthe;
    StatusJeu etatJeu = StatusJeu.CONTINUE;
    boolean modeEffraye = false;
    int niveau;
    Joueur joueur;
    int nbrFruit = 0;


    final int TIMER = 300;
    int NBF_INITIAL = 4;
    int NBRFRUIT = 305;
}