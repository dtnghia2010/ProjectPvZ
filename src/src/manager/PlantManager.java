package manager;

import component.MyButtons;
import component.Plant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlantManager {
    private Image[] plantImages;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private List<Plant> plantList = new ArrayList<>();
    private MyButtons[] plantFromBar;
    private int type;
    private boolean selected, located;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public PlantManager(int type) {
        this.type = type;
        importImg();
        initButtons();
    }

    public void initPlants(){
        plantList.add(new Plant(100));
    }

    private void initButtons(){
        plantFromBar = new MyButtons[5];
        plantFromBar[0] = new MyButtons("Sunflower", 350, 0, 90, 90);
        plantFromBar[1] = new MyButtons("Peashooter", 440, 0, 90, 90);
        plantFromBar[2] = new MyButtons("Wall-nut", 530, 0, 90, 90);
        plantFromBar[3] = new MyButtons("Snow Pea", 620, 0, 90, 90);
        plantFromBar[4] = new MyButtons("Cherry Bomb", 710, 0, 90, 90);
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

    public Image getPlantImages() {
        return plantImages[type];
    }



    public List<Plant> getPlantList() {
        return plantList;
    }



    public MyButtons[] getPlantFromBar() {
        return plantFromBar;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public boolean getSelected(){
        return selected;
    }
    public boolean setLocated(boolean located) {
        this.located = located;
        return located;
    }
}
