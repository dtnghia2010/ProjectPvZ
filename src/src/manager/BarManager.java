package manager;


import component.Bar;
import component.MyButtons;
import scenes.Playing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BarManager {
    private Image[] pick_plantBar;
    private Image[] plantInCD;
    private Image pickedPlant;
    private Image plantBar;
    private MyButtons pickPlant[];
    private List<Integer> plantPickedID = new ArrayList<>();
    private boolean[] isPlantInCD = new boolean[5];
    private boolean[] isCDReducing = new boolean[5];
    private boolean[] isPlantEnoughSun = new boolean[5];
    private boolean isPlantLocked = false;
    private static BarManager instance = null;

    public boolean isPlantLocked() {
        return isPlantLocked;
    }

    public void setPlantLocked(boolean plantLocked) {
        isPlantLocked = plantLocked;
    }

    private int[] plantCD = new int[5];
    private Toolkit t = Toolkit.getDefaultToolkit();
    private static Playing playing;
    private BarManager(Playing playing) {
        initButtons();
        importImg();
        initPlantInCD();
        this.playing = playing;
    }

    public static BarManager createBar(Playing playing) {
        if(instance == null) {
            instance = new BarManager(playing);
        } else {
            System.out.println("Cannot create another BarManager");
        }
        return instance;
    }

    private void initButtons() {
        pickPlant = new MyButtons[6];
        pickPlant[0] = new MyButtons("Sunflower", 465, 20, 80, 70);
        pickPlant[1] = new MyButtons("Peashooter", 560, 20, 80, 70);
        pickPlant[2] = new MyButtons("Wall-nut", 655, 20, 80, 70);
        pickPlant[3] = new MyButtons("Shadow peashooter", 750, 20, 80, 70);
        pickPlant[4] = new MyButtons("Cherry Bomb", 845, 20, 80, 70);
        pickPlant[5] = new MyButtons("Shovel",940,20,80,70);
    }

    private void importImg(){
        pick_plantBar = new Image[6];
        try {
            pick_plantBar[0] = t.getImage(getClass().getResource("/plantBar/Sunflower.png"));
            pick_plantBar[1] = t.getImage(getClass().getResource("/plantBar/Peashooter.png"));
            pick_plantBar[2] = t.getImage(getClass().getResource("/plantBar/Wall-nut.png"));
            pick_plantBar[3] = t.getImage(getClass().getResource("/plantBar/Shadow_Peashooter.png"));
            pick_plantBar[4] = t.getImage(getClass().getResource("/plantBar/Cherry_Bomb.png"));
            pick_plantBar[5] = t.getImage(getClass().getResource("/shovel/shovel.png"));
            pickedPlant = t.getImage(getClass().getResource("/plantBar/plantSelected.png"));
            plantBar = t.getImage(getClass().getResource("/plantBar/plantPanel.png"));
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

    public void setPlantCD(int index ,int plantCD) {
        this.plantCD[index] = plantCD;
    }

    public void setIsPlantInCD(int index, boolean isPlantInCD) {
        this.isPlantInCD[index] = isPlantInCD;
    }

    public void drawPlantbar(Graphics g){
        g.setColor(Color.black);
        g.drawRect(370, 10,580,90);
        g.setColor(Color.pink);
        g.fillRect(370, 10, 580, 90);
        g.drawImage(plantBar, 370, 10, 580, 90, null);
        Graphics2D g2d = (Graphics2D) g;
        int distance = 0;
        for (Image p : pick_plantBar){
            g.drawImage(p, 465 + distance, 20, 80, 70, null);
            distance += 95;
        }
    }
    public void sunFlower(){
        playing.getPlantManager().setIDhold(0);
        plantPickedID.add(0);
    }
    public void peaShooter(){
        playing.getPlantManager().setIDhold(1);
        plantPickedID.add(1);
    }
    public void wall_nut(){
        playing.getPlantManager().setIDhold(2);
        plantPickedID.add(2);
    }
    public void shadowPea(){
        playing.getPlantManager().setIDhold(3);
        plantPickedID.add(3);
    }
    public void cherryBomb(){
        playing.getPlantManager().setIDhold(4);
        plantPickedID.add(4);
    }
    public void setCDatStartOfGame(){
        plantCD[1] = 95;
        plantCD[2] = 95;
        plantCD[3] = 95;
        plantCD[4] = 95;
        plantPickedID.add(1);
        plantPickedID.add(2);
        plantPickedID.add(3);
        plantPickedID.add(4);
        isPlantInCD[1] = true;
        isPlantInCD[2] = true;
        isPlantInCD[3] = true;
        isPlantInCD[4] = true;
    }
    public Image getPickedPlant() {
        return pickedPlant;
    }
    {
        isCDReducing[0] = false;
        isCDReducing[1] = false;
        isCDReducing[2] = false;
        isCDReducing[3] = false;
        isCDReducing[4] = false;
    }
    public void update(){
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
        plantEnoughSun();
    }

    public boolean[] getIsPlantEnoughSun() {
        return isPlantEnoughSun;
    }
    public void resetCD(int index){
        if(playing.isStartWaveForCD()) {
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
            plantInCD[0] = t.getImage(getClass().getResource("/plantInCD/Sunflower.png"));
            plantInCD[1] = t.getImage(getClass().getResource("/plantInCD/Peashooter.png"));
            plantInCD[2] = t.getImage(getClass().getResource("/plantInCD/Wall-nut.png"));
            plantInCD[3] = t.getImage(getClass().getResource("/plantInCD/Shadow_Peashooter.png"));
            plantInCD[4] = t.getImage(getClass().getResource("/plantInCD/Cherry_Bomb.png"));
        } catch (Exception e){

        }
    }
    public void drawPlantInCD(Graphics g){
        int distance = 0;
        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0;i<5;i++){
            if(isPlantInCD[i]){
                g.drawImage(plantInCD[i], 465 + distance, 20, 80, 70, null);
                int cd = (plantCD[i]+59)/60;
                g2d.setColor(Color.YELLOW);
                g2d.setFont(new Font("Arial",Font.BOLD,30));
                g2d.drawString(String.format("%d",cd),495 + distance,65);
            }
            distance += 95;
        }
    }
    public void plantEnoughSun(){
        if(playing.getSunManager().getSunHold() < 50){
            for(int i = 0;i<isPlantEnoughSun.length;i++){
                isPlantEnoughSun[i] = false;
            }
        } else if(playing.getSunManager().getSunHold() < 100){
            isPlantEnoughSun[1] = false;
            isPlantEnoughSun[3] = false;
            isPlantEnoughSun[4] = false;
            isPlantEnoughSun[0] = true;
            isPlantEnoughSun[2] = true;
        } else if(playing.getSunManager().getSunHold() < 150){
            for (int i = 0;i<3;i++){
                isPlantEnoughSun[i] = true;
            }
            isPlantEnoughSun[3] = false;
            isPlantEnoughSun[4] = false;
        } else if(playing.getSunManager().getSunHold() < 175){
            for (int i = 0;i<3;i++){
                isPlantEnoughSun[i] = true;
            }
            isPlantEnoughSun[3] = false;
            isPlantEnoughSun[4] = true;
        } else {
            for(int i = 0;i<isPlantEnoughSun.length;i++){
                isPlantEnoughSun[i] = true;
            }
        }
    }
    public void drawPlantNotEnoughSun(Graphics g){
        if(playing.getSunManager().getSunHold() < 50){
            g.drawImage(plantInCD[0],465,20, 80, 70, null);
            g.drawImage(plantInCD[1],465+95,20, 80, 70, null);
            g.drawImage(plantInCD[2],465+190,20, 80, 70, null);
            g.drawImage(plantInCD[3],465+285,20, 80, 70, null);
            g.drawImage(plantInCD[4],465+380,20, 80, 70, null);
        } else if(playing.getSunManager().getSunHold() < 100){
            g.drawImage(plantInCD[1],465+95,20, 80, 70, null);
            g.drawImage(plantInCD[3],465+286,20, 80, 70, null);
            g.drawImage(plantInCD[4],465+380,20, 80, 70, null);
        } else if(playing.getSunManager().getSunHold() < 150){
            g.drawImage(plantInCD[3],465+285,20, 80, 70, null);
            g.drawImage(plantInCD[4],465+380,20, 80, 70, null);
        } else if(playing.getSunManager().getSunHold() < 175){
            g.drawImage(plantInCD[3],465+285,20, 80, 70, null);
        }
    }
    public void draw(Graphics g){
        drawPlantbar(g);
        drawPlantNotAvailableFromStart(g);
        drawPlantInCD(g);
        drawPlantNotEnoughSun(g);
    }
    public void drawPlantNotAvailableFromStart(Graphics g){
        if(!playing.isStartWaveForCD()){
            g.drawImage(plantInCD[0],465,20, 80, 70, null);
        }
    }
    public MyButtons[] getPickPlant() {
        return pickPlant;
    }
}
