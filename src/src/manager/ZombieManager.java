package manager;

import scenes.Playing;
import zombie.Zombie;
import Object.FakePlant;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class ZombieManager {
    private final int zWidth = 80, zHeight = 140;
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
            zombies.add(new Zombie(1024, 60+95*rnd(), type));
        }
    }

    public void draw(Graphics g) {
        synchronized (zombies) {
            if (zombies.size() > 0) {
                for (Zombie z : zombies) {
                    if (z.isAlived()) {
                        g.drawImage(zImages[z.getType()], z.X(), z.Y(), zWidth, zHeight, null);
                    }
                }
            }
        }
    }

    public void updates() {
        for (Zombie z : zombies) {
            if (z.isAlived()) {
                move(z);
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

    public void attack() {
        FakePlant fakePlant = null;
        for (Zombie z: zombies) {
            z.bite(fakePlant);
        }
    }

    private int rnd() {
        return random.nextInt(0,5);
    }

    public ArrayList<Zombie> getZombies() {
        return zombies;
    }


}
