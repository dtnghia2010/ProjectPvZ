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
    private Image shovelSprite = t.getImage(getClass().getResource("/shovel/shovel-sprite.png"));
    private Tile[] tilesOfHouseOwner = new Tile[5];
    public int wTileOfHouseOwner = 125;
    public int hTileOfHouseOwner = 70;
    private static TileManager instance;

    public boolean isInTile() {
        return isInTile;
    }
    private TileManager(Playing playing) {
        initTiles();
        importHardBlurPlant();
        importLightBlurPlant();
        initTilesOfHouseOwner();
        this.playing = playing;
    }

    public static TileManager createTileManager(Playing playing) {
        if(instance == null) {
            instance = new TileManager(playing);
        } else {
            System.out.println("Cannot create another TileManager");
        }
        return instance;
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
    public void drawTiles(Graphics g) {
        for (Tile t : tiles) {
            Rectangle r = t.getBound();
            g.setColor(Color.lightGray);
            g.fillRect(r.x, r.y, r.width, r.height);

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

    private void initTilesOfHouseOwner() {
        int curX = 230;
        int curY = 171;
        int rowCounter = 0;

        for (int i = 0; i < 5; i++) {
            if (rowCounter >= 1) {
                // Nếu đã đủ số lượng tile trong một hàng, thì di chuyển xuống hàng mới
                curX = 230;
                curY += hTileOfHouseOwner + 10; // Khoảng cách giữa các hàng
                rowCounter = 0; // Đặt lại số lượng tile trong một hàng
            }
            // Tạo mới tile và đặt tọa độ và kích thước cho tile
            tilesOfHouseOwner[i] = new Tile(new Rectangle(curX, curY, wTileOfHouseOwner, hTileOfHouseOwner));

            // Cập nhật tọa độ y cho tile tiếp theo trong cùng một hàng

            rowCounter++; // Tăng số lượng tile trong một hàng
        }
    }

    public void drawTiles(Graphics g, HouseOwnerManager houseOwnerManager) {
        int curX = 200; // Tọa độ x ban đầu
        int curY = 171; // Tọa độ y ban đầu

        for (Tile t: tilesOfHouseOwner) {
             Rectangle r = new Rectangle((int)t.getBound().getX(),(int)t.getBound().getY(), t.getWTileOfHouseOwner(), t.getHTileOfHouseOwner());

             g.setColor(Color.pink);
             g.fillRect(r.x, r.y, r.width, r.height);

             curY += t.getHTileOfHouseOwner() + 10; // Khoảng cách giữa các hàng
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
            if(playing.getPlantManager().getIDhold() >= 0 && playing.getKeyBoardManager().getPlantPickedByKeyBoard() < 5){
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
    public void drawShovelSprite(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        if(playing.getKeyBoardManager().getPlantPickedByKeyBoard() == 5){
            if(playing.getPlantManager().isShoveled()){
                Rectangle r = new Rectangle((int)tiles[playing.getKeyBoardManager().getTileSelectedByKeyBoard()].getBound().getX(),(int)tiles[playing.getKeyBoardManager().getTileSelectedByKeyBoard()].getBound().getY(),tiles[playing.getKeyBoardManager().getTileSelectedByKeyBoard()].getwTile(),tiles[playing.getKeyBoardManager().getTileSelectedByKeyBoard()].gethTile());
                g2d.drawImage(shovelSprite,(int)r.getX()-10,(int)r.getY()-5,(int)r.getWidth()+20,(int)r.getHeight()+20,null);
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
//        drawTiles(g);
        drawPlantPreparedToPlanted(g);
        drawTileSelectedByMouse(g);
        drawTileSelectedByKeyBoard(g);
        drawShovelSprite(g);
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

