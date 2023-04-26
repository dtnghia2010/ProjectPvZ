package manager;

import component.Tile;
import component.plant;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class plantManager {
    private List<plant> listOfPlant = new ArrayList<>();
    private Toolkit t = Toolkit.getDefaultToolkit();
    public plantManager(){

    }
    public void addPlant(int ID){
        listOfPlant.add(new plant(100,ID));
    }
    public void drawPlant(Graphics g, Tile tile){
        Graphics2D g2d = (Graphics2D) g;
        for(plant plant:listOfPlant){
            if(plant.getID() == 1){

            }
        }
    }
    public void mousePressed(int x, int y){
        TileManager tileManager = new TileManager();
        for(int i = 0;i<tileManager.getTiles().length;i++){
            if(tileManager.getTiles()[i].getBound().contains(x,y)){

            }
        }
    }
}
