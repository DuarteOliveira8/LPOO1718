package dkeep.logic;

import dkeep.logic.Map;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/*
 * Game Logic classs
 */
public class GameLogic {
	/*
	 * current index of the guard's movements array
	 */
	int indiceG = 0;
	/*
	 * index from 0 to 3 to determine if the ogre's random movement is up, down, left or right 
	 */
	int indiceO = 0;

	/*
	 * function that's in charge of moving the guard or the ogres depending on the current level
	 * 
	 * @param gameMap the current game map
	 * @param guard the in-game level 1 guard
	 * @param ogres array that has the all the in-game level 2 ogres
	 */
	public void moveNPC(Map gameMap, Guard guard, ArrayList<Ogre> ogres) {
		if (gameMap.currentMap.level == 1) {
			guard.characterState = ThreadLocalRandom.current().nextInt(0, 2);
			
			if (!(guard.characterState == 1 && guard.personality == -1)) {
				guard.symbol = 'G';
				
				if (guard.personality == 1 && guard.characterState == 1) {
					if (guard.patrolreverse[indiceG] == 'w')
						guard.moveUp(gameMap);
					else if (guard.patrolreverse[indiceG] == 'a')
						guard.moveLeft(gameMap);
					else if (guard.patrolreverse[indiceG] == 's')
						guard.moveDown(gameMap);
					else if (guard.patrolreverse[indiceG] == 'd')
						guard.moveRight(gameMap);
				}
				else {
					if (guard.patrol[indiceG] == 'w')
						guard.moveUp(gameMap);
					else if (guard.patrol[indiceG] == 'a')
						guard.moveLeft(gameMap);
					else if (guard.patrol[indiceG] == 's')
						guard.moveDown(gameMap);
					else if (guard.patrol[indiceG] == 'd')
						guard.moveRight(gameMap);
				}
				
				if(guard.personality == 1 && guard.characterState == 1)
					indiceG--;
				else 
					indiceG++;
			}
			else {
				guard.symbol = 'g';
				gameMap.currentMap.map[guard.y][guard.x] = guard.symbol;
			}
			
			if (indiceG == guard.patrol.length)
				indiceG = 0;
			
			if(indiceG == -1)
				indiceG = guard.patrolreverse.length - 1;
		}
		
		for (Ogre ogre : ogres) {
			if(gameMap.currentMap.level == 2 && ogre.stunned == 0) {
				indiceO = ThreadLocalRandom.current().nextInt(0, 4);
				if (ogre.movement[indiceO] == 'w')
					ogre.moveUp(gameMap);
				else if (ogre.movement[indiceO] == 'a')
					ogre.moveLeft(gameMap);
				else if (ogre.movement[indiceO] == 's')
					ogre.moveDown(gameMap);
				else if (ogre.movement[indiceO] == 'd')
					ogre.moveRight(gameMap);
			}
			
			if(ogre.stunned > 0)
				ogre.stunned--;
		}
		
		for (Ogre ogre : ogres) {
			if(gameMap.currentMap.level == 2 && ogre.stunned == 0) {
				ogre.addClub(gameMap);
			}
		}
	}
	
	
	/*
	 * function that verifies if the hero has lost the game
	 * 
	 * @param hero the in-game hero
	 * @param guard the in-game level 1 guard
	 * @param ogres array that has the all the in-game level 2 ogres
	 * @param map the current game map
	 * 
	 * @return -1 if the game was lost or 0 if the hero can continue to play the game
	 * 
	 */
	public int verifyGameState(Hero hero, Guard guard, ArrayList<Ogre> ogres, Map map) {
		if(map.currentMap.level == 1 && guard.symbol == 'G') {
			if((hero.x == guard.x+1 && hero.y == guard.y) ||
				(hero.x == guard.x-1 && hero.y == guard.y) ||
				(hero.x == guard.x && hero.y == guard.y+1) ||
				(hero.x == guard.x && hero.y == guard.y-1)){
				return -1;
			}
			else
				return 0;
		}
		else if(map.currentMap.level == 2) {
			for (Ogre ogre : ogres) {
				if(((hero.x == ogre.x+1 && hero.y == ogre.y) ||
						(hero.x == ogre.x-1 && hero.y == ogre.y) ||
						(hero.x == ogre.x && hero.y == ogre.y+1) ||
						(hero.x == ogre.x && hero.y == ogre.y-1)) && !hero.armed){
					return -1;
				}
				
				if(ogre.clubX != 0  && ogre.clubY != 0) {
					if((hero.x == ogre.clubX+1 && hero.y == ogre.clubY) ||
							(hero.x == ogre.clubX-1 && hero.y == ogre.clubY) ||
							(hero.x == ogre.clubX && hero.y == ogre.clubY+1) ||
							(hero.x == ogre.clubX && hero.y == ogre.clubY-1)){
						return -1;
					}
				}
				else
					return 0;
			}
		}
		
		return 0;
	}
}