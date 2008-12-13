import java.awt.Graphics;
import java.awt.Color;

public class Shot extends Entity
{

	private int radius; // size of the shot
	private Color color; // color of shot
	private int speed; // speed of shot

	// Constructor
	public Shot(int x, int y, int type)
	{
		if (type == 1) {
			radius = 8;
			color = Color.GREEN;
			speed = 5;

		} else if (type == 2) {
			radius = 12;
			color = Color.RED;
			speed = 4;

		} else if (type == 3) {
			radius = 4;
			color = Color.BLUE;
			speed = 6;

		} else {
			radius = 8;
			color = Color.WHITE;
			speed = 5;
		}

		x_pos = x;
		y_pos = y;
		width = radius;
		height = radius;
	}

	// moving shot in y direction
	public void tick()
	{
		x_pos += speed;
	}

	// draw the shot to the screen
	public void drawShot(Graphics g)
	{
		g.setColor(color);
		g.fillOval(x_pos - (radius / 2), y_pos - (radius / 2), radius, radius);

	}
}