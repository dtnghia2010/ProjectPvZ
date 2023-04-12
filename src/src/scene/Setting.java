package scene;

import ui.MyButton;
import static scene.GameStates.*;
import javax.swing.*;
import java.awt.*;


public class Setting implements SceneMethods {
    private JPanel jpanel;
    private MyButton bMenu;
    public Setting(JPanel jpanel) {
        this.jpanel = jpanel;
        initButtons();
    }
    public void initButtons() {
        bMenu = new MyButton("Main menu", 0,0,150, 70);
    }
    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img, 0,0,jpanel.getWidth(), jpanel.getHeight(), null);
        drawButtons(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(bMenu.getBounds().contains(x,y)) {
            setGameStates(gameStates.Menu);
        }
    }

    @Override
    public void drawButtons(Graphics g) {
        bMenu.draw(g);
    }
}
