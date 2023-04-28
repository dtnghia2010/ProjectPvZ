package manager;

import component.MyButtons;
import component.Plant;
import component.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlantManager {
    private Image[] plantImages;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private List<Plant> plantList = new ArrayList<>();
    private int type;
    private boolean selected = false;
    private boolean located = false;

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
}
