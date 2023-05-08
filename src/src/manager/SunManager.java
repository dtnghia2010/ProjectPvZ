package manager;

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
    public List<Sun> listOfSun = new ArrayList<>();
    private int realTimeCounter = 0;
    private int sunHold = 1000;
    private Random random = new Random();
    private void sunCreation(){
        int randx = random.nextInt(1000)+200;
        listOfSun.add(new Sun(randx,0,90,90));
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
                if(rSun.contains(x,y) && sun.isThere()){
                    sunHold += 50;
                    sun.setThere(false);
                    System.out.println("Current sun: "+sunHold);
                }
            }
        }
    }
    public void drawSun(Graphics g){
        Rectangle holder = new Rectangle(250,0,100,100);
        Graphics2D g2r = (Graphics2D) g;
        g2r.setColor(Color.PINK);
        g2r.fill(holder);
        Graphics2D g2s = (Graphics2D) g;
        g2s.drawImage(sunImage,255,-10,90,90,null);
        Graphics2D g2d = (Graphics2D) g;
        synchronized (listOfSun){
            Iterator<Sun> iterator = listOfSun.iterator();
            while (iterator.hasNext()){
                Sun sun = iterator.next();
                if(sun.isThere()){
                    g2d.drawImage(sunImage,(int)sun.getX(),(int)sun.getY(),sun.getWidth(),sun.getHeight(),null);
                    g.setColor(Color.RED);
                    g.drawRect((int)sun.getBounds().getX(),(int)sun.getBounds().getY(),(int)sun.getBounds().getWidth(),(int)sun.getBounds().getHeight());
                }
            }
        }
    }
    public void update(){
        frameCount();
        if(realTimeCounter == 450){
            sunCreation();
            realTimeCounter = 0;
        }
        synchronized (listOfSun){
            Iterator<Sun> iterator= listOfSun.iterator();
            while (iterator.hasNext()){
                Sun sun = iterator.next();
                sun.move();
            }
        }
    }
}
