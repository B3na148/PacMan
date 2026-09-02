package Default;
import processing.core.PVector;

public class Pac extends Player{
public char desired_direction;
public char current_direction;
	public Pac (){
		super(	new PVector((int)60,(int)60),
				new PVector(1,1),
				5,
				new PVector(248,10,252)
				);
		this.current_direction = 'A';
		this.desired_direction = 'A';
	}
	
	public void checkDesired(PacMain object) {
		int[][] map = object.map.map1;
		PVector grid = object.pac.grid;
		char des = object.pac.desired_direction;
		//here i just want to check if desiredDirection
		//more accurate her grid value is fine for moving there
		if (des == 'U') {
			if (map[(int)grid.y - 1][(int)(grid.x)] != 1) {
				object.pac.current_direction = 'U';
				object.pac.speed = 4;
			}
		}
		else if (des == 'D') {
		    if (map[(int)grid.y + 1][(int)(grid.x)] != 1) {
		        object.pac.current_direction = 'D';
		        object.pac.speed = 4;
		    }
		}
		else if (des == 'L') {
		    if (map[(int)(grid.y)][(int)grid.x - 1] != 1) {
		        object.pac.current_direction = 'L';
		        object.pac.speed = 4;
		    }
		}
		else if (des == 'R') {
		    if (map[(int)(grid.y)][(int)grid.x + 1] != 1) {
		        object.pac.current_direction = 'R';
		        object.pac.speed = 4;
		    }
		}
		else if (des == 'A') {
			object.pac.current_direction = object.pac.desired_direction;
			object.pac.speed = 4;
			
		}
		
	}
	public void checkDirection(PacMain object) {
		// 18 rows and 13 columns
		// so its 40 by 40 for every block
		int[][] map = object.map.map1;
		char dir = object.pac.current_direction;
		if (dir == 'U') {
			if (map[(int)object.pac.grid.y - 1][(int)object.pac.grid.x] == 1) {
				object.pac.speed = 0;
			}
		}
		else if (dir == 'D') {
		    if (map[(int)object.pac.grid.y + 1][(int)object.pac.grid.x] == 1) {
		        object.pac.speed = 0;
		    }
		}
		else if (dir == 'L') {
		    if (map[(int)object.pac.grid.y][(int)object.pac.grid.x - 1] == 1) {
		        object.pac.speed = 0;
		    }
		}
		else if (dir == 'R') {
		    if (map[(int)object.pac.grid.y][(int)object.pac.grid.x + 1] == 1) {
		        object.pac.speed = 0;
		    }
		}
		
	}

	
	
	
}
