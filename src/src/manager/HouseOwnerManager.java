package manager;

import scenes.Playing;
import HouseOwner.HouseOwner;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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


    public static void frameCount() {
        if (realTimeCounter < 30) {
            realTimeCounter++;
        }
    }

    public static void isResetTime() {
        if (isReset) {
            realTimeCounter = 0;
            isReset = false;
        }
    }

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

}


