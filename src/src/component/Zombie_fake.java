package component;

public class Zombie_fake {
    private int hp;
    private int x,y;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
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

    public Zombie_fake(int hp, int x, int y){
        this.hp = hp;
        this.x = x;
        this.y = y;
    }
}
