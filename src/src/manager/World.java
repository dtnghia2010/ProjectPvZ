package manager;

import inputs.MyMouseListener;
import scenes.GameScenes;
import scenes.Lose;
import scenes.Menu;
import scenes.Playing;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class World extends JPanel implements Runnable {
    private Image[] image= new Image[3];;
    private Random random;
    private double FPS_SET = 200.0;
    private double UPS_SET = 150.0;
    private MyMouseListener myMouseListener;
    private Lose lose = new Lose(this);
    private Menu menu = new Menu(this);
    private Playing playing = new Playing(this);
    private Toolkit t = Toolkit.getDefaultToolkit();
    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
    public World() {
        random = new Random();
        initInput();
        initClasses();
        importImg();
        loadSprites();
        start();
    }
    public void initInput() {
        myMouseListener = new MyMouseListener();
        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        setFocusable(true);
        requestFocus();
    }

    public void initClasses() {

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);

    }

    public void render(Graphics g) {
        switch (GameScenes.gameScenes) {
            case MENU:
                //dang lam toi day
                menu.render(g, image[0]);
                break;
            case PLAYING:

                break;
            case LOSE:

                break;
        }
    }

    public void importImg() {
        try {
            image[0] = t.getImage(getClass().getResource("/menu.jpg"));
            image[1] = t.getImage(getClass().getResource("/lawn.png"));
            image[2] = t.getImage(getClass().getResource("/lose.png"));
        } catch (Exception e) {
            e.printStackTrace();
//            e.getLocalizedMessage();
        }
    }

    public void loadSprites() {
    }

    public int getRnd() {
        return random.nextInt(32);
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long lastUpdate = System.nanoTime();
        int updates = 0;
        int frames = 0;
        long lastTimeCheck = System.currentTimeMillis();
        long now;
        while (true) {
            now = System.nanoTime();
            //repaint game
            if (now - lastFrame >= timePerFrame) {
                lastFrame = now;
                frames++;
                repaint();
            }
            //update game
            if (now - lastUpdate >= timePerUpdate) {
                lastUpdate = now;
                updates++;
                //updates()
            }
            //check FPS & UPS
            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }
}

