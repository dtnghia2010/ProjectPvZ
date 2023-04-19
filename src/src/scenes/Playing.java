package scenes;

import manager.World;
import player.MyButtons;

import javax.swing.*;

import static scenes.GameScenes.*;
import java.awt.*;
import scenes.Tile;

public class Playing implements SceneMethods{
    private Tile tile = new Tile();
    private World w;
    private MyButtons bMenu, bQuit, pickPlant[];
    private Image[] pick_plantBar;
    private Toolkit t = Toolkit.getDefaultToolkit();

    public Playing(World w) {
        this.w = w;
        importImage();
    }
    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img,0,0, w.getWidth(), w.getHeight(), null);
        initButtons();
        bMenu.draw(g);
        bQuit.draw(g);
        tile.render(g);
        drawPlantbar(g);
    }

    private void initButtons() {
        bMenu = new MyButtons("Main menu", 0,0,150,70);
        bQuit = new MyButtons("End game", 0, 80, 150, 70);
        pickPlant = new MyButtons[5];
        pickPlant[0] = new MyButtons("Sunflower", 350, 0, 90, 90);
        pickPlant[1] = new MyButtons("Peashooter", 440, 0, 90, 90);
        pickPlant[2] = new MyButtons("Wall-nut", 530, 0, 90, 90);
        pickPlant[3] = new MyButtons("Snow Pea", 620, 0, 90, 90);
        pickPlant[4] = new MyButtons("Cherry Bomb", 710, 0, 90, 90);
    }

    private void drawPlantbar(Graphics g){
        g.setColor(Color.black);
        g.drawRect(350, 0,450,90);
        g.setColor(Color.pink);
        g.fillRect(350, 0, 450, 90);
        int distance = 0;
        for (Image p : pick_plantBar){
            g.drawImage(p, 350 + distance, 0, 90, 90, null);
            distance += 90;
        }
    }

    private void importImage(){
        pick_plantBar = new Image[5];
        try {
            pick_plantBar[0] = t.getImage(getClass().getResource("/plantBar/PvZ_Sunflower.jpg"));
            pick_plantBar[1] = t.getImage(getClass().getResource("/plantBar/PvZ_Peashooter.jpg"));
            pick_plantBar[2] = t.getImage(getClass().getResource("/plantBar/PvZ_Wall-nut.jpg"));
            pick_plantBar[3] = t.getImage(getClass().getResource("/plantBar/PvZ_Snow_Pea.jpg"));
            pick_plantBar[4] = t.getImage(getClass().getResource("/plantBar/PvZ_Cherry_Bomb.jpg"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void mouseClicked(int x, int y) {
        if(bMenu.getBounds().contains(x,y)) {
            setGameScenes(MENU);
        } else if(bQuit.getBounds().contains(x,y)) {
            setGameScenes(LOSE);
        }
        for (MyButtons b : pickPlant){
            if (b.getBounds().contains(x, y)){
                System.out.println("You choose " + b.getText());
            }
        }
    }
}
