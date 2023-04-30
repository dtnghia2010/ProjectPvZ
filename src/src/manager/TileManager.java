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
            tiles[i] = new Tile(new Rectangle(0, 0, 70, 80));
            if (rowCounter >= 9) {
                curY += tiles[i].gethTile() + 15;
                curX = 250;
                rowCounter = 0;
            }
            curX += tiles[i].getwTile() + 8;
            tiles[i].setCurX(curX);
            tiles[i].setCurY(curY);
            rowCounter++;
        }
    }

    public void drawTiles(Graphics g, PlantManager plantManager) {
        for (Tile t : tiles) {
            Rectangle r = new Rectangle(t.getCurX(),t.getCurY(),t.getwTile(),t.gethTile());
//            for (int i = 0; i < plantManager.getPlantList().size(); i++) {
//                    if (plantManager.isLocated()) {
//                        g.drawImage(plantManager.getPlantImages(plantManager.getPlantList().get(i).getPlantID()), r.x, r.y, r.width, r.height, null);
//                    }
//                if (plantManager.getPlantList().get(i).getPlantID() == 0) {
//                    g.drawImage(plantManager.getPlantImages(0), );
//                } else if (plantManager.getPlantList().get(i).getPlantID() == 1) {
//                    g.drawImage(plantManager.getPlantImages(1), r.x, r.y, r.width, r.height, null);
//                }
//            }
        }
    }
//            g.setColor(Color.blue);
//            g.fillRect(r.x, r.y, r.width, r.height);
//            }




    public Tile[] getTiles() {
        return tiles;
    }
}
