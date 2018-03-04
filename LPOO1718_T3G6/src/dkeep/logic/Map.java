package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;
import dkeep.logic.CurrentMap;

public class Map {
	int heroX = 1;
	int heroY = 1;
	int guardX = 8;
	int guardY = 1;
//	int ogreX = 4;
//	int ogreY = 1;
	//int club = 0; 
	
	
	
	char map1[][] = {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ','I',' ','X',' ',' ','X'},
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
			{'I',' ',' ',' ',' ',' ',' ',' ','k','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}
	};
	
	CurrentMap currentMap = new CurrentMap(map1);
	
	public void printMap() {
		currentMap.printMap();
	}

	public void changeMap() {
		currentMap.map = map2;
		currentMap.map[1][4] = 'O';
		currentMap.map[8][1] = 'H';
		currentMap.level++;
	}

//	public void removeClub() {
//		if(club == 1 && currentMap.map[ogreY-1][ogreX] != 'X'  && currentMap.map[ogreY-1][ogreX] != 'I') { // CIMA
//			if(currentMap.map[ogreY-1][ogreX] == '$')
//				currentMap.map[ogreY-1][ogreX] = 'k';
//			else
//				currentMap.map[ogreY-1][ogreX] = ' ';
//		}
//		else if(club == 2 && currentMap.map[ogreY][ogreX+1] != 'X'  && currentMap.map[ogreY][ogreX+1] != 'I') { // DIREITA
//			if(currentMap.map[ogreY][ogreX+1] == '$')
//				currentMap.map[ogreY][ogreX+1] = 'k';
//			else
//				currentMap.map[ogreY][ogreX+1] = ' ';
//		}
//		else if(club == 3 && currentMap.map[ogreY+1][ogreX] != 'X'  && currentMap.map[ogreY+1][ogreX] != 'I') { // BAIXO
//			if(currentMap.map[ogreY+1][ogreX] == '$')
//				currentMap.map[ogreY+1][ogreX] = 'k';
//			else
//				currentMap.map[ogreY+1][ogreX] = ' ';
//		}
//		else if(club == 4 && currentMap.map[ogreY][ogreX-1] != 'X'  && currentMap.map[ogreY][ogreX-1] != 'I') { // ESQUERDA
//			if(currentMap.map[ogreY][ogreX-1] == '$')
//				currentMap.map[ogreY][ogreX-1] = 'k';
//			else
//				currentMap.map[ogreY][ogreX-1] = ' ';
//		}
//	}
//	
//	public void addClub() {
//		club = ThreadLocalRandom.current().nextInt(1, 5);
//		
//		if(club == 1 && currentMap.map[ogreY-1][ogreX] != 'X'  && currentMap.map[ogreY-1][ogreX] != 'I') { // CIMA
//			if(currentMap.map[ogreY-1][ogreX] == 'k')
//				currentMap.map[ogreY-1][ogreX] = '$';
//			else
//				currentMap.map[ogreY-1][ogreX] = '*';
//		}
//		else if(club == 2 && currentMap.map[ogreY][ogreX+1] != 'X'  && currentMap.map[ogreY][ogreX+1] != 'I') { // DIREITA
//			if(currentMap.map[ogreY][ogreX+1] == 'k')
//				currentMap.map[ogreY][ogreX+1] = '$';
//			else
//				currentMap.map[ogreY][ogreX+1] = '*';
//		}
//		else if(club == 3 && currentMap.map[ogreY+1][ogreX] != 'X'  && currentMap.map[ogreY+1][ogreX] != 'I') { // BAIXO
//			if(currentMap.map[ogreY+1][ogreX] == 'k')
//				currentMap.map[ogreY+1][ogreX] = '$';
//			else
//				currentMap.map[ogreY+1][ogreX] = '*';
//		}
//		else if(club == 4 && currentMap.map[ogreY][ogreX-1] != 'X'  && currentMap.map[ogreY][ogreX-1] != 'I') { // ESQUERDA
//			if(currentMap.map[ogreY][ogreX-1] == 'k')
//				currentMap.map[ogreY][ogreX-1] = '$';
//			else
//				currentMap.map[ogreY][ogreX-1] = '*';
//		}
//	}

	public int moveLeft(char person, int level) {
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
			if (currentMap.map[y][x-1] == '*') {
				if (lever == 0)
					currentMap.map[y][x-1] = 'H';
				else
					currentMap.map[y][x-1] = 'K';
				currentMap.map[y][x] = ' ';
				return -1;
			} 
		}
		
		if (currentMap.map[y][x-1] == 'I' && currentMap.map[y][x] == 'K') {
			if (lever == 1){
				currentMap.map[y][x-1] = 'S';
				currentMap.map[y][x] = 'H';
			}
		}
		else if (currentMap.map[y][x-1] == ' ') {
			if(currentMap.map[y][x] == '$') {
				currentMap.map[y][x] = 'k';
				currentMap.map[y][x-1] = 'O';
			}
			else if (lever == 0 || person == 'O' || person == 'G') {
				currentMap.map[y][x-1] = person;
				currentMap.map[y][x] = ' ';
			}
			else {
				if (level == 2) {
					currentMap.map[y][x-1] = 'K';
				}
				else {
					currentMap.map[y][x-1] = 'H';
				}

				currentMap.map[y][x] = ' ';
			}

			if (lever == 1 && person == 'H' && level == 1) {
				currentMap.map[y][x] = 'k';
				lever = 0;
			}
			x--;
		}
		else if (currentMap.map[y][x-1] == 'k') {
			currentMap.map[y][x] = ' ';
			if (level == 1) {
				lever = 1;
				currentMap.map[y][x-1] = 'H';
				currentMap.map[5][0] = 'S';
				currentMap.map[6][0] = 'S';
			}
			else if (level == 2) {
				if (person == 'H') {
					lever = 1;
					currentMap.map[y][x-1] = 'K';
				}
				else if (person == 'O')
					currentMap.map[y][x-1] = '$';
			}	
			x--;
		}
		else if (currentMap.map[y][x-1] == 'S' && currentMap.map[y][x] == 'H') {
			currentMap.map[y][x] = ' ';
			currentMap.map[y][x-1] = person;
			return 1;
		}

		
		if (person == 'H' && level == 1) {
			if (currentMap.map[y][x-1] == 'G' || currentMap.map[y+1][x] == 'G' || currentMap.map[y-1][x] == 'G' || currentMap.map[y][x+1] == 'G') {
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

	public int moveRight(char person, int level) {
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

		if (person == 'H') { //class heroi
			if (currentMap.map[y][x+1] == '*') {
				if (lever == 0)
					currentMap.map[y][x+1] = 'H';
				else
					currentMap.map[y][x+1] = 'K';
				currentMap.map[y][x] = ' ';
				return -1;
			} 
		}
		
		if (currentMap.map[y][x+1] == 'I') {
			if (lever == 1 && currentMap.map[y][x] == 'K'){
				currentMap.map[y][x+1] = 'S';
				currentMap.map[y][x] = 'H';
			}
		}
		else if (currentMap.map[y][x+1] == ' ') {
			if(currentMap.map[y][x] == '$') {
				currentMap.map[y][x] = 'k';
				currentMap.map[y][x+1] = 'O';
			}
			else if (lever == 0 || person == 'O' || person == 'G') {
				currentMap.map[y][x+1] = person;
				currentMap.map[y][x] = ' ';
			}
			else {
				if (level == 2) {
					currentMap.map[y][x+1] = 'K';
				}
				else {
					currentMap.map[y][x+1] = 'H';
				}
				currentMap.map[y][x] = ' ';
			}
			if (lever == 1 && person == 'H' && level == 1) {
				currentMap.map[y][x] = 'k';
				lever = 0;
			}
			x++;
		}
		else if (currentMap.map[y][x+1] == 'k') {
			currentMap.map[y][x] = ' ';
			if (level == 1) {
				lever = 1;
				currentMap.map[y][x+1] = 'H';
				currentMap.map[5][0] = 'S';
				currentMap.map[6][0] = 'S';
			}
			else if (level == 2) {
				if (person == 'H') {
					lever = 1;
					currentMap.map[y][x+1] = 'K';
				}
				else if (person == 'O')
					currentMap.map[y][x+1] = '$';
			}
			x++;
		}
		else if (currentMap.map[y][x+1] == 'S' && currentMap.map[y][x] == 'H') {
			currentMap.map[y][x] = ' ';
			currentMap.map[y][x+1] = person;
			return 1;
		}
		
		if (person == 'H' && level == 1) {
			if (currentMap.map[y][x-1] == 'G' || currentMap.map[y+1][x] == 'G' || currentMap.map[y-1][x] == 'G' || currentMap.map[y][x+1] == 'G') {
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

	public int moveDown(char person, int level) {
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
			if (currentMap.map[y+1][x] == '*') {
				if (lever == 0)
					currentMap.map[y+1][x] = 'H';
				else
					currentMap.map[y+1][x] = 'K';
				currentMap.map[y][x] = ' ';
				return -1;
			} 
		}
		
		if (currentMap.map[y+1][x] == 'I') {
			if (lever == 1 && currentMap.map[y][x] == 'K'){
				currentMap.map[y+1][x] = 'S';
				currentMap.map[y][x] = 'H';
			}
		}
		else if (currentMap.map[y+1][x] == ' ') {
			if(currentMap.map[y][x] == '$') {
				currentMap.map[y][x] = 'k';
				currentMap.map[y+1][x] = 'O';
			}
			else if (lever == 0 || person == 'O' || person == 'G') {
				currentMap.map[y+1][x] = person;
				currentMap.map[y][x] = ' ';
			}
			else {
				if (level == 2) {
					currentMap.map[y+1][x] = 'K';
				}
				else {
					currentMap.map[y+1][x] = 'H';
				}
				currentMap.map[y][x] = ' ';
			}
			if (lever == 1 && person == 'H' && level == 1) {
				currentMap.map[y][x] = 'k';
				lever = 0;
			}
			y++;
		}
		else if (currentMap.map[y+1][x] == 'k') {
			currentMap.map[y][x] = ' ';
			if (level == 1) {
				lever = 1;
				currentMap.map[y+1][x] = 'H';
				currentMap.map[5][0] = 'S';
				currentMap.map[6][0] = 'S';
			}
			else if (level == 2) {
				if (person == 'H') {
					lever = 1;
					currentMap.map[y+1][x] = 'K';
				}
				else if (person == 'O')
					currentMap.map[y+1][x] = '$';
			}
			y++;
		}
		else if (currentMap.map[y+1][x] == 'S' && currentMap.map[y][x] == 'H') {
			currentMap.map[y][x] = ' ';
			currentMap.map[y+1][x] = person;
			return 1;
		}
		
		if (person == 'H' && level == 1) {
			if (currentMap.map[y][x-1] == 'G' || currentMap.map[y+1][x] == 'G' || currentMap.map[y-1][x] == 'G' || currentMap.map[y][x+1] == 'G') {
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

	public int moveUp(char person, int level) {
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
			if (currentMap.map[y-1][x] == '*') {
				if (lever == 0)
					currentMap.map[y-1][x] = 'H';
				else
					currentMap.map[y-1][x] = 'K';
				currentMap.map[y][x] = ' ';
				return -1;
			}
		}
		
		if (currentMap.map[y-1][x] == 'I') {
			if (lever == 1 && currentMap.map[y][x] == 'K'){
				currentMap.map[y-1][x] = 'S';
				currentMap.map[y][x] = 'H';
			}
		}
		else if (currentMap.map[y-1][x] == ' ') {
			if(currentMap.map[y][x] == '$') {
				currentMap.map[y][x] = 'k';
				currentMap.map[y-1][x] = 'O';
			}
			else if (lever == 0 || person == 'O' || person == 'G') {
				currentMap.map[y-1][x] = person;
				currentMap.map[y][x] = ' ';
			}
			else {
				if (level == 2) {
					currentMap.map[y-1][x] = 'K';
				}
				else {
					currentMap.map[y-1][x] = 'H';
				}
				currentMap.map[y][x] = ' ';
			}
			if (lever == 1 && person == 'H' && level == 1) {
				currentMap.map[y][x] = 'k';
				lever = 0;
			}
			y--;
		}
		else if (currentMap.map[y-1][x] == 'k') {
			currentMap.map[y][x] = ' ';
			if (level == 1) {
				lever = 1;
				currentMap.map[y-1][x] = 'H';
				currentMap.map[5][0] = 'S';
				currentMap.map[6][0] = 'S';
			}
			else if (level == 2) {
				if (person == 'H') {
					lever = 1;
					currentMap.map[y-1][x] = 'K';
				}
				else if (person == 'O')
					currentMap.map[y-1][x] = '$';
			}
			y--;
		}
		else if (currentMap.map[y-1][x] == 'S' && currentMap.map[y][x] == 'H') {
			currentMap.map[y][x] = ' ';
			currentMap.map[y-1][x] = person;
			return 1;
		}
		
		if (person == 'H' && level == 1) {
			if (currentMap.map[y][x-1] == 'G' || currentMap.map[y+1][x] == 'G' || currentMap.map[y-1][x] == 'G' || currentMap.map[y][x+1] == 'G') {
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
