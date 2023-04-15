package manager;

import object.Zombie;
import scene.Playing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ZombieManager {
    private Playing playing;
    private ArrayList<Zombie> zombies;
    private Image[] zomImg;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private int zWidth = 80, zHeight = 150;

    public ZombieManager(Playing playing) {
        this.playing = playing;
        zombies = new ArrayList<>();
        importImg();
    }
    public void addZombie(int type, int x, int y) {
        zombies.add(new Zombie(type, x, y));
    }
    public void draw(Graphics g) {
        for(Zombie z: zombies) {
            if(z != null) {
                g.drawImage(getZomImg(z), (int)z.getX(), (int)z.getY(), zWidth, zHeight, null);
            }
        }
    }
    private void importImg() {
        zomImg = new Image[2];
        try {
            zomImg[0] = t.getImage(getClass().getResource("/zombie/zombie.png"));
            zomImg[1] = t.getImage(getClass().getResource("/zombie/conehead_zombie.png"));
        } catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR - importing IMG (zombie class)");
        }
    }
    private Image getZomImg(Zombie z) {
        return zomImg[z.getType()];
    }
    public void update() {
        for(Zombie z: zombies) {
            if(z != null ) {
                move();
            }
        }
    }
    public void move() {
        for(Zombie z: zombies) {
            if(z.getX() < 100) {
                z = null;
            } else {
                z.setX(z.getX()-z.getSpd());
            }
        }
    }
}
