package manager;

import Audio.Audio;
import Plant.Plant;
import Plant.Projectile;
import scenes.Playing;
import zombie.Zombie;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjectileManager {
    private List<Projectile> listOfProjectile = new ArrayList<>();
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Playing playing;
    private Image[] projectileImage = new Image[2];

    public ProjectileManager(Playing playing){
        this.playing = playing;
    }

    public void projectileCreated(Plant plant){
        synchronized (listOfProjectile){
            if(plant.getPlantID() == 1){
                listOfProjectile.add(new Projectile(plant.getX()+plant.getWidth(),plant.getY()+8,plant.getATK(),1));
            } else if(plant.getPlantID() == 3){
                listOfProjectile.add(new Projectile(plant.getX()+plant.getWidth(),plant.getY()+8,plant.getATK(),2));
            }
        }
    }


    public void update(){
        synchronized (listOfProjectile){
            Iterator<Projectile> iterator = listOfProjectile.iterator();
            while ((iterator.hasNext())){
                Projectile projectile = iterator.next();
                projectile.move();
                if(projectile.getX() >= 1024){
                    iterator.remove();
                }
            }
        }
        projectileCollideZombie();
    }
    public void drawProjectile(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        projectileImage[0] = t.getImage(getClass().getResource("/Projectile/Pea.png"));
        projectileImage[1] = t.getImage(getClass().getResource("/Projectile/shadow_projectile.png"));
        synchronized (listOfProjectile){
            Iterator<Projectile> iterator = listOfProjectile.iterator();
            while ((iterator.hasNext())) {
                Projectile projectile = iterator.next();
                if(projectile.getID() == 1){
                    g2d.drawImage(projectileImage[0],projectile.getX(),projectile.getY(),30,30,null);
                } else if(projectile.getID() == 2){
                    g2d.drawImage(projectileImage[1],projectile.getX(),projectile.getY()-10,70,30,null);
                }
            }
        }
    }

    public void projectileCollideZombie(){
        synchronized (playing.getZombieManager().getZombies()){
            Iterator<Zombie> iterator = playing.getZombieManager().getZombies().iterator();
            while ((iterator.hasNext())){
                Zombie zombie = iterator.next();
                Rectangle r = new Rectangle();
                if(zombie.getType() == 0 || zombie.getType() == 1){
                    r.setBounds((int)zombie.getBound().getX()+25,(int)zombie.getBound().getY(),(int)zombie.getBound().getWidth(),(int)zombie.getBound().getHeight());
                } else {
                    r.setBounds((int) zombie.X(),(int) zombie.Y(),zombie.getWidth(),zombie.getHeight());
                }
                synchronized (listOfProjectile){
                    Iterator<Projectile> iterator2 = listOfProjectile.iterator();
                    while (iterator2.hasNext()){
                        Projectile projectile = iterator2.next();
                        if(projectile.getID() == 1){
                            projectileDealDamage(30,r,projectile,zombie,iterator2);
                        } else if(projectile.getID() == 2){
                            projectileDealDamage(50,r,projectile,zombie,iterator2);
                        }
                    }
                }
            }
        }
    }
    public void projectileDealDamage(int distance,Rectangle r, Projectile projectile, Zombie zombie, Iterator iterator2){
        if(r.contains(projectile.getX()+distance,projectile.getY()) && zombie.isAlived()){
            Audio.splat();
            zombie.setHp(zombie.getHp()-projectile.getATK());
            if(projectile.getID() == 2 && !zombie.isSlowed()){
                zombie.setSpd(zombie.getSpd()/2);
                zombie.setSlowed(true);
            }
            iterator2.remove();
            if(zombie.getHp() <= 0){
                Audio.zombieDeath();
                zombie.setDead(true);
                zombie.dead();
            }
        }
    }
}
