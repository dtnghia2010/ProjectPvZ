package manager;

import Audio.Audio;
import Plant.Plant;
import Sun.Sun;
import scenes.Playing;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SunManager {
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Image sunImage = t.getImage(getClass().getResource("/Sun/sun.png"));;
    private List<Sun> listOfSun = new ArrayList<>();
    private Playing playing;
    private int realTimeCounter = 0;
    private int sunHold = 1500;
    private Random random = new Random();
    private int randomTimeSunDrop = 600;
    private int fakeSize = 6000;
    private static SunManager instance;
    private SunManager(Playing playing){
        this.playing = playing;
        sunInit();
    }

    public static SunManager createSunManager(Playing playing) {
        if(instance == null) {
            instance = new SunManager(playing);
        } else {
            System.out.println("Cannot create another TileManager");
        }
        return instance;
    }
    private void sunInit(){
        for(int i = 0;i< 6000;i++){
            int randx = random.nextInt(300)+550;
            listOfSun.add(new Sun(randx,-300,70,70,400));
        }
    }

    public void sunCreation(){
        int randx = random.nextInt(fakeSize);
        listOfSun.get(randx).setY(150);
        listOfSun.get(randx).setBound(new Rectangle((int)listOfSun.get(randx).getX(),150,70,70));
        listOfSun.get(randx).setThere(true);
        fakeSize--;
    }
    public void sunCreatedBySunFlower(Plant plant){
        int randx = random.nextInt(fakeSize);
        listOfSun.get(randx).setX(plant.getX());
        listOfSun.get(randx).setY(plant.getY()-30);
        listOfSun.get(randx).setBound(new Rectangle(plant.getX(),plant.getY()-30,70,70));
        listOfSun.get(randx).setBoundaryDrop(plant.getY()+30);
        listOfSun.get(randx).setThere(true);
        fakeSize--;
    }
    public int getSunHold() {
        return sunHold;
    }
    public void sunConsumed(int sunConsumed){
        sunHold -= sunConsumed;
    }
    public void frameCount(){
        realTimeCounter++;
    }
    private void collectSun(Sun sun){
        sun.setSunCLicked(true);
        sun.setDistanceTOMoveToStorage(sun.calculateDistanceMoveToStorage());
        sunHold += 25;
        sun.setCollected(true);
    }
    public void clickSun(int x, int y){
        Iterator<Sun> iterator = listOfSun.iterator();
        while (iterator.hasNext()){
            Sun sun = iterator.next();
            Rectangle rSun = sun.getBounds();
            if(rSun.contains(x,y) && !sun.isSunCLicked() && sun.isThere()){
                Audio.sunCollected();
                collectSun(sun);
            }
        }
    }

    public void sunCollectedByKeyBoard(){
        if(!playing.getMouseMotionManager().isControlledByMouse()){
            Iterator<Sun> iterator = listOfSun.iterator();
            while (iterator.hasNext()){
                Sun sun = iterator.next();
                Rectangle rSun = new Rectangle((int)sun.getBounds().getX()+15,(int)sun.getBounds().getY()+30,(int)sun.getBounds().getWidth()-30,(int)sun.getBounds().getHeight()-30);
                if(playing.getTileManager().getTiles()[playing.getKeyBoardManager().getTileSelectedByKeyBoard()].getBound().intersects(rSun)){
                    if(!sun.isCollected() && sun.isThere()){
                        Audio.sunCollected();
                        collectSun(sun);
                    }
                }
            }
        }
    }
    public void removeSun(){
        Rectangle holder = new Rectangle(355,-70,90,90);
        Iterator<Sun> iterator = listOfSun.iterator();
        while (iterator.hasNext()){
            Sun sun = iterator.next();
            Rectangle rSun = new Rectangle((int)sun.getX(),(int)sun.getY(),sun.getWidth(),sun.getHeight());
            if(holder.intersects(rSun) && sun.isSunCLicked() && sun.isThere()){
                sun.setThere(false);
            }
        }
    }
    public void drawSunHolder(Graphics g){
//        Rectangle holder = new Rectangle(355,-70,90,90);
//        g.drawRect((int)holder.getX(),(int)holder.getY(),(int)holder.getWidth(),(int)holder.getWidth());
        Graphics2D g2n = (Graphics2D) g;
        g2n.setFont(new Font("Arial",Font.BOLD,16));
        g2n.setColor(Color.BLACK);
        g2n.drawString(String.format("%d",sunHold),getAlignment(),95);
    }
    public int getAlignment(){
        if(sunHold == 0){
            return 405;
        } else if(sunHold < 100){
            return 400;
        } else if(sunHold < 1000){
            return 395;
        } else {
            return 390;
        }
    }
    public void drawSun(Graphics g){
        drawSunHolder(g);
        Graphics2D g2d = (Graphics2D) g;
        Iterator<Sun> iterator = listOfSun.iterator();
        while (iterator.hasNext()){
            Sun sun = iterator.next();
            if(sun.isThere()){
                g2d.drawImage(sunImage,(int)sun.getX(),(int)sun.getY(),sun.getWidth(),sun.getHeight(),null);
//                        g.setColor(Color.RED);
//                        g.drawRect((int)sun.getBounds().getX()+15,(int)sun.getBounds().getY()+30,(int)sun.getBounds().getWidth()-30,(int)sun.getBounds().getHeight()-30);
//                    g.drawRect((int)sun.getX(),(int)sun.getY(),sun.getWidth(),sun.getHeight());
            }
        }
    }

    public void collectAllSun(){
        Iterator<Sun> iterator = listOfSun.iterator();
        while (iterator.hasNext()){
            Sun sun = iterator.next();
            if(!sun.isCollected() && sun.isThere()){
                Audio.sunCollected();
                collectSun(sun);
            }
            sun.moveToStorage();
        }
    }

    public void update(Playing playing){
        if(playing.isStartWaveForCD()){
            frameCount();
            if(realTimeCounter == randomTimeSunDrop){
                sunCreation();
                realTimeCounter = 0;
                randomTimeSunDrop = random.nextInt(300)+900;
            }
            sunCollectedByKeyBoard();
            Iterator<Sun> iterator= listOfSun.iterator();
            while (iterator.hasNext()){
                Sun sun = iterator.next();
                if(sun.isThere()){
                    sun.move();
                    sun.moveToStorage();
                }
            }
        } else {
            collectAllSun();
        }
        removeSun();
    }
}
