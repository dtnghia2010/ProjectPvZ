package scenes;

import manager.*;
import component.MyButtons;

import static scenes.GameScenes.*;
import java.awt.*;

public class Playing implements SceneMethods {
    private TileManager tileManager;
    private BarManager barManager;
    private ButtonManager buttonManager;
    private ZombieManager zombieManager;
    private WaveManager waveManager;
    private boolean startWave = false;
    private World w;

    public Playing(World w) {
        this.w = w;
        initComponents();
        initObjects();
        initEvents();
        startGame();
    }

    private void initEvents() {
        waveManager = new WaveManager(this);
    }

    private void initObjects() {
        zombieManager = new ZombieManager(this);
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
        }
        for (MyButtons b : barManager.getPickPlant()) {
            if (b.getBounds().contains(x, y)) {
                System.out.println("You choose " + b.getText());
            }
        }
    }

    public void updates() {
        if(startWave) {
            if (isTimeForNewZombie()) {
//                if (waveManager.isNewWaveReady()) {
                    spawnZombie();
//                }
            }
            waveManager.updates();
            zombieManager.updates();
            if(zombieManager.allZombieDead()) {
                startWave = false;
                zombieManager.getZombies().clear();
                System.out.println("Zombies cleared");
            }
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
}
        public void updates() {
            zombieManager.updates();

        }
        public void startGame() {
            zombieManager.addRandomZombies(45);
        }
