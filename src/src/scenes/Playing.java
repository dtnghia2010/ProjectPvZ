package scenes;

import manager.*;
import component.MyButtons;
import zombie.Zombie;

import static scenes.GameScenes.*;
import java.awt.*;

public class Playing implements SceneMethods {
    private TileManager tileManager;
    private BarManager barManager;
    private PlantManager plantManager;
    private ButtonManager buttonManager;
    private ProjectileManager projectileManager;
    private ZombieManager zombieManager;
    private WaveManager waveManager;
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
    }
    private void initComponents() {
        barManager = new BarManager();
        tileManager = new TileManager();
        buttonManager = new ButtonManager();
        plantManager = new PlantManager();
        projectileManager = new ProjectileManager();
    }
    public void update(){
        plantManager.alertPlant(tileManager,zombieManager);
        plantManager.calmPlant(tileManager,zombieManager);
//        projectileManager.projectileCreated(plantManager);
        plantManager.plantAttack(projectileManager);
        projectileManager.update();
        projectileManager.projectileCollideZombie(zombieManager);
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
        }
        for (MyButtons b : barManager.getPickPlant()) {
            if (b.getBounds().contains(x, y)) {
                System.out.println("You choose " + b.getText());
            }
        }
        for (MyButtons b2 : barManager.getPickPlant()) {
            if (b2.getBounds().contains(x, y)) {
                plantManager.setSelected(true);
                if (b2.getText().contains("Sunflower")) {
                    plantManager.sunFlower();
                } else if (b2.getText().contains("Peashooter")) {
                    plantManager.peaShooter();
                } else if (b2.getText().contains("Wall-nut")) {
                    plantManager.wall_nut();
                } else if (b2.getText().contains("Snow Pea")) {
                    plantManager.snowPea();
                } else if (b2.getText().contains("Cherry Bomb")) {
                    plantManager.cherryBomb();
                }
            }
        }

    }

    @Override
    public void mousePressed(int x, int y) {

    }

    public void mouseReleased(int x, int y) {
        plantManager.mouse(x, y);
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
        zombieManager.createHorde(45);
    }
}

