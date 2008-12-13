//import java.awt.Graphics;
//import java.awt.Image;

public class Player
{
      private int x_pos;
      private int y_pos;

      public Player(int x, int y)
      {
            x_pos = x;
            y_pos = y;
      }
      
      public int getX()
      {
    	  return x_pos;
      }
      
      public int getY()
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

      // draw the player
      //public void drawPlayer(Graphics g, Image jet)
      //{
    	  
      //}
} 