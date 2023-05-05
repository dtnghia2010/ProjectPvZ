package scenes;

import Audio.Audio;
import manager.World;
import component.MyButtons;
import static scenes.GameScenes.*;
import java.awt.*;

public class Menu implements SceneMethods {
    private World w;
    private MyButtons bPlaying, bSetting, bQuit;
    public Menu(World w) {
        this.w = w;
    }
    public void initButtons() {
        bPlaying = new MyButtons("Play", 430, 329,150,70);
        bSetting = new MyButtons("Setting", 430, 410, 150, 70);
        bQuit = new MyButtons("Quit", 430, 491, 150, 70);
    }

    public void mouseClicked(int x, int y) {
        if(bPlaying.getBounds().contains(x,y)) {
//            Audio.readySetPlant();
//            Audio.roofStage();
            setGameScenes(PLAYING);
        } else if (bQuit.getBounds().contains(x,y)) {
            setGameScenes(LOSE);
        } else if (bSetting.getBounds().contains(x,y)) {
//            setGameScenes(LOSE);
            System.out.println("Setting");
        }
    }

    @Override
    public void mousePressed(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {

    }

    public void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bSetting.draw(g);
        bQuit.draw(g);
    }
    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img,0,0, w.getWidth(), w.getHeight(), null);
        initButtons();
        drawButtons(g);
    }
    public void updates () {
    }
}
