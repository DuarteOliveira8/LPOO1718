package dkeep.logic;

import dkeep.logic.Map;

/*
 * The Character class is the main class from which in-game character derive.
 */
public abstract class Character {
	/*
	 * x coordinate
	 */
	public int x;
	/*
	 * y coordinate
	 */
	public int y;
	/*
	 * if the character is on top of the key/lever 
	 */
	public int lever = 0;
	/*
	 * the letter that represents the character in its current state
	 */
	public char symbol;

	/*
	 * function that makes the character move one cell to the left
	 * 
	 * @param map the current game map
	 */
	public void moveLeft(Map map) {
		if(map.currentMap.emptymap[y][x-1] == ' ' && lever == 0 && !(map.currentMap.keyX == x-1 && map.currentMap.keyY == y) && !(map.currentMap.xClub == x-1 && map.currentMap.yClub == y)) {
			x--;
		}
		else
			moveLeftSpecific(map);
	}
	
	/*
	 * abstract specific function for each different character in case the cell to its left is not a free cell
	 * 
	 * @param map the current game map
	 */
	public abstract void moveLeftSpecific(Map map);
	
	/*
	 * function that makes the character move one cell to the right
	 * 
	 * @param map the current game map
	 */
	public void moveRight(Map map) {
		if(map.currentMap.emptymap[y][x+1] == ' ' && lever == 0 && !(map.currentMap.keyX == x+1 && map.currentMap.keyY == y) && !(map.currentMap.xClub == x+1 && map.currentMap.yClub == y)) {
			x++;
		}
		else
			moveRightSpecific(map);
	}
	
	/*
	 * abstract specific function for each different character in case the cell to its right is not a free cell
	 * 
	 * @param map the current game map
	 */
	public abstract void moveRightSpecific(Map map);
	
	/*
	 * function that makes the character move one cell upwards
	 * 
	 * @param map the current game map
	 */
	public void moveUp(Map map) {
		if(map.currentMap.emptymap[y-1][x] == ' ' && lever == 0 && !(map.currentMap.keyX == x && map.currentMap.keyY == y-1) && !(map.currentMap.xClub == x && map.currentMap.yClub == y-1)) {
			y--;
		}
		else
			moveUpSpecific(map);
	}
	
	/*
	 * abstract specific function for each different character in case its upwards cell is not a free cell
	 * 
	 * @param map the current game map
	 */
	public abstract void moveUpSpecific(Map map);
	
	/*
	 * function that makes the character move one cell downwards
	 * 
	 * @param map the current game map
	 */
	public void moveDown(Map map) {
		if(map.currentMap.emptymap[y+1][x] == ' ' && lever == 0 && !(map.currentMap.keyX == x && map.currentMap.keyY == y+1) && !(map.currentMap.xClub == x && map.currentMap.yClub == y+1)) {
			y++;
		}
		else
			moveDownSpecific(map);
	}
	
	/*
	 * abstract specific function for each different character in case its downwards cell is not a free cell
	 * 
	 * @param map the current game map
	 */
	public abstract void moveDownSpecific(Map map);
	
	/*
	 * updates the character coordinates
	 * 
	 * @param x the new x coordinate
	 * @param y the new y coordinate
	 */
	public void changePosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/*
	 * @return the current x coordinate
	 */
	public int getX() {
		return x;
	}
	
	/*
	 * @return the current y coordinate
	 */
	public int getY() {
		return y;
	}
}