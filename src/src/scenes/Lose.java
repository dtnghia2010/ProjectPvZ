package scenes;

import manager.World;
import component.MyButtons;

import javax.swing.*;

import static scenes.GameScenes.*;
import java.awt.*;

public class Lose implements SceneMethods {
    private World w;
    private MyButtons bMenu;
    private Image[] buttonOfLose;
    private Toolkit t = Toolkit.getDefaultToolkit();

    public Lose(World w) {
        this.w = w;
    }

    private void initButtons() {
        bMenu = new MyButtons("Main menu", 435,530,165,80);
    }

    private void importImg(){
        buttonOfLose = new Image[1];
        try {
            buttonOfLose[0] = t.getImage(getClass().getResource("/scene/EXIT TO MAP.png"));
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error - importImage()");
        }
    }

    public void drawImg(Graphics g){
        g.drawImage(buttonOfLose[0], 435,530,165,80, null );
    }

    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img,0,0, w.getWidth(), w.getHeight(), null);
        initButtons();
        bMenu.draw(g);
        importImg();
        drawImg(g);
    }

    public void mouseClicked(int x, int y) {
        if(bMenu.getBounds().contains(x,y)) {
            setGameScenes(MENU);
        }
    }

    @Override
    public void mousePressed(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {

    }

    public void updates() {
    }
}
