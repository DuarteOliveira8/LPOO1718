package dkeep.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/*
 * Current Map class
 */
public class CurrentMap {
	/*
	 * array of the empty map
	 */
	public char emptymap[][];
	/*
	 * array of the current map
	 */
	public char map[][];
	/*
	 * current level
	 */
	public int level = 1;
	/*
	 * 1 if the game is being currently played and 0 if it's changing levels or is not being played
	 */
	public int onGame = 1;
	/*
	 * true if the doors are open and false when they're not
	 */
	public boolean doorsOpen = false;
	/*
	 * current displayed symbol of the key
	 */
	public char keySymbol;
	/*
	 * coordinates of the key
	 */
	public int keyX, keyY;
	/*
	 * coordinates of the club
	 */
	public int xClub, yClub;
	/*
	 * coordinates of the hero on level 2
	 */
	public int heroX2, heroY2;
	/*
	 * coordinates of the ogre on level 2
	 */
	public int ogreX2, ogreY2;
	/*
	 * coordinates of the key on level 2
	 */
	public int keyX2, keyY2;
	/*
	 * coordinates of the club on level 2
	 */
	public int clubX2, clubY2;
	
	/*
	 * function that copies a map and returns it
	 * 
	 * @param map map to copy
	 * 
	 * @return the map copy
	 */
	private char[][] copyMap(char[][] map) {
		char[][] newMap = new char[map.length][];
		
		for (int i = 0; i < map.length ; i++) {
			newMap[i] = Arrays.copyOf(map[i],map[i].length);
		}
		
		return newMap;
	}
	
	/*
	 * CurrentMap constructor
	 * 
	 * @param map current map
	 */
	public CurrentMap(char[][] map) {
		initializeLevel(1, map);
	}
	
	/*
	 * initializes all the Current Map variables depending on the current level
	 * 
	 * @param level current level
	 * @param map current map
	 */
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
			
			keyX = keyX2;
			keyY = keyY2;
			keySymbol = 'k';
			
			doorsOpen = false;
			
			xClub = clubX2;
			yClub = clubY2;
			}
	}
	
	/*
	 * function used to print the map to the console
	 * 
	 * @param hero current in-game hero
	 * @param guard current in-game guard
	 * @param ogres current array of in-game ogres
	 * 
	 * @return string of the current map state
	 */
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
	
	/*
	 * function that opens the doors on the wall around the map
	 */
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
