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
    private ZombieManager zombieManager;
    private WaveManager waveManager;
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
        notifManager.setNotif(new PlayingNotif(0));
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

    private void initComponents() {
        barManager = new BarManager(this);
        tileManager = new TileManager(this);
        buttonManager = new ButtonManager(this);
        plantManager = new PlantManager(this);
        projectileManager = new ProjectileManager(this);
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public void update() {
        plantManager.alertPlant(tileManager, zombieManager);
        plantManager.calmPlant(tileManager, zombieManager);
//        projectileManager.projectileCreated(plantManager);
        plantManager.plantAttack(projectileManager);
        projectileManager.update();
        projectileManager.projectileCollideZombie(zombieManager);
        barManager.update();
    }

    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img, 0, 0, w.getWidth(), w.getHeight(), null);
        buttonManager.drawButtons(g);
        tileManager.drawTiles(g, plantManager);
        barManager.drawPlantbar(g);
        barManager.drawPlantInCD(g);
        plantManager.drawPlant(g);
        zombieManager.draw(g);
        projectileManager.drawProjectile(g);
        notifManager.drawNotif(g);
    }

    public PlantManager getPlantManager() {
        return plantManager;
    }

    public BarManager getBarManager() {
        return barManager;
    }

    public void mouseClicked(int x, int y) {
        if (buttonManager.getbMenu().getBounds().contains(x, y)) {
            setGameScenes(MENU);
        } else if (buttonManager.getbQuit().getBounds().contains(x, y)) {
            setGameScenes(LOSE);
        } else if (buttonManager.getbStart().getBounds().contains(x, y)) {
            if (!startWave && zombieManager.allZombieDead()) {
                startWave = true;
                callHorde = false;
                startWaveForCD = true;
                System.out.println("click on start");
                waveManager.readyNewWave();
                notifManager.reset();
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
                if (b2.getText().contains("Sunflower")) {
                    barManager.sunFlower();
                } else if (b2.getText().contains("Peashooter")) {
                    barManager.peaShooter();
                } else if (b2.getText().contains("Wall-nut")) {
                    barManager.wall_nut();
                } else if (b2.getText().contains("Snow Pea")) {
                    barManager.snowPea();
                } else if (b2.getText().contains("Cherry Bomb")) {
                    barManager.cherryBomb();
                }
            }
        }

    }

    public void mousePressed(int x, int y) {

    }

    public void mouseReleased(int x, int y) {
        plantManager.mouse(x, y);
    }

    public void keyBoardPress(KeyEvent e) {
        barManager.keyBoardChoosePlant(e);
        barManager.keyBoardSelectPlant(e);
        barManager.tileSelectedByKeyBoard(e);
    }

    public void updates() {
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

    public WaveManager getWaveManager() {
        return waveManager;
    }

    public ZombieManager getZombieManager() {
        return zombieManager;
    }

    public boolean isStartWave() {
        return startWave;
    }

    public boolean isZombieApproaching() {
        return zombieApproaching;
    }
}

