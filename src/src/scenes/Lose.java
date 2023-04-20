package scenes;

import manager.World;
import component.MyButtons;
import static scenes.GameScenes.*;
import java.awt.*;

public class Lose implements SceneMethods {
    private World w;
    private MyButtons bMenu;
    public Lose(World w) {
        this.w = w;

    }

    private void initButtons() {
        bMenu = new MyButtons("Main menu", 0,0,150,70);
    }

    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img,0,0, w.getWidth(), w.getHeight(), null);
        initButtons();
        bMenu.draw(g);
    }

    public void mouseClicked(int x, int y) {
        if(bMenu.getBounds().contains(x,y)) {
            setGameScenes(MENU);
        }
    }
}
