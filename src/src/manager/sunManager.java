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

public class sunManager {
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Image sunImage = t.getImage(getClass().getResource("/Sun/sun.png"));;
    private List<Sun> listOfSun = new ArrayList<>();
    private Playing playing;
    private int realTimeCounter = 0;
    private int sunHold = 1500;
    private Random random = new Random();
    private int randomTimeSunDrop = 600;
    private boolean isSunCreated = false;
    private boolean isSunRemoved = false;
    public sunManager(Playing playing){
        this.playing = playing;
    }
    public void sunCreation(){
        synchronized (listOfSun){
            int randx = random.nextInt(300)+400;
            listOfSun.add(new Sun(randx,100,70,70,400));
        }
    }
    public void sunCreatedBySunFlower(Plant plant){
        synchronized (listOfSun){
            listOfSun.add(new Sun(plant.getX(),plant.getY()-30,70,70,plant.getY()+30));
        }
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
    public void clickSun(int x, int y){
        Iterator<Sun> iterator = listOfSun.iterator();
        while (iterator.hasNext()){
            Sun sun = iterator.next();
            Rectangle rSun = sun.getBounds();
            if(rSun.contains(x,y) && !sun.isSunCLicked()){
                Audio.sunCollected();
                sun.setSunCLicked(true);
                sun.setDistanceTOMoveToStorage(sun.calculateDistanceMoveToStorage());
                sunHold += 50;
            }
        }
    }
    public void sunCollectedByKeyBoard(){
        if(!playing.getMouseMotionManager().isControlledByMouse()){
            Iterator<Sun> iterator = listOfSun.iterator();
            while (iterator.hasNext()){
                Sun sun = iterator.next();
                Rectangle rSun = new Rectangle((int)sun.getBounds().getX()+15,(int)sun.getBounds().getY()-30,(int)sun.getBounds().getWidth()-30,(int)sun.getBounds().getHeight()-30);
                if(playing.getTileManager().getTiles()[playing.getKeyBoardManager().getTileSelectedByKeyBoard()].getBound().intersects(rSun)){
                    if(!sun.isCollected()){
                        Audio.sunCollected();
                        sun.setSunCLicked(true);
                        sun.setDistanceTOMoveToStorage(sun.calculateDistanceMoveToStorage());
                        sunHold += 50;
                        sun.setCollected(true);
                    }
                }
            }
        }
    }
    public void removeSun(){
        Rectangle holder = new Rectangle(355,-70,90,90);
        synchronized (listOfSun){
            Iterator<Sun> iterator = listOfSun.iterator();
            while (iterator.hasNext()){
                Sun sun = iterator.next();
                Rectangle rSun = new Rectangle((int)sun.getX(),(int)sun.getY(),sun.getWidth(),sun.getHeight());
                if(holder.intersects(rSun) && sun.isSunCLicked()){
                    isSunRemoved = true;
                    iterator.remove();
                } else {
                    isSunRemoved = false;
                }
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
        synchronized (listOfSun){
            if(!isSunRemoved && !isSunCreated){
                Graphics2D g2d = (Graphics2D) g;
                Iterator<Sun> iterator = listOfSun.iterator();
                while (iterator.hasNext()){
                    Sun sun = iterator.next();
                    if(sun.isThere()){
                        g2d.drawImage(sunImage,(int)sun.getX(),(int)sun.getY(),sun.getWidth(),sun.getHeight(),null);
//                    g.setColor(Color.RED);
//                    g.drawRect((int)sun.getBounds().getX()+15,(int)sun.getBounds().getY()-30,(int)sun.getBounds().getWidth()-30,(int)sun.getBounds().getHeight()-30);
//                    g.drawRect((int)sun.getX(),(int)sun.getY(),sun.getWidth(),sun.getHeight());
                    }
                }
            }
        }
    }

    public void setSunCreated(boolean sunCreated) {
        isSunCreated = sunCreated;
    }
    public void collectAllSun(){
        synchronized (listOfSun){
            Iterator<Sun> iterator = listOfSun.iterator();
            while (iterator.hasNext()){
                Sun sun = iterator.next();
                if(!sun.isCollected()){
                    sun.setDistanceTOMoveToStorage(sun.calculateDistanceMoveToStorage());
                    sunHold += 50;
                    Audio.sunCollected();
                    sun.setCollected(true);
                    sun.setSunCLicked(true);
                }
                sun.moveToStorage();
            }
        }
    }

    public void update(Playing playing){
        if(playing.isStartWaveForCD()){
            frameCount();
            if(realTimeCounter == randomTimeSunDrop){
                isSunCreated = true;
                sunCreation();
                realTimeCounter = 0;
                randomTimeSunDrop = random.nextInt(300)+600;
            } else {
                isSunCreated = false;
            }
            sunCollectedByKeyBoard();
            Iterator<Sun> iterator= listOfSun.iterator();
            while (iterator.hasNext()){
                Sun sun = iterator.next();
                sun.move();
                sun.moveToStorage();
            }
        } else {
            collectAllSun();
        }
        removeSun();
    }
}
