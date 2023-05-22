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

    private void initTilesOfHouseOwner() {
        int curX = 150;
        int curY = 100;
        int rowCounter = 0;

        for (int i = 0; i < 5; i++) {
            if (rowCounter >= 1) {
                // Nếu đã đủ số lượng tile trong một hàng, thì di chuyển xuống hàng mới
                curY += hTileOfHouseOwner + 15; // Khoảng cách giữa các hàng
                curX = 150; // Đặt lại tọa độ x ban đầu của tile
                rowCounter = 0; // Đặt lại số lượng tile trong một hàng
            }
            // Tạo mới tile và đặt tọa độ và kích thước cho tile
            tilesOfHouseOwner[i] = new Tile(new Rectangle(curX, curY, wTileOfHouseOwner, hTileOfHouseOwner));

            // Cập nhật tọa độ y cho tile tiếp theo trong cùng một hàng

            rowCounter++; // Tăng số lượng tile trong một hàng
        }
    }


    public void drawTiles(Graphics g, PlantManager plantManager) {
        for (Tile t : tiles) {
            Rectangle r = new Rectangle(t.getCurX(), t.getCurY(), t.getwTile(), t.gethTile());
//            for (int i = 0; i < plantManager.getPlantList().size(); i++) {
//                    if (plantManager.isLocated()) {
//                        g.drawImage(plantManager.getPlantImages(plantManager.getPlantList().get(i).getPlantID()), r.x, r.y, r.width, r.height, null);
//                    }
//                if (plantManager.getPlantList().get(i).getPlantID() == 0) {
//                    g.drawImage(plantManager.getPlantImages(0), );
//                } else if (plantManager.getPlantList().get(i).getPlantID() == 1) {
//                    g.drawImage(plantManager.getPlantImages(1), r.x, r.y, r.width, r.height, null);
//                }
        }
    }

//    public void drawTiles(Graphics g, HouseOwnerManager houseOwnerManager) {
//        int curX = 150; // Tọa độ x ban đầu
//        int curY = 100; // Tọa độ y ban đầu
//        int rowCounter = 0; // Biến đếm số hàng
//
//        for (Tile t : tilesOfHouseOwner) {
//            Rectangle r = new Rectangle(t.getCurX(), t.getCurY(),t.getWTileOfHouseOwner(), t.getHTileOfHouseOwner());
//
//            // Vẽ ô màu cho Tile
//            g.setColor(Color.pink);
//            g.fillRect(r.x, r.y, r.width, r.height);
//
//             //Cập nhật tọa độ x và y cho ô tiếp theo
//            curY += t.getwTile() + 30; // 15 là khoảng cách giữa các hàng
//            rowCounter++;
//
//            // Kiểm tra nếu đã vẽ đủ số hàng, di chuyển sang cột tiếp theo
//            if (rowCounter >= 5) {
//                curY = 100; // Đặt lại tọa độ y
//                curX += t.getwTile() + 8; // 8 là khoảng cách giữa các ô
//                rowCounter = 0; // Đặt lại số hàng
//            }
//        }
//    }
public void drawTiles(Graphics g, HouseOwnerManager houseOwnerManager) {
    int curX = 150; // Tọa độ x ban đầu
    int curY = 100; // Tọa độ y ban đầu

    for (Tile t: tilesOfHouseOwner) {
        Rectangle r = new Rectangle(curX, curY, t.getWTileOfHouseOwner(), t.getHTileOfHouseOwner());

        // Vẽ ô màu cho Tile
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
