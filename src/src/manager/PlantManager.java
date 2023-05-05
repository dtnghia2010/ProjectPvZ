package manager;


import Audio.Audio;
import component.Plant;
import component.Tile;
import scenes.Playing;
import zombie.Zombie;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlantManager {
    private Image[] plantImages;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private List<Plant> plantList = new ArrayList<>();


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
    private int IDhold, HPhold, ATKhold;
//    public void countTile(){
//        for(int i =0;i<tileManager.getTiles().length;i++){
//            System.out.println(tileManager.getTiles()[i].getBound().getX());
//            System.out.println(tileManager.getTiles()[i].getBound().getY());
//        }
//    }


    public PlantManager(Playing playing) {
        this.playing = playing;
        importImg();
    }

    public void initPlants(int plantID,int plantHP, int ATK) {
        plantList.add(new Plant(plantHP, plantID,ATK));
    }

    public void setIDhold(int IDhold) {
        this.IDhold = IDhold;
    }

    public void setHPhold(int HPhold) {
        this.HPhold = HPhold;
    }

    public int getIDhold() {
        return IDhold;
    }

    public int getHPhold() {
        return HPhold;
    }

    public int getATKhold() {
        return ATKhold;
    }

    public void setATKhold(int ATKhold) {
        this.ATKhold = ATKhold;
    }
    private void importImg() {
        plantImages = new Image[5];
        try {
            plantImages[0] = t.getImage(getClass().getResource("/plant/sunflower.png"));
            plantImages[1] = t.getImage(getClass().getResource("/plant/pea_shooter.png"));
            plantImages[2] = t.getImage(getClass().getResource("/plant/Wall-nut.png"));
            plantImages[3] = t.getImage(getClass().getResource("/plant/snow_pea_shooter.png"));
            plantImages[4] = t.getImage(getClass().getResource("/plant/cherry_bomb.gif"));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error - importImage()");
        }
    }

    public void drawPlant(Graphics g){
        synchronized (plantList){
            Iterator<Plant> iterator = plantList.iterator();
            while (iterator.hasNext()){
                Plant pl = iterator.next();
                if (pl.getPlantID() == 0){
                    g.drawImage(plantImages[0], (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getX(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getY(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getWidth(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getHeight(), null);
                } else if (pl.getPlantID() == 1){
                    g.drawImage(plantImages[1], (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getX(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getY(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getWidth(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getHeight(), null);
                } else if (pl.getPlantID() == 2){
                    g.drawImage(plantImages[2], (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getX(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getY(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getWidth(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getHeight(), null);
                } else if (pl.getPlantID() == 3){
                    g.drawImage(plantImages[3], (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getX(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getY(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getWidth(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getHeight(), null);
                } else if (pl.getPlantID() == 4){
                    g.drawImage(plantImages[4], (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getX(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getY(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getWidth(), (int)playing.getTileManager().getTiles()[pl.getTileHold()].getBound().getHeight(), null);
                }
            }
        }
//        synchronized (plantList){
//            for (Plant pl : plantList){
//                if (pl.getPlantID() == 0){
//                    g.drawImage(plantImages[0], (int)tileManager.getTiles()[pl.getTileHold()].getBound().getX(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getY(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getWidth(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getHeight(), null);
//                } else if (pl.getPlantID() == 1){
//                    g.drawImage(plantImages[1], (int)tileManager.getTiles()[pl.getTileHold()].getBound().getX(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getY(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getWidth(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getHeight(), null);
//                } else if (pl.getPlantID() == 2){
//                    g.drawImage(plantImages[2], (int)tileManager.getTiles()[pl.getTileHold()].getBound().getX(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getY(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getWidth(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getHeight(), null);
//                } else if (pl.getPlantID() == 3){
//                    g.drawImage(plantImages[3], (int)tileManager.getTiles()[pl.getTileHold()].getBound().getX(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getY(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getWidth(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getHeight(), null);
//                } else if (pl.getPlantID() == 4){
//                    g.drawImage(plantImages[4], (int)tileManager.getTiles()[pl.getTileHold()].getBound().getX(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getY(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getWidth(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getHeight(), null);
//                }
//            }
//        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void mouse(int x, int y){
        if(selected && !playing.getBarManager().getIsPlantInCD()[playing.getBarManager().getPlantPickedID()]){
            for (int i = 0; i < playing.getTileManager().getTiles().length; i++){
                if(!playing.getTileManager().getTiles()[i].isOccupied()){
                    Rectangle r = new Rectangle((int)playing.getTileManager().getTiles()[i].getBound().getX(), (int)playing.getTileManager().getTiles()[i].getBound().getY(), (int)playing.getTileManager().getTiles()[i].getBound().getWidth(), (int)playing.getTileManager().getTiles()[i].getBound().getHeight());
                    if (r.contains(x, y)){
                        Audio.tapGrass();
                        playing.getTileManager().getTiles()[i].setOccupied(true);
                        initPlants(IDhold,HPhold,ATKhold);
                        playing.getBarManager().setIsPlantInCD(playing.getBarManager().getPlantPickedID(),true);
                        for (int j = 0; j < plantList.size(); j++){
                            plantList.get(plantList.size() - 1).setTileHold(i);
                            if(!playing.getTileManager().getTiles()[i].isPlanted()){
                                plantList.get(plantList.size() - 1).setX(r.x);
                                plantList.get(plantList.size() - 1).setY(r.y);
                                plantList.get(plantList.size() - 1).setWidth(r.width);
                                plantList.get(plantList.size() - 1).setHeight(r.height);
                                if(plantList.get(plantList.size()-1).getPlantID() == 4){
                                    plantList.get(plantList.size()-1).setExplodeCD(110);
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
        if(selected && !playing.getBarManager().getIsPlantInCD()[playing.getBarManager().getPlantPickedID()]){
            for(int i = 0;i<playing.getTileManager().getTiles().length;i++){
                if(!playing.getTileManager().getTiles()[i].isOccupied() && i == playing.getTileManager().getTileSelectedByKeyBoard()){
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
                            if(plantList.get(plantList.size()-1).getPlantID() == 4){
                                plantList.get(plantList.size()-1).setExplodeCD(120);
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
                if(r.contains(zombie.X(),zombie.Y()+70)){
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
    public boolean isPlantAttack(int start, int end, TileManager tileManager, ZombieManager zombieManager){
        for(int i = start;i<end;i++){
            Rectangle r = tileManager.getTiles()[i].getBound();
            Iterator<Zombie> iterator = zombieManager.getZombies().iterator();
            while (iterator.hasNext()){
                Zombie zombie = iterator.next();
                Rectangle rZombie = new Rectangle((int)zombie.X(),(int)zombie.Y()+70,zombie.getWidth(),zombie.getHeight()-70);
                if(r.intersects(rZombie)){
                    return true;
                }
            }
        }
        return false;
    }

    public void calmPlant(TileManager tileManager, ZombieManager zombieManager){
        if (!isPlantAttack(0,9,tileManager,zombieManager)){
            for(int i = 0;i<9;i++){
                setPlantIdle(tileManager.getTiles()[i]);
            }
        }
        if (!isPlantAttack(9,18,tileManager,zombieManager)){
            for(int i = 9;i<18;i++){
                setPlantIdle(tileManager.getTiles()[i]);
            }
        }
        if (!isPlantAttack(18,27,tileManager,zombieManager)){
            for(int i = 18;i<27;i++){
                setPlantIdle(tileManager.getTiles()[i]);
            }
        }
        if (!isPlantAttack(27,36,tileManager,zombieManager)){
            for(int i = 27;i<36;i++){
                setPlantIdle(tileManager.getTiles()[i]);
            }
        }
        if (!isPlantAttack(36,45,tileManager,zombieManager)){
            for(int i = 36;i<45;i++){
                setPlantIdle(tileManager.getTiles()[i]);
            }
        }
    }
    public void plantAttack(ProjectileManager projectileManager){
//        for (int i = 0;i<plantList.size();i++){
//            if(plantList.get(i).isDangered()){
//                projectileManager.projectileCreated(plantList.get(i));
//            }
//        }
        if(projectileManager.getRealTimeCounter() == 90){
            synchronized (plantList){
                for(Plant plant:plantList){
                    if(plant.isDangered()){
                        projectileManager.projectileCreated(plant);
                    }
                }
            }
            projectileManager.isResetTime();
        }
    }
    public void timeExplode(){
        synchronized (plantList){
            Iterator<Plant> iterator = plantList.iterator();
            while (iterator.hasNext()){
                Plant plant = iterator.next();
                if(plant.getPlantID() == 4){
                    plant.setExplodeCD(plant.getExplodeCD()-1);
                    System.out.println(plant.getExplodeCD());
                    if(plant.getExplodeCD() == 0){
                        cherryExplode(plant.getX(), plant.getY());
                        iterator.remove();
                    }
                }
            }
        }
    }
    public void cherryExplode(int x, int y){
        Rectangle explodeRange = new Rectangle(x-90,y-70,240,210);
        synchronized (playing.getZombieManager().getZombies()){
            Iterator<Zombie> iterator = playing.getZombieManager().getZombies().iterator();
            while (iterator.hasNext()){
                Zombie zombie = iterator.next();
                if(explodeRange.contains(zombie.X(),zombie.Y())){
                    iterator.remove();
                }
            }
        }
    }
    public void drawExplosion(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

    }

}
