package scenes;

import component.MyButtons;
import manager.World;

import javax.swing.*;
import java.awt.*;

import static scenes.GameScenes.MENU;
import static scenes.GameScenes.setGameScenes;

public class Win implements SceneMethods{
    private World w;
    private MyButtons bMenu;
    private Image[] buttonOfWin;
    private Toolkit t = Toolkit.getDefaultToolkit();

    public Win(World w){
        this.w = w;
    }

    public void initButtons(){
        bMenu = new MyButtons("Main menu", 455,555,120,42);
    }

    private void importImg(){
        buttonOfWin = new Image[1];
        try {
            buttonOfWin[0] = t.getImage(getClass().getResource("/scene/EXIT TO MAP.png"));
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error - importImage()");
        }
    }

    public void drawImg(Graphics g){
        g.drawImage(buttonOfWin[0], 455,555,120,42, null);
    }

    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img, 0, 0 , w.getWidth(), w.getHeight(), null);
        initButtons();
        bMenu.draw(g);
        importImg();
        drawImg(g);
    }

    @Override
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
    public void updates(){
    }
}
