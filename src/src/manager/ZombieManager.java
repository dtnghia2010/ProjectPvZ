package manager;

import scenes.Playing;
import zombie.Zombie;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ZombieManager {
    private ArrayList<Zombie> zombies;
    private Image[] zImages;
    private Playing playing;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Random random = new Random();

    public ZombieManager(Playing playing) {
        this.playing = playing;
        zombies = new ArrayList<>();
        importImg();
    }

    public void importImg() {
        zImages = new Image[3];
        try {
            zImages[0] = t.getImage(getClass().getResource("/zombie/zombie.png"));
            zImages[1] = t.getImage(getClass().getResource("/zombie/conehead_zombie.png"));
            zImages[2] = t.getImage(getClass().getResource("/zombie/candyZombie.png"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR-importImg()-ZombieManager");
        }
    }

    public void spawnZombie(int type) {
        synchronized (zombies) {
            System.out.println("a zombie created");
            if(!allZombieDead()) {
                zombies.add(new Zombie(1024+rnd(0,1000), 60 + 95 * rnd(0,5), type));
            } else {
                zombies.add(new Zombie(1024, 60 + 95 * rnd(0,5), type));
            }
        }
    }

    public void draw(Graphics g) {
        synchronized (zombies) {
            if (zombies.size() > 0) {
                for (Zombie z : zombies) {
                    if (z.isAlived()) {
                        g.drawImage(zImages[z.getType()], (int) z.X(), (int) z.Y(), z.getWidth(), z.getHeight(), null);
                        g.setColor(Color.RED);
                        g.drawRect((int) z.X(), (int) z.Y(), z.getWidth(), z.getHeight());
                    }
                }
            }
        }
    }
    public void move(Zombie z) {
        if (z.X() <= 100) {
            z.dead();
        } else {
            z.move();
        }
    }

    public boolean allZombieDead() {
        for (Zombie z : zombies) {
            if (z.isAlived()) {
                return false;
            }
        }
        return true;
    }

    public void createHorde(int count) {
        for (int i = 0; i < count; i++) {
/*            int x = 1500 + rnd(0,1000);
            int y = 60 + 95 * rnd(0,1000);
            int type = rnd(0,2);
            Zombie zombie = new Zombie(x, y, type);*/
            spawnZombie(rnd(0,2));
        }
    }

    public void updates() {
        for (Zombie z : zombies) {
            if (z.isAlived()) {
                // Cập nhật tọa độ di chuyển cho zombie còn sống
                move(z);
            }
        }
    }


//    public void attack() {
//        for (Zombie z : zombies) {
//            z.bite(fakePlant);
//        }
//    }
    private int rnd(int s, int e) {
        return random.nextInt(s,e);
    }

    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

}



