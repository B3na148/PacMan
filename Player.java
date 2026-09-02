package Default;
import processing.core.PVector;
public class Player {

public PVector pos;
public PVector grid;
public int speed;
public PVector color;

	public Player(PVector pos,PVector grid, int speed, PVector color) {
		this.pos = pos;
		this.grid = grid;
		this.speed = speed;
		this.color = color;
	}
	
}
