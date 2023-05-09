package scenes;

import Audio.Audio;
import manager.*;
import component.MyButtons;
import zombie.Zombie;

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
    private boolean startWave = false;
    private boolean startWaveForCD = false;
    private World w;
    private Toolkit t = Toolkit.getDefaultToolkit();

    public Playing(World w) {
        this.w = w;
        initComponents();
        initObjects();
        initEvents();
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

    private void initComponents() {
        barManager = new BarManager(this);
        tileManager = new TileManager(this);
        buttonManager = new ButtonManager();
        plantManager = new PlantManager(this);
        projectileManager = new ProjectileManager(this);
        sunManager = new sunManager();
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
        plantManager.plantAttack();
        projectileManager.update();
        projectileManager.projectileCollideZombie(zombieManager);
        barManager.update();
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
        barManager.drawPlantSelectedByMouse(g);
        barManager.drawPlantSelectedByKeyBoard(g);
        barManager.drawPlantNotAvailableFromStart(g);
        tileManager.drawTileSelectedByKeyBoard(g);
        tileManager.drawTileSelectedByMouse(g);
        plantManager.drawPlant(g);
        plantManager.drawExplosion(g);
        zombieManager.draw(g);
        projectileManager.drawProjectile(g);
        sunManager.drawSun(g);
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
            startWave = true;
            startWaveForCD = true;
            barManager.setPlantLocked(false);
            waveManager.readyNewWave();
            plantManager.setSelected(false);
            plantManager.setTimeToPlant(true);
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
        barManager.returnToSelectPlantByMouse();
    }
    public boolean isStartWave() {
        return startWave;
    }

    public void mouseReleased(int x, int y) {
        plantManager.mouse(x, y);
    }
    public void mouseMove(int x, int y){
        barManager.mouseTrackPlantBar(x,y);
        tileManager.tileTrack(x,y);
    }


    public void setStartWave(boolean startWave) {
        this.startWave = startWave;
    }

    public void keyBoardPress(KeyEvent e){
        barManager.keyBoardChoosePlant(e);
        barManager.keyBoardSelectPlant(e);
        tileManager.tileSelectedByKeyBoard(e);
        barManager.returnToSelectPlantByKeyBoard(e);
        barManager.startGame(e);
    }

    public void updates() {
        if(startWave) {
            if (isTimeForNewZombie()) {
                spawnZombie();
            }
            if(zombieManager.allZombieDead()) {
                startWave = false;
                zombieManager.getZombies().clear();
                createHorde();
                System.out.println("Zombies cleared");
            } else {
                zombieAtk();
            }
        }
        waveManager.updates();
        zombieManager.updates();
        zombieManager.ZombieCollidePlant();
    }

    private void zombieAtk() {
        for(Zombie z: zombieManager.getZombies()) {
            //TODO zombie attack plant
        }
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

    public WaveManager getWaveManager() {
        return waveManager;
    }

    public ZombieManager getZombieManager() {
        return zombieManager;
    }
    public void createHorde() {
        zombieManager.createHorde(30);
    }
}

