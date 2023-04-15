package scene;

import java.awt.*;

public interface SceneMethods {
    public void render(Graphics g, Image img);
    public void drawButtons(Graphics g);
    public void mouseClicked(int x, int y);
    public void mousePressed(int x, int y);
    public void mouseReleased(int x, int y);
}
