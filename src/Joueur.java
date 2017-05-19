
/**
 * Created by Amadou Ly,Cheickhouna Lo, et Ismael Goulani le 26-01-2017.
 */

public class Joueur extends Perso{
    public Joueur(Labyrinthe labyrinthe, int vie){
        this.vie = vie;
        initialCoordsJoueur(labyrinthe);
    }

    public void initialCoordsJoueur(Labyrinthe labyrinthe) {
        for (int i = 0; i < labyrinthe.getJoueurs().size(); i++) {
            if (labyrinthe.getJoueurs().get(i).presence.equals("J0")) {
                this.x = labyrinthe.getJoueurs().get(i).x;
                this.y = labyrinthe.getJoueurs().get(i).y;
                break;
            }
        }
    }

    public void decrementerVie(){
        this.vie--;
    }

    public void incrementeScore(int n){
        this.score += n;
    }

    public void afficherScore(){
        System.out.printf("\t\t\tScore : %d \n", this.score);
    }

    public int getVie(){
        return vie;
    }

    public void setVie(int vie){
        this.vie = vie;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int y){
        this.score = score;
    }

    private int score = 0;
    private int vie;
    private int x;
    private int y;
}