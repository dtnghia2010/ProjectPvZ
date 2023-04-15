package inputs;

import manager.World;
import scene.GameStates;
import scene.Menu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyMouseListener implements MouseListener, MouseMotionListener {
    private World w;
    private Menu menu;
    public MyMouseListener(World w) {
        this.w = w;
        menu = new Menu(w);
    }
    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getButton() == MouseEvent.BUTTON1) {
            switch (GameStates.gameStates) {
                case Menu:
                    w.getMenu().mouseClicked(e.getX(), e.getY());
                    break;
                case Playing:
                    w.getPlaying().mouseClicked(e.getX(), e.getY());
                    break;
                case Lose:
                    w.getLose().mouseClicked(e.getX(), e.getY());
                    break;
                case Setting:
                    w.getSetting().mouseClicked(e.getX(), e.getY());
                    break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            switch (GameStates.gameStates) {
                case Menu:
                    w.getMenu().mousePressed(e.getX(), e.getY());
                    break;
                case Playing:
                    w.getPlaying().mousePressed(e.getX(), e.getY());
                    break;
                case Lose:
                    w.getLose().mousePressed(e.getX(), e.getY());
                    break;
                case Setting:
                    w.getSetting().mousePressed(e.getX(), e.getY());
                    break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            switch (GameStates.gameStates) {
                case Menu:
                    w.getMenu().mouseReleased(e.getX(), e.getY());
                    break;
                case Playing:
                    w.getPlaying().mouseReleased(e.getX(), e.getY());
                    break;
                case Lose:
                    w.getLose().mouseReleased(e.getX(), e.getY());
                    break;
                case Setting:
                    w.getSetting().mouseReleased(e.getX(), e.getY());
                    break;
            }
        }
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
