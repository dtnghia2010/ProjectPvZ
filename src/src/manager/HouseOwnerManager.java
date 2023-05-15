package manager;

import component.Plant;
import component.Tile;
import scenes.Playing;
import HouseOwner.HouseOwner;
import zombie.Zombie;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

public class HouseOwnerManager {
    private HouseOwner houseOwner;
    private Image[] zImages;
    private Playing playing;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Random random = new Random();
    private static int realTimeCounter = 0;
    private static boolean isReset = false;
    private int counter = 0;
    private BufferedImage houseOwnerImage;
    private int x;
    private int y;
    private int speed;

    //public static int getRealTimeCounter() {
    //return realTimeCounter;

    public HouseOwnerManager(Playing playing) {
        this.playing = playing;
        houseOwner = new HouseOwner(200, 470, 100);
        importImg();
    }

    public HouseOwnerManager(String imageUrl, int x, int y, int speed){
            try {
                URL url = new URL(imageUrl);
                this.houseOwnerImage = ImageIO.read(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.x = x;
            this.y = y;
            this.speed = speed;
        }


        public void moveUp () {
            this.y -= this.speed;
        }

        public void moveDown () {
            this.y += this.speed;
        }


//    public static void frameCount() {
//        if (realTimeCounter < 30) {
//            realTimeCounter++;
//        }
//    }
//
//    public static void isResetTime() {
//        if (isReset) {
//            realTimeCounter = 0;
//            isReset = false;
//        }
//    }

    public void importImg() {
        zImages = new Image[1];
        try {
            Image houseOwnerImg = ImageIO.read(getClass().getResource("/HouseOwner/HouseOwner.png"));
            zImages[0] = houseOwnerImg.getScaledInstance(120, 150, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ERROR-importImg()-HouseOwner");
        }
    }


    public void draw(Graphics g) {
        synchronized (houseOwner) {
            if (houseOwner.isAlived()) {
                g.drawImage(zImages[0], (int) houseOwner.getX(), (int) houseOwner.getY(), null);
            }
        }
    }


    public void move() {
        if (y <= 0) {
            y = houseOwner.getHeight() - 80;
        } else if (y >= houseOwner.getHeight() - 80) {
            y = 0;
        }
        y -= speed;
    }
    public void mouseClicked(int x, int y) {
        if ((x <= 310) &&(x>=150)) {
            int mouseX = x;
            int mouseY = y;
            int ownerX = (int) houseOwner.getX();
            int ownerY = (int) houseOwner.getY();
            int distanceX = mouseX - ownerX;
            int distanceY = mouseY - ownerY;
            int steps = 5;
            for (int i = 0; i < steps; i++) {
                int newX = ownerX + (distanceX * i / steps);
                int newY = ownerY + (distanceY * i / steps);
                houseOwner.setLocation(newX, newY);
                try {
                    Thread.sleep(50); // Tạm dừng trong một khoảng thời gian nhỏ để tạo hiệu ứng di chuyển
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setHouseOwnerDangered(Tile tile){
        Rectangle HouseOwner = tile.getBound();
            if(HouseOwner.contains(houseOwner.getX(),houseOwner.getY())){
                houseOwner.setDangered(true);
            }
    }
    public void setHouseOwnerIdle(Tile tile){
        Rectangle HouseOwner = tile.getBound();
            if(HouseOwner.contains(houseOwner.getX(),houseOwner.getY())){
                houseOwner.setDangered(false);
            }
    }

    public void alertPlant(TileManager tileManager, ZombieManager zombieManager){
        for(int i = 0;i<tileManager.getTiles().length;i++){
            Rectangle r = tileManager.getTiles()[i].getBound();
            Iterator<Zombie> iterator = zombieManager.getZombies().iterator();
            while (iterator.hasNext()){
                Zombie zombie = iterator.next();
                if(r.contains(zombie.X(),zombie.Y()+70)){
                    if(i>=0 && i<9){
                        for(int j = 0;j < 9;j++){
                            setHouseOwnerDangered(tileManager.getTiles()[j]);
                        }
                    } else if(i >= 9 && i<18){
                        for(int j = 9;j < 18;j++){
                            setHouseOwnerDangered(tileManager.getTiles()[j]);
                        }
                    } else if(i>=18 && i<27){
                        for(int j = 18;j < 27;j++){
                            setHouseOwnerDangered(tileManager.getTiles()[j]);
                        }
                    } else if(i>=27 && i<36){
                        for(int j = 27;j < 36;j++){
                            setHouseOwnerDangered(tileManager.getTiles()[j]);
                        }
                    } else if (i >= 36 && i<45) {
                        for(int j = 36;j < 45;j++){
                            setHouseOwnerDangered(tileManager.getTiles()[j]);
                        }
                    }
                }
            }
        }
    }
    public void HouseOwnerAttack(ProjectileManager projectileManager){

        if(projectileManager.getRealTimeCounter() == 90){
            synchronized (houseOwner){
                    if(houseOwner.isDangered()){
                        projectileManager.projectileCreated(HouseOwner);
                    }
                }
            }
            projectileManager.isResetTime();
        }

    public int getX() {
        return x;
    }
    public int getY(){
        return y;
    }
}



