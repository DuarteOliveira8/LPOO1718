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
	
	public void removeClub() {
		if(club == 1 && map[ogreY-1][ogreX] != 'X'  && map[ogreY-1][ogreX] != 'I') { // CIMA
			if(map[ogreY-1][ogreX] == '$')
				map[ogreY-1][ogreX] = 'k';
			else
				map[ogreY-1][ogreX] = ' ';
		}
		else if(club == 2 && map[ogreY][ogreX+1] != 'X'  && map[ogreY][ogreX+1] != 'I') { // DIREITA
			if(map[ogreY][ogreX+1] == '$')
				map[ogreY][ogreX+1] = 'k';
			else
				map[ogreY][ogreX+1] = ' ';
		}
		else if(club == 3 && map[ogreY+1][ogreX] != 'X'  && map[ogreY+1][ogreX] != 'I') { // BAIXO
			if(map[ogreY+1][ogreX] == '$')
				map[ogreY+1][ogreX] = 'k';
			else
				map[ogreY+1][ogreX] = ' ';
		}
		else if(club == 4 && map[ogreY][ogreX-1] != 'X'  && map[ogreY][ogreX-1] != 'I') { // ESQUERDA
			if(map[ogreY][ogreX-1] == '$')
				map[ogreY][ogreX-1] = 'k';
			else
				map[ogreY][ogreX-1] = ' ';
		}
	}
	
	public void addClub() {
		club = ThreadLocalRandom.current().nextInt(1, 5);
		
		if(club == 1 && map[ogreY-1][ogreX] != 'X'  && map[ogreY-1][ogreX] != 'I') { // CIMA
			if(map[ogreY-1][ogreX] == 'k')
				map[ogreY-1][ogreX] = '$';
			else
				map[ogreY-1][ogreX] = '*';
		}
		else if(club == 2 && map[ogreY][ogreX+1] != 'X'  && map[ogreY][ogreX+1] != 'I') { // DIREITA
			if(map[ogreY][ogreX+1] == 'k')
				map[ogreY][ogreX+1] = '$';
			else
				map[ogreY][ogreX+1] = '*';
		}
		else if(club == 3 && map[ogreY+1][ogreX] != 'X'  && map[ogreY+1][ogreX] != 'I') { // BAIXO
			if(map[ogreY+1][ogreX] == 'k')
				map[ogreY+1][ogreX] = '$';
			else
				map[ogreY+1][ogreX] = '*';
		}
		else if(club == 4 && map[ogreY][ogreX-1] != 'X'  && map[ogreY][ogreX-1] != 'I') { // ESQUERDA
			if(map[ogreY][ogreX-1] == 'k')
				map[ogreY][ogreX-1] = '$';
			else
				map[ogreY][ogreX-1] = '*';
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
		
		if (person == 'H' && level == 2) {
			if (map[y][x-1] == '*') {
				if (lever == 0)
					map[y][x-1] = 'H';
				else
					map[y][x-1] = 'K';
				map[y][x] = ' ';
				return -1;
			} 
		}
		
		if (map[y][x-1] == 'I' && map[y][x] == 'K') {
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
				map[y][x-1] = person;
				map[y][x] = ' ';
			}
			else {
				if (level == 2) {
					map[y][x-1] = 'K';
				}
				else {
					map[y][x-1] = 'H';
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

		
		if (person == 'H' && level == 1) {
			if (map[y][x-1] == 'G' || map[y+1][x] == 'G' || map[y-1][x] == 'G' || map[y][x+1] == 'G') {
				return -1;
			} 
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

		if (person == 'H') {
			if (map[y][x+1] == '*') {
				if (lever == 0)
					map[y][x+1] = 'H';
				else
					map[y][x+1] = 'K';
				map[y][x] = ' ';
				return -1;
			} 
		}
		if (map[y][x+1] == 'I') {
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
		
		if (person == 'H' && level == 1) {
			if (map[y][x-1] == 'G' || map[y+1][x] == 'G' || map[y-1][x] == 'G' || map[y][x+1] == 'G') {
				return -1;
			} 
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
		
		if (person == 'H') {
			if (map[y+1][x] == '*') {
				if (lever == 0)
					map[y+1][x] = 'H';
				else
					map[y+1][x] = 'K';
				map[y][x] = ' ';
				return -1;
			} 
		}
		if (map[y+1][x] == 'I') {
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
		
		if (person == 'H' && level == 1) {
			if (map[y][x-1] == 'G' || map[y+1][x] == 'G' || map[y-1][x] == 'G' || map[y][x+1] == 'G') {
				return -1;
			} 
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
		
		if (person == 'H') {
			if (map[y-1][x] == '*') {
				if (lever == 0)
					map[y-1][x] = 'H';
				else
					map[y-1][x] = 'K';
				map[y][x] = ' ';
				return -1;
			}
		}
		
		if (map[y-1][x] == 'I') {
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
		
		if (person == 'H' && level == 1) {
			if (map[y][x-1] == 'G' || map[y+1][x] == 'G' || map[y-1][x] == 'G' || map[y][x+1] == 'G') {
				return -1;
			} 
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