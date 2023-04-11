package scenes;

import manager.World;

import java.awt.*;

public class Menu implements SceneMethods {
    private World w;
    public Menu(World w) {
        this.w = w;
    }
    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img, 0,0,null);
    }
}
