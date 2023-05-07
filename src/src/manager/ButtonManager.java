package manager;

import component.MyButtons;

import javax.swing.*;
import java.awt.*;

public class ButtonManager {
    private MyButtons bMenu, bQuit, bStart;
    private Image[] buttonOfPlaying;
    private Toolkit t = Toolkit.getDefaultToolkit();

    public ButtonManager() {
        initButtons();
        importImg();
    }

    private void initButtons() {
        bMenu = new MyButtons("Main menu", 0,0,165,80);
        bQuit = new MyButtons("End game", 920, 575, 100, 50);
        bStart = new MyButtons("Start", 0, 80, 165, 70);
    }

    public void importImg(){
        buttonOfPlaying = new Image[3];
        try {
            buttonOfPlaying[0] = t.getImage(getClass().getResource("/scene/EXIT TO MAP.png"));
            buttonOfPlaying[1] = t.getImage(getClass().getResource("/scene/EXIT.png"));
            buttonOfPlaying[2] = t.getImage(getClass().getResource("/scene/LET'S ROCK.png"));
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error - importImage()");
        }
    }

    public void drawImg(Graphics g){
        g.drawImage(buttonOfPlaying[0], 0,0,165,80, null);
        g.drawImage(buttonOfPlaying[1], 920,575,100,50, null);
        g.drawImage(buttonOfPlaying[2], 0,80,165,70, null);
    }

    public void drawButtons(Graphics g) {
        bMenu.draw(g);
        bQuit.draw(g);
        bStart.draw(g);
    }

    public MyButtons getbMenu() {
        return bMenu;
    }

    public MyButtons getbQuit() {
        return bQuit;
    }
    public MyButtons getbStart() {return bStart;}
}
