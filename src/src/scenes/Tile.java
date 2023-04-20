package scenes;

import java.awt.*;
import javax.swing.*;

public class Tile {
    private final int ROWS = 5, COLS = 9;
    private int wTile = 70, hTile = 80;
    private Tile[] tiles = new Tile[45];
    private Rectangle bound;
    private Boolean occupied = false;
//    private Plant plant;

    public Tile(Rectangle bound) {
        this.bound = bound;
    }

    /*      public void render(Graphics g) {
            Graphics2D G2D = (Graphics2D) g ;
            G2D.setColor(Color.pink);
            int upX=0;
            int upY=0;
            for (int i=1; i<=ROWS; i++){
                for (int j=1; j<=COLS; j++) {
                    Rectangle RTG = new Rectangle(500 + upX, 110+upY, 90, 110);
                    upX += 110;
                    G2D.fill(RTG);
                }
                upY += 130;
                upX =0;
            }
          }*/
    private void drawTiles(Graphics g) {
        for (Tile tl : tiles) {
            Rectangle r = tl.getBound();
//            if (tl.isOccupied() == true) {
//                for(Plant pl: plants) {
//                    if(pl.isPlaced() == true) {
//                        g.drawImage(tl.getPlant().getPlantImg(), r.x, r.y, r.width, r.height, null);
//                    }
//                }
//            } else {
            g.setColor(Color.blue);
            g.fillRect(r.x, r.y, r.width, r.height);
//            }
        }
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

