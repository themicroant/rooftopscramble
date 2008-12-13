
import java.awt.Graphics;
import java.awt.Color;

public class Shot extends Entity
{

	private Color color; // color of shot
	private int radius = 8; // size of the shot

	// Constructor
	public Shot(int x, int y, int type)
	{
		x_pos = x;
		y_pos = y;
		
		if (type == 1) {
			radius = 8;
			color = Color.BLACK;
		} else if (type == 2) {
			radius = 12;
			color = Color.RED;
		} else if (type == 3) {
			radius = 4;
			color = Color.BLUE;
		} else {
			radius = 8;
			color = Color.WHITE;
		}
	}

	// moving shot in y direction
	public void tick()
	{
		x_pos += 5;
	}

	// draw the shot to the screen
	public void drawShot(Graphics g)
	{
		g.setColor(color);
		g.fillOval(x_pos - (radius / 2), y_pos - (radius / 2), radius, radius);

	}
}