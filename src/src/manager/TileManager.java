package manager;

import component.Tile;

import java.awt.*;

public class TileManager {
    private Tile[] tiles = new Tile[45];
    private int wTile = 70, hTile = 80;

    public TileManager() {
        initTiles();
    }
    private void initTiles() {
        int curX = 250, curY = 120, rowCounter = 0;
        for(int i = 0; i < 45; i++) {
            if(rowCounter >= 9) {
                curY += hTile+15;
                curX = 250;
                rowCounter = 0;
            }
            curX += (wTile+8);
            tiles[i] = new Tile(new Rectangle(curX, curY, wTile, hTile));
            rowCounter++;
        }
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public void drawTiles(Graphics g) {
        for (Tile tl : tiles) {
            Rectangle r = tl.getBound();
//            if (tl.isOccupied() == true) {
//                for(Plant pl: plants) {
//                    if(pl.isPlaced() == true) {
//                        g.drawImage(tl.getPlant().getPlantImg(), r.x, r.y, r.width, r.height, null);
//                    }
//                }
//            } else {
/*            g.setColor(Color.blue);
            g.fillRect(r.x, r.y, r.width, r.height);*/
//            }
        }
    }
}