package component;

import java.awt.*;

public class Tile {
    private final int ROWS = 5, COLS = 9;
    private int curX, curY;
    private int wTile = 70, hTile = 80;

    public void setwTile(int wTile) {
        this.wTile = wTile;
    }

    public void sethTile(int hTile) {
        this.hTile = hTile;
    }

    public int getwTile() {
        return wTile;
    }

    public int gethTile() {
        return hTile;
    }

    public int getCurX() {
        return curX;
    }

    public void setCurX(int curX) {
        this.curX = curX;
    }

    public int getCurY() {
        return curY;
    }

    public void setCurY(int curY) {
        this.curY = curY;
    }

    private Rectangle bound;
    private Boolean occupied = false;
    public Tile(Rectangle bound) {
        this.bound = bound;
    }
    public Rectangle getBound() {
        return bound;
    }
    public void setOccupied(Boolean b) {
        occupied = b;
    }
    public boolean isOccupied() {
        return occupied;
    }
}

