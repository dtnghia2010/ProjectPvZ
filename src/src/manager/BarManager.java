package manager;

import Audio.Audio;
import component.MyButtons;
import component.Plant;
import scenes.Playing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Iterator;

public class BarManager {
    private Image[] pick_plantBar;
    private Image[] plantInCD;
    private Image framePlant;
    private MyButtons pickPlant[];
    private int plantPickedID;
    private int plantPickedByKeyBoard = 0;
    private boolean[] isPlantInCD = new boolean[5];
    private boolean[] isCDReducing = new boolean[5];
    private int[] plantCD = new int[5];
    private int tile = 0;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Playing playing;
    public BarManager(Playing playing) {
        initButtons();
        importImg();
        initPlantInCD();
        this.playing = playing;
    }

    private void initButtons() {
        pickPlant = new MyButtons[5];
        pickPlant[0] = new MyButtons("Sunflower", 350, 0, 90, 90);
        pickPlant[1] = new MyButtons("Peashooter", 440, 0, 90, 90);
        pickPlant[2] = new MyButtons("Wall-nut", 530, 0, 90, 90);
        pickPlant[3] = new MyButtons("Snow Pea", 620, 0, 90, 90);
        pickPlant[4] = new MyButtons("Cherry Bomb", 710, 0, 90, 90);
    }

    private void importImg(){
        pick_plantBar = new Image[5];
        try {
            pick_plantBar[0] = t.getImage(getClass().getResource("/plantBar/PvZ_Sunflower.jpg"));
            pick_plantBar[1] = t.getImage(getClass().getResource("/plantBar/PvZ_Peashooter.jpg"));
            pick_plantBar[2] = t.getImage(getClass().getResource("/plantBar/PvZ_Wall-nut.jpg"));
            pick_plantBar[3] = t.getImage(getClass().getResource("/plantBar/PvZ_Snow_Pea.jpg"));
            pick_plantBar[4] = t.getImage(getClass().getResource("/plantBar/PvZ_Cherry_Bomb.jpg"));
            framePlant = t.getImage(getClass().getResource("/plantBar/plantSelected.png"));
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error - importImage()");
        }
    }

    public boolean[] getIsPlantInCD() {
        return isPlantInCD;
    }

    public int getPlantPickedID() {
        return plantPickedID;
    }

    public void setIsPlantInCD(int index,boolean isPlantInCD) {
        this.isPlantInCD[index] = isPlantInCD;
    }

