package dkeep.logic;

import dkeep.logic.Map;

public abstract class Character {
	int x;
	int y;
	char symbol;
	
	public void moveLeft(Map map, int level) {
		if(map.currentMap.map[y][x-1] == ' ') {
			map.currentMap.map[y][x] = ' ';
			map.currentMap.map[y][x-1] = symbol;
		}
		else
			moveLeftSpecific(map);
	}
	
	public abstract void moveLeftSpecific(Map map);
	
	public void moveRight(Map map) {
		if(map.currentMap.map[y][x+1] == ' ') {
			map.currentMap.map[y][x] = ' ';
			map.currentMap.map[y][x+1] = symbol;
		}
		else
			moveRightSpecific(map);
	}
	
	public abstract void moveRightSpecific(Map map);
	
	public void moveUp(Map map) {
		if(map.currentMap.map[y-1][x] == ' ') {
			map.currentMap.map[y][x] = ' ';
			map.currentMap.map[y-1][x] = symbol;
		}
		else
			moveUpSpecific(map);
	}
	
	public abstract void moveUpSpecific(Map map);
	
	public void moveDown(Map map) {
		if(map.currentMap.map[y+1][x] == ' ') {
			map.currentMap.map[y][x] = ' ';
			map.currentMap.map[y+1][x] = symbol;
		}
		else
			moveDownSpecific(map);
	}
	
	public abstract void moveDownSpecific(Map map);
}