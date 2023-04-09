package ui;

import inputs.MyMouseListener;

import java.awt.*;

public class MyButton {
    private int x, y, width, height;
    private String text;
    private Rectangle bounds;
    public MyButton(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void initBound() {
        bounds = new Rectangle(x,y,width,height);
    }
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x,y,width,height);

        g.setColor(Color.BLACK);
        g.drawRect(x,y,width,height);

        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(text, x - w/2 + width/2,y + h/2 + height/2);
    }
    public Rectangle getBounds() {
        return getBounds();
    }
}
