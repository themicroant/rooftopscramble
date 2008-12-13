
public class RobotEnemy extends Enemy {

	/*
	 * Flies at the player.
	 */
	public RobotEnemy(int x, int y) {
		super(x, y);
	}
	
	public void move(int x, int y) {
		if(x >= x_pos) {
			moveX();
		}
		
	}

}
