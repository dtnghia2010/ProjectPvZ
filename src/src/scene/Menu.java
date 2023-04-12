package scene;


import manager.World;
import ui.MyButton;
import static scene.GameStates.*;
import javax.swing.*;
import java.awt.*;
public class Menu implements SceneMethods {
    private World w;
    private MyButton bPlaying, bSetting, bQuit;
    public Menu(World w) {
        this.w = w;
    }
    public void initButtons() {
        bPlaying = new MyButton("Play", w.getWidth()/2, 329,150,70);
        bSetting = new MyButton("Setting", 430, 410, 150, 70);
        bQuit = new MyButton("Quit", 430,491, 150, 70);
    }
    public void render(Graphics g, Image img) {
        g.drawImage(img, 0, 0, w.getWidth(), w.getHeight(), null);
        initButtons();
        drawButtons(g);
    }
    public void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bSetting.draw(g);
        bQuit.draw(g);
    }

    public void mouseClicked(int x, int y) {
        if(bPlaying.getBounds().contains(x,y)) {
            setGameStates(GameStates.Playing);
        } else if (bQuit.getBounds().contains(x,y)) {
            setGameStates(GameStates.Lose);
        } else if (bSetting.getBounds().contains(x,y)) {
            setGameStates(GameStates.Setting);
        }
    }
    public MyButton getbPlaying(){
        return this.bPlaying;
    }
}
