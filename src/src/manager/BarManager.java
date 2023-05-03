package manager;

import component.MyButtons;

import javax.swing.*;
import java.awt.*;

public class BarManager {
    private Image[] pick_plantBar;
    private MyButtons pickPlant[];
    private Toolkit t = Toolkit.getDefaultToolkit();
    public BarManager() {
        initButtons();
        importImg();
    }

    private void initButtons() {
        pickPlant = new MyButtons[5];
        pickPlant[0] = new MyButtons("Sunflower", 350, 0, 90, 90);
        pickPlant[1] = new MyButtons("Peashooter", 440, 0, 90, 90);
        pickPlant[2] = new MyButtons("Wall-nut", 530, 0, 90, 90);
        pickPlant[3] = new MyButtons("Snow Pea", 620, 0, 90, 90);
        pickPlant[4] = new MyButtons("Cherry Bomb", 710, 0, 90, 90);
    }

    private void importImg(){
        pick_plantBar = new Image[5];
        try {
            pick_plantBar[0] = t.getImage(getClass().getResource("/plantBar/PvZ_Sunflower.jpg"));
            pick_plantBar[1] = t.getImage(getClass().getResource("/plantBar/PvZ_Peashooter.jpg"));
            pick_plantBar[2] = t.getImage(getClass().getResource("/plantBar/PvZ_Wall-nut.jpg"));
            pick_plantBar[3] = t.getImage(getClass().getResource("/plantBar/PvZ_Snow_Pea.jpg"));
            pick_plantBar[4] = t.getImage(getClass().getResource("/plantBar/PvZ_Cherry_Bomb.jpg"));
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error - importImage()");
        }
    }
    public void drawPlantbar(Graphics g){
        g.setColor(Color.black);
        g.drawRect(350, 0,450,90);
        g.setColor(Color.pink);
        g.fillRect(350, 0, 450, 90);
        int distance = 0;
        for (Image p : pick_plantBar){
            g.drawImage(p, 350 + distance, 0, 90, 90, null);
            distance += 90;
        }
    }

    public MyButtons[] getPickPlant() {
        return pickPlant;
    }
}
