package manager;

import scenes.Playing;
import zombie.Zombie;

import javax.tools.Tool;
import java.awt.*;
import java.util.ArrayList;

public class ZombieManager {
    private final int zWidth = 80, zHeight = 140;
    private ArrayList<Zombie> zombies;
    private Image[] zImages;
    private Playing playing;
    private Toolkit t = Toolkit.getDefaultToolkit();
    public ZombieManager(Playing playing) {
        this.playing = playing;
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

    public void spawnZombie(int type) {
        synchronized (zombies) {
            zombies.add(new Zombie(1024, 60, type));
        }
    }
    public void draw(Graphics g) {
        for(Zombie z: zombies) {
            g.drawImage(zImages[z.getType()], z.X(), z.Y(), zWidth, zHeight, null);
        }
/*        g.drawImage(zImages[0], 900,60, zWidth,zHeight, null);
        g.drawImage(zImages[0], 900,60+95, zWidth,zHeight, null);
        g.drawImage(zImages[0], 900,60+95*2, zWidth,zHeight, null);
        g.drawImage(zImages[0], 900,60+95*3, zWidth,zHeight, null);
        g.drawImage(zImages[0], 900,60+95*4, zWidth,zHeight, null);*/
    }
    public void move() {
        for(Zombie z: zombies) {
            z.move();
        }
    }
    public void bite() {

    }
}
