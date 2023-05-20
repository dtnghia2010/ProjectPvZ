package scenes;

import Audio.Audio;
import manager.*;
import component.MyButtons;

import static scenes.GameScenes.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Playing implements SceneMethods {
    private TileManager tileManager;
    private BarManager barManager;
    private PlantManager plantManager;
    private ButtonManager buttonManager;
    private ProjectileManager projectileManager;
    private sunManager sunManager;
    private ZombieManager zombieManager;
    private WaveManager waveManager;
    private KeyBoardManager keyBoardManager;
    private MouseMotionManager mouseMotionManager;
    private NotifManager notifManager;
    private boolean startWave = false, callHorde = false, zombieApproaching = false;
    private boolean startWaveForCD = false;
    private World w;
    private Toolkit t = Toolkit.getDefaultToolkit();

    public Playing(World w) {
        this.w = w;
        initManagers();
    }

    private void initManagers() {
        buttonManager = new ButtonManager(this);
        mouseMotionManager = new MouseMotionManager(this);
        //singleton application
        waveManager = WaveManager.createWaveManager(this);
        notifManager = NotifManager.createNotifManager(this);
        zombieManager = ZombieManager.createZombieManager(this);
        barManager = BarManager.createBar(this);
        tileManager = TileManager.createTileManager(this);
        plantManager = PlantManager.createPlantManager(this);
        projectileManager = ProjectileManager.createProjectileManager(this);
        sunManager = manager.sunManager.createSunManager(this);
        keyBoardManager = KeyBoardManager.createKeyBoardManager(this);
    }
    public boolean isStartWaveForCD() {
        return startWaveForCD;
    }

    public manager.sunManager getSunManager() {
        return sunManager;
    }

    public KeyBoardManager getKeyBoardManager() {
        return keyBoardManager;
    }

    public MouseMotionManager getMouseMotionManager() {
        return mouseMotionManager;
    }

    public TileManager getTileManager() {
        return tileManager;
    }
    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img, 0, 0, w.getWidth(), w.getHeight(), null);
        buttonManager.drawButtons(g);
