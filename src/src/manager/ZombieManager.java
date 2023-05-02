package manager;

import component.Plant;
import scenes.Playing;
import zombie.Zombie;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ZombieManager {
    private ArrayList<Zombie> zombies;
    private Image[] zImages;
    private Playing playing;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Random random = new Random();
    private static int realTimeCounter = 0;
    private static boolean isReset = false;
    private int counter = 0;
    public static int getRealTimeCounter() {
        return realTimeCounter;
    }

    public static void frameCount(){
        if(realTimeCounter<60){
            realTimeCounter++;
        }
    }
    public static void isResetTime(){
        if(isReset){
            realTimeCounter = 0;
            isReset = false;
        }
    }
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
            if(!allZombieDead()) {
                zombies.add(new Zombie(1024+rnd(0,1000), 60 + 95 * rnd(0,5), type));
            } else {
                zombies.add(new Zombie(1024, 60 + 95 * rnd(0,5), type));
            }
        }
    }

    public void draw(Graphics g) {
        synchronized (zombies) {
            if (zombies.size() > 0) {
                for (Zombie z : zombies) {
                    if (z.isAlived()) {
                        g.drawImage(zImages[z.getType()], (int) z.X(), (int) z.Y(), z.getWidth(), z.getHeight(), null);
                        g.setColor(Color.RED);
                        g.drawRect((int) z.X(), (int) z.Y(), z.getWidth(), z.getHeight());
                    }
                }
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

    public void createHorde(int count) {
        for (int i = 0; i < count; i++) {
/*            int x = 1500 + rnd(0,1000);
            int y = 60 + 95 * rnd(0,1000);
            int type = rnd(0,2);
            Zombie zombie = new Zombie(x, y, type);*/
            spawnZombie(rnd(0,2));
        }
    }

    public void updates() {
        frameCount();
        for (Zombie z : zombies) {
            if (z.isAlived()) {
                // Cập nhật tọa độ di chuyển cho zombie còn sống
                move(z);
            }
        }
    }


//    public void attack() {
//        for (Zombie z : zombies) {
//            z.bite(fakePlant);
//        }
//    }
    private int rnd(int s, int e) {
        return random.nextInt(s,e);
    }

    public ArrayList<Zombie> getZombies() {
        return zombies;
    }
    public void ZombieCollidePlant(PlantManager plantManager){
//        synchronized (zombies){
//            for(int i = 0;i<zombies.size();i++){
//                Rectangle r = new Rectangle((int)zombies.get(i).X(),(int)zombies.get(i).Y(),zombies.get(i).getWidth(),zombies.get(i).getHeight());
//                for(int j = 0;j<plantManager.getPlantList().size();j++){
//                    if(r.contains(plantManager.getPlantList().get(j).getX()+plantManager.getPlantList().get(j).getWidth(),plantManager.getPlantList().get(j).getY())){
//                        zombies.get(i).setCollided(true);
//                        if(realTimeCounter >= 90){
//                            zombies.get(i).attackPlant(plantManager.getPlantList().get(j));
//                            System.out.println("plant "+j+" hp: "+plantManager.getPlantList().get(j).getPlantHP());
//                            isReset = true;
//                        }
//                        for(int k = 0;k<zombies.size();k++){
//                            if(r.contains(zombies.get(k).X(),zombies.get(k).Y())){
//                                zombies.get(k).defeatPlant(plantManager.getPlantList().get(j));
//                            }
//                        }
//                    }
//                }
//            }
//            isResetTime();
//        }
        synchronized (zombies){
            Iterator<Zombie> iterator = zombies.iterator();
            while (iterator.hasNext()){
                Zombie zombie = iterator.next();
                Rectangle r = new Rectangle((int)zombie.X(),(int)zombie.Y(),zombie.getWidth(),zombie.getHeight());
                Iterator<Plant> iterator1 = plantManager.getPlantList().iterator();
                while (iterator1.hasNext()){
                    Plant plant = iterator1.next();
                    if(r.contains(plant.getX()+plant.getWidth(),plant.getY())){
                        zombie.setCollided(true);
                        if(realTimeCounter >= 60){
                            zombie.attackPlant(plant);
                            isReset = true;
                            plant.removePlant(plant,iterator1);
                        }
                        for(Zombie zombie1:zombies){
                            if(r.contains(zombie1.X(),zombie1.Y())){
                                zombie1.defeatPlant(plant);
                            }
                        }
                    }
                }
            }
            isResetTime();
            counter++;
        }
    }

}



