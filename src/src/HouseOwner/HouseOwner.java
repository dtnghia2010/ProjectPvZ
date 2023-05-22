package HouseOwner;
import java.awt.*;
import java.util.Iterator;
public class HouseOwner {
    private int hp, dmg;
    private final int atk =100;
    private static final int width = 40, height = 60;
    private float spd = 0.8f;
    private boolean Collided = false;
    private double x;
    private double y;
    private boolean isAlived = true;
    private boolean isDead = false;

    private Rectangle bound;
    private boolean isDangered = false;

    public boolean isDangered() {
        return isDangered;
    }

    public void setDangered(boolean dangered) {
        isDangered = dangered;
    }

    public HouseOwner(double x, double y, int hp) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.bound = new Rectangle(new Dimension(width, height));
    }
    public void decreaseHealth(int damage) {
        hp -= damage;
    }

    public void move() {
        if(!Collided){
            y -= spd;
        }
    }

    public int getHealth() {
        return hp;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public float getSpd() {
        return spd;
    }

    public void setSpd(float spd) {
        this.spd = spd;
    }

    public boolean isCollided() {
        return Collided;
    }

    public void setCollided(boolean collided) {
        Collided = collided;
    }

    public boolean isAlived() {
        return isAlived;
    }

    public boolean isDead() {
        return isDead;
    }

    public Rectangle getBound() {
        return bound;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getATK() {
        return atk;
    }

}

