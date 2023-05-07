package manager;


import Audio.Audio;
import Plant.Plant;
import component.Tile;
import scenes.Playing;
import zombie.Zombie;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlantManager {
    private final Image[] sunFlower = new Image[26];
    private final Image[] peaShooter_idle = new Image[59];
    private final Image[] peaShooter_attack = new Image[59];
    private final Image[] wall_nut = new Image[82];
    private final Image[] shadowPea_Idle = new Image[23];
    private final Image[] shadowPea_Attack = new Image[21];
    private final Image[] cherryBombGif = new Image[49];
    private Toolkit t = Toolkit.getDefaultToolkit();
    private List<Plant> plantList = new ArrayList<>();
    private boolean[] isRowInDanger = new boolean[5];

    public boolean isTimeToPlant() {
        return isTimeToPlant;
    }

    public void setTimeToPlant(boolean timeToPlant) {
        isTimeToPlant = timeToPlant;
    }
    private boolean isTimeToPlant = true;
    public boolean isSelected() {
        return selected;
    }
    private Playing playing;
    private boolean selected = false;
    private int IDhold, HPhold, ATKhold, frameCountLimitHold;

    public PlantManager(Playing playing) {
        this.playing = playing;
        importCherryBombGif();
        importPeaShooter();
        importSunFlower();
        importWallNut();
        importShadowPea();
    }

    public void setFrameCountLimitHold(int frameCountLimitHold) {
        this.frameCountLimitHold = frameCountLimitHold;
    }

    public void initPlants(int plantID, int plantHP, int ATK, int frameCount) {
        plantList.add(new Plant(plantHP, plantID,ATK,frameCount));
    }

    public void setIDhold(int IDhold) {
        this.IDhold = IDhold;
    }
    public void setHPhold(int HPhold) {
        this.HPhold = HPhold;
    }
    public void setATKhold(int ATKhold) {
        this.ATKhold = ATKhold;
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
    public void updateFrameCountIdle(Plant plant){
        plant.setFrameCDIdle(plant.getFrameCDIdle()+1);
        if(plant.getFrameCDIdle()%4 == 0){
            plant.setFrameCountIdle(plant.getFrameCountIdle()+1);
        }
    }
    public void updateFrameCountAttack(Plant plant){
        plant.setFrameCDAttack(plant.getFrameCDAttack()+1);
        if(plant.getFrameCDAttack()%5 == 0){
            plant.setFrameCountAttack(plant.getFrameCountAttack()+1);
        }
    }
    public void drawPlant(Graphics g){
        synchronized (plantList){
            Iterator<Plant> iterator = plantList.iterator();
            while (iterator.hasNext()){
                Plant pl = iterator.next();
                if (pl.getPlantID() == 0){
                    g.drawImage(sunFlower[pl.getFrameCountIdle()], (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getX(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getY(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getWidth(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getHeight(), null);
                    updateFrameCountIdle(pl);
                } else if (pl.getPlantID() == 1){
                    if(!pl.isDangered()){
                        g.drawImage(peaShooter_idle[pl.getFrameCountIdle()], (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getX(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getY(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getWidth(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getHeight(), null);
                        updateFrameCountIdle(pl);
                    } else {
                        g.drawImage(peaShooter_attack[pl.getFrameCountAttack()], (int) playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getX(), (int) playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getY(), (int) playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getWidth(), (int) playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getHeight(), null);
                        updateFrameCountAttack(pl);
                    }
                } else if (pl.getPlantID() == 2){
                    g.drawImage(wall_nut[pl.getFrameCountIdle()], (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getX(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getY(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getWidth(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getHeight(), null);
                    updateFrameCountIdle(pl);
                } else if (pl.getPlantID() == 3){
                    if(!pl.isDangered()){
                        g.drawImage(shadowPea_Idle[pl.getFrameCountIdle()], (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getX()-10, (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getY()-30, (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getWidth()+30, (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getHeight()+30, null);
                        updateFrameCountIdle(pl);
                    } else {
                        g.drawImage(shadowPea_Attack[pl.getFrameCountAttack()], (int) playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getX()-10, (int) playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getY()-30, (int) playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getWidth()+30, (int) playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getHeight()+30, null);
                        updateFrameCountAttack(pl);
                    }
                } else if (pl.getPlantID() == 4){
                    g.drawImage(cherryBombGif[pl.getFrameCountIdle()],(int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getX(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getY(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getWidth(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getHeight(), null);
                    updateFrameCountIdle(pl);
                }
            }
        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void mouse(int x, int y){
        if(selected && !playing.getBarManager().getIsPlantInCD()[playing.getBarManager().getPlantPickedID().get(playing.getBarManager().getPlantPickedID().size()-1)]){
            for (int i = 0; i < playing.getTileManager().getTiles().length; i++){
                if(!playing.getTileManager().getTiles()[i].isOccupied()){
                    Rectangle r = new Rectangle((int)playing.getTileManager().getTiles()[i].getBound().getX(), (int)playing.getTileManager().getTiles()[i].getBound().getY(), (int)playing.getTileManager().getTiles()[i].getBound().getWidth(), (int)playing.getTileManager().getTiles()[i].getBound().getHeight());
                    if (r.contains(x, y)){
                        Audio.tapGrass();
                        playing.getTileManager().getTiles()[i].setOccupied(true);
                        initPlants(IDhold,HPhold,ATKhold,frameCountLimitHold);
                        playing.getBarManager().setIsPlantInCD(playing.getBarManager().getPlantPickedID().get(playing.getBarManager().getPlantPickedID().size()-1),true);
                        for (int j = 0; j < plantList.size(); j++){
                            plantList.get(plantList.size() - 1).setTileHold(i);
                            if(!playing.getTileManager().getTiles()[i].isPlanted()){
                                plantList.get(plantList.size() - 1).setX(r.x);
                                plantList.get(plantList.size() - 1).setY(r.y);
                                plantList.get(plantList.size() - 1).setWidth(r.width);
                                plantList.get(plantList.size() - 1).setHeight(r.height);
                                if(plantList.get(plantList.size()-1).getPlantID() == 1){
                                    plantList.get(plantList.size()-1).setFrameCountAttackLimit(59);
                                } else if(plantList.get(plantList.size()-1).getPlantID() == 3){
                                    plantList.get(plantList.size()-1).setFrameCountAttackLimit(20);
                                }
                                playing.getTileManager().getTiles()[i].setPlanted(true);
                            }
                        }
                    }
                }
            }
        }
    }
    public void plantCreateByKeyBoard(){
        if(selected && !playing.getBarManager().getIsPlantInCD()[playing.getBarManager().getPlantPickedID().get(playing.getBarManager().getPlantPickedID().size()-1)]){
            for(int i = 0;i<playing.getTileManager().getTiles().length;i++){
                if(!playing.getTileManager().getTiles()[i].isOccupied() && i == playing.getTileManager().getTileSelectedByKeyBoard()){
                    Rectangle r = new Rectangle((int)playing.getTileManager().getTiles()[i].getBound().getX(), (int)playing.getTileManager().getTiles()[i].getBound().getY(), (int)playing.getTileManager().getTiles()[i].getBound().getWidth(), (int)playing.getTileManager().getTiles()[i].getBound().getHeight());
                    Audio.tapGrass();
                    playing.getTileManager().getTiles()[i].setOccupied(true);
                    initPlants(IDhold,HPhold,ATKhold,frameCountLimitHold);
                    playing.getBarManager().setIsPlantInCD(playing.getBarManager().getPlantPickedID().get(playing.getBarManager().getPlantPickedID().size()-1),true);
                    for (int j = 0; j < playing.getPlantManager().getPlantList().size(); j++){
                        playing.getPlantManager().getPlantList().get(playing.getPlantManager().getPlantList().size() - 1).setTileHold(i);
                        if(!playing.getTileManager().getTiles()[i].isPlanted()){
                            playing.getPlantManager().getPlantList().get(playing.getPlantManager().getPlantList().size() - 1).setX(r.x);
                            playing.getPlantManager().getPlantList().get(playing.getPlantManager().getPlantList().size() - 1).setY(r.y);
                            playing.getPlantManager().getPlantList().get(playing.getPlantManager().getPlantList().size() - 1).setWidth(r.width);
                            playing.getPlantManager().getPlantList().get(playing.getPlantManager().getPlantList().size() - 1).setHeight(r.height);
                            if(plantList.get(j).getPlantID() == 1){
                                plantList.get(i).setFrameCountAttackLimit(59);
                            } else if(plantList.get(i).getPlantID() == 3){
                                plantList.get(i).setFrameCountAttackLimit(20);
                            }
                            playing.getTileManager().getTiles()[i].setPlanted(true);
                        }
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
                    plant.setFrameCountAttack(0);
                    plant.setDangered(false);
                }
            }
        }
    }

    public List<Plant> getPlantList() {
        return plantList;
    }

    public void alertPlant(TileManager tileManager, ZombieManager zombieManager){
        for(int i = 0;i<tileManager.getTiles().length;i++){
            Rectangle r = tileManager.getTiles()[i].getBound();
            Iterator<Zombie> iterator = zombieManager.getZombies().iterator();
            while (iterator.hasNext()){
                Zombie zombie = iterator.next();
                if(r.contains(zombie.X()+50,zombie.Y()+70) && zombie.isAlived()){
                    if(i>=0 && i<9){
                        for(int j = 0;j < 9;j++){
                            setPlantDangered(tileManager.getTiles()[j]);
                        }
                    } else if(i >= 9 && i<18){
                        for(int j = 9;j < 18;j++){
                            setPlantDangered(tileManager.getTiles()[j]);
                        }
                    } else if(i>=18 && i<27){
                        for(int j = 18;j < 27;j++){
                            setPlantDangered(tileManager.getTiles()[j]);
                        }
                    } else if(i>=27 && i<36){
                        for(int j = 27;j < 36;j++){
                            setPlantDangered(tileManager.getTiles()[j]);
                        }
                    } else if (i >= 36 && i<45) {
                        for(int j = 36;j < 45;j++){
                            setPlantDangered(tileManager.getTiles()[j]);
                        }
                    }
                }
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

    public void calmPlant(TileManager tileManager, ZombieManager zombieManager){
        for (int i = isPlantAttack(0,9,tileManager,zombieManager); i < 9; i++) {
            setPlantIdle(tileManager.getTiles()[i]);
        }

        for (int i = isPlantAttack(9,18,tileManager,zombieManager); i < 18; i++) {
            setPlantIdle(tileManager.getTiles()[i]);
        }

        for (int i = isPlantAttack(18,27,tileManager,zombieManager); i < 27; i++) {
            setPlantIdle(tileManager.getTiles()[i]);
        }

        for (int i = isPlantAttack(27,36,tileManager,zombieManager); i < 36; i++) {
            setPlantIdle(tileManager.getTiles()[i]);
        }

        for (int i = isPlantAttack(36,45,tileManager,zombieManager); i < 45; i++) {
            setPlantIdle(tileManager.getTiles()[i]);
        }
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
                        Audio.Explode();
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
        Rectangle explodeRange = new Rectangle(x-100,y-120,250,300);
        explosionX = explodeRange.getX();
        explosionY = explodeRange.getY();
        explosionWidth = explodeRange.getWidth();
        explosionHeight = explodeRange.getHeight();
        synchronized (playing.getZombieManager().getZombies()){
            Iterator<Zombie> iterator = playing.getZombieManager().getZombies().iterator();
            while (iterator.hasNext()){
                Zombie zombie = iterator.next();
                if(explodeRange.contains(zombie.X(),zombie.Y()+70)){
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

}
