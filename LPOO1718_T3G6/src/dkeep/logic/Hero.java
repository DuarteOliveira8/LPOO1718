package dkeep.logic;

import dkeep.logic.Character;

/*
 * Hero class derived from the Character class
 */
public class Hero extends Character{
	/*
	 * boolean that's true when hero is armed and false when it's not 
	 */
	public boolean armed = false;
	
	/*
	 * Hero empty constructor
	 */
	public Hero() {
		
	}
	
	/*
	 * Hero constructor
	 * 
	 * @param x the current x coordinate
	 * @param y the current y coordinate
	 */
	public Hero(int x, int y) {
		this.x = x;
		this.y = y;
		symbol = 'H';
	}
	
	@Override
	public void moveLeftSpecific(Map map) {
		if(map.currentMap.emptymap[y][x-1] == ' ' && lever == 1 && !(map.currentMap.xClub == x-1 && map.currentMap.yClub == y)) {
			if (map.currentMap.level != 2)
				lever = 0;
			x--;
		}
		else if (map.currentMap.emptymap[y][x-1] == 'I' && symbol == 'K') {
			symbol = 'H';
			lever = 0;
			map.currentMap.doorsOpen = true;
		}
		else if (map.currentMap.keyX == x-1 && map.currentMap.keyY == y) {
			if (map.currentMap.level == 1) {
				lever = 1;
				map.currentMap.doorsOpen = true;
			}
			else if (map.currentMap.level == 2) {
				lever = 1;
				symbol = 'K';
			}	
			x--;
		}
		else if (map.currentMap.emptymap[y][x-1] == 'I' && map.currentMap.doorsOpen) {
			map.currentMap.level++;
			x--;
			map.currentMap.onGame = 0;
		}
		else if(map.currentMap.xClub == x-1 && map.currentMap.yClub == y) {
			symbol = 'A';
			x--;
			armed = true;
		}
	}
	
	@Override
	public void moveRightSpecific(Map map) {
		if(map.currentMap.emptymap[y][x+1] == ' ' && lever == 1 && !(map.currentMap.xClub == x+1 && map.currentMap.yClub == y)) {
			if (map.currentMap.level != 2)
				lever = 0;
			x++;
		}
		else if (map.currentMap.emptymap[y][x+1] == 'I' && symbol == 'K') {
			symbol = 'H';
			lever = 0;
			map.currentMap.doorsOpen = true;
		}
		else if (map.currentMap.keyX == x+1 && map.currentMap.keyY == y) {
			if (map.currentMap.level == 1) {
				lever = 1;
				map.currentMap.doorsOpen = true;
			}
			else if (map.currentMap.level == 2) {
				lever = 1;
				symbol = 'K';
			}
			x++;
		}
		else if (map.currentMap.emptymap[y][x+1] == 'I' && map.currentMap.doorsOpen) {
			map.currentMap.level++;
			x++;
			map.currentMap.onGame = 0;
		}
		else if(map.currentMap.xClub == x+1 && map.currentMap.yClub == y) {
			symbol = 'A';
			x++;
			armed = true;
		}
		
	}
	
	@Override
	public void moveDownSpecific(Map map) {
		if(map.currentMap.emptymap[y+1][x] == ' ' && lever == 1 && !(map.currentMap.xClub == x && map.currentMap.yClub == y+1)) {
			if (map.currentMap.level != 2)
				lever = 0;
			y++;
		}
		else if (map.currentMap.emptymap[y+1][x] == 'I' && symbol == 'K') {
			symbol = 'H';
			lever = 0;
			map.currentMap.doorsOpen = true;
		}
		else if (map.currentMap.keyX == x && map.currentMap.keyY == y+1) {
			if (map.currentMap.level == 1) {
				lever = 1;
				map.currentMap.doorsOpen = true;
			}
			else if (map.currentMap.level == 2) {
				lever = 1;
				symbol = 'K';
				map.currentMap.keySymbol = ' ';
			}
			y++;
		}
		else if (map.currentMap.emptymap[y+1][x] == 'I' && map.currentMap.doorsOpen) {
			map.currentMap.level++;
			y++;
			map.currentMap.onGame = 0;
		}
		else if(map.currentMap.xClub == x && map.currentMap.yClub == y+1) {
			symbol = 'A';
			y++;
			armed = true;
		}
	}
	
	@Override
	public void moveUpSpecific(Map map) {
		if(map.currentMap.emptymap[y-1][x] == ' ' && lever == 1 && !(map.currentMap.xClub == x && map.currentMap.yClub == y-1)) {
			if (map.currentMap.level != 2)
				lever = 0;
			y--;
		}
		else if (map.currentMap.emptymap[y-1][x] == 'I' && symbol == 'K') {
			symbol = 'H';
			lever = 0;
			map.currentMap.doorsOpen = true;
		}
		else if (map.currentMap.keyX == x && map.currentMap.keyY == y-1) {
			if (map.currentMap.level == 1) {
				lever = 1;
				map.currentMap.doorsOpen = true;
			}
			else if (map.currentMap.level == 2) {
				lever = 1;
				symbol = 'K';
				map.currentMap.keyX = -1;
				map.currentMap.keyY = -1;
			}
			y--;
		}
		else if (map.currentMap.emptymap[y-1][x] == 'I' && map.currentMap.doorsOpen) {
			map.currentMap.level++;
			y--;
			map.currentMap.onGame = 0;
		}
		else if(map.currentMap.xClub == x && map.currentMap.yClub == y-1) {
			symbol = 'A';
			y--;
			armed = true;
		}
	}
}
