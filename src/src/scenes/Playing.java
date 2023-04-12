package scenes;

import manager.World;
import player.MyButtons;
import static scenes.GameScenes.*;
import java.awt.*;

public class Playing implements SceneMethods{
    private World w;
    private MyButtons bMenu, bQuit;
    public Playing(World w) {
        this.w = w;
    }
    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img,0,0, w.getWidth(), w.getHeight(), null);
        initButtons();
        bMenu.draw(g);
        bQuit.draw(g);
    }

    private void initButtons() {
        bMenu = new MyButtons("Main menu", 0,0,150,70);
        bQuit = new MyButtons("End game", 0, 80, 150, 70);
    }

    public void mouseClicked(int x, int y) {
        if(bMenu.getBounds().contains(x,y)) {
            setGameScenes(MENU);
        } else if(bQuit.getBounds().contains(x,y)) {
            setGameScenes(LOSE);
        }
    }
}
