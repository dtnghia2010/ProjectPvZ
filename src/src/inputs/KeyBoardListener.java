package inputs;

import scene.GameStates;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A) {
            GameStates.gameStates = GameStates.Menu;
            System.out.println("Switch to Menu");
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            GameStates.gameStates = GameStates.Playing;
            System.out.println("Switch to Playing");
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            GameStates.gameStates = GameStates.Lose;
            System.out.println("Switch to Lose");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A) {
            GameStates.gameStates = GameStates.Menu;
            System.out.println("Switch to Menu");
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            GameStates.gameStates = GameStates.Playing;
            System.out.println("Switch to Playing");
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            GameStates.gameStates = GameStates.Lose;
            System.out.println("Switch to Lose");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A) {
            GameStates.gameStates = GameStates.Menu;
            System.out.println("Switch to Menu");
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            GameStates.gameStates = GameStates.Playing;
            System.out.println("Switch to Playing");
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            GameStates.gameStates = GameStates.Lose;
            System.out.println("Switch to Lose");
        }
        System.out.println("___");
    }
}
