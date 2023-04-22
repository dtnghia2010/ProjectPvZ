package manager;

import component.Plant;
import component.Tile;
import scenes.Playing;

import java.awt.*;

public class TileManager {
    private Tile[] tiles = new Tile[45];
    public TileManager() {
        initTiles();
    }

    private void initTiles() {
        int curX = 250, curY = 120, rowCounter = 0;
        for (int i = 0; i < 45; i++) {
            if (rowCounter >= 9) {
                curY += 80 + 15;
                curX = 250;
                rowCounter = 0;
            }
            curX += (70 + 8);
            tiles[i] = new Tile(new Rectangle(curX, curY, 70, 80));
            rowCounter++;
        }
    }

    public void drawTiles(Graphics g, PlantManager plantManager, Playing playing) {
        for (Tile t : tiles) {
            Rectangle r = t.getBound();
            if (t.isOccupied() == true) {
                for (int i = 0; i < plantManager.getPlantList().size(); i++) {
                    if (plantManager.setLocated(true)) {
                        g.drawImage(t.getPlantManager(playing).get(i).getPlantImages(), r.x, r.y, r.width, r.height, null);
                    }
                }
            }
//            if (tl.isOccupied() == true) {
//                for(Plant pl: PlantInTiles) {
//                    if(pl.isPlaced() == true) {
//                        g.drawImage(tl.getPlant().getPlantImg(), r.x, r.y, r.width, r.height, null);
//                    }
//                }
//            } else {
//            g.setColor(Color.blue);
//            g.fillRect(r.x, r.y, r.width, r.height);
//            }
        }
    }

    public Tile[] getTiles() {
        return tiles;
    }
}
