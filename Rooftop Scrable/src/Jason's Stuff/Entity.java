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
      
       public int getY()
      {
         return y_pos;
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
   	
       public Rectangle getBounds() {
         return new Rectangle(x_pos,y_pos,width,height);
      }
      
       public void collision(Entity a) {
         if (a instanceof Enemy)
            System.out.println("hit building");
          System.out.println("hit");
      }
   
   
   }