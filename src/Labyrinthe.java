import java.util.*;

/**
 * Created by Amadou Ly,Cheickhouna Lo, et Ismael Goulani le 26-01-2017.
 */

public class Labyrinthe{

    private Etat [][]grilleForme;
    private CellInfo [][]grilleInfo;

    private ArrayList<Coordonnee> coords;
    private ArrayList<Coordonnee> energies;
    private ArrayList<Coordonnee> teleporteurs;
    private ArrayList<Coordonnee> portes;
    private ArrayList<Coordonnee> fantomesCoords;
    private ArrayList<Coordonnee> joueurs;

    private int numLignes;
    private int numColonnes;

    public class CellInfo {

        boolean north 	= false;
        boolean	south 	= false;
        boolean east 	= false;
        boolean west 	= false;
        boolean visited = false;
        boolean occuped = false;

        char presence ;

    }
// On utilise une classe interne pour récupérer les coordonnées des diférents objets.
    public class Coordonnee {
        int x;
        int y;
        String presence;

        public Coordonnee(int y, int x, String presence) {
            this.x = x;
            this.y = y;
            this.presence = presence;
        }
    }
	// Contructeur par défaut
    public Labyrinthe() {
        coords = new ArrayList<Coordonnee>();
        teleporteurs = new ArrayList<Coordonnee>();
        portes = new ArrayList<Coordonnee>();
        fantomesCoords = new ArrayList<Coordonnee>();
        joueurs = new ArrayList<Coordonnee>();
        energies = new ArrayList<Coordonnee>();

        System.out.println("Chargement du Labyrinthe");
        lectureFormeLaby("mazes/labyDefaut.txt");

        initialisationGrilleInfo();
    }
	// Constructeur avec parametre.
    public Labyrinthe(int map) {
        coords = new ArrayList<Coordonnee>();
        teleporteurs = new ArrayList<Coordonnee>();
        portes = new ArrayList<Coordonnee>();
        fantomesCoords = new ArrayList<Coordonnee>();
        joueurs = new ArrayList<Coordonnee>();
        energies = new ArrayList<Coordonnee>();


        List<String> nomFichiers = Arrays.asList("labyDefaut.txt", "bigMaze.txt"); // asList permet d'ajouter des String simultanément 
        System.out.println("Chargement du Labyrinthe");
        
				//On initialise le labyrinthe
        String chemin = "mazes/"+nomFichiers.get(map);
        lectureFormeLaby(chemin);


        initialisationGrilleInfo();

    }

    public void lectureFormeLaby(String nomFichier) {
        System.out.println("\t Lecture du labyrinthe a l'etat initial");

        // permet de donner automatiquement des noms.
        int compteurT = 0,
                compteurP = 0,//Porte
                compteurJ = 0,//Joueur
                compteurE = 0,//Energie
                compteurFR = 0,//Fantome rouge
                compteurFP = 0,// Fantome Rose
                compteurFB = 0,// Fantome Blue
                compteurFO = 0;// Fantome Orange

        LecteurFichier f = new LecteurFichier();//Classe utilisant des flux pour dessiner le labyrinthe en utilisant des entiers.
        f.lireFichier(nomFichier);

        numLignes = f.numLignes;
        numColonnes = f.numColonnes;

        grilleForme = new Etat[numLignes][numColonnes];//Contenue des entiers
        int tableauValeurs;

        for (int ligne = 0; ligne < numLignes; ligne++) {
            for (int colonne = 0; colonne < numColonnes; colonne++) {
                tableauValeurs = f.grille[ligne][colonne];

                switch (tableauValeurs) {
                    case 1:// Mur
                        this.grilleForme[ligne][colonne] = Etat.mur;
                        break;
                    case 2:// Teleportation
                        this.grilleForme[ligne][colonne] = Etat.teleportation;
                        teleporteurs.add(new Coordonnee(ligne, colonne, "T"+compteurT));
                        compteurT++;
                        break;
                    case 3://Porte
                        this.grilleForme[ligne][colonne] = Etat.porte;
                        portes.add(new Coordonnee(ligne, colonne, "P"+compteurP));
                        compteurP++;
                        break;
                    case 4:// Fantome Rose
                        this.grilleForme[ligne][colonne] = Etat.f_Rouge;
                        fantomesCoords.add(new Coordonnee(ligne, colonne, "FR"+compteurFR));
                        compteurFR++;
                        break;
                    case 5:// Fantome Rose
                        this.grilleForme[ligne][colonne] = Etat.f_Rose;
                        fantomesCoords.add(new Coordonnee(ligne, colonne, "FP"+compteurFP));
                        compteurFP++;
                        break;
                    case 6:// Fantome Orange
                        this.grilleForme[ligne][colonne] = Etat.f_Orange;
                        fantomesCoords.add(new Coordonnee(ligne, colonne, "FO"+compteurFO));
                        compteurFO++;
                        break;
                    case 7:// Fantome Blue
                        this.grilleForme[ligne][colonne] = Etat.f_Bleu;
                        fantomesCoords.add(new Coordonnee(ligne, colonne, "FB"+compteurFB));
                        compteurFB++;
                        break;
                    case 8://Présence de diamant 
                        this.grilleForme[ligne][colonne] = Etat.energie;
                        energies.add(new Coordonnee(ligne, colonne, "E"+compteurE));
                        compteurE++;
                        break;
                    case 9://Présence de joueur
                        this.grilleForme[ligne][colonne] = Etat.joueur;
                        joueurs.add(new Coordonnee(ligne, colonne, "J"+compteurJ));
                        compteurJ++;
                        break;
                    case 10:// Position du piège
                        this.grilleForme[ligne][colonne] = Etat.piege;
                        coords.add(new Coordonnee(ligne, colonne, "K"));
                        break;
                    case 11:// Espace vide pour écrire le score et le nombre de vie restant
                        this.grilleForme[ligne][colonne] = Etat.presentation;
                        coords.add(new Coordonnee(ligne, colonne, "."));
                        break;
                    default:// Présence de fruit
                        this.grilleForme[ligne][colonne]  = Etat.fruit;
                        break;
                }
            }
        }
    }

