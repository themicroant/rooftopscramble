import java.awt.Graphics;
import java.awt.Color;

public class Shot
{

      private int x_pos;
      private int y_pos;

      // size of the shot
      private static final int RADIS = 8;

      // Constructor
      public Shot(int x, int y)
      {
            x_pos = x;
            y_pos = y;
      }

      // returns y position, needed for testing if shot has left the game area
      public int getYPos()
      {
            return y_pos;
      }

      // moving shot in y direction
      public void moveShot(int speed)
      {
            y_pos += speed;
      }

      // draw the shot to the screen
      public void drawShot(Graphics g)
      {
            g.setColor(Color.black);
            g.fillOval(x_pos-(RADIS/2), y_pos-(RADIS/2), RADIS, RADIS);
      }
} 