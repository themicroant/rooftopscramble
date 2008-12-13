
public class BirdEnemy extends Enemy {

	/*
	 * Flies at the player.
	 */
	public BirdEnemy(int x, int y) {
		super(x, y);
		speed = 2;
	}
	
	public void move(int x, int y) {
		if(x >= x_pos) {
			int distance = x_pos - x;
			chase(x);
			diagonal(-(int)Math.signum(distance), 1);
		}
		
	}

}
