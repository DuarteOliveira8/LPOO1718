package dkeep.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class CurrentMap {
	public char emptymap[][];
	public char map[][];
	public int level = 1;
	public int onGame = 1;
	public boolean doorsOpen = false;
	public char keySymbol;
	public int keyX, keyY;
	public int xClub, yClub;
	
	private char[][] copyMap(char[][] map) {
		char[][] newMap = new char[map.length][];
		
		for (int i = 0; i < map.length ; i++) {
			newMap[i] = Arrays.copyOf(map[i],map[i].length);
		}
		
		return newMap;
	}
	
	public CurrentMap(char[][] map) {
		initializeLevel(1, map);
	}
	
	public void initializeLevel(int level, char[][] map) {
		if (level == 1) {
			this.map = copyMap(map);
			this.emptymap = copyMap(map);
			keyX = 7;
			keyY = 8;
			keySymbol = 'k';
		}
		else if (level == 2) {
			this.map = copyMap(map);
			this.emptymap = copyMap(map);
			
			keyX = 8;
			keyY = 1;
			keySymbol = 'k';
			
			doorsOpen = false;
			
			
			do{
				xClub = ThreadLocalRandom.current().nextInt(1, 9);
				yClub = ThreadLocalRandom.current().nextInt(1, 9);
			}while((xClub == 1 && yClub == 8) || (xClub == 8 && yClub == 1) || (xClub == 4 && yClub == 1));
		}
	}
	
	public String printMap(Hero hero, Guard guard, ArrayList<Ogre> ogres) {
		map = copyMap(emptymap);
		
		if (doorsOpen) 
			openDoors();
		
		if(level == 1)
			map[guard.y][guard.x] = guard.symbol;
		else if(level == 2) {
			for(Ogre ogre : ogres) {
				if (ogre.clubY != -1 && ogre.clubX != -1)
					map[ogre.clubY][ogre.clubX] = ogre.clubSymbol;
			}
			for(Ogre ogre : ogres) {
				map[ogre.y][ogre.x] = ogre.symbol;
			}
		}
		
		if (hero.lever == 0)
			map[keyY][keyX] = keySymbol;
		
		if (!hero.armed && level == 2)
			map[yClub][xClub] = '+'; 
		
		map[hero.y][hero.x] = hero.symbol;
		
		String res = new String();
		
		for(int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				res += map[i][j] + " ";
			}
			res += "\n";
		}
		
		return res;
	}
	
	public void openDoors() {
		for (int i = 0; i < map.length; i++) {
			if(map[i][0] == 'I')
				map[i][0] = 'S';
		}
		
		for (int i = 0; i < map.length; i++) {
			if(map[i][map[0].length-1] == 'I')
				map[i][map[0].length-1] = 'S';
		}
		
		for (int i = 0; i < map[0].length; i++) {
			if(map[0][i] == 'I')
				map[0][i] = 'S';
		}
		
		for (int i = 0; i < map[map.length-1].length; i++) {
			if(map[map.length-1][i] == 'I')
				map[map.length-1][i] = 'S';
		}
	}
}
