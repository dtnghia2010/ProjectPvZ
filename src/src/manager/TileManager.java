package manager;

import component.Tile;
import scenes.Playing;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TileManager {
    private Tile[] tiles = new Tile[45];
    private int wTile = 60, hTile = 70;
    private Playing playing;
    private Image[] plantLightBlur = new Image[5];
    private Image[] plantHardBlur = new Image[5];
    private Toolkit t = Toolkit.getDefaultToolkit();

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


    public void drawTileSelectedByKeyBoard(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0;i<playing.getTileManager().getTiles().length;i++){
            if(i == playing.getKeyBoardManager().getTileSelectedByKeyBoard()){
                g2d.drawImage(playing.getBarManager().getPickedPlant(), (int)playing.getTileManager().getTiles()[i].getBound().getX(),(int)playing.getTileManager().getTiles()[i].getBound().getY(),playing.getTileManager().getTiles()[i].getwTile(),playing.getTileManager().getTiles()[i].gethTile(),null);
            }
        }
    }
    public void drawPlantPreparedToPlanted(Graphics g){
        if((playing.getPlantManager().isSelected() && playing.getMouseMotionManager().isControlledByMouse()) || !playing.getMouseMotionManager().isControlledByMouse()){
            Rectangle r = new Rectangle((int)tiles[playing.getKeyBoardManager().getTileSelectedByKeyBoard()].getBound().getX(),(int)tiles[playing.getKeyBoardManager().getTileSelectedByKeyBoard()].getBound().getY(),tiles[playing.getKeyBoardManager().getTileSelectedByKeyBoard()].getwTile(),tiles[playing.getKeyBoardManager().getTileSelectedByKeyBoard()].gethTile());
            if(playing.getPlantManager().getIDhold() >= 0){
                if(!playing.getBarManager().getIsPlantInCD()[playing.getPlantManager().getIDhold()] && playing.getBarManager().getIsPlantEnoughSun()[playing.getPlantManager().getIDhold()] && !playing.getPlantManager().isForbidden()){
                    Graphics2D g2d = (Graphics2D) g;
                    if(playing.getPlantManager().getIDhold() != 3){
                        g2d.drawImage(plantLightBlur[playing.getPlantManager().getIDhold()],(int)r.getX(),(int)r.getY()+5,(int)r.getWidth()-15,(int)r.getHeight()-15,null);
                    } else {
                        g2d.drawImage(plantLightBlur[playing.getPlantManager().getIDhold()],(int)r.getX(),(int)r.getY()-25,(int)r.getWidth(),(int)r.getHeight()+15,null);
                    }
                } else {
                    Graphics2D g2n = (Graphics2D) g;
                    if(playing.getPlantManager().getIDhold() != 3){
                        g2n.drawImage(plantHardBlur[playing.getPlantManager().getIDhold()],(int)r.getX(),(int)r.getY()+5,(int)r.getWidth()-15,(int)r.getHeight()-15,null);
                    } else {
                        g2n.drawImage(plantHardBlur[playing.getPlantManager().getIDhold()],(int)r.getX(),(int)r.getY()-25,(int)r.getWidth(),(int)r.getHeight()+15,null);
                    }
                }
            }
        }
    }

    public void setInTile(boolean inTile) {
        isInTile = inTile;
    }


    public void drawTileSelectedByMouse(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(playing.getBarManager().getPickedPlant(),(int)tiles[playing.getMouseMotionManager().getTileSelectedByMouse()].getBound().getX(),(int)tiles[playing.getMouseMotionManager().getTileSelectedByMouse()].getBound().getY(),tiles[playing.getMouseMotionManager().getTileSelectedByMouse()].getwTile(),tiles[playing.getMouseMotionManager().getTileSelectedByMouse()].gethTile(),null);
    }
    public void draw(Graphics g){
        drawPlantPreparedToPlanted(g);
        drawTileSelectedByMouse(g);
        drawTileSelectedByKeyBoard(g);
    }
}
