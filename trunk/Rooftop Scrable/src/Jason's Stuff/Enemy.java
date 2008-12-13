import java.awt.Graphics;
import java.awt.Color;

public abstract class Enemy extends Entity
{
       private int speed = 1;
      boolean diagonal = true;
      // size of the enemy
      private static final int RADIS = 30;
   
       public Enemy(int x, int y)
      {
         x_pos = x;
         y_pos = y;
         width = 45;
         height = 45;
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
      public void moveY()
      {
            y_pos += speed;
      }
      public abstract void move(int x, int y);
      
      public void chase(int x) {
    	  	if(x < x_pos) {
    	  		x_pos -= speed;
    	  	}else if (x > x_pos) {
    	  		x_pos += speed;
    	  	}
      }
      
      public void flee(int x) {
    	  if(x > x_pos) {
  	  			x_pos -= speed;
  	  		}else if (x < x_pos) {
  	  			x_pos += speed;
  	  	  }
      }
      
      public void diagonal(int vertical, int horizontal) {
    	  if(diagonal) {
  	  			x_pos += speed  * Math.signum(horizontal);
  	  		}else  {
  	  			y_pos += speed * Math.signum(vertical);
  	  	  }
    	  diagonal = !diagonal;
      }
      // generate a shot at the current position of the spaceship
      // and return this shot to the calling method
      public Shot generateShot()
      {
            Shot shot = new Shot(x_pos, y_pos, 1);

            return shot;
      }

	  public void draw(Graphics g) {
		g.setColor(Color.RED);
   	  	g.fillRect(x_pos-(RADIS/2), y_pos-(RADIS/2), RADIS, RADIS);	
	  }

	
} 