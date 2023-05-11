package scenes;

import Audio.Audio;
import manager.*;
import component.MyButtons;
import notification.PlayingNotif;

import static scenes.GameScenes.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Playing implements SceneMethods {
    private TileManager tileManager;
    private BarManager barManager;
    private PlantManager plantManager;
    private ButtonManager buttonManager;
    private ProjectileManager projectileManager;
    private manager.sunManager sunManager;
    private ZombieManager zombieManager;
    private WaveManager waveManager;
    private KeyBoardManager keyBoardManager;
    private MouseMotionManager mouseMotionManager;
    private boolean startWave = false;
    private NotifManager notifManager;
    private boolean startWave = false, callHorde = false, zombieApproaching = false;
    private boolean startWaveForCD = false;
    private World w;
    private Toolkit t = Toolkit.getDefaultToolkit();

    public Playing(World w) {
        this.w = w;
        initComponents();
        initObjects();
        initEvents();
        initNotifs();
    }

    private void initNotifs() {
        notifManager = new NotifManager(this);
    }

    private void initEvents() {
        waveManager = new WaveManager(this);
    }

    private void initObjects() {
        zombieManager = new ZombieManager(this);
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

    private void initComponents() {
        barManager = new BarManager(this);
        tileManager = new TileManager(this);
        buttonManager = new ButtonManager(this);
        tileManager = new TileManager(this);
        buttonManager = new ButtonManager();
        plantManager = new PlantManager(this);
        projectileManager = new ProjectileManager(this);
        sunManager = new sunManager(this);
        keyBoardManager = new KeyBoardManager(this);
        mouseMotionManager = new MouseMotionManager(this);
        projectileManager = new ProjectileManager(this);
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public void update(){
        plantManager.alertPlant(tileManager,zombieManager);
        plantManager.calmPlant(tileManager,zombieManager);
        plantManager.update();
        plantManager.updateSunflower();
        plantManager.timeExplode();
//        projectileManager.projectileCreated(plantManager);
        plantManager.plantAttack(projectileManager);
        projectileManager.update();
        projectileManager.projectileCollideZombie(zombieManager);
        barManager.update();
        barManager.plantEnoughSun();
        sunManager.update(this);
    }
    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img, 0, 0, w.getWidth(), w.getHeight(), null);
        buttonManager.drawButtons(g);
        tileManager.drawTiles(g, plantManager);
        barManager.drawPlantbar(g);
        barManager.drawPlantInCD(g);
        barManager.drawPlantNotEnoughSun(g);
        mouseMotionManager.drawPlantSelectedByMouse(g);
        keyBoardManager.drawPlantSelectedByKeyBoard(g);
        barManager.drawPlantNotAvailableFromStart(g);
        tileManager.drawTileSelectedByKeyBoard(g);
        tileManager.drawTileSelectedByMouse(g);
        tileManager.drawPlantPreparedToPlanted(g);
        plantManager.drawPlant(g);
        plantManager.drawExplosion(g);
        zombieManager.draw(g);
        projectileManager.drawProjectile(g);
        sunManager.drawSun(g);
        notifManager.drawNotif(g);
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
        if (buttonManager.getbMenu().getBounds().contains(x, y)) {
            setGameScenes(MENU);
        } else if (buttonManager.getbQuit().getBounds().contains(x, y)) {
            setGameScenes(LOSE);
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
        for (MyButtons b : barManager.getPickPlant()) {
            if (b.getBounds().contains(x, y)) {
                Audio.tapPlantBar();
                System.out.println("You choose " + b.getText());
            }
        }
        for (MyButtons b2 : barManager.getPickPlant()) {
            if (b2.getBounds().contains(x, y)) {
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
                        barManager.peaShooter();
                    } else if (b2.getText().contains("Wall-nut")) {
                        barManager.wall_nut();
                    } else if (b2.getText().contains("Snow Pea")) {
                        barManager.shadowPea();
                    } else if (b2.getText().contains("Cherry Bomb")) {
                        barManager.cherryBomb();
                    }
                }
                barManager.setPlantLocked(true);
            }
        }
        sunManager.clickSun(x,y);
    }


    public void mousePressed(int x, int y) {

    }
    public void MousePress(){
        mouseMotionManager.returnToSelectPlantByMouse();
    }
    public boolean isStartWave() {
        return startWave;
    }

    public void mouseReleased(int x, int y) {
        plantManager.mouse(x, y);
    }
    public void mouseMove(int x, int y){
        mouseMotionManager.changeStatusToMouse(x,y,w);
        mouseMotionManager.mouseTrackPlantBar(x,y);
        tileManager.tileTrack(x,y);
    }


    public void setStartWave(boolean startWave) {
        this.startWave = startWave;
    }

    public void keyBoardPress(KeyEvent e){
        keyBoardManager.changeStatusToKeyBoard(e);
        keyBoardManager.keyBoardChoosePlant(e);
        keyBoardManager.keyBoardSelectPlant(e);
        tileManager.tileSelectedByKeyBoard(e);
        keyBoardManager.returnToSelectPlantByKeyBoard(e);
        keyBoardManager.startGame(e);
    }

    public void updates() {
        if(getNotifManager().isEndCDWave()) {
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
        System.out.println("click on start");
        waveManager.readyNewWave();
        notifManager.reset();
        notifManager.resetEndCDWave();
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

