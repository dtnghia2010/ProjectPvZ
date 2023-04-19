package scenes;

import java.awt.*;
import javax.swing.*;

public class Tile{
    private static final int ROWS = 5;
    private static final int COLS = 9;


      public Tile(){}
      public void render(Graphics g) {
        Graphics2D G2D = (Graphics2D) g ;
        G2D.setColor(Color.pink);
        int upX=0;
        int upY=0;
        for (int i=1; i<=ROWS; i++){
            for (int j=1; j<=COLS; j++) {
                Rectangle RTG = new Rectangle(500 + upX, 110+upY, 90, 110);
                upX += 110;
                G2D.fill(RTG);
            }
            upY += 130;
            upX =0;
        }
      }
    }

