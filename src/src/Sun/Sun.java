package Sun;

import java.awt.*;

public class Sun {
    private double x, y;
    private int boundaryDrop;
    private boolean isSunCLicked = false;

    public double getX() {
        return x;
    }

    public void setSunCLicked(boolean sunCLicked) {
        isSunCLicked = sunCLicked;
    }

    public double getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private int width, height;
    private Rectangle bound;
    private boolean isThere = true;

    public boolean isThere() {
        return isThere;
    }

    public Rectangle getBounds() {
        return bound;
    }

    public void setThere(boolean there) {
        isThere = there;
    }

    public Sun(double x, double y, int width, int height, int boundaryDrop){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.boundaryDrop = boundaryDrop;
        this.bound = new Rectangle((int)x,(int)y,width,height);
    }
    public void move(){
        if(y<boundaryDrop && !isSunCLicked){
            y++;
            bound.y = (int)y;
        }
    }
    public double calculateDistanceMoveToStorage(){
        double width = x - 250;
        double height = y;
        double ratio = width/height;
        return ratio;
    }

    public double getDistanceTOMoveToStorage() {
        return distanceTOMoveToStorage;
    }

    public void setDistanceTOMoveToStorage(double distanceTOMoveToStorage) {
        this.distanceTOMoveToStorage = distanceTOMoveToStorage;
    }

    public boolean isSunCLicked() {
        return isSunCLicked;
    }
    private double distanceTOMoveToStorage;
    public void moveToStorage(){
        if(isSunCLicked){
            if(y>0){
                y--;
                x = x-distanceTOMoveToStorage;
            }
        }
    }
}
