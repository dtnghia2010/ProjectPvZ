package scenes;

import manager.World;

import java.awt.*;

public class Lose implements SceneMethods {
    private World w;
    public Lose(World w) {
        this.w = w;
    }
    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img,0,0, w.getWidth(), w.getHeight(), null);
    }
}
