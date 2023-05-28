package manager;

import Audio.Audio;
import Plant.Plant;
import scenes.Playing;
import zombie.Zombie;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ZombieManager {
    private ArrayList<Zombie> zombies;
    private Image[] zImages;
    private Image[] normalZombie_Move = new Image[52];
    private Image[] normalZombie_Eat = new Image[25];
    private Image[] coneHead_Move = new Image[67];
    private Playing playing;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Random random = new Random();
    private static int realTimeCounter = 0;
    private static boolean isReset = false;
    private static boolean zReachedEnd = false;
    private int counter = 0;
    private static ZombieManager instance = null;
    public static int getRealTimeCounter() {
        return realTimeCounter;
    }

    public static void frameCount(){
        if(realTimeCounter<30){
            realTimeCounter++;
        }
    }
    public static void isResetTime(){
        if(isReset){
            realTimeCounter = 0;
            isReset = false;
        }
    }
    private ZombieManager(Playing playing) {
        this.playing = playing;
        zombies = new ArrayList<>();
        importImg();
        importNormalZombie();
        importConeHead();
//        initZombieTest();
    }

    public static ZombieManager createZombieManager(Playing playing) {
        if(instance == null) {
            instance = new ZombieManager(playing);
        } else {
            System.out.println("Cannot create another ZombieManager");
        }
        return instance;
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
    public void importNormalZombie(){
        for (int i = 0;i<normalZombie_Move.length;i++){
            normalZombie_Move[i] = t.getImage(getClass().getResource("/zombie - move/"+i+".png"));
        }
        for(int i = 0;i<normalZombie_Eat.length;i++){
            normalZombie_Eat[i] = t.getImage(getClass().getResource("/zombie - eat/"+i+".png"));
        }
    }
    public void importConeHead(){
        for(int i = 0;i<coneHead_Move.length;i++){
            coneHead_Move[i] = t.getImage(getClass().getResource("/conehead - move/"+i+".png"));
        }
    }
    public void initZombieTest(){
        zombies.add(new Zombie(-999,-999,0));
    }
    public void spawnZombie(int type) {
        synchronized (zombies) {
            System.out.println("a zombie created");
            if(!allZombieDead()) {
                zombies.add(new Zombie(1024+rnd(0,1000), 133 + 80 * rnd(0,5), type));
                if(rnd(0,100) > 90) {
                    Audio.zombieGroaning();
                }
            } else {
                zombies.add(new Zombie(1024, 140 + 80 * rnd(0,5), type));
            }
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        synchronized (zombies) {
            if (zombies.size() > 0) {
                for (Zombie z : zombies) {
                    if (z.isAlived()) {
                        if(z.getType() == 0){
                            if(!z.isCollided()){
                                g.drawImage(normalZombie_Move[z.getFrameCountMove()],(int) z.X(), (int) z.Y(), z.getWidth()+30, z.getHeight()+25, null);
//                            g.setColor(Color.RED);
//                            g2d.drawRect((int)z.X()-50,(int)z.Y(),z.getWidth()+100,z.getHeight());
                            } else if(z.isCollided()) {
                                g.drawImage(normalZombie_Eat[z.getFrameCountEat()],(int) z.X(), (int) z.Y()+8, z.getWidth()+18, z.getHeight(), null);
                            }
                        } else if (z.getType() == 1) {
                            g.drawImage(coneHead_Move[z.getFrameCountMove()],(int) z.X(), (int) z.Y(), z.getWidth()+30, z.getHeight()+10, null);
//                            g.setColor(Color.RED);
//                            g2d.drawRect((int)z.X()-50,(int)z.Y(),z.getWidth()+100,z.getHeight());
//                            g2d.drawRect((int)z.getBound().getX(),(int) z.getBound().getY(), (int)z.getBound().getWidth(),(int)z.getBound().getHeight());
                        } else {
                            g.drawImage(zImages[z.getType()], (int) z.X(), (int) z.Y(), z.getWidth(), z.getHeight(), null);
                            g.setColor(Color.RED);
//                             g.drawRect((int) z.X(), (int) z.Y(), z.getWidth(), z.getHeight());
//                            g2d.fillRect((int) z.getBound().getX(),(int) z.getBound().getY(), (int)z.getBound().getWidth(),(int)z.getBound().getHeight());
                        }
                    }
                }
            }
        }
    }
    public void move(Zombie z) {
/*        if (z.X() <= 100) {
            z.dead();
            zReachedEnd = true;
        } else {
            if(z.getType() == 0 || z.getType() == 1){
                z.updateFrameCountMove();
            } else {
                z.move();
            }
        }*/
        if(z.getType() == 0 || z.getType() == 1){
            z.updateFrameCountMove();
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
            spawnZombie(rnd(0, 2));
        }
    }

    public void updates() {
        frameCount();
        for (Zombie z : zombies) {
            if (z.isAlived()) {
                // Cập nhật tọa độ di chuyển cho zombie còn sống
                if (z.X() <= 100) {
                    z.dead();
                    zReachedEnd = true;
                } else {
                    move(z);
                }
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

    public static boolean iszReachedEnd() {
        return zReachedEnd;
    }

    public void ZombieCollidePlant(){
        synchronized (zombies){
            Iterator<Zombie> iterator = zombies.iterator();
            while (iterator.hasNext()){
                Zombie zombie = iterator.next();
                Rectangle r = new Rectangle();
                if(zombie.getType() == 0 || zombie.getType() == 1){
                    r.setBounds((int)zombie.getBound().getX(),(int)zombie.getBound().getY(),(int)zombie.getBound().getWidth(),(int)zombie.getBound().getHeight());
                } else {
                    r.setBounds((int) zombie.X(),(int) zombie.Y(),zombie.getWidth(),zombie.getHeight());
                }
                synchronized (playing.getPlantManager().getPlantList()){
                    Iterator<Plant> iterator1 = playing.getPlantManager().getPlantList().iterator();
                    while (iterator1.hasNext()){
                        Plant plant = iterator1.next();
                        if(plant.isAlived()){
                            if(r.contains(plant.getX()+plant.getWidth()-zombie.getWidth()+30,plant.getY()) && zombie.isAlived()){
                                zombie.setCollided(true);
                                zombie.updateFrameCountEat();
                                if(realTimeCounter >= 30){
                                    Audio.zombieEat();
                                    zombie.attackPlant(plant);
                                    isReset = true;
                                    plant.removePlant(plant,iterator1,playing.getTileManager(),playing.getPlantManager());
                                    zombie.defeatPlant(plant);
                                }
                                for(Zombie zombie1:zombies){
                                    Rectangle rZombie = new Rectangle((int)zombie1.X()-50,(int)zombie1.Y(),zombie1.getWidth()+100,zombie1.getHeight());
                                    if(r.intersects(rZombie)){
                                        zombie1.defeatPlant(plant);
                                    }
                                }
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



