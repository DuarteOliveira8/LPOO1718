package dkeep.logic;

import dkeep.logic.Map;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GameLogic {
	int indiceG = 0;
	int indiceO = 0;

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
				ogre.removeClub(gameMap);
				indiceO = ThreadLocalRandom.current().nextInt(0, 4);
				if (ogre.movement[indiceO] == 'w')
					ogre.moveUp(gameMap);
				else if (ogre.movement[indiceO] == 'a')
					ogre.moveLeft(gameMap);
				else if (ogre.movement[indiceO] == 's')
					ogre.moveDown(gameMap);
				else if (ogre.movement[indiceO] == 'd')
					ogre.moveRight(gameMap);
				ogre.addClub(gameMap);
			}
			
			if(ogre.stunned > 0)
				ogre.stunned--;
		}
	}
	
	public int verifyGameState(Hero hero, Guard guard, ArrayList<Ogre> ogres, Map map) {
		if(map.currentMap.level == 1 && guard.symbol == 'G') {
			if(map.currentMap.map[guard.y][guard.x + 1] == hero.symbol ||
				map.currentMap.map[guard.y][guard.x - 1] == hero.symbol ||
				map.currentMap.map[guard.y + 1][guard.x] == hero.symbol ||
				map.currentMap.map[guard.y - 1][guard.x] == hero.symbol) {
				return -1;
			}
			else
				return 0;
		}
		else if(map.currentMap.level == 2) {
			for (Ogre ogre : ogres) {
				if (ogre.clubX == hero.x && ogre.clubY == hero.y)
					return -1;
				else
					return 0;
			}
			
		}
		
		return 0;
	}
}