package manager;

import component.Tile;
import scenes.Playing;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TileManager {
    private Tile[] tiles = new Tile[45];
    private int wTile = 70, hTile = 80;
    private Playing playing;
    private Image[] plantLightBlur = new Image[5];
    private Image[] plantHardBlur = new Image[5];
    private Toolkit t = Toolkit.getDefaultToolkit();
    private int tileSelectedByMouse;
    private int tileSelectedByKeyBoard = 0;

    public boolean isInTile() {
        return isInTile;
    }
    public TileManager(Playing playing) {
        initTiles();
        importHardBlurPlant();
        importLightBlurPlant();
        this.playing = playing;
    }
    private boolean isInTile = false;


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
    public void importLightBlurPlant(){
        plantLightBlur[0] = t.getImage(getClass().getResource("/Blur_Plants/Sunflower/sunflower (light - blur).png"));
        plantLightBlur[1] = t.getImage(getClass().getResource("/Blur_Plants/Peashooter/peashooter (light - blur).png"));
        plantLightBlur[2] = t.getImage(getClass().getResource("/Blur_Plants/Wall-nut/wall-nut (light - blur).png"));
        plantLightBlur[3] = t.getImage(getClass().getResource("/Blur_Plants/ShadowPea/ShadowPea (light - blur).png"));
        plantLightBlur[4] = t.getImage(getClass().getResource("/Blur_Plants/CherryBomb/cherrybomb (light - blur).png"));
    }
    public void importHardBlurPlant(){
        plantHardBlur[0] = t.getImage(getClass().getResource("/Blur_Plants/Sunflower/sunflower (hard - blur).png"));
        plantHardBlur[1] = t.getImage(getClass().getResource("/Blur_Plants/Peashooter/peashooter (hard - blur).png"));
        plantHardBlur[2] = t.getImage(getClass().getResource("/Blur_Plants/Wall-nut/wall-nut (hard - blur).png"));
        plantHardBlur[3] = t.getImage(getClass().getResource("/Blur_Plants/ShadowPea/ShadowPea (hard - blur).png"));
        plantHardBlur[4] = t.getImage(getClass().getResource("/Blur_Plants/CherryBomb/cherrybomb (hard - blur).png"));
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
            plant(e);
        }
    }
    public void plant(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(playing.getPlantManager().isTimeToPlant()){
                playing.getPlantManager().setTimeToPlant(false);
            } else {
                playing.getPlantManager().plantCreateByKeyBoard(tileSelectedByKeyBoard);
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
    public void drawPlantPreparedToPlanted(Graphics g){
        if(playing.getPlantManager().isSelected()){
            Rectangle r = new Rectangle((int)tiles[tileSelectedByKeyBoard].getBound().getX(),(int)tiles[tileSelectedByKeyBoard].getBound().getY(),tiles[tileSelectedByKeyBoard].getwTile(),tiles[tileSelectedByKeyBoard].gethTile());
            if(playing.getPlantManager().getIDhold() >= 0){
                if(!playing.getBarManager().getIsPlantInCD()[playing.getPlantManager().getIDhold()] && playing.getBarManager().getIsPlantEnoughSun()[playing.getPlantManager().getIDhold()]){
                    Graphics2D g2d = (Graphics2D) g;
                    if(playing.getPlantManager().getIDhold() != 3){
                        g2d.drawImage(plantLightBlur[playing.getPlantManager().getIDhold()],(int)r.getX(),(int)r.getY(),(int)r.getWidth(),(int)r.getHeight(),null);
                    } else {
                        g2d.drawImage(plantLightBlur[playing.getPlantManager().getIDhold()],(int)r.getX(),(int)r.getY()-10,(int)r.getWidth(),(int)r.getHeight()+10,null);
                    }
                } else {
                    Graphics2D g2n = (Graphics2D) g;
                    if(playing.getPlantManager().getIDhold() != 3){
                        g2n.drawImage(plantHardBlur[playing.getPlantManager().getIDhold()],(int)r.getX(),(int)r.getY(),(int)r.getWidth(),(int)r.getHeight(),null);
                    } else {
                        g2n.drawImage(plantHardBlur[playing.getPlantManager().getIDhold()],(int)r.getX(),(int)r.getY()-10,(int)r.getWidth(),(int)r.getHeight()+10,null);
                    }
                }
            }
            if(playing.getPlantManager().isForbidden()){
                Graphics2D g2f = (Graphics2D) g;
                g2f.drawImage(plantHardBlur[0],(int)r.getX(),(int)r.getY(),(int)r.getWidth(),(int)r.getHeight(),null);
            }
        }
    }

    public void setInTile(boolean inTile) {
        isInTile = inTile;
    }

    public void tileTrack(int x, int y){
        for(int i = 0;i<tiles.length;i++){
            Rectangle r = new Rectangle((int)tiles[i].getBound().getX(),(int)tiles[i].getBound().getY(),tiles[i].getwTile(),tiles[i].gethTile());
            if(r.contains(x,y)){
                isInTile = true;
                tileSelectedByMouse = i;
                tileSelectedByKeyBoard = tileSelectedByMouse;
            }
        }
    }
    public void drawTileSelectedByMouse(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(playing.getBarManager().getPickedPlant(),(int)tiles[tileSelectedByMouse].getBound().getX(),(int)tiles[tileSelectedByMouse].getBound().getY(),tiles[tileSelectedByMouse].getwTile(),tiles[tileSelectedByMouse].gethTile(),null);
    }
}
