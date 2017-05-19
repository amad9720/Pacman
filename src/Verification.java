/**
 * Created by Amadou Ly,Cheickhouna Lo, et Ismael Goulani le 26-01-2017.
 */

public class Verification {
    public static boolean coordValides(int numLignes, int numColonnes, int y, int x) {
        if((y >= 0 & x >= 0) & (y < numLignes & x < numColonnes)) return true;
        return false;
    }

    public static boolean absenceFantome(Jeu jeu, int y, int x) {
        Etat etat = jeu.labyrinthe.getGrilleForme()[y][x];
        if (etat.isF_Bleu() | etat.isF_Orange() | etat.isF_Rose() | etat.isF_Rouge()) return false;
        return true;
    }
    
    //Occupable par les fantomes et le pacman
    //@surdefinition
    public static boolean isAllOccupable(Jeu jeu, int y, int x) {
        if (jeu.labyrinthe.getGrilleForme()[y][x].isAllOccupable()) return true;
        return false;
    }

    //Occupable par le pacman
    //@surdefinition
    public static boolean isPacManOccupable(Jeu jeu, int y, int x) {
        if (jeu.labyrinthe.getGrilleForme()[y][x].isPacManOccupable()) return true;
        return false;
    }

    public static boolean occuped(Jeu jeu, int y, int x) {
        if (jeu.labyrinthe.getGrilleInfo()[y][x].occuped) return true;
        return false;
    }

    //Utiliser les ancienne coordonnee pour eviter les problemes de passage a travers
    public static boolean collision(Joueur joueur, Fantome fantome) {
        if ((joueur.getX() == fantome.getXPrecedent()) && (joueur.getY() == fantome.getYPrecedent())) {
            if ((joueur.getXPrecedent() == fantome.getX()) && (joueur.getYPrecedent() == fantome.getY()))
                return true;
            return false;
        }
        return false;
    }

    //@surdefinition
    public static boolean collision(Fantome fantome, Joueur joueur) {
        if ((joueur.getXPrecedent() == fantome.getX()) && (joueur.getYPrecedent() == fantome.getY())) {
            if ((joueur.getX() == fantome.getXPrecedent()) && (joueur.getY() == fantome.getYPrecedent()))
                return true;
            return false;
        }
        return false;
    }

    public static boolean superposition(Joueur joueur, Fantome fantome) {
        if ((joueur.getX() == fantome.getX()) && (joueur.getY() == fantome.getY()))
            return true;
        return false;
    }

    //@surdefinition
    public static boolean superposition(Joueur joueur, int y, int x) {
        if ((joueur.getX() == x) && (joueur.getY() == y))
            return true;
        return false;
    }

    public static boolean croisement(Joueur joueur, Fantome fantome) {
        if (superposition(joueur, fantome) || collision(joueur, fantome)) return true;
        return false;
    }

    //@surdefinition
    public static boolean croisement(Fantome fantome, Joueur joueur) {
        if (superposition(joueur, fantome) || collision(fantome, joueur)) return true;
        return false;
    }

    //@redefinition
    public static boolean isF_Rouge(Fantome fantome) {
        if (fantome.getType() == "FR0") return true;
        return false;
    }

    //@surdefinition
    public static boolean isF_Bleu(Fantome fantome) {
        if (fantome.getType() == "FB0") return true;
        return false;
    }

    //@surdefinition
    public static boolean isF_Rose(Fantome fantome) {
        if (fantome.getType() == "FP0") return true;
        return false;
    }

    //@surdefinition
    public static boolean isF_Orange(Fantome fantome) {
        if (fantome.getType() == "FO0") return true;
        return false;
    }

    public static boolean prendEnergie(Jeu jeu) {
        for (int i = 0; i < jeu.labyrinthe.getEnergies().size(); i++) {
            if (superposition(jeu.joueur, jeu.labyrinthe.getEnergies().get(i).y, jeu.labyrinthe.getEnergies().get(i).x)) {
                jeu.labyrinthe.getEnergies().remove(i);
                return true;
            }
        }
        return false;
    }

    //juste pour le joueur
    //@surdefinition
    public static boolean isFruit(Jeu jeu) {
        if (jeu.labyrinthe.getGrilleForme()[jeu.joueur.getY()][jeu.joueur.getX()].isFruit()) return true;
        return false;
    }

    //@surdefinition
    public static boolean isJoueur(Jeu jeu, int y, int x) {
        if (jeu.labyrinthe.getGrilleForme()[y][x].isJoueur()) return true;
        return false;
    }
    //@surdefinition
    public static boolean isFantome(Jeu jeu, int y, int x) {
        if (jeu.labyrinthe.getGrilleForme()[y][x].isFantome()) return true;
        return false;
    }

    //juste pour le fantome
    //@surdefinition
    public static boolean isFruitEtFantome(Jeu jeu, int y, int x) {
        if (jeu.labyrinthe.getGrilleForme()[y][x].isFruitEtFantome()) return true;
        return false;
    }

    public static boolean isEnergieEtFantome(Jeu jeu, int y, int x) {
        if (jeu.labyrinthe.getGrilleForme()[y][x].isEnergieEtFantome()) return true;
        return false;
    }

    //@surdefinition
    public static boolean isTeleportation(Jeu jeu, int y, int x) {
        if (jeu.labyrinthe.getGrilleForme()[y][x].isTeleportation()) return true;
        return false;
    }

}