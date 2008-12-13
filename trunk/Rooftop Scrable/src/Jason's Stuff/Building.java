   import java.awt.*;
   
    public class Building extends Entity
   {
      private Image building;
      private int x_pos;
      private int y_pos;
   
       public Building(int x, int y)
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
      
       public Image getImage()
      {
         return building;
      }
      
   	
   
   }