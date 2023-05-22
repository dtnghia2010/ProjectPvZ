package manager;

import component.Plant;
import component.Tile;
import scenes.Playing;

import java.awt.*;

public class TileManager {
    private Tile[] tiles = new Tile[45];
    private int wTile = 70, hTile = 80;

    private Tile[] tilesOfHouseOwner = new Tile[5];
    public int wTileOfHouseOwner = 160;
    public int hTileOfHouseOwner = 80;

    public TileManager() {
        initTiles();
        initTilesOfHouseOwner();
    }

    private void initTiles() {
        int curX = 250, curY = 120, rowCounter = 0;
        for (int i = 0; i < 45; i++) {
            if (rowCounter >= 9) {
                curY += hTile + 15;
                curX = 250;
                rowCounter = 0;
            }
            curX += (wTile + 8);
            tiles[i] = new Tile(new Rectangle(curX, curY, wTile, hTile));
            rowCounter++;
        }
    }

    public void drawTiles(Graphics g, PlantManager plantManager) {
        for (Tile t : tiles) {
            Rectangle r = new Rectangle(t.getCurX(), t.getCurY(), t.getwTile(), t.gethTile());
        }
    }

    private void initTilesOfHouseOwner() {
        int curX = 150;
        int curY = 100;
        int rowCounter = 0;

        for (int i = 0; i < 5; i++) {
            if (rowCounter >= 1) {
                // Nếu đã đủ số lượng tile trong một hàng, thì di chuyển xuống hàng mới
                curX = 150;
                curY += hTileOfHouseOwner + 15; // Khoảng cách giữa các hàng
                rowCounter = 0; // Đặt lại số lượng tile trong một hàng
            }
            // Tạo mới tile và đặt tọa độ và kích thước cho tile
            tilesOfHouseOwner[i] = new Tile(new Rectangle(curX, curY, wTileOfHouseOwner, hTileOfHouseOwner));

            // Cập nhật tọa độ y cho tile tiếp theo trong cùng một hàng

            rowCounter++; // Tăng số lượng tile trong một hàng
        }
    }

    public void drawTiles(Graphics g, HouseOwnerManager houseOwnerManager) {
        int curX = 150; // Tọa độ x ban đầu
        int curY = 100; // Tọa độ y ban đầu

        for (Tile t: tilesOfHouseOwner) {
             Rectangle r = new Rectangle((int)t.getBound().getX(),(int)t.getBound().getY(), t.getWTileOfHouseOwner(), t.getHTileOfHouseOwner());

             g.setColor(Color.pink);
             g.fillRect(r.x, r.y, r.width, r.height);

             curY += t.getHTileOfHouseOwner() + 15; // Khoảng cách giữa các hàng
    }
}

    public Tile[] getTiles() {
        return tiles;
    }

    public Tile[] getTilesOfHouseOwner() {
        return tilesOfHouseOwner;
    }
    public int getWTileOfHouseOwner() {
        return wTileOfHouseOwner;
    }

    public int getHTileOfHouseOwner() {
        return hTileOfHouseOwner;
    }
}
