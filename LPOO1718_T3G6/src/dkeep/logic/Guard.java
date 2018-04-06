package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;

import dkeep.logic.Character;

/*
 * Guard class derived from the Character class
 */
public class Guard extends Character{
	/*
	 * array that contains all the guard's predefined movements
	 */
	char patrol[] = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
	/*
	 * array that contains all the guard's predefined movements in reverse, in case the personality is suspicious
	 */
	char patrolreverse[] = {'s','d','w','w','w','w','d','d','d','d','d','d','w','a','a','a','a','a','a','a','s','s','s','s'};
	/*
	 * guard's current personality (-1 if drunk, 0 if normal and 1 if suspicious)
	 */
	public int personality; //-1: drunk // 0: normal // 1: suspicious
	/*
	 * when the guard's persoanlity is suspicious, determines if the guard is going to make its normals movements or go in the reverse way
	 */
	public int characterState = 0;
	
	/*
	 * Guard empty constructor 
	 */
	public Guard() {
	}
	
	/*
	 * Guard constructor
	 * 
	 * @param x current x coordinate
	 * @param y current y coordinate
	 */
	public Guard(int x, int y) {
		this.x = x;
		this.y = y;
		symbol = 'G';
		this.personality = 0;
	}
	
	@Override
	public void moveLeftSpecific(Map map) {
	}
	
	@Override
	public void moveRightSpecific(Map map) {
	}
	
	@Override
	public void moveDownSpecific(Map map) {
	}
	
	@Override
	public void moveUpSpecific(Map map) {
	}
}
