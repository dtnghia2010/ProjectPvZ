package scenes;

import Audio.Audio;
import manager.World;
import component.MyButtons;

import javax.swing.*;

import static scenes.GameScenes.*;
import java.awt.*;

public class Menu implements SceneMethods {
    private World w;
    private MyButtons bPlaying, bSetting, bQuit;
    private Image[] buttonOfMenu;
    private Toolkit t = Toolkit.getDefaultToolkit();

    public Menu(World w) {
        this.w = w;
    }
    public void initButtons() {
        bPlaying = new MyButtons("Play", 435, 350,160,90);
        bSetting = new MyButtons("Setting", 430, 410, 160, 110);
        bQuit = new MyButtons("Quit", 430, 491, 160, 90);
    }

    private void importImg(){
        buttonOfMenu = new Image[3];
        try {
            buttonOfMenu[0] = t.getImage(getClass().getResource("/scene/PLAY.png"));
            buttonOfMenu[1] = t.getImage(getClass().getResource("/scene/SETTINGS.png"));
            buttonOfMenu[2] = t.getImage(getClass().getResource("/scene/EXIT.png"));
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error - importImage()");
        }
    }

    public void drawImg(Graphics g){
        g.drawImage(buttonOfMenu[0], 435, 350, 160, 90, null);
        g.drawImage(buttonOfMenu[1], 430, 410, 160, 110, null);
        g.drawImage(buttonOfMenu[2], 430, 491, 160, 90, null);
    }

    public void mouseClicked(int x, int y) {
        if(bPlaying.getBounds().contains(x,y)) {
            Audio.readySetPlant();
            Audio.roofStage();
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
        importImg();
        drawImg(g);
    }
    public void updates () {
    }
}
