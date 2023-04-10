import manager.World;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        World newWorld = new World();
        frame.add(newWorld);
        newWorld.startThread();
        frame.setTitle("Plants vs Zombies");
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
