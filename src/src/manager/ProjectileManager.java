package manager;

import component.Plant;
import component.Projectile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjectileManager {
    private List<Projectile> listOfProjectile = new ArrayList<>();
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Image projectileImage;
    private int realTimeCounter = 0;
    private boolean isReset = false;

    public int getRealTimeCounter() {
        return realTimeCounter;
    }

    public void frameCount(){
        if(realTimeCounter<120){
            realTimeCounter++;
        }
    }
    public void projectileCreated(Plant plant){
        synchronized (listOfProjectile){
            if(plant.getPlantID() == 1 || plant.getPlantID() == 3){
                listOfProjectile.add(new Projectile(plant.getX()+plant.getWidth(),plant.getY()+8,plant.getATK()));
                isReset = true;
            }
        }
//        synchronized (listOfProjectile){
//            Iterator<Plant> iterator = plantManager.getPlantList().iterator();
//            while ((iterator.hasNext())){
//                Plant plant = iterator.next();
//                if(plant.getID() == 1 || plant.getID() == 3){
//                    if(realTimeCounter == 120){
//                        listOfProjectile.add(new Projectile(plant.getX()+plant.getWidth(),plant.getY()+8,(int)plant.getATK()));
//                        isReset = true;
//                    }
//                }
//            }
//        }
    }
    public void isResetTime(){
        if(isReset){
            realTimeCounter = 0;
            isReset = false;
        }
    }
    public void update(){
        frameCount();
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
    }
    public void drawProjectile(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        projectileImage = t.getImage(getClass().getResource("/Projectile/Pea.png"));
        synchronized (listOfProjectile){
            Iterator<Projectile> iterator = listOfProjectile.iterator();
            while ((iterator.hasNext())) {
                Projectile projectile = iterator.next();
                g2d.drawImage(projectileImage,projectile.getX(),projectile.getY(),30,30,null);
            }
        }
    }

    public void projectileCollideZombie(Zombie_fakeManager zombieFakeManager){
        Rectangle r = new Rectangle(zombieFakeManager.getZombieFake().getX(),zombieFakeManager.getZombieFake().getY(),20,50);
        synchronized (listOfProjectile){
            Iterator<Projectile> iterator = listOfProjectile.iterator();
            while (iterator.hasNext()){
                Projectile projectile = iterator.next();
                if(r.contains(projectile.getX()+30,projectile.getY())){
                    zombieFakeManager.getZombieFake().setHp(zombieFakeManager.getZombieFake().getHp()-projectile.getATK());
                    iterator.remove();
                }
            }
        }
    }
}
