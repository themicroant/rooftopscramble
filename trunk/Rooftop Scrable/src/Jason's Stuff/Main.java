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
      private Building buildingA;
      private Building buildingB;
      private Building buildingC;
      private BirdEnemy[] enemy;
      private Shot[] shots;
      private ArrayList<Entity> EArray = new ArrayList<Entity>();
      private int distanceMoved = 0;
      private final int WIDTH = 640;
      private final int HEIGHT = 480;
      
      private int gameState = 1;
      
   // constants
      private int PLAYER_SPEED = 3;
   
   // move flags
      private boolean LEFT;
      private boolean RIGHT;
   
   // double buffering
      private Image dbImage;
      private Graphics dbg;
      private Image jetImage, city;
      
      public Image PS0,PS1,PS2;
   
       public void init()
      {
         resize(WIDTH, HEIGHT);
         width = getWidth();
         height = getHeight();
         
         PS0 =Transparency.makeColorTransparent(getImage(getDocumentBase(), "megagirl_standing.gif"),new Color(0).white);
         PS1 =Transparency.makeColorTransparent(getImage(getDocumentBase(), "megagirl_running.gif"),new Color(0).white);
         PS2 =Transparency.makeColorTransparent(getImage(getDocumentBase(), "megagirl_jumping.gif"),new Color(0).white);
      
         buildingA= new Building(0, 410);
         buildingA.setImage(Transparency.makeColorTransparent(getImage(getDocumentBase(), "rooftop.gif"),new Color(0).black));
         EArray.add(buildingA);
         
         buildingB= new Building(-550, 410);
         buildingB.setImage(Transparency.makeColorTransparent(getImage(getDocumentBase(), "rooftop.gif"),new Color(0).black));
         EArray.add(buildingB);
         
         buildingC= new Building(550, 410);
         buildingC.setImage(Transparency.makeColorTransparent(getImage(getDocumentBase(), "rooftop.gif"),new Color(0).black));
         EArray.add(buildingC);
      	
         player = new Player(width/2, 200);
         player.setImage(Transparency.makeColorTransparent(getImage(getDocumentBase(), "megagirl_standing.gif"),new Color(0).white));
         EArray.add(player);
         
         enemy = new BirdEnemy[1];
         for(int i = 0; i < 1;i++) {
            enemy[i] = new BirdEnemy(600, 0);
            enemy[i].setImage(Transparency.makeColorTransparent(getImage(getDocumentBase(), "my_bird.gif"),new Color(0).white));
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
               
               	
            }
            
            checkCollisions();   
         	
            Rectangle playerBounds = player.getBounds();
            boolean fall = true;
           
            for (int j = 0; j < EArray.size(); j++) {
               Entity a2 = EArray.get(j);
               Rectangle r2 = a2.getBounds();
               if (playerBounds.intersects(r2)) {
                  if(a2 instanceof Building)
                     fall=false;
               }
            }
                     
            if(fall)
            {
               player.moveY(2);
            }
            
            if(player.isJumping())
            {
            	player.tick();
            	player.moveY(-4);
               
            }
            
         
            if(LEFT){
            
               for(Entity EN : EArray) {
                  if(!(EN instanceof Player))
                     EN.moveX(PLAYER_SPEED);
               }
               player.setImage(PS1);
            }
            else{
               player.setImage(PS0);
            }
         
            if(RIGHT){
               for(Entity EN : EArray) {
                  if(!(EN instanceof Player))
                     EN.moveX(-PLAYER_SPEED);
               }
               player.setImage(PS1);
            }
            else{
               player.setImage(PS0);
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
         if(key == Event.LEFT){
            LEFT=true;
         }
         else if(key == Event.RIGHT){            
            RIGHT=true;
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
            	   EArray.add(player.generateShot());
                  
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
         else if(key == 52)// 4 used for testing takeDamage
         {
            player.takeDamage(1);
         }
       
         return true;
      }
   
       public boolean keyUp(Event e, int key)
      {
         if(key == Event.LEFT)
         {
            LEFT=false;
         }
         else if(key == Event.RIGHT)
         {
            RIGHT=false;
         }
      
         return true;
      }
       
       public void setGameState( int state ){
    	   gameState = state;
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
    	   if( player.getLife() == 0)
    	   {
    		   gameState = 2;
    	   }
    		   
    	   if( gameState == 1)
    	   {
         g.drawImage(city, WIDTH+distanceMoved, 0, WIDTH, HEIGHT, this );
         g.drawImage(city, distanceMoved, 0, WIDTH, HEIGHT, this );
      

         for(Entity e : EArray) {
            g.drawImage(e.getImage(), e.getX(), e.getY(), this );
         }
         
         // draw shots
         for(int i=0; i<shots.length; i++)
         {
            if(shots[i] != null)
            {
               shots[i].drawShot(g);
            }
         }
         
         
         drawUI(g);
      
         distanceMoved--;
         distanceMoved=distanceMoved%WIDTH;
         
    	   } else {
    		   g.setColor(Color.BLACK);
    		   g.fillRect(0, 0, WIDTH, HEIGHT);
    		   
    		   g.setColor(Color.WHITE);
    		   String msg = "GAME OVER";
    		   g.drawChars(msg.toCharArray(), 0, msg.length(), 300, 200);
    		   
    		   
    	   }
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
