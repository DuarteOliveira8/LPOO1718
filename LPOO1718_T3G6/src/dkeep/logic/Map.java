package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;
import dkeep.logic.CurrentMap;

public class Map {
	
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
	
	public CurrentMap currentMap = new CurrentMap(map1);
	
	public void printMap() {
		currentMap.printMap();
	}

	public void changeMap() {

		int xClub, yClub;
		
		do{
			xClub = ThreadLocalRandom.current().nextInt(1, 9);
			yClub = ThreadLocalRandom.current().nextInt(1, 9);
		}while((xClub == 1 && yClub == 8) || (xClub == 8 && yClub == 1) || (xClub == 4 && yClub == 1));
		
		currentMap.map = map2;
		currentMap.map[1][4] = 'O';
		currentMap.map[8][1] = 'H';
		currentMap.map[yClub][xClub] = '+'; 
	}
}
