package player;

import manager.World;
import scenes.GameScenes;
import scenes.Menu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyBoardListener implements KeyListener {
    private World w;
    public KeyBoardListener(World w){
        this.w = w;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
//        if(e.getKeyCode() == KeyEvent.VK_A) {
//            System.out.println("Menu");
//            GameScenes.gameScenes = GameScenes.MENU;
//        } else if(e.getKeyCode() == KeyEvent.VK_S) {
//            System.out.println("Playing");
//            GameScenes.gameScenes = GameScenes.PLAYING;
//        } else if(e.getKeyCode() == KeyEvent.VK_D) {
//            System.out.println("Lose");
//            GameScenes.gameScenes = GameScenes.LOSE;
//        }
        switch (GameScenes.gameScenes){
            case PLAYING:
                w.getPlaying().keyBoardPress(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
