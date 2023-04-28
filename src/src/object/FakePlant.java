package object;

import java.awt.*;

public class FakePlant {
    private int hp, dmg;
    private boolean isSelected = false;
    private boolean isPlaced = false;

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }

    private Toolkit t = Toolkit.getDefaultToolkit();
    private Image image;

    public boolean isSelected() {
        return isSelected;
    }
    private void importImg() {
        try {
            image = t.getImage(getClass().getResource("/plant/pea_shooter.png"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR - importImg()");
        }
    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public FakePlant() {
        hp = 100;
        dmg = 0;
        importImg();
    }
    public void hurt(int dmg) {
        hp -= dmg;
    }
    public Image getPlantImg() {
        return image;
    }
}
