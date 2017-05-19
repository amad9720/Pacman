/**
 * Created by Amadou Ly,Cheickhouna Lo, et Ismael Goulani le 26-01-2017.
 */

public class Fantome extends Perso {

    public Fantome(Labyrinthe labyrinthe, String typeFantome){
        this.type = typeFantome;
        initialCoordsFantome(labyrinthe);

    }

    public void initialCoordsFantome(Labyrinthe labyrinthe) {
        for (int i = 0; i < labyrinthe.getFantomesCoords().size(); i++) {
            if (labyrinthe.getFantomesCoords().get(i).presence.equals(type)) {
                this.x = labyrinthe.getFantomesCoords().get(i).x;
                this.y = labyrinthe.getFantomesCoords().get(i).y;
                break;
            }
        }
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public String getType(){
        return type;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }


    private String type;
    private int x;
    private int y;

}