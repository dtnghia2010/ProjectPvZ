package scene;

import javax.swing.*;
import java.awt.*;

public class Playing implements SceneMethods{
    JPanel panel;
    public Playing(JPanel panel) {
        this.panel = panel;
    }
    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img, 0, 0, panel.getWidth(), panel.getHeight(), null);
    }

    @Override
    public void mouseClicked(int x, int y) {

    }
}
