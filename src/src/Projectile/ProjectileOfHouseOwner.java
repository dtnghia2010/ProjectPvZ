package Projectile;

import HouseOwner.HouseOwner;
import scenes.Playing;

import java.awt.*;
import java.util.Iterator;

public class ProjectileOfHouseOwner extends ProjectileLogic {
    private Image[] projectileImage = new Image[1];
    private Toolkit t = Toolkit.getDefaultToolkit();
    public void isResetTime() {
        if (isIsReset()) {
//            realTimeCounter = 0;
            setRealTimeCounter(0);
            setIsReset(false);
        }
    }

    @Override
    public void projectileCreated(Shooter shooter) {
        synchronized (getListOfProjectile()) {
            int x = (int) shooter.getX();
            int y = (int) shooter.getY();
            getListOfProjectile().add(new Projectile(x + shooter.getWidth() + 20, y + 30, shooter.getATK(), 3));
//            isReset = true;
            setIsReset(true);
        }
    }

    @Override
    public void drawProjectile(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        projectileImage[0] = t.getImage(getClass().getResource("/Projectile/HouseOwner.png"));
        synchronized (getListOfProjectile()) {
            Iterator<Projectile> iterator = getListOfProjectile().iterator();
            while ((iterator.hasNext())) {
                Projectile projectile = iterator.next();
                if (projectile.getID() == 3) {
                    g2d.drawImage(projectileImage[0], projectile.getX(), projectile.getY(), 30, 30, null);
                }
            }
        }
    }
}
