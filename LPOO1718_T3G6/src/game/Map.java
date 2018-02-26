package game;

import java.util.concurrent.ThreadLocalRandom;

public class Map {
	int lever = 0;
	int heroX = 1;
	int heroY = 1;
	int guardX = 8;
	int guardY = 1;
	int ogreX = 4;
	int ogreY = 1;
	int level = 1;
	int club = 0; 
	
	char map1[][] = {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X','H',' ',' ','I',' ','X',' ','G','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'X',' ','I',' ','I',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X',' ','X','X','X','X',' ','X'},
			{'X',' ','I',' ','I',' ','X','k',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}
	};
	
	char map2[][] = {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'I',' ',' ',' ','O',' ',' ',' ','k','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','H',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}
	};
	
	char map[][] = map1;
	
	public void changeMap() {
		map = map2;
		heroX = 1;
		heroY = 8;
		level++;
	}

	public void printMap() {
		for(int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	public int moveLeft(char person) {
		int x = 0;
		int y = 0;
		
		if(person == 'H') {
			x = heroX;
			y = heroY;
		}
		else if(person == 'G') {
			x = guardX;
			y = guardY;
		}
		else if(person == 'O') {
			x = ogreX;
			y = ogreY;
		}
		
		if(person == 'O') {
			if(club == 1 && map[y-1][x] != 'X' && map[y-1][x] != 'I')
				map[y-1][x] = ' ';
			else if(club == 2 && map[y][x+1] != 'X' && map[y][x+1] != 'I')
				map[y][x+1] = ' ';
			else if(club == 3 && map[y+1][x] != 'X' && map[y+1][x] != 'I')
				map[y+1][x] = ' ';
			else if(club == 4 && map[y][x-1] != 'X' && map[y][x-1] != 'I')
				map[y][x-1] = ' '; 
		}
		
		if (map[y][x-1] == 'X') {
			return 0;
		}
		else if (map[y][x-1] == 'I' && map[y][x] == 'K') {
			if (lever == 1){
				map[y][x-1] = 'S';
				map[y][x] = 'H';
			}
			
		}
		else if (map[y][x-1] == ' ') {
			if(map[y][x] == '$') {
				map[y][x] = 'k';
				map[y][x-1] = 'O';
			}
			else if (lever == 0 || person == 'O' || person == 'G') {
				map[y][x - 1] = person;
				map[y][x] = ' ';
			}
			else {
				if (level == 2) {
					map[y][x - 1] = 'K';
				}
				else {
					map[y][x - 1] = 'H';
				}

				map[y][x] = ' ';
			}
			
			if (lever == 1 && person == 'H' && level == 1) {
				map[y][x] = 'k';
				lever = 0;
			}
			x--;
		}
		else if (map[y][x-1] == 'k') {
			map[y][x] = ' ';
			if (level == 1) {
				lever = 1;
				map[y][x-1] = 'H';
				map[5][0] = 'S';
				map[6][0] = 'S';
			}
			else if (level == 2) {
				if (person == 'H') {
					lever = 1;
					map[y][x-1] = 'K';
				}
				else if (person == 'O')
					map[y][x-1] = '$';
			}	
			x--;
		}
		else if (map[y][x-1] == 'S' && map[y][x] == 'H') {
			map[y][x] = ' ';
			map[y][x-1] = person;
			return 1;
		}
		
		if(level == 2 && person == 'O') {
			club = ThreadLocalRandom.current().nextInt(1, 5);
			
			if(club == 1 && map[y-1][x] != 'X' && map[y-1][x] != 'I') {
				if (map[y-1][x] == 'k') {
					map[y-1][x] = '$';
				}
				else
					map[y-1][x] = '*';
			}
			else if(club == 2 && map[y][x+1] != 'X' && map[y][x+1] != 'I') {
				if (map[y][x+1] == 'k') {
					map[y][x+1] = '$';
				}
				else
					map[y][x+1] = '*';
			}
			else if(club == 3 && map[y+1][x] != 'X'&& map[y+1][x] != 'I') {
				if (map[y+1][x] == 'k') {
					map[y+1][x] = '$';
				}
				else
					map[y+1][x] = '*';
			}
			else if(club == 4 && map[y][y-1] != 'X'&& map[y][x-1] != 'I') {
				if (map[y][x-1] == 'k') {
					map[y][x-1] = '$';
				}
				else
					map[y][x-1] = '*';
			}
		}
		
		if (map[y][x-1] == 'G' || map[y+1][x] == 'G' || map[y-1][x] == 'G' || map[y][x+1] == 'G' || map[y][x] == '*') {
			return -1;
		}
		
		if(person == 'H') {
			heroX = x;
			heroY = y;
		}
		else if(person == 'G') {
			guardX = x;
			guardY = y;
		}
		else if(person == 'O') {
			ogreX = x;
			ogreY = y;
		}
		
		
		return 0;
	}
	
	public int moveRight(char person) {
		int x = 0;
		int y = 0;
		
		if(person == 'H') {
			x = heroX;
			y = heroY;
		}
		else if(person == 'G') {
			x = guardX;
			y = guardY;
		}
		else if(person == 'O') {
			x = ogreX;
			y = ogreY;
		}
		
		if(person == 'O') {
			if(club == 1 && map[y-1][x] != 'X' && map[y-1][x] != 'I')
				map[y-1][x] = ' ';
			else if(club == 2 && map[y][x+1] != 'X' && map[y][x+1] != 'I')
				map[y][x+1] = ' ';
			else if(club == 3 && map[y+1][x] != 'X' && map[y+1][x] != 'I')
				map[y+1][x] = ' ';
			else if(club == 4 && map[y][x-1] != 'X' && map[y][x-1] != 'I')
				map[y][x-1] = ' '; 
		}
		
		if (map[y][x+1] == 'X')
			return 0;
		else if (map[y][x+1] == 'I') {
			if (lever == 1 && map[y][x] == 'K'){
				map[y][x+1] = 'S';
				map[y][x] = 'H';
			}
		}
		else if (map[y][x+1] == ' ') {
			if(map[y][x] == '$') {
				map[y][x] = 'k';
				map[y][x+1] = 'O';
			}
			else if (lever == 0 || person == 'O' || person == 'G') {
				map[y][x+1] = person;
				map[y][x] = ' ';
			}
			else {
				if (level == 2) {
					map[y][x+1] = 'K';
				}
				else {
					map[y][x+1] = 'H';
				}
				map[y][x] = ' ';
			}
			if (lever == 1 && person == 'H' && level == 1) {
				map[y][x] = 'k';
				lever = 0;
			}
			x++;
		}
		else if (map[y][x+1] == 'k') {
			map[y][x] = ' ';
			if (level == 1) {
				lever = 1;
				map[y][x+1] = 'H';
				map[5][0] = 'S';
				map[6][0] = 'S';
			}
			else if (level == 2) {
				if (person == 'H') {
					lever = 1;
					map[y][x+1] = 'K';
				}
				else if (person == 'O')
					map[y][x+1] = '$';
			}
			x++;
		}
		else if (map[y][x+1] == 'S' && map[y][x] == 'H') {
			map[y][x] = ' ';
			map[y][x+1] = person;
			return 1;
		}
		
		if(level == 2 && person == 'O') {
			club = ThreadLocalRandom.current().nextInt(1, 5);
			
			if(club == 1 && map[y-1][x] != 'X' && map[y-1][x] != 'I') {
				if (map[y-1][x] == 'k') {
					map[y-1][x] = '$';
				}
				else
					map[y-1][x] = '*';
			}
			else if(club == 2 && map[y][x+1] != 'X' && map[y][x+1] != 'I') {
				if (map[y][x+1] == 'k') {
					map[y][x+1] = '$';
				}
				else
					map[y][x+1] = '*';
			}
			else if(club == 3 && map[y+1][x] != 'X' && map[y+1][x] != 'I') {
				if (map[y+1][x] == 'k') {
					map[y+1][x] = '$';
				}
				else
					map[y+1][x] = '*';
			}
			else if(club == 4 && map[y][x-1] != 'X' && map[y][x-1] != 'I') {
				if (map[y][x-1] == 'k') {
					map[y][x-1] = '$';
				}
				else
					map[y][x-1] = '*';
			}
		}
		
		if (map[y][x+1] == 'G' || map[y+1][x] == 'G' || map[y-1][x] == 'G' || map[y][x+1] == 'G' || map[y][x] == '*') {
			return -1;
		}
		
		if(person == 'H') {
			heroX = x;
			heroY = y;
		}
		else if(person == 'G') {
			guardX = x;
			guardY = y;
		}
		else if(person == 'O') {
			ogreX = x;
			ogreY = y;
		}
		
		return 0;
	}
	
	public int moveDown(char person) {
		int x = 0;
		int y = 0;
		
		if(person == 'H') {
			x = heroX;
			y = heroY;
		}
		else if(person == 'G') {
			x = guardX;
			y = guardY;
		}
		else if(person == 'O') {
			x = ogreX;
			y = ogreY;
		}
		
		if(person == 'O') {
			if(club == 1 && map[y-1][x] != 'X' && map[y-1][x] != 'I')
				map[y-1][x] = ' ';
			else if(club == 2 && map[y][x+1] != 'X' && map[y][x+1] != 'I')
				map[y][x+1] = ' ';
			else if(club == 3 && map[y+1][x] != 'X' && map[y+1][x] != 'I')
				map[y+1][x] = ' ';
			else if(club == 4 && map[y][x-1] != 'X' && map[y][x-1] != 'I')
				map[y][x-1] = ' '; 
		}
		
		if (map[y+1][x] == 'X')
			return 0;
		else if (map[y+1][x] == 'I') {
			if (lever == 1 && map[y][x] == 'K'){
				map[y+1][x] = 'S';
				map[y][x] = 'H';
			}
		}
		else if (map[y+1][x] == ' ') {
			if(map[y][x] == '$') {
				map[y][x] = 'k';
				map[y+1][x] = 'O';
			}
			else if (lever == 0 || person == 'O' || person == 'G') {
				map[y+1][x] = person;
				map[y][x] = ' ';
			}
			else {
				if (level == 2) {
					map[y+1][x] = 'K';
				}
				else {
					map[y+1][x] = 'H';
				}
				map[y][x] = ' ';
			}
			if (lever == 1 && person == 'H' && level == 1) {
				map[y][x] = 'k';
				lever = 0;
			}
			y++;
		}
		else if (map[y+1][x] == 'k') {
			map[y][x] = ' ';
			if (level == 1) {
				lever = 1;
				map[y+1][x] = 'H';
				map[5][0] = 'S';
				map[6][0] = 'S';
			}
			else if (level == 2) {
				if (person == 'H') {
					lever = 1;
					map[y+1][x] = 'K';
				}
				else if (person == 'O')
					map[y+1][x] = '$';
			}
			y++;
		}
		else if (map[y+1][x] == 'S' && map[y][x] == 'H') {
			map[y][x] = ' ';
			map[y+1][x] = person;
			return 1;
		}
		
		if(level == 2 && person == 'O') {
			club = ThreadLocalRandom.current().nextInt(1, 5);
			
			if(club == 1 && map[y-1][x] != 'X' && map[y-1][x] != 'I') {
				if (map[y-1][x] == 'k') {
					map[y-1][x] = '$';
				}
				else
					map[y-1][x] = '*';
			}
			else if(club == 2 && map[y][x+1] != 'X' && map[y][x+1] != 'I') {
				if (map[y][x+1] == 'k') {
					map[y][x+1] = '$';
				}
				else
					map[y][x+1] = '*';
			}
			else if(club == 3 && map[y+1][x] != 'X' && map[y+1][x] != 'I') {
				if (map[y+1][x] == 'k') {
					map[y+1][x] = '$';
				}
				else
					map[y+1][x] = '*';
			}
			else if(club == 4 && map[y][x-1] != 'X' && map[y][x-1] != 'I') {
				if (map[y][x-1] == 'k') {
					map[y][x-1] = '$';
				}
				else
					map[y][x-1] = '*';
			}
		}
		
		if (map[y+1][x] == 'G' || map[y][x+1] == 'G' || map[y][x-1] == 'G' || map[y+1][x] == 'G' || map[y][x] == '*') {
			return -1;
		}
		
		if(person == 'H') {
			heroX = x;
			heroY = y;
		}
		else if(person == 'G') {
			guardX = x;
			guardY = y;
		}
		else if(person == 'O') {
			ogreX = x;
			ogreY = y;
		}
		
		return 0;
	}
	
	public int moveUp(char person) {
		int x = 0;
		int y = 0;
		
		if(person == 'H') {
			x = heroX;
			y = heroY;
		}
		else if(person == 'G') {
			x = guardX;
			y = guardY;
		}
		else if(person == 'O') {
			x = ogreX;
			y = ogreY;
		}
		
		if(person == 'O') {
			if(club == 1 && map[y-1][x] != 'X' && map[y-1][x] != 'I')
				map[y-1][x] = ' ';
			else if(club == 2 && map[y][x+1] != 'X' && map[y][x+1] != 'I')
				map[y][x+1] = ' ';
			else if(club == 3 && map[y+1][x] != 'X' && map[y+1][x] != 'I')
				map[y+1][x] = ' ';
			else if(club == 4 && map[y][x-1] != 'X' && map[y][x-1] != 'I')
				map[y][x-1] = ' '; 
		}
		
		if (map[y-1][x] == 'X')
			return 0;
		else if (map[y-1][x] == 'I') {
			if (lever == 1 && map[y][x] == 'K'){
				map[y-1][x] = 'S';
				map[y][x] = 'H';
			}
		}
		else if (map[y-1][x] == ' ') {
			if(map[y][x] == '$') {
				map[y][x] = 'k';
				map[y-1][x] = 'O';
			}
			else if (lever == 0 || person == 'O' || person == 'G') {
				map[y-1][x] = person;
				map[y][x] = ' ';
			}
			else {
				if (level == 2) {
					map[y-1][x] = 'K';
				}
				else {
					map[y-1][x] = 'H';
				}
				

				map[y][x] = ' ';
			}
			if (lever == 1 && person == 'H' && level == 1) {
				map[y][x] = 'k';
				lever = 0;
			}
			y--;
		}
		else if (map[y-1][x] == 'k') {
			map[y][x] = ' ';
			if (level == 1) {
				lever = 1;
				map[y-1][x] = 'H';
				map[5][0] = 'S';
				map[6][0] = 'S';
			}
			else if (level == 2) {
				if (person == 'H') {
					lever = 1;
					map[y-1][x] = 'K';
				}
				else if (person == 'O')
					map[y-1][x] = '$';
			}
			y--;
		}
		else if (map[y-1][x] == 'S' && map[y][x] == 'H') {
			map[y][x] = ' ';
			map[y-1][x] = person;
			return 1;
		}
		
		if(level == 2 && person == 'O') {
			club = ThreadLocalRandom.current().nextInt(1, 5);
			
			if(club == 1 && map[y-1][x] != 'X' && map[y-1][x] != 'I') {
				if (map[y-1][x] == 'k') {
					map[y-1][x] = '$';
				}
				else
					map[y-1][x] = '*';
			}
			else if(club == 2 && map[y][x+1] != 'X' && map[y][x+1] != 'I') {
				if (map[y][x+1] == 'k') {
					map[y][x+1] = '$';
				}
				else
					map[y][x+1] = '*';
			}
			else if(club == 3 && map[y+1][x] != 'X' && map[y+1][x] != 'I') {
				if (map[y+1][x] == 'k') {
					map[y+1][x] = '$';
				}
				else
					map[y+1][x] = '*';
			}
			else if(club == 4 && map[y][x-1] != 'X' && map[y][x-1] != 'I') {
				if (map[y][x-1] == 'k') {
					map[y][x-1] = '$';
				}
				else
					map[y][x-1] = '*';
			}
		}
		
		if (map[y-1][x] == 'G' || map[y][x-1] == 'G' || map[y][x+1] == 'G' || map[y-1][x] == 'G' || map[y-1][x] == '*') {
			return -1;
		}
		
		if(person == 'H') {
			heroX = x;
			heroY = y;
		}
		else if(person == 'G') {
			guardX = x;
			guardY = y;
		}
		else if(person == 'O') {
			ogreX = x;
			ogreY = y;
		}
		
		return 0;
	}
}
