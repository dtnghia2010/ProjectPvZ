package manager;

import Audio.Audio;
import component.MyButtons;
import scenes.Playing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class BarManager {
    private Image[] pick_plantBar;
    private Image[] plantInCD;
    private Image pickedPlant;
    private MyButtons pickPlant[];
    private List<Integer> plantPickedID = new ArrayList<>();
    private int plantPickedByKeyBoard = 0;
    private int plantPickedByMouse = 0;
    private boolean[] isPlantInCD = new boolean[5];
    private boolean[] isCDReducing = new boolean[5];
    private boolean isPlantLocked = false;

    public boolean isPlantLocked() {
        return isPlantLocked;
    }

    public void setPlantLocked(boolean plantLocked) {
        isPlantLocked = plantLocked;
    }

    private int[] plantCD = new int[5];
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
            pickedPlant = t.getImage(getClass().getResource("/plantBar/plantSelected.png"));
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error - importImage()");
        }
    }

    public boolean[] getIsPlantInCD() {
        return isPlantInCD;
    }

    public List<Integer> getPlantPickedID() {
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
            distance += 90;
        }
    }
    public void sunFlower(){
        playing.getPlantManager().setIDhold(0);
        plantPickedID.add(0);
        if(!playing.isStartWaveForCD() && !isPlantInCD[0]){
            plantCD[0] = 60;
        } else if(playing.isStartWaveForCD() && !isPlantInCD[0]) {
            plantCD[0] = 240;
        }
    }
    public void peaShooter(){
        playing.getPlantManager().setIDhold(1);
        plantPickedID.add(1);
        if(!playing.isStartWaveForCD() && !isPlantInCD[1]){
            plantCD[1] = 60;
        } else if(playing.isStartWaveForCD() && !isPlantInCD[1]) {
            plantCD[1] = 240;
        }
    }
    public void wall_nut(){
        playing.getPlantManager().setIDhold(2);
        plantPickedID.add(2);
        if(!playing.isStartWaveForCD() && !isPlantInCD[2]){
            plantCD[2] = 60;
        } else if(playing.isStartWaveForCD() && !isPlantInCD[2]) {
            plantCD[2] = 600;
        }
    }
    public void shadowPea(){
        playing.getPlantManager().setIDhold(3);
        plantPickedID.add(3);
        if(!playing.isStartWaveForCD() && !isPlantInCD[3]){
            plantCD[3] = 60;
        } else if (playing.isStartWaveForCD() && !isPlantInCD[3]){
            plantCD[3] = 240;
        }
    }
    public void cherryBomb(){
        playing.getPlantManager().setIDhold(4);
        plantPickedID.add(4);
        if(!playing.isStartWaveForCD() && !isPlantInCD[4]){
            plantCD[4] = 60;
        } else if(playing.isStartWaveForCD() && !isPlantInCD[4]) {
            plantCD[4] = 900;
        }
    }
    public void pickPlantByKeyBoard(){
        switch (plantPickedByKeyBoard){
            case 0:
                if(playing.isStartWaveForCD()){
                    sunFlower();
                } else {
                    Audio.plantNotAvailable();
                }
                break;
            case 1:
                peaShooter();
                break;
            case 2:
                wall_nut();
                break;
            case 3:
                shadowPea();
                break;
            case 4:
                cherryBomb();
                break;
        }
    }
    public void keyBoardChoosePlant(KeyEvent e){
        if(!playing.getPlantManager().isSelected()){
            if(plantPickedByKeyBoard > 0 && plantPickedByKeyBoard <4){
                if(e.getKeyCode() == KeyEvent.VK_A){
                    plantPickedByKeyBoard--;
                    plantPickedByMouse = plantPickedByKeyBoard;
                } else if(e.getKeyCode() == KeyEvent.VK_D){
                    plantPickedByKeyBoard++;
                    plantPickedByMouse = plantPickedByKeyBoard;
                }
            } else if(plantPickedByKeyBoard==0){
                if(e.getKeyCode() == KeyEvent.VK_D){
                    plantPickedByKeyBoard++;
                    plantPickedByMouse = plantPickedByKeyBoard;
                }
            } else if (plantPickedByKeyBoard==4) {
                if(e.getKeyCode() == KeyEvent.VK_A){
                    plantPickedByKeyBoard--;
                    plantPickedByMouse = plantPickedByKeyBoard;
                }
            }
            pickPlantByKeyBoard();
        }
    }
    public void drawPlantSelectedByKeyBoard(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(pickedPlant,(int)pickPlant[plantPickedByKeyBoard].getBounds().getX(),(int)pickPlant[plantPickedByKeyBoard].getBounds().getY(),(int)pickPlant[plantPickedByKeyBoard].getBounds().getWidth(),(int)pickPlant[plantPickedByKeyBoard].getBounds().getHeight(),null);
    }
    public void mouseTrackPlantBar(int x, int y){
        if(!playing.getPlantManager().isSelected()){
            for(int i = 0;i<pickPlant.length;i++){
                Rectangle r = pickPlant[i].getBounds();
                if(r.contains(x,y)){
                    if(playing.getTileManager().isInTile()){
                        playing.getTileManager().setInTile(false);
                        playing.getPlantManager().setSelected(false);
                    }
                    plantPickedByMouse = i;
                    plantPickedByKeyBoard = plantPickedByMouse;
                }
            }
        }
    }
    public void drawPlantSelectedByMouse(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(pickedPlant,(int)pickPlant[plantPickedByMouse].getBounds().getX(),(int)pickPlant[plantPickedByMouse].getBounds().getY(),(int)pickPlant[plantPickedByMouse].getBounds().getWidth(),(int)pickPlant[plantPickedByMouse].getBounds().getHeight(),null);
    }
    public void keyBoardSelectPlant(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            playing.getPlantManager().setSelected(true);
            Audio.tapPlantBar();
        }
    }

    public Image getPickedPlant() {
        return pickedPlant;
    }

    public void returnToSelectPlantByKeyBoard(KeyEvent e){
        if(!playing.isStartWaveForCD() && playing.getPlantManager().isSelected()){
            if(e.getKeyCode() == KeyEvent.VK_SHIFT){
                playing.getPlantManager().setSelected(false);
                playing.getPlantManager().setTimeToPlant(true);
                isPlantLocked = false;
            }
        }
    }
    public void returnToSelectPlantByMouse(){
        if(!playing.isStartWaveForCD() && playing.getPlantManager().isSelected()){
            playing.getPlantManager().setSelected(false);
            playing.getPlantManager().setTimeToPlant(true);
            isPlantLocked = false;
        }
    }
    public void startGame(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            playing.setStartWave(true);
            playing.setStartWaveForCD(true);
            playing.getWaveManager().readyNewWave();
            playing.getPlantManager().setSelected(false);
            playing.getPlantManager().setTimeToPlant(true);
            isPlantLocked = false;
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
            for(int i = 0; i<plantPickedID.size();i++){
                switch (plantPickedID.get(i)){
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
            plantCD[index] = 60;
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
    public void drawPlantNotEnoughSun(Graphics g){
        if(playing.getSunManager().getSunHold() < 50){
            g.drawImage(plantInCD[0],350,0, 90, 90, null);
            g.drawImage(plantInCD[1],350+90,0, 90, 90, null);
            g.drawImage(plantInCD[2],350+180,0, 90, 90, null);
            g.drawImage(plantInCD[3],350+270,0, 90, 90, null);
            g.drawImage(plantInCD[4],350+360,0, 90, 90, null);
        } else if(playing.getSunManager().getSunHold() < 100){
            g.drawImage(plantInCD[1],350+90,0, 90, 90, null);
            g.drawImage(plantInCD[3],350+270,0, 90, 90, null);
            g.drawImage(plantInCD[4],350+360,0, 90, 90, null);
        } else if(playing.getSunManager().getSunHold() < 150){
            g.drawImage(plantInCD[3],350+270,0, 90, 90, null);
            g.drawImage(plantInCD[4],350+360,0, 90, 90, null);
        } else if(playing.getSunManager().getSunHold() < 250){
            g.drawImage(plantInCD[3],350+270,0, 90, 90, null);
        }
    }
    public void drawPlantNotAvailableFromStart(Graphics g){
        if(!playing.isStartWaveForCD()){
            g.drawImage(plantInCD[0],350,0, 90, 90, null);
        }
    }
    public MyButtons[] getPickPlant() {
        return pickPlant;
    }
}
