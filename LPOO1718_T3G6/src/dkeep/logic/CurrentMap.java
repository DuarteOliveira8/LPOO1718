package dkeep.logic;

public class CurrentMap {
	public char map[][];
	int lever = 0;
	public int level = 1;
	public int onGame = 1;
	
	CurrentMap(char[][] map) {
		this.map = map;
		this.map[1][1] = 'H';
		this.map[1][8] = 'G';
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
