package scenes;

import Audio.Audio;
import Projectile.ProjectileLogic;
import Projectile.ProjectileOfHouseOwner;
import Projectile.ProjectileOfPlant;
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
    private sunManager sunManager;
    private ProjectileOfPlant projectileOfPlant;
    private ProjectileOfHouseOwner projectileOfHouseOwner;
    private ZombieManager zombieManager;
    private WaveManager waveManager;
    private KeyBoardManager keyBoardManager;
    private MouseMotionManager mouseMotionManager;
    private NotifManager notifManager;
    private boolean startWave = false, callHorde = false, zombieApproaching = false;
    private HouseOwnerManager houseOwnerManager;
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
        plantManager = new PlantManager(this);
        sunManager = new sunManager(this);
        keyBoardManager = new KeyBoardManager(this);
        mouseMotionManager = new MouseMotionManager(this);
        projectileOfHouseOwner = new ProjectileOfHouseOwner();
        projectileOfPlant = new ProjectileOfPlant();
        houseOwnerManager = new HouseOwnerManager(this);
    }
    public ProjectileOfPlant getProjectileOfPlant() {
        return projectileOfPlant;
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
        //tileManager.drawTiles(g, houseOwnerManager);
        plantManager.draw(g);
        tileManager.drawShovelSprite(g);
        zombieManager.draw(g);
        sunManager.drawSun(g);
        notifManager.drawNotif(g);
        buttonManager.drawImg(g);
        projectileOfPlant.drawProjectile(g);
        houseOwnerManager.draw(g);
        projectileOfHouseOwner.drawProjectile(g);
        projectileOfPlant.drawProjectile(g);
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
        houseOwnerManager.mouseClicked(x,y);
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
    }
    public void updates() {
//        System.out.println("1");
        setupZombie();
        plantManager.update();
        barManager.update();
        sunManager.update(this);
        waveManager.updates();
        zombieManager.updates();
        zombieManager.ZombieCollidePlant();
        projectileOfPlant.update(this);
        projectileOfHouseOwner.update(this);
        projectileOfHouseOwner.projectileCollideZombie(this);
        projectileOfPlant.projectileCollideZombie(this);
        houseOwnerManager.alertHouseOwner(tileManager, zombieManager);
        houseOwnerManager.houseOwnerAttack(projectileOfHouseOwner,zombieManager);
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
        notifManager.resetEndCDWave();
    }

    public void setCallHorde(boolean callHorde) {
        this.callHorde = callHorde;
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

