package scene;


import ui.MyButton;
import static scene.GameStates.*;
import javax.swing.*;
import java.awt.*;
public class Menu implements SceneMethods {
    private JPanel panel;
    private MyButton bPlaying, bSetting, bQuit;
    public Menu(JPanel panel) {
        this.panel = panel;
        initButtons();
    }
    public void initButtons() {
        bPlaying = new MyButton("Play", panel.getWidth()/2, 329,150,70);
        bSetting = new MyButton("Setting", 430, 410, 150, 70);
        bQuit = new MyButton("Quit", 430,491, 150, 70);
    }
    public void render(Graphics g, Image img) {
        g.drawImage(img, 0, 0, panel.getWidth(), panel.getHeight(), null);
        drawButton(g);
    }
    public void drawButton(Graphics g) {
        bPlaying.draw(g);
        bSetting.draw(g);
        bQuit.draw(g);
    }

    public void mouseClicked(int x, int y) {
        System.out.println("Call mouseClicked");
        if(bPlaying.getBounds().contains(x,y)) {
            System.out.println("Call setGameState");
            setGameStates(GameStates.Playing);
        } else if (bQuit.getBounds().contains(x,y)) {
            setGameStates(GameStates.Lose);
        } else if (bSetting.getBounds().contains(x,y)) {
            System.out.println("Setting");
        }
    }
    public MyButton getbPlaying(){
        return this.bPlaying;
    }
}
