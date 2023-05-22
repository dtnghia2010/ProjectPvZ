package scenes;

import Audio.Audio;
import Projectile.ProjectileLogic;
import Projectile.ProjectileOfHouseOwner;
import Projectile.ProjectileOfPlant;
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
    private ProjectileOfPlant projectileOfPlant;
    private ProjectileOfHouseOwner projectileOfHouseOwner;
    private ZombieManager zombieManager;
    private WaveManager waveManager;
    private HouseOwnerManager houseOwnerManager;
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
    private void initComponents() {
        barManager = new BarManager(this);
        tileManager = new TileManager();
        buttonManager = new ButtonManager();
        plantManager = new PlantManager(this);
//        projectileManager = new ProjectileLogic();
        projectileOfHouseOwner = new ProjectileOfHouseOwner();
        projectileOfPlant = new ProjectileOfPlant();
        houseOwnerManager = new HouseOwnerManager(this);
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public void update(){
        plantManager.alertPlant(tileManager,zombieManager);
        plantManager.calmPlant(tileManager,zombieManager);
//      projectileManager.projectileCreated(plantManager);
        plantManager.plantAttack(projectileOfPlant);
        projectileOfPlant.update();
        projectileOfHouseOwner.update();
        projectileOfHouseOwner.projectileCollideZombie(zombieManager);
        projectileOfPlant.projectileCollideZombie(zombieManager);
        barManager.update();
        houseOwnerManager.alertHouseOwner(tileManager, zombieManager);
        houseOwnerManager.houseOwnerAttack(projectileOfHouseOwner,zombieManager);
    }
    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img, 0, 0, w.getWidth(), w.getHeight(), null);
        buttonManager.drawButtons(g);
        tileManager.drawTiles(g, plantManager);
        tileManager.drawTiles(g, houseOwnerManager);
        barManager.drawPlantbar(g);
        barManager.drawPlantInCD(g);
        zombieManager.draw(g);
        plantManager.drawPlant(g);
//        projectileManager.drawProjectile(g);
        projectileOfPlant.drawProjectile(g);
        projectileOfHouseOwner.drawProjectile(g);
        houseOwnerManager.draw(g);

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
            startWave = true;
            startWaveForCD = true;
            waveManager.readyNewWave();
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
        houseOwnerManager.mouseClicked(x,y);
    }
    public void keyBoardPress(KeyEvent e){
        barManager.keyBoardChoosePlant(e);
        barManager.keyBoardSelectPlant(e);
        barManager.tileSelectedByKeyBoard(e);
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
        zombieManager.createHorde(10);
    }


}

