   import java.awt.*;

    public abstract class Entity
   {
      public int x_pos;
      public int y_pos;
      public int width;
      public int height;
      private Image img;
   
       public int getX()
      {
         return x_pos;
      }
   
       public void moveX(int inc)
      {
         x_pos += inc;
      }
   
       public int getY()
      {
         return y_pos;
      }
   
       public void moveY(int inc)
      {
         y_pos += inc;
      }
   
       public int getwidth()
      {
         return width;
      }
   
       public int getheight()
      {
         return height;
      }
   
       public Image getImage()
      {
         return img;
      }
   
       public void setImage(Image i)
      {
         img = i;
      }
   
       public Rectangle getBounds()
      {
         return new Rectangle(x_pos, y_pos, width, height);
      }
   
       public void collision(Entity a)
      {
         if (this instanceof Player && a instanceof Building){
           // System.out.println("Player hit bldg");
         }
         if (this instanceof Player && a instanceof Enemy)
             ((Player)this).takeDamage(1);
         if (this instanceof Player && a instanceof Enemy)
             ((Player)this).takeDamage(1);
         //if (this instanceof Player && a instanceof Shot)
          //   ((Player)this).takeDamage(1);
         if (this instanceof Enemy && a instanceof Shot) {
             ((Enemy)this).takeDamage(1);
             System.out.println("Enemy hit shot");
         }

            
      }
   
   }