    public void initialisationGrilleInfo() {

        grilleInfo = new CellInfo[numLignes][numColonnes];

        for (int i = 0; i < numLignes; i++) {
            for (int j = 0; j < numColonnes; j++) {
                grilleInfo[i][j] = new CellInfo();
            }
        }

        for (int i = 0; i < numLignes; i++) {
            for (int j = 0; j < numColonnes; j++) {
                if (grilleForme[i][j].isAllOccupable()) {
                    if((i < 0 || j < 0) || (i >= numLignes || j >= numColonnes)) return;
                    else if(grilleInfo[i][j] == null) grilleInfo[i][j] = new CellInfo();

                    if (!grilleInfo[i][j].visited) {

                        if (verification(i, j-1)) grilleInfo[i][j].west = true;
                        if (verification(i, j+1)) grilleInfo[i][j].east = true;
                        if (verification(i-1, j)) grilleInfo[i][j].north = true;
                        if (verification(i+1, j)) grilleInfo[i][j].south = true;

                        grilleInfo[i][j].visited = true;
                    }
                }
            }
        }

        for (int i = 0; i < numLignes; i++) {
            for (int j = 0; j < numColonnes; j++) {
                switch (grilleForme[i][j]) {
                    case f_Rouge:
                        grilleInfo[i][j].presence = 'R';
                        grilleInfo[i][j].occuped = true;
                        break;
                    case f_Rose:
                        grilleInfo[i][j].presence = 'P';
                        grilleInfo[i][j].occuped = true;
                        break;
                    case f_Orange:
                        grilleInfo[i][j].presence = 'O';
                        grilleInfo[i][j].occuped = true;
                        break;
                    case f_Bleu:
                        grilleInfo[i][j].presence = 'B';
                        grilleInfo[i][j].occuped = true;
                        break;
                    case energie:
                        grilleInfo[i][j].presence = 'E';
                        break;
                    case piege:
                        grilleInfo[i][j].presence = 'K';
                        break;
                    case joueur:
                        grilleInfo[i][j].presence = 'J';
                        grilleInfo[i][j].occuped = true;
                        break;
                }
            }
        }
    }

    public boolean verification(int y, int x){
        if (Verification.coordValides(numLignes, numColonnes, y, x)){
            if(grilleForme[y][x].isAllOccupable()) return true;
            else return false;
        }// Verification.coordValides  permet de vérifier qu'on est dans l'enceinte du labyrinthe. 
        else return false;
    }
		
   // Les accésseurs
    public int getNumLignes() {
        return numLignes;
    }

    public int getNumColonnes() {
        return numColonnes;
    }

    public Etat [][] getGrilleForme(){
        return grilleForme;
    }

    public CellInfo [][] getGrilleInfo(){
        return grilleInfo;
    }

    public ArrayList<Coordonnee> getCoords(){
        return coords;
    }

    public ArrayList<Coordonnee> getFantomesCoords(){
        return fantomesCoords;
    }

    public ArrayList<Coordonnee> getJoueurs(){
        return joueurs;
    }

    public ArrayList<Coordonnee> getTeleporteurs(){
        return teleporteurs;
    }

    public ArrayList<Coordonnee> getPortes(){
        return portes;
    }

    public ArrayList<Coordonnee> getEnergies(){
        return energies;
    }


}