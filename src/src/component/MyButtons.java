package component;

import java.awt.*;

public class MyButtons {
    private String text;
    private int x, y, width, height;
    private Rectangle bounds;


    public MyButtons(String text, int x, int y, int width, int height) {
       this.text = text;
       this.x = x;
       this.y = y;
       this.width = width;
       this.height = height;
       initBound();
   }
    public void initBound() {
        bounds = new Rectangle(this.x,this.y,this.width,this.height);
    }

    public Rectangle getBounds() {
        return bounds;
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

    public String getText() {
        return text;
    }
}
