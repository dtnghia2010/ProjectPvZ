package object;

import javax.swing.*;
import java.awt.*;

public class Plant implements Character {
    private boolean seleted = false, placed = false;
    private boolean idle = true, threaten = false;
    private int x, y, type;
    private int hp, dmg;
    private Image[] plantImgs;
    private Toolkit t = Toolkit.getDefaultToolkit();
    public Plant(int type) {
        this.type = type;
        importImg();
    }

    private void importImg() {
        plantImgs = new Image[3];
        try {
            plantImgs[0] = t.getImage(getClass().getResource("/plant/sunflower.png"));
            plantImgs[1] = t.getImage(getClass().getResource("/plant/pea_shooter.png"));
            plantImgs[2] = t.getImage(getClass().getResource("/plant/cherry_bomb.png"));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR - Import IMG (Plant class)");
        }
    }

    public Image getPlantImg() {
        return plantImgs[type];
    }
    public void setSeleted(Boolean b) {
        seleted = b;
    }
    public void setPlaced(boolean b) {
        placed = b;
    }
    public boolean isSelected() {
        return seleted;
    }
    public boolean isPlaced() {
        return placed;
    }

    @Override
    public void Hit() {

    }

    @Override
    public void getHit() {

    }
}
