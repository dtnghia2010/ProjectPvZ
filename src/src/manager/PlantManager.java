package manager;


import Audio.Audio;
import Plant.Plant;
import component.Tile;
import scenes.Playing;
import zombie.Zombie;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PlantManager {
    private final Image[] sunFlower = new Image[27];
    private final Image[] peaShooter_idle = new Image[59];
    private final Image[] peaShooter_attack = new Image[59];
    private final Image[] wall_nut = new Image[82];
    private final Image[] shadowPea_Idle = new Image[23];
    private final Image[] shadowPea_Attack = new Image[21];
    private final Image[] cherryBombGif = new Image[49];
    private Toolkit t = Toolkit.getDefaultToolkit();

    private List<Plant> plantList = new ArrayList<>();
    private boolean isPlantTest = true;
    private boolean isTimeToPlant = false;
    private Playing playing;
    private boolean selected = true;
    private boolean isPlanted = false;
    private boolean isForbidden = false;
    private boolean isShoveled = false;
    private int IDhold, HPhold, ATKhold,sunCostHold, frameCountLimitHold;

    public PlantManager(Playing playing) {
        this.playing = playing;
        importCherryBombGif();
        importPeaShooter();
        importSunFlower();
        importWallNut();
        importShadowPea();
        initPlantTest();
        plantForbiddenFromStart();
    }

    public void initPlants(int plantID, int plantHP, int ATK, int frameCount) {
        plantList.add(new Plant(plantHP, plantID,ATK,frameCount));
    }

    public void setIDhold(int IDhold) {
        this.IDhold = IDhold;
    }
    public boolean isTimeToPlant() {
        return isTimeToPlant;
    }

    public void setTimeToPlant(boolean timeToPlant) {
        isTimeToPlant = timeToPlant;
    }
    public boolean isSelected() {
        return selected;
    }
    public void importSunFlower(){
        for(int i = 0;i<sunFlower.length;i++){
            sunFlower[i] = t.getImage(getClass().getResource("/sunflower/Gif-de-girasol-"+i+".png"));
        }
    }
    public void importPeaShooter(){
        for (int i = 0;i<peaShooter_idle.length;i++){
            peaShooter_idle[i] = t.getImage(getClass().getResource("/peaShooter - idle/PeaShooter_Idle1-"+i+".png"));
        }
        for(int i = 0;i<peaShooter_attack.length;i++){
            peaShooter_attack[i] = t.getImage(getClass().getResource("/peaShooter - attack/PeaShooter_Spit-"+i+".png"));
        }
    }
    public void importWallNut(){
        for(int i = 0;i<wall_nut.length;i++){
            wall_nut[i] = t.getImage(getClass().getResource("/Wall_nut/Wallnut_Idle_animation-"+i+".png"));
        }
    }
    public void importShadowPea(){
        for(int i = 0;i<shadowPea_Idle.length;i++){
            shadowPea_Idle[i] = t.getImage(getClass().getResource("/Shadowpea - idle/"+i+".png"));
        }
        for(int i = 0;i < shadowPea_Attack.length;i++){
            shadowPea_Attack[i] = t.getImage(getClass().getResource("/Shadowpea - attack/"+i+".png"));
        }
    }
    public void importCherryBombGif(){
        try {
            for(int i = 0;i<cherryBombGif.length;i++){
                cherryBombGif[i] = t.getImage(getClass().getResource("/cherryBomb/"+(i + 1)+".png"));
            }
        } catch (Exception e){

        }
    }
    public void drawPlant(Graphics g){
        synchronized (plantList){
            Iterator<Plant> iterator = plantList.iterator();
            while (iterator.hasNext()){
                Plant pl = iterator.next();
                if (pl.getPlantID() == 0){
                    g.drawImage(sunFlower[pl.getFrameCountIdle()], pl.getX(), pl.getY(), pl.getWidth(), pl.getHeight(), null);
                } else if (pl.getPlantID() == 1){
                    if(!pl.isDangered()){
                        g.drawImage(peaShooter_idle[pl.getFrameCountIdle()], pl.getX(), pl.getY(), pl.getWidth(), pl.getHeight(), null);
                    } else {
                        g.drawImage(peaShooter_attack[pl.getFrameCountAttack()], pl.getX(), pl.getY(), pl.getWidth(), pl.getHeight(), null);
                    }
                } else if (pl.getPlantID() == 2){
                    g.drawImage(wall_nut[pl.getFrameCountIdle()], pl.getX(), pl.getY(), pl.getWidth(), pl.getHeight(), null);
                } else if (pl.getPlantID() == 3){
                    if(!pl.isDangered()){
                        g.drawImage(shadowPea_Idle[pl.getFrameCountIdle()], pl.getX()-10, pl.getY()-30, pl.getWidth()+30, pl.getHeight()+30, null);
                    } else {
                        g.drawImage(shadowPea_Attack[pl.getFrameCountAttack()], pl.getX()-10, pl.getY()-30, pl.getWidth()+30, pl.getHeight()+30, null);
                    }
                } else if (pl.getPlantID() == 4){
                    g.drawImage(cherryBombGif[pl.getFrameCountIdle()],pl.getX(), pl.getY(), pl.getWidth(), pl.getHeight(), null);
                }
            }
        }
    }
    public void initPlantTest(){ //Trash plants
        initPlants(0,1,0,25);
        initPlants(1,1,0,58);
        initPlants(2,1,0,81);
        initPlants(3,1,0,20);
        initPlants(4,1,0,30);
        initPlants(1,1,0,58);
        initPlants(3,1,0,20);
        for(int i = 0;i<plantList.size();i++){
            plantList.get(i).setX(-999);
            plantList.get(i).setY(-999);
            plantList.get(i).setWidth(100);
            plantList.get(i).setHeight(100);
            if(plantList.get(i).getPlantID() == 1){
                plantList.get(i).setFrameCountAttackLimit(59);
            } else if(plantList.get(i).getPlantID() == 3){
                plantList.get(i).setFrameCountAttackLimit(21);
            }
        }
    }
    public void update(){
        if(!isPlantTest){
            synchronized (plantList){
                Iterator<Plant> iterator = plantList.iterator();
                while (iterator.hasNext()){
                    Plant plant = iterator.next();
                    plant.updateFrameCountIdle();
                    if(plant.getPlantID() == 1 || plant.getPlantID() == 3){
                        plant.updateFrameCountAttack();
                    }
                }
            }
        }
        if(isPlantTest){
            synchronized (plantList){
                Iterator<Plant> iterator = plantList.iterator();
                while (iterator.hasNext()){
                    Plant plant = iterator.next();
                    setPlantDangeredForTesting();
                    plant.loadAnimationForTheFirstTime();
//                    if(plantList.get(2).getFrameCountIdle()  == plantList.get(2).getFrameCountIdleLimit()-1){
//
//                    }
                }
            }
        }
        alertPlant();
        calmPlant();
        updateSunflower();
        timeExplode();
        plantAttack();
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public void setPlantStatus(Rectangle r){
        plantList.get(plantList.size() - 1).setX(r.x);
        plantList.get(plantList.size() - 1).setY(r.y);
        plantList.get(plantList.size() - 1).setWidth(r.width);
        plantList.get(plantList.size() - 1).setHeight(r.height);
        if(plantList.get(plantList.size()-1).getPlantID() == 1){
            plantList.get(plantList.size()-1).setFrameCountAttackLimit(59);
        } else if(plantList.get(plantList.size()-1).getPlantID() == 3){
            plantList.get(plantList.size()-1).setFrameCountAttackLimit(20);
        }
    }

    public int getIDhold() {
        return IDhold;
    }

    public void setPlantParameter(){
        switch (IDhold){
            case 0:
                HPhold = 100;
                ATKhold = 0;
                frameCountLimitHold = 25;
                sunCostHold = 50;
                if(playing.isStartWaveForCD() && !playing.getBarManager().getIsPlantInCD()[0]){
                    playing.getBarManager().setPlantCD(0,360);
                }
                break;
            case 1:
                HPhold = 100;
                ATKhold = 20;
                frameCountLimitHold = 58;
                sunCostHold = 100;
                if(playing.isStartWaveForCD() && !playing.getBarManager().getIsPlantInCD()[1]){
                    playing.getBarManager().setPlantCD(1,360);
                }
                break;
            case 2:
                HPhold = 1000;
                ATKhold = 0;
                frameCountLimitHold = 81;
                sunCostHold = 50;
                if(playing.isStartWaveForCD() && !playing.getBarManager().getIsPlantInCD()[2]){
                    playing.getBarManager().setPlantCD(2,600);
                }
                break;
            case 3:
                HPhold = 100;
                ATKhold = 20;
                frameCountLimitHold = 20;
                sunCostHold = 175;
                if(playing.isStartWaveForCD() && !playing.getBarManager().getIsPlantInCD()[3]){
                    playing.getBarManager().setPlantCD(3,360);
                }
                break;
            case 4:
                HPhold = 10000;
                ATKhold = 1000;
                frameCountLimitHold = 30;
                sunCostHold = 150;
                if(playing.isStartWaveForCD() && !playing.getBarManager().getIsPlantInCD()[4]){
                    playing.getBarManager().setPlantCD(4,900);
                }
                break;
        }
    }

    public boolean isForbidden() {
        return isForbidden;
    }

    public void setForbidden(boolean forbidden) {
        isForbidden = forbidden;
    }

    public void plantForbiddenFromStart(){
        IDhold = 0;
        playing.getBarManager().setPlantLocked(false);
        isForbidden = true;
    }
    public void plantOnTile(Tile tile, int x, int y,int i){
        if(!tile.isOccupied()){
            Rectangle r = new Rectangle((int)tile.getBound().getX(), (int)tile.getBound().getY(), (int)tile.getBound().getWidth(), (int)tile.getBound().getHeight());
            if (r.contains(x, y)){
                setPlantParameter();
                if(playing.getSunManager().getSunHold() >= sunCostHold){
                    Audio.tapGrass();
                    tile.setOccupied(true);
                    initPlants(IDhold,HPhold,ATKhold,frameCountLimitHold);
                    playing.getBarManager().setIsPlantInCD(playing.getBarManager().getPlantPickedID().get(playing.getBarManager().getPlantPickedID().size()-1),true);
                    for (int j = 0; j < plantList.size(); j++){
                        plantList.get(plantList.size() - 1).setTileHold(i);
                        if(!tile.isPlanted()){
                            setPlantStatus(r);
                            tile.setPlanted(true);
                        }
                    }
                    isPlanted = true;
                    playing.getSunManager().sunConsumed(sunCostHold);
                } else {
                    Audio.plantNotAvailable();
                }
            }
        }
    }
    public void mouse(int x, int y){
        isPlantTest = false;
        if(!isForbidden){
            if(selected && !playing.getBarManager().getIsPlantInCD()[playing.getBarManager().getPlantPickedID().get(playing.getBarManager().getPlantPickedID().size()-1)]){
                for (int i = 0; i < playing.getTileManager().getTiles().length; i++){
                    plantOnTile(playing.getTileManager().getTiles()[i],x,y,i);
                }
                if(playing.isStartWaveForCD()){
                    selected = false;
                    playing.getBarManager().setPlantLocked(false);
                }
            } else if(selected && playing.getBarManager().getIsPlantInCD()[playing.getBarManager().getPlantPickedID().get(playing.getBarManager().getPlantPickedID().size()-1)]) {
                Audio.plantNotAvailable();
            }
        } else {
            for (int i = 0; i < playing.getTileManager().getTiles().length; i++){
                Rectangle r = playing.getTileManager().getTiles()[i].getBound();
                if(r.contains(x,y)){
                    Audio.plantNotAvailable();
                }
            }
        }
    }
    public void plantCreateByKeyBoard(int tileNum){
        isPlantTest = false;
        if(!isForbidden){
            if(!playing.getBarManager().getIsPlantInCD()[playing.getBarManager().getPlantPickedID().get(playing.getBarManager().getPlantPickedID().size()-1)]){
                for(int i = 0;i<playing.getTileManager().getTiles().length;i++){
                    plantOnTile(playing.getTileManager().getTiles()[i],(int)playing.getTileManager().getTiles()[tileNum].getBound().getX(),(int)playing.getTileManager().getTiles()[tileNum].getBound().getY(),i);
                }
                if(playing.isStartWaveForCD() && isPlanted){
//                selected = false;
                    playing.getBarManager().setPlantLocked(false);
                    isPlanted = false;
                }
            } else if(selected && playing.getBarManager().getIsPlantInCD()[playing.getBarManager().getPlantPickedID().get(playing.getBarManager().getPlantPickedID().size()-1)]) {
                Audio.plantNotAvailable();
            }
        }
    }
    public void updateSunflower(){
        if(playing.isStartWaveForCD()){
            synchronized (plantList){
                Iterator<Plant> iterator = plantList.iterator();
                while (iterator.hasNext()){
                    Plant plant = iterator.next();
                    if(plant.getPlantID() == 0){
                        plant.sunCreatedBySunFlower(playing.getSunManager());
                    }
                }
            }
        }
    }

    public void setPlantDangered(Tile tile){
        Rectangle rPlant = tile.getBound();
        for(Plant plant:plantList){
            if(rPlant.contains(plant.getX(),plant.getY())){
                plant.setDangered(true);
            }
        }
    }
    public void setPlantIdle(Tile tile){
        Rectangle rPlant = tile.getBound();
        synchronized (plantList){
            Iterator<Plant> iterator = plantList.iterator();
            while (iterator.hasNext()){
                Plant plant = iterator.next();
                if(rPlant.contains(plant.getX(),plant.getY())){
                    if(plant.isDangered() && plant.getFrameCountAttack() == plant.getFrameCountAttackLimit()-1){
                        plant.setDangered(false);
                        plant.setFrameCountAttack(0);
                        System.out.println("plant ID "+plant.getPlantID()+" frame: "+plant.getFrameCountAttack());
                    }
                }
            }
        }
    }

    public List<Plant> getPlantList() {
        return plantList;
    }

    public void alertPlant(){
        for(int i = 0;i<playing.getTileManager().getTiles().length;i++){
            Rectangle r = playing.getTileManager().getTiles()[i].getBound();
            Iterator<Zombie> iterator = playing.getZombieManager().getZombies().iterator();
            while (iterator.hasNext()){
                Zombie zombie = iterator.next();
                if(r.contains(zombie.X()+50,zombie.Y()+70) && zombie.isAlived()){
                    if(i>=0 && i<9){
                        for(int j = 0;j < 9;j++){
                            setPlantDangered(playing.getTileManager().getTiles()[j]);
                        }
                    } else if(i >= 9 && i<18){
                        for(int j = 9;j < 18;j++){
                            setPlantDangered(playing.getTileManager().getTiles()[j]);
                        }
                    } else if(i>=18 && i<27){
                        for(int j = 18;j < 27;j++){
                            setPlantDangered(playing.getTileManager().getTiles()[j]);
                        }
                    } else if(i>=27 && i<36){
                        for(int j = 27;j < 36;j++){
                            setPlantDangered(playing.getTileManager().getTiles()[j]);
                        }
                    } else if (i >= 36 && i<45) {
                        for(int j = 36;j < 45;j++){
                            setPlantDangered(playing.getTileManager().getTiles()[j]);
                        }
                    }
                }
            }
        }
    }
    public void setPlantDangeredForTesting(){
        if(isPlantTest){
            for(int i = 4;i<=5;i++){
                plantList.get(i).setDangered(true);
            }
        }
    }
    public int isPlantAttack(int start, int end, TileManager tileManager, ZombieManager zombieManager){
        int tileStart;
        for(int i = start;i<end;i++){
            Rectangle r = tileManager.getTiles()[i].getBound();
            Iterator<Zombie> iterator = zombieManager.getZombies().iterator();
            while (iterator.hasNext()){
                Zombie zombie = iterator.next();
                Rectangle rZombie = new Rectangle((int)zombie.X(),(int)zombie.Y()+70,zombie.getWidth(),zombie.getHeight()-70);
                if(r.intersects(rZombie) && zombie.isAlived()){
                    tileStart = i+1;
                    return tileStart;
                }
            }
        }
        return start;
    }

    public void calmPlant(){
        for (int i = isPlantAttack(0,9,playing.getTileManager(),playing.getZombieManager()); i < 9; i++) {
            setPlantIdle(playing.getTileManager().getTiles()[i]);
        }

        for (int i = isPlantAttack(9,18,playing.getTileManager(),playing.getZombieManager()); i < 18; i++) {
            setPlantIdle(playing.getTileManager().getTiles()[i]);
        }

        for (int i = isPlantAttack(18,27,playing.getTileManager(),playing.getZombieManager()); i < 27; i++) {
            setPlantIdle(playing.getTileManager().getTiles()[i]);
        }

        for (int i = isPlantAttack(27,36,playing.getTileManager(),playing.getZombieManager()); i < 36; i++) {
            setPlantIdle(playing.getTileManager().getTiles()[i]);
        }

        for (int i = isPlantAttack(36,45,playing.getTileManager(),playing.getZombieManager()); i < 45; i++) {
            setPlantIdle(playing.getTileManager().getTiles()[i]);
        }
    }

    public boolean isPlantTest() {
        return isPlantTest;
    }

    public void plantAttack(){
        synchronized (plantList){
            for(Plant plant:plantList){
                if(plant.isDangered()){
                    if(plant.getPlantID() == 1){
                        if(plant.getFrameCountAttack() == 31){
                            playing.getProjectileManager().projectileCreated(plant);
                            plant.setFrameCountAttack(plant.getFrameCountAttack()+1);
                        }
                    } else if(plant.getPlantID() == 3){
                        if(plant.getFrameCountAttack() == 8){
                            playing.getProjectileManager().projectileCreated(plant);
                            plant.setFrameCountAttack(plant.getFrameCountAttack()+1);
                        }
                    }
                }
            }
        }
    }
    public void timeExplode(){
        synchronized (plantList){
            Iterator<Plant> iterator = plantList.iterator();
            while (iterator.hasNext()){
                Plant plant = iterator.next();
                if(plant.getPlantID() == 4){
                    if(plant.getFrameCountIdle() == 29){
                        if(!isPlantTest){
                            Audio.Explode();
                        }
                        plant.setPlantHP(0);
                        cherryExplode(plant.getX(),plant.getY());
                        plant.removePlant(plant,iterator,playing.getTileManager());
                        plant.setFrameCountIdle(0);
                        plant.setFrameCDIdle(0);
                    } else if(plant.getFrameCountIdle() == 0){
                        Audio.prepareToExplode();
                    }
                }
            }
        }
    }
    public void cherryExplode(int x, int y){
        Rectangle explodeRange = new Rectangle(x-120,y-130,300,330);
        explosionX = explodeRange.getX();
        explosionY = explodeRange.getY();
        explosionWidth = explodeRange.getWidth();
        explosionHeight = explodeRange.getHeight();
        synchronized (playing.getZombieManager().getZombies()){
            Iterator<Zombie> iterator = playing.getZombieManager().getZombies().iterator();
            while (iterator.hasNext()){
                Zombie zombie = iterator.next();
                if(explodeRange.contains(zombie.getBound().getX(),zombie.getBound().getY()+70)){
                    zombie.dead();
                }
            }
        }
        isExploded = true;
    }
    private double explosionX;
    private double explosionY;
    private double explosionWidth;
    private double explosionHeight;
    private boolean isExploded = false;
    private int explosionTime = 0;
    public void drawExplosion(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        Image Explosion = t.getImage(getClass().getResource("/Event/Powie.png"));
        explosionTime++;
        if(explosionTime<60 && isExploded){
            g2d.drawImage(Explosion,(int)explosionX,(int)explosionY,(int)explosionWidth,(int)explosionHeight,null);
        } else {
            isExploded = false;
            explosionTime = 0;
        }
    }
    public void draw(Graphics g){
        drawPlant(g);
        drawExplosion(g);
    }

    public boolean isShoveled() {
        return isShoveled;
    }

    public void setShoveled(boolean shoveled) {
        isShoveled = shoveled;
    }

    public void removePlantByShovel(int x, int y){
        synchronized (plantList){
            for(int i = 0;i < playing.getTileManager().getTiles().length;i++){
                if(isShoveled){
                    if(playing.getTileManager().getTiles()[playing.getMouseMotionManager().getTileSelectedByMouse()].getBound().contains(x,y)){
                        Iterator<Plant> iterator = plantList.iterator();
                        while (iterator.hasNext()){
                            Plant plant = iterator.next();
                            Rectangle plantRect = new Rectangle(plant.getX(),plant.getY(),plant.getWidth(),plant.getHeight());
                            if(playing.getTileManager().getTiles()[playing.getMouseMotionManager().getTileSelectedByMouse()].isOccupied() && playing.getTileManager().getTiles()[playing.getMouseMotionManager().getTileSelectedByMouse()].getBound().contains(plant.getX(),plant.getY())){
                                playing.getTileManager().getTiles()[playing.getMouseMotionManager().getTileSelectedByMouse()].setOccupied(false);
                                playing.getTileManager().getTiles()[playing.getMouseMotionManager().getTileSelectedByMouse()].setPlanted(false);
                                iterator.remove();
                                isShoveled = false;
                            }
                        }
                    }
                }
            }
        }
    }
}
