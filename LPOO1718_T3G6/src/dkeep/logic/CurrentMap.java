package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;

public class CurrentMap {
	public char map[][];
	public int level = 1;
	public int onGame = 1;
	
	public CurrentMap(char[][] map) {
		this.map = map;
	}
	
	public void initializeLevels(int level) {
		if (level == 1) {
			map[1][1] = 'H';
			map[1][8] = 'G';
		}
		else if (level == 2) {
			int xClub, yClub;
			
			do{
				xClub = ThreadLocalRandom.current().nextInt(1, 9);
				yClub = ThreadLocalRandom.current().nextInt(1, 9);
			}while((xClub == 1 && yClub == 8) || (xClub == 8 && yClub == 1) || (xClub == 4 && yClub == 1));
			
			map[1][4] = 'O';
			map[8][1] = 'H';
			map[yClub][xClub] = '+'; 
		}
	}
	
	public void printMap() {
		for(int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
}
