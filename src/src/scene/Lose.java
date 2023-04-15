package scene;
import ui.MyButton;
import static scene.GameStates.*;
import javax.swing.*;
import java.awt.*;

public class Lose implements SceneMethods{
    private JPanel panel;
    private MyButton bMenu;
    public Lose(JPanel panel) {
        this.panel = panel;
        initButtons();
    }
    public void initButtons() {
        bMenu = new MyButton("Main menu", 0,0, 150,70);
    }
    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img, 0,0, panel.getWidth(), panel.getHeight(), null);
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

    public void mousePressed(int x, int y) {
    }

    public void mouseReleased(int x, int y) {
    }
}
