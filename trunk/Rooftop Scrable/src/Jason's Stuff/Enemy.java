import java.awt.Graphics;
import java.awt.Color;

public class Enemy
{
      private int x_pos;
      private int y_pos;
      
      // size of the enemy
      private static final int RADIS = 30;

      public Enemy(int x, int y)
      {
            x_pos = x;
            y_pos = y;
      }
      
      public int getXPos()
      {
    	  return x_pos;
      }
      
      public int getYPos()
      {
    	  return y_pos;
      }

      // move spaceship in x - direction
      public void moveX(int speed)
      {
    	  x_pos += speed;
      }
      
      // move spaceship in x - direction
      public void moveY(int speed)
      {
            y_pos += speed;
      }

      // generate a shot at the current position of the spaceship
      // and return this shot to the calling method
      public Shot generateShot()
      {
            Shot shot = new Shot(x_pos, y_pos);

            return shot;
      }

      // draw the enemy
      public void drawEnemy( Graphics g)
      {
    	  g.setColor(Color.RED);
    	  g.fillRect(x_pos-(RADIS/2), y_pos-(RADIS/2), RADIS, RADIS);
      }
} 