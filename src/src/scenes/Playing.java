package scenes;

import manager.*;
import component.MyButtons;

import static scenes.GameScenes.*;
import java.awt.*;

public class Playing implements SceneMethods{
    private TileManager tileManager;
    private BarManager barManager;
    private ButtonManager buttonManager;
    private ZombieManager zombieManager;
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
    }

    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img,0,0, w.getWidth(), w.getHeight(), null);
        buttonManager.drawButtons(g);
        tileManager.drawTiles(g);
        barManager.drawPlantbar(g);
    }

    public void mouseClicked(int x, int y) {
        if(buttonManager.getbMenu().getBounds().contains(x,y)) {
            setGameScenes(MENU);
        } else if(buttonManager.getbQuit().getBounds().contains(x,y)) {
            setGameScenes(LOSE);
        }
        for (MyButtons b : barManager.getPickPlant()){
            if (b.getBounds().contains(x, y)){
                System.out.println("You choose " + b.getText());
            }
        }
    }
}
