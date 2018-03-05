package dkeep.logic;

import dkeep.logic.Map;
import java.util.concurrent.ThreadLocalRandom;

public class GameLogic {
	int indiceG = 0;
	int indiceO = 0;

	public void moveNPC(Map gameMap, Guard guard, Ogre ogre) {
		
		if (gameMap.currentMap.level == 1) {
			if (guard.patrol[indiceG] == 'w')
				guard.moveUp(gameMap);
			else if (guard.patrol[indiceG] == 'a')
				guard.moveLeft(gameMap);
			else if (guard.patrol[indiceG] == 's')
				guard.moveDown(gameMap);
			else if (guard.patrol[indiceG] == 'd')
				guard.moveRight(gameMap);
		}
		else if(gameMap.currentMap.level == 2) {
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

		indiceG++;
		if (indiceG == guard.patrol.length)
			indiceG = 0;
	}
	
	public int verifyGameState(Hero hero, Guard guard, Ogre ogre, Map map) {
		if(map.currentMap.level == 1) {
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
			if(ogre.clubX == hero.x && ogre.clubY == hero.y)
				return -1;
			else
				return 0;
			
		}
		
		return 0;
	}
}