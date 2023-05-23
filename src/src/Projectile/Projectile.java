package Projectile;

public class Projectile {
    private int x, y;
    private int ATK;
    private int ID;

    public int getID() {
        return ID;
    }

    public int getATK() {
        return ATK;
    }

    public Projectile(int x, int y, int ATK, int ID){
        this.x = x;
        this.y = y;
        this.ATK = ATK;
        this.ID = ID;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(){
        if(this.x<1024){
            this.x += 8;
        }
    }
}
