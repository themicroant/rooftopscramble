   import java.awt.*;

    public abstract class Entity
   {
      private int x_pos;
      private int y_pos;
      private int width;
      private int height;
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
		
		public void setImage(Image i)
		{
		img = i;
		}
   	  
   	  public abstract void draw(Graphics g);
      
   	  public abstract void update();   	
   }