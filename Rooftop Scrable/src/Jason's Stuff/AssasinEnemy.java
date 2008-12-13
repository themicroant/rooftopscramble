
public class AssasinEnemy extends Enemy {

	/*
	 * Flies at the player.
	 */
	public AssasinEnemy(int x, int y) {
		super(x, y);
	}
	
	public void move(int x, int y) {
		if(x >= x_pos) {
			moveX();
		}
		
	}

}
