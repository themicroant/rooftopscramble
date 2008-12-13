   import java.awt.*;
   
    public class Building extends Entity
   {
      private Image building;
   
       public Building(int x, int y)
      {
         x_pos = x;
         y_pos = y;
			width = 45;
         height = 1;
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