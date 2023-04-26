package manager;

import component.Tile;

import java.awt.*;

public class TileManager {
    private Tile[] tiles = new Tile[45];
    public TileManager() {
        initTiles();
    }

    public Tile[] getTiles() {
        return tiles;
    }

    private void initTiles() {
        int curX = 250, curY = 120, rowCounter = 0;
        for(int i = 0; i < 45; i++) {
            tiles[i] = new Tile(new Rectangle(0, 0, 70, 80));
            if(rowCounter >= 9) {
                curY += tiles[i].gethTile()+15;
                curX = 250;
                rowCounter = 0;
            }
            curX += tiles[i].getwTile()+8;
            tiles[i].setCurX(curX);
            tiles[i].setCurY(curY);
            rowCounter++;
        }
    }
    public void drawTiles(Graphics g) {
        for (Tile tl : tiles) {
            Rectangle r = new Rectangle(tl.getCurX(),tl.getCurY(),tl.getwTile(),tl.gethTile());
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
}
