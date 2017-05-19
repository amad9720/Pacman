/**
 * Created by Amadou Ly,Cheickhouna Lo, et Ismael Goulani le 26-01-2017.
 */

public enum Etat{

    mur,
    vide,
    piege,
    porte,
    fruit,
    energie,
    presentation,
    teleportation,

    joueur,

    fruitEtFantome,
    energieEtFantome,

    f_Orange,
    f_Rouge,
    f_Bleu,
    f_Rose;

    public boolean isPresentation(){
        if (this==presentation) return true;
        else return false;
    }

    public boolean isMur() {
        if (this == mur) return true;
        else return false;
    }

    public boolean isVide() {
        if (this == vide) return true;
        else return false;
    }

    public boolean isTeleportation() {
        if (this == teleportation) return true;
        else return false;
    }

    public boolean isPorte() {
        if (this == porte) return true;
        else return false;
    }

    public boolean isPiege() {
        if (this == piege) return true;
        else return false;
    }

    public boolean isEnergie() {
        if (this == energie) return true;
        else return false;
    }

    public boolean isFruit() {
        if (this == fruit) return true;
        else return false;
    }

    public boolean isJoueur() {
        if (this == joueur) return true;
        else return false;
    }

    public boolean isF_Orange() {
        if (this == f_Orange) return true;
        else return false;
    }

    public boolean isF_Rose() {
        if (this == f_Rose) return true;
        else return false;
    }

    public boolean isF_Rouge() {
        if (this == f_Rouge) return true;
        else return false;
    }

    public boolean isF_Bleu() {
        if (this == f_Bleu) return true;
        else return false;
    }

    public boolean isFruitEtFantome() {
        if (this == fruitEtFantome) return true;
        else return false;
    }

    public boolean isEnergieEtFantome() {
        if (this == energieEtFantome) return true;
        else return false;
    }

    public boolean isFantome() {
        if (isF_Bleu() | isF_Rouge() | isF_Rose() | isF_Orange()) return true;
        else return false;
    }

    public boolean isAllOccupable() {
        if (isTeleportation() | isVide() | isFruit() | isJoueur() | isEnergie() | isFruitEtFantome() | isEnergieEtFantome()) return true;
        else return false;
    }

    public boolean isPacManOccupable() {
        if (isTeleportation() | isVide() | isFruit() | isEnergie() | isF_Orange() | isF_Rouge() | isF_Bleu() | isF_Rose() | isFruitEtFantome() | isEnergieEtFantome()) return true;
        else return false;
    }
}
