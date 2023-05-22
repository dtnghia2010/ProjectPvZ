package Projectile;

import component.Plant;

import java.awt.*;
import java.util.Iterator;

public class ProjectileOfPlant extends ProjectileLogic {
    private Image[] projectileImage = new Image[2];
    private Toolkit t = Toolkit.getDefaultToolkit();

    public void isResetTime() {
        if (isIsReset()) {
//            realTimeCounter = 0;
            setRealTimeCounter(0);
            setIsReset(false);
        }
    }

    public void projectileCreated(Shooter shooter) {
        synchronized (getListOfProjectile()) {
            if (shooter.getID() == 1) {
                getListOfProjectile().add(new Projectile(shooter.getX() + shooter.getWidth(), shooter.getY() + 8, shooter.getATK(), 1));
//                isReset = true;
                setIsReset(true);
            } else if (shooter.getID() == 3) {
                getListOfProjectile().add(new Projectile(shooter.getX() + shooter.getWidth(), shooter.getY() + 8, shooter.getATK(), 2));
//                isReset = true;
                setIsReset(true);
            }
        }

    }


    @Override
    public void drawProjectile(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        projectileImage[0] = t.getImage(getClass().getResource("/Projectile/Pea.png"));
        projectileImage[1] = t.getImage(getClass().getResource("/Projectile/SnowPea.png"));
        synchronized (getListOfProjectile()) {
            Iterator<Projectile> iterator = getListOfProjectile().iterator();
            while ((iterator.hasNext())) {
                Projectile projectile = iterator.next();
                if (projectile.getID() == 1) {
                    g2d.drawImage(projectileImage[0], projectile.getX(), projectile.getY(), 30, 30, null);
                } else if (projectile.getID() == 3) {
                    g2d.drawImage(projectileImage[1], projectile.getX(), projectile.getY(), 30, 30, null);
                }
            }

        }
    }
}
