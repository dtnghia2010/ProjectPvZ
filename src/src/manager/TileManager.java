package manager;

import component.Plant;
import component.Tile;
import scenes.Playing;

import java.awt.*;

public class TileManager {
    private Tile[] tiles = new Tile[45];
    private int wTile = 60, hTile = 70;

    public TileManager() {
        initTiles();
    }

    private void initTiles() {
        int curX = 300, curY = 171, rowCounter = 0;
        for (int i = 0; i < 45; i++) {
            if (rowCounter >= 9) {
                curY += hTile + 10;
                curX = 300;
                rowCounter = 0;
            }
            curX += (wTile + 10);
            tiles[i] = new Tile(new Rectangle(curX, curY, wTile, hTile));
            rowCounter++;
        }
    }

    public void drawTiles(Graphics g, PlantManager plantManager) {
        for (Tile t : tiles) {
            Rectangle r = t.getBound();
//            for (int i = 0; i < plantManager.getPlantList().size(); i++) {
//                    if (plantManager.isLocated()) {
//                        g.drawImage(plantManager.getPlantImages(plantManager.getPlantList().get(i).getPlantID()), r.x, r.y, r.width, r.height, null);
//                    }
//                if (plantManager.getPlantList().get(i).getPlantID() == 0) {
//                    g.drawImage(plantManager.getPlantImages(0), );
//                } else if (plantManager.getPlantList().get(i).getPlantID() == 1) {
//                    g.drawImage(plantManager.getPlantImages(1), r.x, r.y, r.width, r.height, null);
//                }
//            g.setColor(Color.white);
//            g.fillRect(r.x, r.y, r.width, r.height);
        }
    }

    public Tile[] getTiles() {
        return tiles;
    }
}
