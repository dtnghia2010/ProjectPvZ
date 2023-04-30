package manager;

import component.Tile;
import component.Zombie_fake;

import java.awt.*;

public class Zombie_fakeManager {
    private Zombie_fake zombieFake;

    public Zombie_fake getZombieFake() {
        return zombieFake;
    }

    private TileManager tileManager = new TileManager();
    private Toolkit t = Toolkit.getDefaultToolkit();
    public Zombie_fakeManager(){
        this.zombieFake = new Zombie_fake(1000,(int)tileManager.getTiles()[26].getBound().getX(),(int)tileManager.getTiles()[26].getBound().getY());
    }
    public void drawZombie(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        Image zomImage = t.getImage(getClass().getResource("/zombie/zombie.png"));
        g2d.drawImage(zomImage,zombieFake.getX(),zombieFake.getY(),20,50,null);
    }
}
