package scenes;

import component.MyButtons;
import manager.World;

import javax.swing.*;
import java.awt.*;
import static scenes.GameScenes.*;

public class Setting implements SceneMethods{
    private World w;
    private MyButtons bMenu, bQuit, bPlaying;
    private Image[] buttonOfSetting;
    private Toolkit t = Toolkit.getDefaultToolkit();

    public Setting(World w){
        this.w = w;
    }

    public void initButtons(){
        bMenu = new MyButtons("Main menu", 277, 390,133,44);
        bQuit = new MyButtons("Quit", 453, 390, 126, 44);
        bPlaying = new MyButtons("Play", 620, 390, 126, 44);
    }

    private void importImg(){
        buttonOfSetting = new Image[3];
        try {
            buttonOfSetting[0] = t.getImage(getClass().getResource("/scene/EXIT TO MAP.png"));
            buttonOfSetting[1] = t.getImage(getClass().getResource("/scene/EXIT.png"));
            buttonOfSetting[2] = t.getImage(getClass().getResource("/scene/RESUME.png"));
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error - importImage()");
        }
    }

    public void drawImg(Graphics g){
        g.drawImage(buttonOfSetting[0], 277, 390,133,44, null);
        g.drawImage(buttonOfSetting[1], 453, 390, 126, 44, null);
        g.drawImage(buttonOfSetting[2], 620, 390, 126, 44, null);
    }

    public void drawButtons(Graphics g){
        bMenu.draw(g);
        bQuit.draw(g);
        bPlaying.draw(g);
    }

    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img, 0,0, w.getWidth(), w.getHeight(),null);
        initButtons();
        drawButtons(g);
        importImg();
        drawImg(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)){
            setGameScenes(MENU);
        } else if (bQuit.getBounds().contains(x, y)){
            System.exit(0);
//            setGameScenes(LOSE);
        } else if (bPlaying.getBounds().contains(x, y)){
            setGameScenes(PLAYING);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
    }

    @Override
    public void mouseReleased(int x, int y) {
    }

    public void updates(){
    }
}
