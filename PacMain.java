package Default;

import processing.core.PApplet;
import processing.core.PVector;

public class PacMain extends PApplet {
Pac pac;
Ghost ghost;
Maps map;
int coinCount;

	public void settings() {
		size(720,520);
		//18 by 13 size map
	}
	public void setup() {
		frameRate(60);
		coinCount = 0;
		pac = new Pac();
		map = new Maps();
		ghost = new Ghost();
	}
	
	
	
	public void draw() {
		
		pac.grid = (new PVector(pac.pos.x / 40, pac.pos.y / 40));
		ghost.grid = (new PVector(ghost.pos.x / 40, ghost.pos.y / 40));	
		
		map.draw_map(this);
		fill(pac.color.x, pac.color.y, pac.color.z);
		ellipse(pac.pos.x, pac.pos.y, 30, 30);
		
		fill(ghost.color.x, ghost.color.y, ghost.color.z);
		ellipse(ghost.pos.x ,ghost.pos.y, 30, 30);
		coin_check();
		
		fill(pac.color.x, pac.color.y, pac.color.z);
		if ((pac.pos.x - 20) % 40 == 0 && (pac.pos.y - 20) % 40 == 0) {				
			pac.checkDesired(this);
			pac.checkDirection(this);
		}
		if ((ghost.pos.x - 20) % 40 == 0 && (ghost.pos.y - 20) % 40 ==0)ghost.ghostDir(this);
		
		
		if (keyPressed) {
			if (key == 'w' || key == 'W') pac.desired_direction = 'U';
			else if (key == 's' || key == 'S') pac.desired_direction = 'D';
			else if (key == 'a' || key == 'A') pac.desired_direction = 'L';
			else if (key == 'd' || key == 'D') pac.desired_direction = 'R';
		}
		movePac();
		moveGhost();
	}
	
	
	public void moveGhost() {
		if (ghost.dir == 'R')ghost.pos.x += ghost.speed;
		else if (ghost.dir == 'L')ghost.pos.x -= ghost.speed;
		else if (ghost.dir == 'U')ghost.pos.y -= ghost.speed;
		else if (ghost.dir == 'D')ghost.pos.y += ghost.speed;
	}
	public void movePac() {
		if (pac.current_direction == 'U') {
			pac.pos.y -= pac.speed;
		}
		else if (pac.current_direction == 'D') {
			pac.pos.y += pac.speed;
		}
		else if (pac.current_direction == 'R') {
			pac.pos.x += pac.speed;
		}
		else if (pac.current_direction == 'L') {
			pac.pos.x -= pac.speed;
		}
		
	}
	//closing the processes after code run.
	@Override
	public void exit() {
	  dispose();
	  System.exit(0);
	}
	public void coin_check() {
		
		if (map.map1[(int)(pac.pos.y / 40)][(int)(pac.pos.x / 40)] == 2) {
			map.map1[(int)(pac.pos.y / 40)][(int)(pac.pos.x / 40)] = 0;
			coinCount ++;
		}
		fill(255);
		textSize(35);
		text("coins:" + coinCount , 20, 35);
		if (coinCount == 6) {
			textSize(70);
			text("victory!",(width/2) - 200, height / 2);
		}
		
	}
	
	public static void main(String[] args) {
		PApplet.main(PacMain.class.getName());
		
	}

}
