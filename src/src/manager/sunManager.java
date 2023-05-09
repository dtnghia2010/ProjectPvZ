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
    public List<Sun> listOfSun = new ArrayList<>();
    private int realTimeCounter = 0;
    private int sunHold = 2000;
    private Random random = new Random();
    public void sunCreation(){
        int randx = random.nextInt(900)+400;
        listOfSun.add(new Sun(randx,0,90,90,400));
    }
    public void sunCreatedBySunFlower(Plant plant){
        listOfSun.add(new Sun(plant.getX(),plant.getY()-30,90,90,plant.getY()+30));
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
        synchronized (listOfSun){
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
    }
    public void removeSun(){
        synchronized (listOfSun){
            Iterator<Sun> iterator = listOfSun.iterator();
            while (iterator.hasNext()){
                Sun sun = iterator.next();
                if(sun.getY() == 0 && sun.isSunCLicked()){
                    iterator.remove();
                }
            }
        }
    }
    public void drawSunHolder(Graphics g){
        Rectangle holder = new Rectangle(250,0,100,100);
        Graphics2D g2r = (Graphics2D) g;
        g2r.setColor(Color.PINK);
        g2r.fill(holder);
        Graphics2D g2s = (Graphics2D) g;
        Graphics2D g2n = (Graphics2D) g;
        g2s.drawImage(sunImage,255,-10,90,90,null);
        g2n.setFont(new Font("Arial",Font.BOLD,24));
        g2n.setColor(Color.BLACK);
        g2n.drawString(String.format("%d",sunHold),getAlignment(),95);
    }
    public int getAlignment(){
        if(sunHold == 0){
            return 295;
        } else if(sunHold < 100){
            return 285;
        } else if(sunHold < 1000){
            return 280;
        } else {
            return 270;
        }
    }
    public void drawSun(Graphics g){
        drawSunHolder(g);
        Graphics2D g2d = (Graphics2D) g;
        synchronized (listOfSun){
            Iterator<Sun> iterator = listOfSun.iterator();
            while (iterator.hasNext()){
                Sun sun = iterator.next();
                if(sun.isThere()){
                    g2d.drawImage(sunImage,(int)sun.getX(),(int)sun.getY(),sun.getWidth(),sun.getHeight(),null);
//                    g.setColor(Color.RED);
//                    g.drawRect((int)sun.getBounds().getX(),(int)sun.getBounds().getY(),(int)sun.getBounds().getWidth(),(int)sun.getBounds().getHeight());
                }
            }
        }
    }
    public void update(Playing playing){
        if(playing.isStartWaveForCD()){
            frameCount();
            if(realTimeCounter == 600){
                sunCreation();
                realTimeCounter = 0;
            }
            synchronized (listOfSun){
                Iterator<Sun> iterator= listOfSun.iterator();
                while (iterator.hasNext()){
                    Sun sun = iterator.next();
                    sun.move();
                    sun.moveToStorage();
                }
            }
            removeSun();
        }
    }
}
