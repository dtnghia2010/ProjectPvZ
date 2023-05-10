package manager;

import component.MyButtons;

import javax.swing.*;
import java.awt.*;

public class ButtonManager {
    private MyButtons bStart, bPause;
    private Image[] buttonOfPlaying;
    private Toolkit t = Toolkit.getDefaultToolkit();

    public ButtonManager() {
        initButtons();
        importImg();
    }

    private void initButtons() {
        bStart = new MyButtons("Start", 0, 0, 145, 45);
        bPause = new MyButtons("Pause", 920, 585, 95, 35);
    }

    public void importImg(){
        buttonOfPlaying = new Image[3];
        try {
            buttonOfPlaying[0] = t.getImage(getClass().getResource("/scene/LET'S ROCK.png"));
            buttonOfPlaying[1] = t.getImage(getClass().getResource("/scene/SETTINGS.png"));
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error - importImage()");
        }
    }

    public void drawImg(Graphics g){
        g.drawImage(buttonOfPlaying[0], 0,0,145,45, null);
        g.drawImage(buttonOfPlaying[1], 920,585,95,35, null);
    }

    public void drawButtons(Graphics g) {
        bStart.draw(g);
        bPause.draw(g);
    }

    public MyButtons getbStart() {
        return bStart;
    }

    public MyButtons getbPause() {
        return bPause;
    }
}
