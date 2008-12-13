
public class Player extends Entity
{
	private final int LIFE_MAX = 10;

	private int life;
	private int weapNum;

	public int PSTATE = 0;

	/*
	 * 
	 * 0=stand 1=run 2=jump 3=fall
	 */

	public Player(int x, int y)
	{
		x_pos = x;
		y_pos = y;
		width = 45;
		height = 45;
		
		life = 10;
		weapNum = 1;
	}

	public String getLifeText()
	{
		return "LIFE " + life + "/" + LIFE_MAX;
	}

	public String getWeapText()
	{
		return "Gun# " + weapNum;
	}

	public void setWeap(int weapNum)
	{
		this.weapNum = weapNum;
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

	public void jump()
	{
		System.out.println("jump");
	}

	// generate a shot at the current position of the spaceship
	// and return this shot to the calling method
	public Shot generateShot()
	{
		Shot shot = new Shot(x_pos + (width / 2), y_pos + (height / 2), weapNum);
		return shot;
	}

}