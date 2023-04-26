package manager;

import component.MyButtons;
import component.Tile;
import component.plant;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class plantManager {
    private List<plant> listOfPlant = new ArrayList<>();
    private TileManager tileManager = new TileManager();
    private Image[] plantImage = new Image[5];
    private Toolkit t = Toolkit.getDefaultToolkit();
    private int IDholder;

    public int getIDholder() {
        return IDholder;
    }

    public void setIDholder(int IDholder) {
        this.IDholder = IDholder;
    }
    public List<plant> getListOfPlant() {
        return listOfPlant;
    }
    public plantManager(){

    }
    public void addPlant(int ID){
        listOfPlant.add(new plant(100,ID));
    }
    public void drawPlant(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        for(plant plant:listOfPlant){
            if(plant.getID() == 1){
                plantImage[0] = t.getImage(getClass().getResource("/plant/sunflower.png"));
                g2d.drawImage(plantImage[0],tileManager.getTiles()[plant.getTileNum()].getCurX(),tileManager.getTiles()[plant.getTileNum()].getCurY(),tileManager.getTiles()[plant.getTileNum()].getwTile(),tileManager.getTiles()[plant.getTileNum()].gethTile(),null);
            } else if(plant.getID() == 2){
                plantImage[1] = t.getImage(getClass().getResource("/plant/pea_shooter.png"));
                g2d.drawImage(plantImage[1],tileManager.getTiles()[plant.getTileNum()].getCurX(),tileManager.getTiles()[plant.getTileNum()].getCurY(),tileManager.getTiles()[plant.getTileNum()].getwTile(),tileManager.getTiles()[plant.getTileNum()].gethTile(),null);
            }
        }
    }
    public void mousePressedField(int x, int y){
        for(int i = 0;i<tileManager.getTiles().length;i++){
            Rectangle r = new Rectangle(tileManager.getTiles()[i].getCurX(),tileManager.getTiles()[i].getCurY(),tileManager.getTiles()[i].getwTile(),tileManager.getTiles()[i].gethTile());
            if(r.contains(x,y)){
                addPlant(IDholder);
                for(int j = 0;j<listOfPlant.size();j++){
                    listOfPlant.get(listOfPlant.size()-1).setTileNum(i);
                }
            }
        }
    }
}
