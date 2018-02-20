package game;

public class Map {
	int lever = 0;
	
	char map[][] = {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X','H',' ',' ','I',' ','X',' ','G','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'X',' ','I',' ','I',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X',' ','X','X','X','X',' ','X'},
			{'X',' ','I',' ','I',' ','X','K',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}
	};

	public void printMap() {
		for(int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	public int moveLeft(char person, Integer x, Integer y) {
		if (map[y][x-1] == 'X' || map[y][x-1] == 'I')
			return 0;
		else if (map[y][x-1] == ' ') {
			map[y][x] = ' ';
			map[y][x-1] = person;
			x--;
		}
		else if (map[y][x-1] == 'K') {
			map[y][x] = ' ';
			map[y][x-1] = person;
			x--;
			lever = 1;
			map[5][0] = 'S';
			map[6][0] = 'S';
		}
		else if (map[y][x-1] == 'S') {
			map[y][x] = ' ';
			map[y][x-1] = person;
			return 1;
		}
		
		if (map[y][x-1] == 'G' || map[y+1][x] == 'G' || map[y-1][x] == 'G' || map[y][x+1] == 'G') {
			return -1;
		}
		
		return 0;
	}
	
	public int moveRight(char person, Integer x, Integer y) {
		if (map[y][x+1] == ' ') {
			map[y][x] = ' ';
			map[y][x+1] = person;
			x++;
		}
		else if (map[y][x+1] == 'G') {
			
		}
		else if (map[y][x+1] == 'K') {
//			map[y][x] = ' ';
//			map[y][x-1] = person;
//			x--;
//			kex = 1;
		}
		
		if (lever == 1) {
			map[y][x-1] = 'K';
		}
		
		if (map[y][x-1] == 'G' || map[y+1][x] == 'G' || map[y-1][x] == 'G' || map[y][x+1] == 'G') {
			return -1;
		}
		
		return 0;
	}
	
	public int moveUp(char person, Integer x, Integer y) {
		if (map[y-1][x] == ' ') {
			map[y][x] = ' ';
			map[y-1][x] = person;
			y--;
		}
		else if (map[y-1][x] == 'G') {
			
		}
		else if (map[y-1][x] == 'K') {
//			map[y][x] = ' ';
//			map[y][x-1] = person;
//			x--;
//			kex = 1;
		}
		
		if (map[y][x-1] == 'G' || map[y+1][x] == 'G' || map[y-1][x] == 'G' || map[y][x+1] == 'G') {
			return -1;
		}
		
		return 0;
	}
	
	public int moveDown(char person, Integer x, Integer y) {
		if (map[y+1][x] == ' ') {
			map[y][x] = ' ';
			map[y+1][x] = person;
			y++;
		}
		else if (map[y+1][x] == 'G') {
			
		}
		else if (map[y+1][x] == 'K') {
//			map[y][x] = ' ';
//			map[y][x-1] = person;
//			x--;
//			keyd = 1;
		}
		
		if (map[y][x-1] == 'G' || map[y+1][x] == 'G' || map[y-1][x] == 'G' || map[y][x+1] == 'G') {
			return -1;
		}
		
		return 0;
	}
}