    public void drawPlantbar(Graphics g){
        g.setColor(Color.black);
        g.drawRect(350, 0,450,90);
        g.setColor(Color.pink);
        g.fillRect(350, 0, 450, 90);
        Graphics2D g2d = (Graphics2D) g;
        int distance = 0;
        for (Image p : pick_plantBar){
            g.drawImage(p, 350 + distance, 0, 90, 90, null);
            switch (plantPickedID){
                case 0:
                    g2d.drawImage(framePlant,350, 0, 90, 90, null);
                    break;
                case 1:
                    g2d.drawImage(framePlant,350 + 90, 0, 90, 90, null);
                    break;
                case 2:
                    g2d.drawImage(framePlant,350 + 180, 0, 90, 90, null);
                    break;
                case 3:
                    g2d.drawImage(framePlant,350 + 270, 0, 90, 90, null);
                    break;
                case 4:
                    g2d.drawImage(framePlant,350 + 360, 0, 90, 90, null);
                    break;
            }
            distance += 90;
        }
    }
    public void sunFlower(){
        playing.getPlantManager().setIDhold(0);
        playing.getPlantManager().setHPhold(100);
        playing.getPlantManager().setATKhold(0);
        plantPickedID = 0;
        if(!playing.isStartWaveForCD()){
            plantCD[0] = 120;
        } else {
            plantCD[0] = 240;
        }
    }
    public void peaShooter(){
        playing.getPlantManager().setIDhold(1);
        playing.getPlantManager().setHPhold(100);
        playing.getPlantManager().setATKhold(20);
        plantPickedID = 1;
        if(!playing.isStartWaveForCD()){
            plantCD[1] = 120;
        } else {
            plantCD[1] = 240;
        }
    }
    public void wall_nut(){
        playing.getPlantManager().setIDhold(2);
        playing.getPlantManager().setHPhold(1000);
        playing.getPlantManager().setATKhold(0);
        plantPickedID = 2;
        if(!playing.isStartWaveForCD()){
            plantCD[2] = 120;
        } else {
            plantCD[2] = 600;
        }
    }
    public void snowPea(){
        playing.getPlantManager().setIDhold(3);
        playing.getPlantManager().setHPhold(100);
        playing.getPlantManager().setATKhold(20);
        plantPickedID = 3;
        if(!playing.isStartWaveForCD()){
            plantCD[3] = 120;
        } else {
            plantCD[3] = 240;
        }
    }
    public void cherryBomb(){
        playing.getPlantManager().setIDhold(4);
        playing.getPlantManager().setHPhold(1);
        playing.getPlantManager().setATKhold(1000);
        plantPickedID = 4;
        if(!playing.isStartWaveForCD()){
            plantCD[4] = 120;
        } else {
            plantCD[4] = 900;
        }
    }
    public void pickPlantByKeyBoard(){
        switch (plantPickedByKeyBoard){
            case 0:
                sunFlower();
                break;
            case 1:
                peaShooter();
                break;
            case 2:
                wall_nut();
                break;
            case 3:
                snowPea();
                break;
            case 4:
                cherryBomb();
                break;
        }
    }
    public void keyBoardChoosePlant(KeyEvent e){
        if(plantPickedByKeyBoard >= 0 && plantPickedByKeyBoard <=4){
            if(e.getKeyCode() == KeyEvent.VK_A){
                plantPickedByKeyBoard--;
            } else if(e.getKeyCode() == KeyEvent.VK_D){
                plantPickedByKeyBoard++;
            }
        } else if(plantPickedByKeyBoard<0){
            plantPickedByKeyBoard = 0;
        } else if (plantPickedByKeyBoard >4) {
            plantPickedByKeyBoard = 4;
        }
        pickPlantByKeyBoard();
    }
    public void keyBoardSelectPlant(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            System.out.println("1");
            playing.getPlantManager().setSelected(true);
        }
    }
    public void tileSelectedByKeyBoard(KeyEvent e){
        if(playing.getPlantManager().isSelected()){
            if(e.getKeyCode() == KeyEvent.VK_A){
                tile--;
            } else if(e.getKeyCode() == KeyEvent.VK_D){
                tile++;
            } else if (e.getKeyCode() == KeyEvent.VK_W) {
                tile = tile-9;
            } else if(e.getKeyCode() == KeyEvent.VK_S){
                tile = tile+9;
            }
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                plantCreate();
            }
        }
    }
    public void drawTileSelected(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0;i<playing.getTileManager().getTiles().length;i++){
            if(i == tile){
                g2d.drawImage(framePlant,playing.getTileManager().getTiles()[i].getCurX(),playing.getTileManager().getTiles()[i].getCurY(),playing.getTileManager().getTiles()[i].getwTile(),playing.getTileManager().getTiles()[i].gethTile(),null);
            }
        }
    }
    public void plantCreate(){
        if(playing.getPlantManager().isSelected() && isPlantInCD[plantPickedID]){
            for(int i = 0;i<playing.getTileManager().getTiles().length;i++){
                if(!playing.getTileManager().getTiles()[i].isOccupied()){
                    Rectangle r = new Rectangle((int)playing.getTileManager().getTiles()[i].getBound().getX(), (int)playing.getTileManager().getTiles()[i].getBound().getY(), (int)playing.getTileManager().getTiles()[i].getBound().getWidth(), (int)playing.getTileManager().getTiles()[i].getBound().getHeight());
                    Audio.tapGrass();
                    playing.getTileManager().getTiles()[i].setOccupied(true);
                    playing.getPlantManager().initPlants(playing.getPlantManager().getIDhold(),playing.getPlantManager().getHPhold(),playing.getPlantManager().getATKhold());
                    playing.getBarManager().setIsPlantInCD(playing.getBarManager().getPlantPickedID(),true);
                    for (int j = 0; j < playing.getPlantManager().getPlantList().size(); j++){
                        playing.getPlantManager().getPlantList().get(playing.getPlantManager().getPlantList().size() - 1).setTileHold(i);
                        if(!playing.getTileManager().getTiles()[i].isPlanted()){
                            playing.getPlantManager().getPlantList().get(playing.getPlantManager().getPlantList().size() - 1).setX(r.x);
                            playing.getPlantManager().getPlantList().get(playing.getPlantManager().getPlantList().size() - 1).setY(r.y);
                            playing.getPlantManager().getPlantList().get(playing.getPlantManager().getPlantList().size() - 1).setWidth(r.width);
                            playing.getPlantManager().getPlantList().get(playing.getPlantManager().getPlantList().size() - 1).setHeight(r.height);
                            playing.getTileManager().getTiles()[i].setPlanted(true);
                        }
                    }
                }
            }
        }
    }
    {
        isCDReducing[0] = false;
        isCDReducing[1] = false;
        isCDReducing[2] = false;
        isCDReducing[3] = false;
        isCDReducing[4] = false;
    }
    public void update(){
        if(playing.getPlantManager().getPlantList() != null){
            Iterator<Plant> iterator = playing.getPlantManager().getPlantList().iterator();
            while (iterator.hasNext()){
                Plant plant = iterator.next();
                switch (plant.getPlantID()){
                    case 0:
                        if(!isCDReducing[0]){
                            CDCount(0);
                            isCDReducing[0] = true;
                        }
                        break;
                    case 1:
                        if(!isCDReducing[1]){
                            CDCount(1);
                            isCDReducing[1] = true;
                        }
                        break;
                    case 2:
                        if(!isCDReducing[2]){
                            CDCount(2);
                            isCDReducing[2] = true;
                        }
                        break;
                    case 3:
                        if(!isCDReducing[3]){
                            CDCount(3);
                            isCDReducing[3] = true;
                        }
                        break;
                    case 4:
                        if(!isCDReducing[4]){
                            CDCount(4);
                            isCDReducing[4] = true;
                        }
                        break;
                }
            }
            isCDReducing[0] = false;
            isCDReducing[1] = false;
            isCDReducing[2] = false;
            isCDReducing[3] = false;
            isCDReducing[4] = false;
        }
    }
    public void resetCD(int index){
        if(!playing.isStartWaveForCD()){
            plantCD[index] = 120;
        } else {
            switch (index){
                case 0:
                    plantCD[index] = 240;
                    break;
                case 1:
                    plantCD[index] = 240;
                    break;
                case 2:
                    plantCD[index] = 600;
                    break;
                case 3:
                    plantCD[index] = 240;
                    break;
                case 4:
                    plantCD[index] = 900;
                    break;
            }
        }
    }
    public void CDCount(int index){
        if(isPlantInCD[index]){
            plantCD[index]--;
            if(plantCD[index] <= 0){
                resetCD(index);
                isPlantInCD[index] = false;
            }
        }
    }
    public void initPlantInCD(){
        plantInCD = new Image[5];
        try {
            plantInCD[0] = t.getImage(getClass().getResource("/plantInCD/sunFolwer.png"));
            plantInCD[1] = t.getImage(getClass().getResource("/plantInCD/peaShooter.png"));
            plantInCD[2] = t.getImage(getClass().getResource("/plantInCD/wallNut.png"));
            plantInCD[3] = t.getImage(getClass().getResource("/plantInCD/snowPea.png"));
            plantInCD[4] = t.getImage(getClass().getResource("/plantInCD/cherryBomb.png"));
        } catch (Exception e){

        }
    }
    public void drawPlantInCD(Graphics g){
        int distance = 0;
        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0;i<5;i++){
            if(isPlantInCD[i]){
                g.drawImage(plantInCD[i], 350 + distance, 0, 90, 90, null);
                int cd = (plantCD[i]+59)/60;
                g2d.setColor(Color.YELLOW);
                g2d.setFont(new Font("Arial",Font.BOLD,30));
                g2d.drawString(String.format("%d",cd),385 +distance,50);
            }
            distance += 90;
        }
    }
    public MyButtons[] getPickPlant() {
        return pickPlant;
    }
}
