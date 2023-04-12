package player;

import manager.World;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import scenes.GameScenes;
import scenes.GameScenes;

public class MyMouseListener implements MouseListener, MouseMotionListener {

    private World w;

    public MyMouseListener(World w) {
        this.w = w;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            switch (GameScenes.gameScenes) {
                case MENU:
                    w.getMenu().mouseClicked(e.getX(), e.getY());
                    break;
                case PLAYING:
                    w.getPlaying().mouseClicked(e.getX(), e.getY());
                    break;
                case LOSE:
                    w.getLose().mouseClicked(e.getX(), e.getY());
                    break;
//                case Setting:
//                    w.getSetting().mouseClicked(e.getX(), e.getY());
//                    break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {


    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
