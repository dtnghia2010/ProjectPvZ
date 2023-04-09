import manager.World;

import javax.swing.*;

public class Main extends JFrame {


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new World());
        frame.pack();
        frame.setTitle("Plants vs Zombies");
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
