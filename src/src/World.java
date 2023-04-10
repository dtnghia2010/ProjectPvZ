import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class World extends JPanel implements Runnable{
    private BufferedImage img;
    private boolean isWallCollideLeft = true;
    private boolean isWallCollideRight = false;
    public World() {

    }
    Thread gameThread;
    private int FPS = 60;
    private Rectangle rectangle = new Rectangle(500,250,100,100);
    public void startThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS; // 1000000000 nano seconds = 1 second
        double nextDraw = System.nanoTime() + drawInterval; // System.nanoTime(): the current (real) time shows in nano-second
        while (gameThread != null){
            double remainingTime = nextDraw - System.nanoTime(); // remaining time after executing every code inside this class
            remainingTime = remainingTime/1000000;
            if(remainingTime < 0){
                remainingTime = 0;
            }

            move();
            try {
                Thread.sleep((long) remainingTime); // let the program stop for the remaining time to avoid bugs
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            nextDraw += drawInterval;
        }
    }
    public void move(){
        if(!isWallCollideRight && isWallCollideLeft){
            rectangle.setBounds((int)rectangle.getX()+3,(int)rectangle.getY(),(int)rectangle.getWidth(),(int)rectangle.getHeight());
            if(rectangle.getX() >= 1024 - 110){
                isWallCollideRight = true;
                isWallCollideLeft = false;
            }
        } else if(!isWallCollideLeft && isWallCollideRight){
            rectangle.setBounds((int)rectangle.getX()-3,(int)rectangle.getY(),(int)rectangle.getWidth(),(int)rectangle.getHeight());
            if(rectangle.getX() <= 0){
                isWallCollideRight = false;
                isWallCollideLeft = true;
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0,0,1024,625,null);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect((int)rectangle.getX(),(int)rectangle.getY(),(int)rectangle.getWidth(),(int)rectangle.getHeight());
    }
    public void importImg() {
        InputStream is = getClass().getResourceAsStream("res/background.jpg");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
