package scenes;

import java.awt.*;
import javax.swing.*;

public class Tile{
    private static final int ROWS = 5;
    private static final int COLS = 9;
    private static final int SIZE = 70;


      public Tile(){}
      public void render(Graphics g) {
        Graphics2D G2D = (Graphics2D) g ;
        G2D.setColor(Color.pink);
        int up=0;
        int up1=0;
        for (int i=1; i<=ROWS; i++){
            for (int j=0; j<=COLS; j++) {
                Rectangle RTG = new Rectangle(320 + up, 90+up1, 70, 90);
                up += 80;
                G2D.fill(RTG);
            }
            up1 += 100;
            up =0;
        }
      }
    }

