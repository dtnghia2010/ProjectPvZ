package Sun;

import java.awt.*;

public class Sun {
    private double x, y;
    private int boundaryDrop;

    public double getX() {
        return x;
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
        if(y<boundaryDrop){
            y++;
            bound.y = (int)y;
        }
    }
}
