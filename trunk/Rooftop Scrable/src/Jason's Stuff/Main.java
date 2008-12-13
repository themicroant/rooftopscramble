import java.applet.*;
import java.awt.*;

public class Main extends Applet implements Runnable
{
	private volatile Thread thread;
	private int width;
	private int height;
	private Player player;
	private Enemy enemy;
	private Shot[] shots;
	private int distanceMoved = 0;
	

	// constants
	private int PLAYER_SPEED = 3;
	private int SHOT_SPEED = 5;
	private static final int TILE_SIZE = 25;
	
	// move flags
	private boolean playerMoveLeft;
	private boolean playerMoveRight;
	private boolean playerMoveUp;
	private boolean playerMoveDown;

	// double buffering
	private Image dbImage;
	private Graphics dbg;
    private Image jetImage, grass;

	public void init()
	{
		width = getWidth();
		height = getHeight();
		player = new Player(width/2, height/2);
		enemy = new Enemy( 127, 117);
		shots = new Shot[5];
		jetImage = getImage( getDocumentBase(), "Jet.JPG" );
		grass = getImage( getDocumentBase(), "Grass.JPG" );		
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
			// do operations on shots in shots array
			for(int i=0; i<shots.length; i++)
			{
				if(shots[i] != null)
				{
					// move shot
					shots[i].moveShot(-SHOT_SPEED);

					// test if shot is out
					if(shots[i].getYPos() < 0)
					{
						// remove shot from array
						shots[i] = null;
					} 

					// other operations
					// ...
					// test for collisions with enemies
					// ...
				}
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
			if(playerMoveUp && player.getY() > 0)
			{
				player.moveY(-PLAYER_SPEED);
			}
			else if(playerMoveDown && player.getY() < height)
			{
				player.moveY(PLAYER_SPEED);
			}

			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException ex)
			{
				// do nothing
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
			playerMoveUp = true;
		}
		else if(key == Event.DOWN)
		{
			playerMoveDown = true;
		}
		else if(key == 32 )
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
		else if(key == Event.UP)
		{
			playerMoveUp = false;
		}
		else if(key == Event.DOWN)
		{
			playerMoveDown = false;
		}

		return true;
	}

	public void update (Graphics g)
	{
		if(dbImage == null)
		{
			dbImage = createImage(width, height);
			dbg = dbImage.getGraphics();
		}
		
		dbg.setColor(getBackground());
		dbg.fillRect(0, 0, width, height);
		
		paint(dbg);
		g.drawImage (dbImage, 0, 0, this);
	}

	public void paint (Graphics g)
	{
		for(int x=0; x<width/TILE_SIZE; x++)
		{
			for(int y=0; y<(height/TILE_SIZE) + 1; y++)
			{
				g.drawImage(grass, x*TILE_SIZE, ((y-1)*TILE_SIZE) - distanceMoved, this );
			}
		}

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
		g.drawImage(jetImage, player.getX()-26, player.getY()-35, this );
		enemy.drawEnemy(g);
		
		distanceMoved = (distanceMoved%TILE_SIZE) - 1;
		
	}
}
