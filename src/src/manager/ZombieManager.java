package manager;

import zombie.Zombie;

import javax.tools.Tool;
import java.awt.*;
import java.util.ArrayList;

public class ZombieManager {
    private ArrayList<Zombie> zombies;
    private Image[] zImages;
    private Toolkit t = Toolkit.getDefaultToolkit();
    public ZombieManager() {
        zombies = new ArrayList<>();
        importImg();
    }
    public void importImg() {
         zImages = new Image[2];
         try {
             zImages[0] = t.getImage(getClass().getResource("/zombie/zombie.png"));
             zImages[1] = t.getImage(getClass().getResource("/zombie/conehead_zombie.png"));
         } catch (Exception e) {
             e.printStackTrace();
             System.err.println("ERROR-importImg()-ZombieManager");
         }
    }
    public void draw() {

    }
    public void move() {

    }
    public void bite() {

    }
}
