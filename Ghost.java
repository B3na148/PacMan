package Default;
import processing.core.PVector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Ghost extends Player{
public static boolean finish = false;
public static int count = 1;
public char dir;
public int[][] mazeSol = new int[13][18];
	public Ghost() {
		super(	new PVector(660, 460),
				new PVector((int)660/40, (int)460/40),
				2,
				new PVector(46,149,240)
				);
		this.dir = 'A';
	}
	
	public int[][] ghostCalc(PacMain object) {
		int[][] map = new int[object.map.map1.length][];
		for (int i = 0; i < object.map.map1.length; i++) {
		    map[i] = object.map.map1[i].clone();
		}
		ArrayList<PVector> seen = new ArrayList<>();
		ArrayList<PVector> news = new ArrayList<>();
		map[(int)object.pac.grid.y][(int)object.pac.grid.x] = 4;
		seen.add(this.grid);
		//loop until found best route
		while (!finish) {
			for (int i = 0; i < seen.size(); i ++) {
				news.addAll(findNiceGrids(map, seen.get(i)));
			}
			count ++;
			seen = new ArrayList<>(news);
			news.clear();
		}
		return map;
	}
	//get position and map and return Nice and not conqared grids 
	public static ArrayList<PVector> findNiceGrids(int[][] map, PVector point){
		ArrayList<PVector> niceGrids = new ArrayList<>();
		//search around our grid
		//up
		if (map[(int)point.y - 1][(int)point.x] == 4 || map[(int)point.y - 1][(int)point.x] == 0) {
			niceGrids.add(new PVector((int)point.x, (int)point.y - 1));
			if (map[(int)point.y - 1][(int)point.x] == 4) {
				map[(int)point.y][(int)point.x] = -count;
				finish = true;
				return niceGrids;
			}
		}
		//down
		if (map[(int)point.y + 1][(int)point.x] == 4 || map[(int)point.y + 1][(int)point.x] == 0) {
		    niceGrids.add(new PVector(point.x, point.y + 1));
		    if (map[(int)point.y + 1][(int)point.x] == 4) {
		    	map[(int)point.y][(int)point.x] = -count;
		        finish = true;
		        return niceGrids;
		    }
		}
		//left
		if (map[(int)point.y][(int)point.x - 1] == 4 || map[(int)point.y][(int)point.x - 1] == 0) {
		    niceGrids.add(new PVector(point.x - 1, point.y));
		    if (map[(int)point.y][(int)point.x - 1] == 4) {
		    	map[(int)point.y][(int)point.x] = -count;
		        finish = true;
		        return niceGrids;
		    }
		}
		//right
		if (map[(int)point.y][(int)point.x + 1] == 4 || map[(int)point.y][(int)point.x + 1] == 0) {
		    niceGrids.add(new PVector(point.x + 1, point.y));
		    if (map[(int)point.y][(int)point.x + 1] == 4) {
		    	map[(int)point.y][(int)point.x] = -count;
		        finish = true;
		        return niceGrids;
		    }
		}
		
		
		map[(int)point.y][(int)point.x] = -count;
		return niceGrids;
	}
	
	public int[][] finalpath (int[][] map, PacMain object) {
		int[][] path = new int[object.map.map1.length][];
		for (int i = 0; i < object.map.map1.length; i++) {
		    path[i] = object.map.map1[i].clone();
		}
		int count = -1;
		int right = 0, left = 0, up = 0, down = 0;
		int x = (int)object.pac.grid.x;
		int y = (int)object.pac.grid.y;
		//make the path -1 -2 ... and after this make pac go over them
		
		while (map[y][x] != -1) {
			
			path[y][x] = count;
			right = map[y][x + 1];
			if (right >= 0)right = -100;
			left = map[y][x - 1];
			if (left >= 0)left = -100;
			up = map[y - 1][x];
			if (up >= 0)up = -100;
			down = map[y + 1][x];
			if (down >= 0)down = -100;
			
			//find lowest nabre = fastest way to ghost and mark it
			if (right >= left && right >= up && right >= down) x += 1;
			else if (left >= right && left >= up && left >= down) x -= 1;
			else if (up >= down && up >= left && up >= right) y -= 1;
			else if (down >= up && down >= left && down >= right) y += 1;
			count --;
		}
		return path;
	}
	public void ghostDir(PacMain object) {
		finish = false;
		count = 1;
		int[][] map = finalpath(ghostCalc(object), object);
		map[(int)object.pac.grid.y][(int)object.pac.grid.x] = -1000;
		
		int up = map[(int)this.grid.y - 1][(int)this.grid.x];
		int down = map[(int)this.grid.y + 1][(int)this.grid.x];
		int left = map[(int)this.grid.y][(int)this.grid.x - 1];
		int right = map[(int)this.grid.y][(int)this.grid.x + 1];
		
		if (up <= down && up <= right && up <= left)this.dir = 'U';
		else if (down <= up && down <= right && down <= left)this.dir = 'D';
		else if (left <= down && left <= up && left <= right)this.dir = 'L';
		else if (right <= left && right <= up && right <= down)this.dir = 'R';
		
		
		
	}
	
	
}
