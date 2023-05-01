package scenes;

import manager.*;
import component.Tile;
import component.MyButtons;
import object.FakePlant;
import zombie.Zombie;
import java.util.List;

import static scenes.GameScenes.*;
import java.awt.*;

public class Playing implements SceneMethods {
    private TileManager tileManager;
    private BarManager barManager;
    private PlantManager plantManager;
    private ButtonManager buttonManager;
    private ProjectileManager projectileManager;
    private Zombie_fakeManager zombieFakeManager;
    private ZombieManager zombieManager;
    private WaveManager waveManager;
    private FakePlantManager fakePlantManager;
    private boolean startWave = false;
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
        fakePlantManager = new FakePlantManager(this);
    }
    private void initComponents() {
        barManager = new BarManager();
        tileManager = new TileManager();
        buttonManager = new ButtonManager();
        plantManager = new PlantManager();
        projectileManager = new ProjectileManager();
        zombieFakeManager = new Zombie_fakeManager();
    }
    public void update(){
        plantManager.alertPlant(tileManager,zombieFakeManager);
//        projectileManager.projectileCreated(plantManager);
        plantManager.plantAttack(projectileManager);
        projectileManager.update();
        projectileManager.projectileCollideZombie(zombieFakeManager);
    }
    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img, 0, 0, w.getWidth(), w.getHeight(), null);
        buttonManager.drawButtons(g);
        tileManager.drawTiles(g, plantManager);
        barManager.drawPlantbar(g);
        zombieManager.draw(g);
        plantManager.drawPlant(g);
        projectileManager.drawProjectile(g);
        zombieFakeManager.drawZombie(g);
    }

    public PlantManager getPlantManager() {
        return plantManager;
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

    public void mouseReleased(int x, int y) {
        plantManager.mouse(x, y);
    }
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

