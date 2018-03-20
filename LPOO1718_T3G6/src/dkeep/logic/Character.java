package dkeep.logic;

import dkeep.logic.Map;

public abstract class Character {
	public int x;
	public int y;
	public int lever = 0;
	public char symbol;
	
	public void moveLeft(Map map) {
		if(map.currentMap.emptymap[y][x-1] == ' ' && lever == 0 && !(map.currentMap.keyX == x-1 && map.currentMap.keyY == y) && !(map.currentMap.xClub == x-1 && map.currentMap.yClub == y)) {
			x--;
		}
		else
			moveLeftSpecific(map);
	}
	
	public abstract void moveLeftSpecific(Map map);
	
	public void moveRight(Map map) {
		if(map.currentMap.emptymap[y][x+1] == ' ' && lever == 0 && !(map.currentMap.keyX == x+1 && map.currentMap.keyY == y) && !(map.currentMap.xClub == x+1 && map.currentMap.yClub == y)) {
			x++;
		}
		else
			moveRightSpecific(map);
	}
	
	public abstract void moveRightSpecific(Map map);
	
	public void moveUp(Map map) {
		if(map.currentMap.emptymap[y-1][x] == ' ' && lever == 0 && !(map.currentMap.keyX == x && map.currentMap.keyY == y-1) && !(map.currentMap.xClub == x && map.currentMap.yClub == y-1)) {
			y--;
		}
		else
			moveUpSpecific(map);
	}
	
	public abstract void moveUpSpecific(Map map);
	
	public void moveDown(Map map) {
		if(map.currentMap.emptymap[y+1][x] == ' ' && lever == 0 && !(map.currentMap.keyX == x && map.currentMap.keyY == y+1) && !(map.currentMap.xClub == x && map.currentMap.yClub == y+1)) {
			y++;
		}
		else
			moveDownSpecific(map);
	}
	
	public abstract void moveDownSpecific(Map map);
	
	public void changePosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}