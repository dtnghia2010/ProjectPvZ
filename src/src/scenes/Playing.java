package scenes;

import component.Plant;
import component.Tile;
import manager.*;
import component.MyButtons;
import java.util.List;

import static scenes.GameScenes.*;

import java.awt.*;
import java.util.ArrayList;

public class Playing implements SceneMethods {
    public List<PlantManager> getListOfPlant() {
        return listOfPlant;
    }

    private List<PlantManager> listOfPlant = new ArrayList<>();
    private TileManager tileManager;
    private BarManager barManager;
    private PlantManager plantManager;
    private ButtonManager buttonManager;
    private World w;
    private Toolkit t = Toolkit.getDefaultToolkit();

    public Playing(World w) {
        this.w = w;
        initComponents();

    }

    private void initComponents() {
        barManager = new BarManager();
        tileManager = new TileManager();
        buttonManager = new ButtonManager();
        plantManager = new PlantManager();
    }

    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img, 0, 0, w.getWidth(), w.getHeight(), null);
        buttonManager.drawButtons(g);
        tileManager.drawTiles(g, plantManager);
        barManager.drawPlantbar(g);
    }

    public PlantManager getPlantManager() {
        return plantManager;
    }

    public void mouseClicked(int x, int y) {
        if (buttonManager.getbMenu().getBounds().contains(x, y)) {
            setGameScenes(MENU);
        } else if (buttonManager.getbQuit().getBounds().contains(x, y)) {
            setGameScenes(LOSE);
        }

        for (MyButtons b1 : barManager.getPickPlant()) {
            if (b1.getBounds().contains(x, y)) {
                System.out.println("You choose " + b1.getText());
            }
        }

        for (MyButtons b2 : barManager.getPickPlant()) {
            if (b2.getBounds().contains(x, y)) {
                if (b2.getText().contains("Sunflower")) {
                    plantManager.initPlants(0);
                    plantManager.setSelected(true);
                } else if (b2.getText().contains("Peashooter")) {
                    plantManager.initPlants(1);
                    plantManager.setSelected(true);
                } else if (b2.getText().contains("Wall-nut")) {
                    plantManager.initPlants(2);
                    plantManager.setSelected(true);
                } else if (b2.getText().contains("Snow Pea")) {
                    plantManager.initPlants(3);
                    plantManager.setSelected(true);
                } else if (b2.getText().contains("Cherry Bomb")) {
                    plantManager.initPlants(4);
                    plantManager.setSelected(true);
                }
            }
        }
    }

    public void mouseReleased(int x, int y) {
        if (plantManager.getSelected()) {
            for (int i = 0; i < 45; i++) {
                if (tileManager.getTiles()[i].getBound().contains(x, y) && !tileManager.getTiles()[i].isOccupied()) {
                    tileManager.getTiles()[i].setOccupied(true);
                    plantManager.setSelected(false);
                    plantManager.setLocated(true);
                }
            }
        }
    }
}
