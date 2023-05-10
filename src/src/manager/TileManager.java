package manager;

import component.Tile;
import scenes.Playing;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TileManager {
    private Tile[] tiles = new Tile[45];
    private int wTile = 70, hTile = 80;
    private int tileSelectedByMouse;
    private int tileSelectedByKeyBoard = 0;

    public boolean isInTile() {
        return isInTile;
    }

    private Playing playing;
    private boolean isInTile = false;

    public TileManager(Playing playing) {
        initTiles();
        this.playing = playing;
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

    public Tile[] getTiles() {
        return tiles;
    }

    public int getTileSelectedByKeyBoard() {
        return tileSelectedByKeyBoard;
    }

    public void tileSelectedByKeyBoard(KeyEvent e){
        playing.getMouseMotionManager().setControlledByMouse(false);
        if(e.getKeyCode() == KeyEvent.VK_A){
            tileSelectedByKeyBoard--;
            tileSelectedByMouse = tileSelectedByKeyBoard;
            isInTile = true;
        } else if(e.getKeyCode() == KeyEvent.VK_D){
            tileSelectedByKeyBoard++;
            tileSelectedByMouse = tileSelectedByKeyBoard;
            isInTile = true;
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            tileSelectedByKeyBoard = tileSelectedByKeyBoard -9;
            tileSelectedByMouse = tileSelectedByKeyBoard;
            isInTile = true;
        } else if(e.getKeyCode() == KeyEvent.VK_S){
            tileSelectedByKeyBoard = tileSelectedByKeyBoard +9;
            tileSelectedByMouse = tileSelectedByKeyBoard;
            isInTile = true;
        }
        if(tileSelectedByKeyBoard <0){
            tileSelectedByKeyBoard = tileSelectedByKeyBoard +9;
            tileSelectedByMouse = tileSelectedByKeyBoard;
        } else if(tileSelectedByKeyBoard > 44){
            tileSelectedByKeyBoard = tileSelectedByKeyBoard -9;
            tileSelectedByMouse = tileSelectedByKeyBoard;
        }
        if(playing.getPlantManager().isSelected()){
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                if(playing.getPlantManager().isTimeToPlant()){
                    playing.getPlantManager().setTimeToPlant(false);
                } else {
                    playing.getPlantManager().plantCreateByKeyBoard(tileSelectedByKeyBoard);
                }
            }
        }
    }
    public void drawTileSelectedByKeyBoard(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0;i<playing.getTileManager().getTiles().length;i++){
            if(i == tileSelectedByKeyBoard){
                g2d.drawImage(playing.getBarManager().getPickedPlant(), (int)playing.getTileManager().getTiles()[i].getBound().getX(),(int)playing.getTileManager().getTiles()[i].getBound().getY(),playing.getTileManager().getTiles()[i].getwTile(),playing.getTileManager().getTiles()[i].gethTile(),null);
            }
        }
    }

    public void setInTile(boolean inTile) {
        isInTile = inTile;
    }

    public void tileTrack(int x, int y){
        playing.getMouseMotionManager().setControlledByMouse(true);
        if(playing.getPlantManager().isSelected()){
            for(int i = 0;i<tiles.length;i++){
                Rectangle r = new Rectangle((int)tiles[i].getBound().getX(),(int)tiles[i].getBound().getY(),tiles[i].getwTile(),tiles[i].gethTile());
                if(r.contains(x,y)){
                    isInTile = true;
                    tileSelectedByMouse = i;
                    tileSelectedByKeyBoard = tileSelectedByMouse;
                }
            }
        }
    }
    public void drawTileSelectedByMouse(Graphics g) {
        if(playing.getPlantManager().isSelected()){
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(playing.getBarManager().getPickedPlant(),(int)tiles[tileSelectedByMouse].getBound().getX(),(int)tiles[tileSelectedByMouse].getBound().getY(),tiles[tileSelectedByMouse].getwTile(),tiles[tileSelectedByMouse].gethTile(),null);
        }
    }
}
