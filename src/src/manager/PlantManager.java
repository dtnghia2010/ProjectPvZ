package manager;


import Audio.Audio;
import Plant.Plant;
import component.Tile;
import scenes.Playing;
import zombie.Zombie;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
    private List<Plant> plantListTest = new ArrayList<>();
    private boolean isPlantTest = true;
    private boolean isTimeToPlant = false;
    private Playing playing;
    private boolean selected = true;
    private boolean isPlanted = false;
    private boolean isForbidden = false;
    private boolean isShoveled = false;
    private boolean isPlantPlanted = false;
    private boolean isPlantRemoved = false;
    private int IDhold;
    private int sunCostHold;
    private int[] numPlant = new int[5];
    private Random random = new Random();
    private int plantSize;

    public boolean isPlantPlanted() {
        return isPlantPlanted;
    }

    public void setPlantPlanted(boolean plantPlanted) {
        isPlantPlanted = plantPlanted;
    }

    public boolean isPlantRemoved() {
        return isPlantRemoved;
    }

    public void setPlantRemoved(boolean plantRemoved) {
        isPlantRemoved = plantRemoved;
    }
    public void setNumPlant(){
        numPlant[0] = 0;
        numPlant[1] = 1;
        numPlant[2] = 2;
        numPlant[3] = 3;
        numPlant[4] = 4;
    }
    public PlantManager(Playing playing) {
        this.playing = playing;
        importCherryBombGif();
        importPeaShooter();
        importSunFlower();
        importWallNut();
        importShadowPea();
        plantForbiddenFromStart();
        initStorage();
        setNumPlant();
    }

    public void setSunCostHold(int sunCostHold) {
        this.sunCostHold = sunCostHold;
    }

    public void shiftPlant(Tile tile){
        for(int i = 0;i<plantSize;i++){
            if(plantList.get(i).getPlantID() == IDhold && i == numPlant[IDhold]){
                Rectangle r = tile.getBound();
                plantList.get(i).setX(r.x);
                plantList.get(i).setY(r.y);
                plantList.get(i).setWidth(r.width);
                plantList.get(i).setHeight(r.height);
                if(plantList.get(i).getPlantID() == 1){
                    plantList.get(i).setFrameCountAttackLimit(59);
                } else if(plantList.get(i).getPlantID() == 3){
                    plantList.get(i).setFrameCountAttackLimit(20);
                }
                plantList.get(i).setAlived(true);
                playing.getBarManager().setIsPlantInCD(plantList.get(i).getPlantID(),true);
                numPlant[IDhold] = numPlant[IDhold] +5;
                break;
            }
        }
        plantSize--;
    }
    public void initStorage(){
        for(int i = 0; i<200;i++){
            plantList.add(new Plant(100,0,0,25));
            plantList.add(new Plant(100,1,20,58));
            plantList.add(new Plant(1000,2,0,81));
            plantList.add(new Plant(100,3,20,20));
            plantList.add(new Plant(10000,4,1000,30));
        }
        plantSize = 999;
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
    public void update(){
        if(!isPlantTest){
            Iterator<Plant> iterator = plantList.iterator();
            while (iterator.hasNext()){
                Plant plant = iterator.next();
                plant.updateFrameCountIdle();
                if(plant.isAlived()){
                    if(plant.getPlantID() == 1 || plant.getPlantID() == 3){
                        plant.updateFrameCountAttack();
                    }
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

    public int getIDhold() {
        return IDhold;
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
                if(playing.getSunManager().getSunHold() >= sunCostHold){
                    Audio.tapGrass();
                    tile.setOccupied(true);
                    isPlantPlanted = true;
                    shiftPlant(tile);
                    for (int j = 0; j < plantList.size(); j++){
                        plantList.get(plantList.size() - 1).setTileHold(i);
                        if(!tile.isPlanted()){
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
        if(!isPlantPlanted && !isPlantRemoved){
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
                    }
                }
            }
        }
    }

    public List<Plant> getPlantList() {
        return plantList;
    }

    public void alertPlant(){
        if(!isPlantPlanted && !isPlantRemoved){
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
        if(!isPlantPlanted && !isPlantRemoved){
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
        Iterator<Plant> iterator = plantList.iterator();
        while (iterator.hasNext()){
            Plant plant = iterator.next();
            if(plant.isAlived()){
                if(plant.getPlantID() == 4){
                    if(plant.getFrameCountIdle() == 29){
                        if(!isPlantTest){
                            Audio.Explode();
                        }
                        plant.setPlantHP(0);
                        cherryExplode(plant.getX(),plant.getY());
                        plant.removePlant(plant,iterator,playing.getTileManager(),this);
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
            if(!isPlantPlanted && !isPlantRemoved){
                Iterator<Zombie> iterator = playing.getZombieManager().getZombies().iterator();
                while (iterator.hasNext()){
                    Zombie zombie = iterator.next();
                    if(explodeRange.contains(zombie.getBound().getX(),zombie.getBound().getY()+70)){
                        zombie.dead();
                    }
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
            if(!isPlantPlanted && !isPlantRemoved){
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
                                    isPlantRemoved = true;
                                    iterator.remove();
                                    isShoveled = false;
                                }
                            }
                        }
                    }
                }
            } else {
                isPlantPlanted = false;
                isPlantRemoved = false;
            }
        }
    }
}
