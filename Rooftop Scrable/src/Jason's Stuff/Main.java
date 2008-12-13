   import java.applet.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.util.*;


    public class Main extends Applet implements Runnable
   {
    	// to appease Eclipse
      private static final long serialVersionUID = 1L;
   	
   	
      private volatile Thread thread;
      private int width;
      private int height;
      private Player player;
      private BirdEnemy[] enemy;
      private Shot[] shots;
      private ArrayList<Entity> EArray = new ArrayList<Entity>();
      private int distanceMoved = 0;
      private final int WIDTH = 640;
      private final int HEIGHT = 480;
      
   // constants
      private int PLAYER_SPEED = 3;
   
   // move flags
      private boolean playerMoveLeft;
      private boolean playerMoveRight;
   
   // double buffering
      private Image dbImage;
      private Graphics dbg;
      private Image jetImage, city;
   
       public void init()
      {
         resize(WIDTH, HEIGHT);
         width = getWidth();
         height = getHeight();
         player = new Player(width/2, 400);
         player.setImage(Transparency.makeColorTransparent(getImage(getDocumentBase(), "megagirl_standing.gif"),new Color(0).white));
         EArray.add(player);
         
         enemy = new BirdEnemy[1];
         for(int i = 0; i < 1;i++) {
            enemy[i] = new BirdEnemy(127, 117);
            EArray.add(enemy[i]);
         }
         shots = new Shot[5];
         city = getImage( getDocumentBase(), "city.JPG" );		
         
      }
   
       public void start ()
      {
         thread = new Thread(this);
         thread.start();
      }
   
       public void stop()
      {
         thread = null;
      }
   
       public void destroy()
      {
         thread = null;
      } 
   
       public void run ()
      {
         Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
      
         while (thread != null)
         {
            for(int i=0; i<shots.length; i++)
            {
               if(shots[i] != null)
               {
                  shots[i].tick();
               
                  if(shots[i].getX() > 640)
                  {
                     shots[i] = null;
                  } 
               
               }
               
            	checkCollisions();	
            }
         
         // move player
            if(playerMoveLeft && player.getX() > 0)
            {		
               player.moveX(-PLAYER_SPEED);
            }
            else if(playerMoveRight && player.getX() < width)
            {
               player.moveX(PLAYER_SPEED);
            }
         
            try
            {
               Thread.sleep(10);
            }
                catch (InterruptedException ex)
               {
               // do nothing
               }
                
            for(Enemy e : enemy) {
               e.move(player.getX(), player.getY());
            }
                
         // repaint applet
            repaint();
         
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
         }
      }
   
       public boolean keyDown(Event e, int key)
      {
         if(key == Event.LEFT)
         {
            playerMoveLeft = true;
         }
         else if(key == Event.RIGHT)
         {
            playerMoveRight = true;
         }
         else if(key == Event.UP)
         {
        	 player.jump();
         }
         else if(key == 32 )//Spacebar
         {
          // generate new shot and add it to shots array
            for(int i=0; i<shots.length; i++)
            {
               if(shots[i] == null)
               {
                  shots[i] = player.generateShot();
                  break;
               }
            }
         }
         else if(key == 49)// 1
         {
            player.setWeap(1);
         }
         else if(key == 50)// 2
         {
            player.setWeap(2);
         }
         else if(key == 51)// 3
         {
            player.setWeap(3);
         }
       
         return true;
      }
   
       public boolean keyUp(Event e, int key)
      {
         if(key == Event.LEFT)
         {
            playerMoveLeft = false;
         }
         else if(key == Event.RIGHT)
         {
            playerMoveRight = false;
         }
      
         return true;
      }
   
       public void update (Graphics g)
      {
         if(dbImage == null)
         {
            dbImage = createImage(1000, 1000);
            dbg = dbImage.getGraphics();
         }
      
         dbg.setColor(getBackground());
         dbg.fillRect(0, 0, 1000, 1000);
         paint(dbg);
         g.drawImage (dbImage, 0, 0, this);
         
      }
   
       public void paint (Graphics g)
      {    
         g.drawImage(city, WIDTH+distanceMoved, 0, WIDTH, HEIGHT, this );
         g.drawImage(city, distanceMoved, 0, WIDTH, HEIGHT, this );
      
      // draw shots
         for(int i=0; i<shots.length; i++)
         {
            if(shots[i] != null)
            {
               shots[i].drawShot(g);
            }
         }
      
      // draw player
      //player.drawPlayer(g, jetImage);
         g.drawImage(player.getImage(), player.getX()-26, player.getY()-35, this );
         for(Enemy e : enemy) {
            e.draw(g);
         }
         drawUI(g);
      
         distanceMoved--;
         distanceMoved=distanceMoved%WIDTH;
      }
       
       public void drawUI(Graphics g){
         int textX = 10;
         int textY = 20;
      
         g.setColor(Color.BLACK);
         g.setFont(new Font("Arial", Font.BOLD, 20 ));
      
         String life = player.getLifeText();
         g.drawChars(life.toCharArray(),0,life.length(),textX,textY);
      
         String weap = player.getWeapText();
         g.drawChars(weap.toCharArray(),0,weap.length(),textX,textY+25);
      
      }
   	
   	
       public void checkCollisions() {
         Rectangle playerBounds = player.getBounds();
         for (int i = 0; i < EArray.size(); i++) {
            Entity a1 = EArray.get(i);
            Rectangle r1 = a1.getBounds();
            if (r1.intersects(playerBounds)) {
               player.collision(a1);
               a1.collision(player);
            }
            for (int j = i+1; j < EArray.size(); j++) {
               Entity a2 = EArray.get(j);
               Rectangle r2 = a2.getBounds();
               if (r1.intersects(r2)) {
                  a1.collision(a2);
                  a2.collision(a1);
               }
            }
         }
      }
   
   }
