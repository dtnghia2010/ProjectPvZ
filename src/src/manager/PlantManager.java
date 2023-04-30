package manager;

import component.Plant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlantManager {
    private Image[] plantImages;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private List<Plant> plantList = new ArrayList<>();
    private TileManager tileManager = new TileManager();
    private int type;
    private boolean selected = false;
    private boolean located = false;
    private int IDhold;

    public int getIDhold() {
        return IDhold;
    }

    public void setIDhold(int IDhold) {
        this.IDhold = IDhold;
    }



    public boolean isLocated() {
        return located;
    }

    public void setLocated(boolean located) {
        this.located = located;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public PlantManager() {
        importImg();
    }

    public void initPlants(int plantID) {
        plantList.add(new Plant(100, plantID));
    }

    private void importImg() {
        plantImages = new Image[5];
        try {
            plantImages[0] = t.getImage(getClass().getResource("/plant/sunflower.png"));
            plantImages[1] = t.getImage(getClass().getResource("/plant/pea_shooter.png"));
            plantImages[2] = t.getImage(getClass().getResource("/plant/Wall-nut.png"));
            plantImages[3] = t.getImage(getClass().getResource("/plant/snow_pea_shooter.png"));
            plantImages[4] = t.getImage(getClass().getResource("/plant/cherry_bomb.png"));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error - importImage()");
        }
    }

    public void drawPlant(Graphics g){
        for (Plant pl : plantList){
            if (pl.getPlantID() == 0){
                g.drawImage(plantImages[0], tileManager.getTiles()[pl.getTileHold()].getCurX(), tileManager.getTiles()[pl.getTileHold()].getCurY(), tileManager.getTiles()[pl.getTileHold()].getwTile(), tileManager.getTiles()[pl.getTileHold()].gethTile(), null);
            } else if (pl.getPlantID() == 1){
                g.drawImage(plantImages[1], tileManager.getTiles()[pl.getTileHold()].getCurX(), tileManager.getTiles()[pl.getTileHold()].getCurY(), tileManager.getTiles()[pl.getTileHold()].getwTile(), tileManager.getTiles()[pl.getTileHold()].gethTile(), null);
            } else if (pl.getPlantID() == 2){
                g.drawImage(plantImages[2], tileManager.getTiles()[pl.getTileHold()].getCurX(), tileManager.getTiles()[pl.getTileHold()].getCurY(), tileManager.getTiles()[pl.getTileHold()].getwTile(), tileManager.getTiles()[pl.getTileHold()].gethTile(), null);
            } else if (pl.getPlantID() == 3){
                g.drawImage(plantImages[3], tileManager.getTiles()[pl.getTileHold()].getCurX(), tileManager.getTiles()[pl.getTileHold()].getCurY(), tileManager.getTiles()[pl.getTileHold()].getwTile(), tileManager.getTiles()[pl.getTileHold()].gethTile(), null);
            } else if (pl.getPlantID() == 4){
                g.drawImage(plantImages[4], tileManager.getTiles()[pl.getTileHold()].getCurX(), tileManager.getTiles()[pl.getTileHold()].getCurY(), tileManager.getTiles()[pl.getTileHold()].getwTile(), tileManager.getTiles()[pl.getTileHold()].gethTile(), null);
            }
        }
    }

    public Image getPlantImages(int plantID) {
        return plantImages[plantID];
    }


    public List<Plant> getPlantList() {
        return plantList;
    }


    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean getSelected() {
        return selected;
    }

    public void mouse(int x, int y){
        if(selected){
            for (int i = 0; i < tileManager.getTiles().length; i++){
                if(!tileManager.getTiles()[i].isOccupied()){
                    Rectangle r = new Rectangle(tileManager.getTiles()[i].getCurX(), tileManager.getTiles()[i].getCurY(), tileManager.getTiles()[i].getwTile(), tileManager.getTiles()[i].gethTile());
                    if (r.contains(x, y)){
                        tileManager.getTiles()[i].setOccupied(true);
                        initPlants(IDhold);
                        for (int j = 0; j < plantList.size(); j++){
                            plantList.get(plantList.size() - 1).setTileHold(i);
                        }
                    }
                }
            }
        }
    }
}
