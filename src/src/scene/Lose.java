package scene;
import javax.swing.*;
import java.awt.*;

public class Lose implements SceneMethods{
    JPanel panel;
    public Lose(JPanel panel) {
        this.panel = panel;
    }
    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img, 0,0, panel.getWidth(), panel.getHeight(), null);
    }

    @Override
    public void mouseClicked(int x, int y) {

    }
}
