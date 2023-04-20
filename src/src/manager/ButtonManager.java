package manager;

import component.MyButtons;

import java.awt.*;

public class ButtonManager {
    private MyButtons bMenu, bQuit;

    public ButtonManager() {
        initButtons();
    }

    private void initButtons() {
        bMenu = new MyButtons("Main menu", 0,0,150,70);
        bQuit = new MyButtons("End game", 0, 80, 150, 70);
    }

    public void drawButtons(Graphics g) {
        bMenu.draw(g);
        bQuit.draw(g);
    }

    public MyButtons getbMenu() {
        return bMenu;
    }

    public MyButtons getbQuit() {
        return bQuit;
    }
}
