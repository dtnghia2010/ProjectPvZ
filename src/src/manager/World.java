package manager;

import inputs.KeyBoardListener;
import inputs.MyMouseListener;
import scene.GameStates;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import scene.Menu;
import scene.Playing;
import scene.Lose;

public class World extends JPanel implements Runnable {
    private int screenWidth = 1024;
    private int screenHeight = 625;
    private Image[] img = new Image[4];
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Menu menu;
    private Playing playing;
    private Lose lose;
    private Random random = new Random();
    private Thread gameThread;
    private double FPS_SET = 10000.0;
    private double UPS_SET = 10000.0;

    private MyMouseListener myMouseListener;
    private KeyBoardListener keyBoardListener;
    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    public World() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        initInput();
        importImg();
        initClass();
        start();
    }

    public void initInput() {
        System.out.println("initInput succeed");
        myMouseListener = new MyMouseListener();
        keyBoardListener = new KeyBoardListener();
        addKeyListener(keyBoardListener);
        setFocusable(true);
        requestFocus();
    }


    public void initClass() {
        menu = new Menu(this);
        playing = new Playing(this);
        lose = new Lose(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }
    public void render(Graphics g) {
        switch (GameStates.gameStates) {
            case Menu:
                menu.render(g, img[0]);
                break;
            case Playing:
                playing.render(g, img[1]);
                break;
            case Lose:
                lose.render(g, img[2]);
                break;
            case Background:
                //get background
                break;
        }
    }

    public void importImg() {
        try {
            img[0] = t.getImage(getClass().getResource("/menu.jpg"));
            img[1] = t.getImage(getClass().getResource("/lawn.png"));
            img[2] = t.getImage(getClass().getResource("/lose.png"));
            img[3] = t.getImage(getClass().getResource("/playing.png"));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot open image!"); //show error dialog
        }
    }

    @Override
    public void run() {
        double timePerFrame =1000000000.0/FPS_SET;
        long lastFrame = System.nanoTime();
        double timePerUpdate = 1000000000.0/UPS_SET;
        long lastUpdate = System.nanoTime();
        int updates = 0;
        int frames = 0;
        long lastTimeCheck = System.currentTimeMillis();
        long now;
        while(true) {
            now = System.nanoTime();
            //repaint game
            if(now - lastFrame >= timePerFrame) {
                lastFrame = now;
                frames++;
                repaint();
            }
            //update game
            if(now - lastUpdate >= timePerUpdate) {
                lastUpdate = now;
                updates++;
                //updates()
            }
            //check FPS & UPS
            if(System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }
}
