
public class BirdEnemy extends Enemy {

	/*
	 * Flies at the player.
	 */
	public BirdEnemy(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void move(int x, int y) {
		chase(x);
		diagonal(1, 1);
	}

}
