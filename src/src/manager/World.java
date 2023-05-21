package manager;

import player.KeyBoardListener;
import player.MyMouseListener;
import scenes.*;
import scenes.Menu;
import scenes.Playing;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class World extends JPanel implements Runnable {
    private int screenWidth = 1024, screenHeight = 625;
    private ArrayList<Image> img = new ArrayList<>();
    private Random random;
    private double FPS_SET = 200.0;
    private double UPS_SET = 60.0;
    private MyMouseListener myMouseListener;
    private KeyBoardListener keyBoardListener;
    private Lose lose;
    private Menu menu;
    private Playing playing;
    private Setting setting;
    private Win win;
    private Toolkit t = Toolkit.getDefaultToolkit();

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
    public World() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        random = new Random();
        initInput();
        initClasses();
        importImg();
        start();
    }

    public Lose getLose() {
        return lose;
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Setting getSetting() {
        return setting;
    }

    public Win getWin() {
        return win;
    }

    public void initInput() {
        myMouseListener = new MyMouseListener(this);
        keyBoardListener = new KeyBoardListener(this);
        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyBoardListener);
        setFocusable(true);
        requestFocus();
    }

    public void initClasses() {
        lose = new Lose(this);
        playing = new Playing(this);
        menu = new Menu(this);
        setting = new Setting(this);
        win = new Win(this);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    public void render(Graphics g) {
        switch (GameScenes.gameScenes) {
            case MENU:
                menu.render(g, img.get(0));
                break;
            case PLAYING:
                playing.render(g, img.get(1));
                break;
            case LOSE:
                lose.render(g, img.get(2));
                break;
            case SETTING:
                setting.render(g, img.get(3));
                break;
            case WIN:
                win.render(g,img.get(4));
        }
    }
    public void updates() {
        switch (GameScenes.gameScenes) {
            case MENU:
                getMenu().updates();
                break;
            case PLAYING:
                getPlaying().updates();
                break;
            case LOSE:
                getLose().updates();
                break;
            case SETTING:
                getSetting().updates();
                break;
            case WIN:
                getWin().updates();
        }
    }

    public void importImg() {
        img.add(t.getImage(getClass().getResource("/scene/menu.jpg")));
        img.add(t.getImage(getClass().getResource("/scene/lawn.png")));
        img.add(t.getImage(getClass().getResource("/scene/lose.png")));
        img.add(t.getImage(getClass().getResource("/scene/pause.png")));
        img.add(t.getImage(getClass().getResource("/scene/win.png")));
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
                updates();
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