//        tileManager.drawTiles(g, plantManager);
        barManager.draw(g);
        mouseMotionManager.drawPlantSelectedByMouse(g);
        keyBoardManager.drawPlantSelectedByKeyBoard(g);
        tileManager.draw(g);
        plantManager.draw(g);
        zombieManager.draw(g);
        projectileManager.drawProjectile(g);
        sunManager.drawSun(g);
        notifManager.drawNotif(g);
        buttonManager.drawImg(g);
    }
    public PlantManager getPlantManager() {
        return plantManager;
    }

    public BarManager getBarManager() {
        return barManager;
    }

    public void setWaveManager(WaveManager waveManager) {
        this.waveManager = waveManager;
    }

    public void setStartWaveForCD(boolean startWaveForCD) {
        this.startWaveForCD = startWaveForCD;
    }

    public ProjectileManager getProjectileManager() {
        return projectileManager;
    }

    public void mouseClicked(int x, int y) {
        changeScene(x,y);
        choosePlant(x,y);
        sunManager.clickSun(x,y);
    }


    public void changeScene(int x, int y){
        if (buttonManager.getbSetting().getBounds().contains(x, y)) {
            setGameScenes(SETTING);
        } else if (buttonManager.getbStart().getBounds().contains(x, y)) {
            if (!startWave && zombieManager.allZombieDead()) {
                startGame();
/*                startWave = true;
                callHorde = false;
                startWaveForCD = true;
                System.out.println("click on start");
                waveManager.readyNewWave();
                notifManager.reset();*/
            }
        }
    }
    public void choosePlant(int x, int y){
        for (MyButtons b2 : barManager.getPickPlant()) {
            if (b2.getBounds().contains(x, y)) {
                System.out.println("You choose " + b2.getText());
                Audio.tapPlantBar();
                plantManager.setSelected(true);
                if(!barManager.isPlantLocked()){
                    if (b2.getText().contains("Sunflower")) {
                        if(!isStartWaveForCD()){
                            plantManager.plantForbiddenFromStart();
                        } else {
                            plantManager.setForbidden(false);
                            barManager.sunFlower();
                        }
                    } else if (b2.getText().contains("Peashooter")) {
                        plantManager.setForbidden(false);
                        barManager.peaShooter();
                    } else if (b2.getText().contains("Wall-nut")) {
                        plantManager.setForbidden(false);
                        barManager.wall_nut();
                    } else if (b2.getText().contains("Shadow peashooter")) {
                        plantManager.setForbidden(false);
                        barManager.shadowPea();
                    } else if (b2.getText().contains("Cherry Bomb")) {
                        plantManager.setForbidden(false);
                        barManager.cherryBomb();
                    } else if(b2.getText().contains("Shovel")){
                        plantManager.setSelected(false);
                        plantManager.setShoveled(true);
                    }
                }
                barManager.setPlantLocked(true);
            }
        }
    }

    public void mousePressed(int x, int y) {

    }
    public void MousePress(){
        mouseMotionManager.returnToSelectPlantByMouse();
    }
    public void mouseReleased(int x, int y) {
        plantManager.mouse(x, y);
        plantManager.removePlantByShovel(x,y);
    }
    public void mouseMove(int x, int y){
        mouseMotionManager.changeStatusToMouse(x,y,w);
        mouseMotionManager.mouseTrackPlantBar(x,y);
        mouseMotionManager.tileTrack(x,y);
    }


    public void setStartWave(boolean startWave) {
        this.startWave = startWave;
    }

    public void keyBoardPress(KeyEvent e){
        keyBoardManager.changeStatusToKeyBoard(e);
        keyBoardManager.keyBoardChoosePlant(e);
        keyBoardManager.keyBoardSelectPlant(e);
        keyBoardManager.tileSelectedByKeyBoard(e);
        keyBoardManager.plant(e);
        keyBoardManager.removePlantUsingKeyBoard(e);
//        keyBoardManager.returnToSelectPlantByKeyBoard(e);
        keyBoardManager.startGame(e);
    }
    public void setupZombie(){
        if(zombieManager.iszReachedEnd()) {
//            setGameScenes(LOSE);
        }
        if(getNotifManager().getWaveCDTime().isEndCDWave()) {
            System.out.println("startGame");
            startGame();
        }
        if (startWave) {
            if (isTimeForNewZombie()) {
                spawnZombie();
            }
            if (waveManager.hordeDead() && callHorde == true) {
                startWave = false;
            }
            if (zombieManager.allZombieDead() && !callHorde) {
                zombieManager.getZombies().clear();
                if (waveManager.isEndWaves()) {
                    System.out.println("you win");
                } else {
                    waveManager.createHorde();
                    callHorde = true;
                    zombieApproaching = true;
                }
//                notifManager.setNotif(new PlayingNotif(0));
            }
        }
    }
    public void updates() {
        setupZombie();
        plantManager.update();
        projectileManager.update();
        barManager.update();
        sunManager.update(this);
        waveManager.updates();
        zombieManager.updates();
        zombieManager.ZombieCollidePlant();
    }

    private void spawnZombie() {
        zombieManager.spawnZombie(waveManager.getNextZombie());
    }

    private boolean isTimeForNewZombie() {
        if (waveManager.isTimeForNewZombie()) {
            if (waveManager.isThereMoreZombieInWave()) {
                return true;
            }
        }
        return false;
    }

    private void startGame() {
        startWave = true;
        callHorde = false;
        startWaveForCD = true;
        plantManager.setSelected(false);
        plantManager.setForbidden(false);
        System.out.println("click on start");
        waveManager.readyNewWave();
        notifManager.reset();
//        notifManager.resetEndCDWave();
    }

    public WaveManager getWaveManager() {
        return waveManager;
    }

    public ZombieManager getZombieManager() {
        return zombieManager;
    }

    public NotifManager getNotifManager() {
        return notifManager;
    }

    public boolean isStartWave() {
        return startWave;
    }

    public boolean isZombieApproaching() {
        return zombieApproaching;
    }
}

