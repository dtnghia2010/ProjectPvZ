package manager;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class HouseOwnerManager {
    private BufferedImage houseOwnerImage;
    private int x;
    private int y;
    private int speed;

    public HouseOwnerManager(String imageUrl, int x, int y, int speed) {
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


    public void moveUp() {
        this.y -= this.speed;
    }

    public void moveDown() {
        this.y += this.speed;
    }

    public void draw(Graphics g) {
        g.drawImage(this.houseOwnerImage, this.x, this.y, null);
    }
}
