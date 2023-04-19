package scene;

import manager.WaveManager;
import manager.ZombieManager;
import object.Zombie;
import tile.Tile;
import ui.MyButton;
import object.Plant;
import static scene.GameStates.*;
import javax.swing.*;
import java.awt.*;
import java.util.ListIterator;

public class Playing implements SceneMethods{
    private int wTile = 70, hTile = 80;
    private JPanel panel;
    private MyButton bMenu, bPlantChoice[];
    private Image[] plantChoiceImg;
    private Plant[] plants;
    private Tile[] tiles = new Tile[45];
    private ZombieManager zM;
    private WaveManager wM;
    private Toolkit t = Toolkit.getDefaultToolkit();
    public Playing(JPanel panel) {
        this.panel = panel;
        initZombies();
        initPlants();
        importImg();
        initTiles();
    }
    public void initZombies() {
        zM = new ZombieManager(this);
        wM = new WaveManager(this);
    }

    private void spawnZombies() {
        if(wM.isTimeForNewZombie()) {
            zM.spawnZombie(1, 1024, 150);
        }
    }

    public void update() {
        spawnZombies();
        zM.update();
        wM.update();
    }

    private void initPlants() {
        plants = new Plant[3];
        plants[0] = new Plant(0);
        plants[1] = new Plant(1);
        plants[2] = new Plant(2);
    }

    private void initTiles() {
        int curX = 250, curY = 120, rowCounter = 0;
        for(int i = 0; i < 45; i++) {
            if(rowCounter >= 9) {
                curY += hTile+15;
                curX = 250;
                rowCounter = 0;
            }
            curX += (wTile+8);
            tiles[i] = new Tile(new Rectangle(curX, curY, wTile, hTile));
            rowCounter++;
        }
    }

    private void importImg() {
        plantChoiceImg = new Image[7];
        try {
            plantChoiceImg[0] = t.getImage(getClass().getResource("/plantBar/PvZ_Sunflower.jpg"));
            plantChoiceImg[1] = t.getImage(getClass().getResource("/plantBar/PvZ_Peashooter.jpg"));
            plantChoiceImg[2] = t.getImage(getClass().getResource("/plantBar/PvZ_Cherry_Bomb.jpg"));
            plantChoiceImg[3] = t.getImage(getClass().getResource("/plantBar/PvZ_Wall-nut.jpg"));
            plantChoiceImg[4] = t.getImage(getClass().getResource("/plantBar/PvZ_Potato_Mine.jpg"));
            plantChoiceImg[5] = t.getImage(getClass().getResource("/plantBar/PvZ_Snow_Pea.jpg"));
            plantChoiceImg[6] = t.getImage(getClass().getResource("/plantBar/PvZ_Chomper.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR - importImg()");
        }
    }

    public void initButtons() {
        bMenu = new MyButton("Main menu", 0,0,150,70);
        bPlantChoice = new MyButton[7];
        bPlantChoice[0] = new MyButton("Sunflower", 250,10,102,102);
        bPlantChoice[1] = new MyButton("PeaShooter", 352,10, 102,102);
        bPlantChoice[2] = new MyButton("CherryBomb", 454,10, 102,102);
        bPlantChoice[3] = new MyButton("Wallnut", 556,10, 102,102);
        bPlantChoice[4] = new MyButton("PotatoMine", 658,10, 102,102);
        bPlantChoice[5] = new MyButton("SnowPea", 760,10,102,102);
        bPlantChoice[6] = new MyButton("Chomper", 862,10, 102,102);
    }
    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img, 0, 0, panel.getWidth(), panel.getHeight(), null);
        initButtons();
        drawPlantBar(g);
        drawButtons(g);
        drawTiles(g);
        zM.draw(g);
    }

    private void drawPlantBar(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(248,8,718,106);
        g.setColor(Color.black);
        g.drawRect(248,8,718,106);
        int step = 0;
        for(Image i: plantChoiceImg) {
            g.drawImage(i, 250+step, 10, 102, 102,null);
            step += 102;
        }
    }

    private void drawTiles(Graphics g) {
        for(Tile tl: tiles) {
            Rectangle r = tl.getBound();
            if (tl.isOccupied() == true) {
                for(Plant pl: plants) {
                    if(pl.isPlaced() == true) {
                        g.drawImage(tl.getPlant().getPlantImg(), r.x, r.y, r.width, r.height, null);
                    }
                }
            } else {
//                g.setColor(Color.blue);
//                g.fillRect(r.x, r.y, r.width, r.height);
            }
        }
    }
    @Override
    public void drawButtons(Graphics g) {
        bMenu.draw(g);
    }
    @Override
    public void mouseClicked(int x, int y) {
        Graphics gMouse = panel.getGraphics();
        if(bMenu.getBounds().contains(x,y)) {
            setGameStates(gameStates.Menu);
        } else {
            for(MyButton b: bPlantChoice) {
                if(b.getBounds().contains(x,y)) {
                    System.out.printf("You choose %s%n", b.getText());
                    break;
                }
            }
/*            for (Tile tl: tiles) {
                if(tl.getBound().contains(x,y)) {
                    zM.spawnZombie(1,x,y);
                }
            }*/
        }

    }

    public void mousePressed(int x, int y) {
        for(MyButton b: bPlantChoice) {
            if(b.getBounds().contains(x,y)) {
                if(b.getText().contains("Sunflower")) {
                    plants[0].setSeleted(true);
                } else if (b.getText().contains("PeaShooter")) {
                    plants[1].setSeleted(true);
                } else if (b.getText().contains("CherryBomb")) {
                    plants[2].setSeleted(true);
                }
            }
        }
    }

    public void mouseReleased(int x, int y) {
        for(Plant pl: plants) {
            if(pl.isSelected() == true) {
                for(Tile tl: tiles) {
                    if(tl.getBound().contains(x,y) && tl.isOccupied() == false) {
                        tl.setOccupied(true);
                        tl.setPlant(pl);
                        pl.setSeleted(false);
                        pl.setPlaced(true);
                    }
                }
            }
        }
    }
}
