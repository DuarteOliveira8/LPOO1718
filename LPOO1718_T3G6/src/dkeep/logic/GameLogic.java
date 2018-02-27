package dkeep.logic;

import dkeep.logic.Map;
import java.util.concurrent.ThreadLocalRandom;

public class GameLogic {
	int indiceG = 0;
	int indiceO = 0;

	public void moveNPC(int level, Map gameMap) {
		char patrol[] = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w','w'};
		char ogre[] = {'a', 's', 'd', 'w'};

		if (level == 1) {
			if (patrol[indiceG] == 'w')
				gameMap.moveUp('G', level);
			else if (patrol[indiceG] == 'a')
				gameMap.moveLeft('G', level);
			else if (patrol[indiceG] == 's')
				gameMap.moveDown('G', level);
			else if (patrol[indiceG] == 'd')
				gameMap.moveRight('G', level);
		}
		else if(level == 2) {
			gameMap.removeClub();

			indiceO = ThreadLocalRandom.current().nextInt(0, 4);
			if (ogre[indiceO] == 'w')
				gameMap.moveUp('O', level);
			else if (ogre[indiceO] == 'a')
				gameMap.moveLeft('O', level);
			else if (ogre[indiceO] == 's')
				gameMap.moveDown('O', level);
			else if (ogre[indiceO] == 'd')
				gameMap.moveRight('O', level);

			gameMap.addClub();
		}

		indiceG++;
		if (indiceG == patrol.length)
			indiceG = 0;
	}
}