package scenes;

import component.Tile;
import manager.*;
import component.MyButtons;
import object.FakePlant;
import zombie.Zombie;

import static scenes.GameScenes.*;
import java.awt.*;

public class Playing implements SceneMethods {
    private TileManager tileManager;
    private BarManager barManager;
    private ButtonManager buttonManager;
    private ZombieManager zombieManager;
    private WaveManager waveManager;
    private FakePlantManager fakePlantManager;
    private boolean startWave = false;
    private World w;

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
        fakePlantManager = new FakePlantManager(this);
    }
    private void initComponents() {
        barManager = new BarManager();
        tileManager = new TileManager();
        buttonManager = new ButtonManager();
    }

    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img, 0, 0, w.getWidth(), w.getHeight(), null);
        buttonManager.drawButtons(g);
        tileManager.drawTiles(g);
        barManager.drawPlantbar(g);
        zombieManager.draw(g);
    }

    public void mouseClicked(int x, int y) {
        if (buttonManager.getbMenu().getBounds().contains(x, y)) {
            setGameScenes(MENU);
        } else if (buttonManager.getbQuit().getBounds().contains(x, y)) {
            setGameScenes(LOSE);
        } else if (buttonManager.getbStart().getBounds().contains(x, y)) {
            startWave = true;
            waveManager.readyNewWave();
        } else {
            for(Tile tl: tileManager.getTiles()) {
                if(tl.getBound().contains(x,y)) {
                    tl.setOccupied(true);
                    fakePlantManager.getPlant().setPlaced(true);
                    tl.setFakePlant(fakePlantManager.getPlant());
                }
            }
        }
        for (MyButtons b : barManager.getPickPlant()) {
            if (b.getBounds().contains(x, y)) {
                System.out.println("You choose " + b.getText());
            }
        }
    }

    @Override
    public void mousePressed(int x, int y) {
    }

    @Override
    public void mouseReleased(int x, int y) {

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
        zombieManager.createHorde(45);
    }
}

