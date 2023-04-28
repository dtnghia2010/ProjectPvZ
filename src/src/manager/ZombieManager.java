package manager;

import zombie.Zombie;

import javax.tools.Tool;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class ZombieManager {
    private ArrayList<Zombie> zombies;
    private Image[] zImages;
    private Toolkit t = Toolkit.getDefaultToolkit();

    public ZombieManager() {
        zombies = new ArrayList<>();
        importImg();
    }
    //newmethod - update zombie; create method update;

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

    public void addZombie(Zombie zombie) {
        zombies.add(zombie);
    }

    public void draw(Graphics g) {
        for (Zombie zombie : zombies) {
            int x = zombie.getX();
            int y = zombie.getY();
            g.drawImage(zImages[zombie.getType()], x, y, 100, 162, null);
        }
    }
    public void move(Graphics g) {
        updates();
        for (Zombie zombie : zombies) {
            int x = zombie.getX();
            int y = zombie.getY();
            int spd = zombie.getSpd();
            //g.drawImage(zImages[0], x, y, 100, 162, null);
            zombie.setX(x - spd);
        }
    }


    public void updates() {
        ArrayList<Zombie> deadZombies = new ArrayList<>(); // danh sách phụ để lưu trữ các zombie đã chết
        for (Zombie zombie : zombies) {
            if (zombie.isAlived()) {
                // Cập nhật tọa độ di chuyển cho zombie còn sống
                int x = zombie.getX();
                int y = zombie.getY();
                int spd = zombie.getSpd();
                zombie.move(-spd, 0);
            } else {
                // Thêm zombie đã chết vào danh sách phụ
                deadZombies.add(zombie);
            }
        }
        // Loại bỏ các zombie đã chết khỏi danh sách zombies
        zombies.removeAll(deadZombies);
    }
    public void addRandomZombies(int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int x = 1500 + random.nextInt(1000);
            int y = 60+95*random.nextInt(5);
            int type = random.nextInt(2) ;
            Zombie zombie = new Zombie(x, y, type);
            zombies.add(zombie);
        }
    }

}




