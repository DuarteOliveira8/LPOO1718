package dkeep.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import dkeep.logic.CurrentMap;
import dkeep.logic.GameLogic;
import dkeep.logic.Guard;
import dkeep.logic.Hero;
import dkeep.logic.Map;
import dkeep.logic.Ogre;

public class TestOgreRandomBehaviour {
	
	char[][] map = {{'X','X','X','X','X','X','X'},
					{'X',' ',' ',' ',' ',' ','X'},
					{'X',' ',' ',' ',' ',' ','X'},
					{'I',' ',' ',' ',' ',' ','X'},
					{'I',' ',' ',' ',' ',' ','X'},
					{'X',' ',' ',' ',' ',' ','X'},
					{'X','X','X','X','X','X','X'}};

	@Test(timeout=1000)
	public void testOgreRandomMove() {
		boolean moveup = false, movedown = false, moveleft = false, moveright = false;

		Map gameMap = new Map();
		GameLogic gameLogic = new GameLogic();
		gameMap.currentMap = new CurrentMap(map);
		gameMap.currentMap.level = 2;
		
		Guard guard = new Guard(3,3);
		Ogre ogre = new Ogre (2,2);
		ArrayList<Ogre> ogres = new ArrayList<Ogre>();
		ogres.add(ogre);
		
		int indiceO = 0;
		
		
		while(!moveup || !movedown || !moveleft || !moveright) {
			ogre.x = 3;
			ogre.y = 3;
			
			gameLogic.moveNPC(gameMap, guard, ogres);
			
			if(ogre.x == 3 && ogre.y == 2)
				moveup = true;
			else if(ogre.x == 3 && ogre.y == 4)
				movedown = true;
			else if(ogre.x == 2 && ogre.y == 3)
				moveleft = true;
			else if (ogre.x == 4 && ogre.y == 3)
				moveright = true;
			else
				fail("Ogre behaviour failed");
		}
	}

	@Test(timeout=1000)
	public void testClubRandomMove() {
		boolean moveup = false, movedown = false, moveleft = false, moveright = false;
		boolean clubup = false, clubdown = false, clubleft = false, clubright = false;

		Map gameMap = new Map();
		GameLogic gameLogic = new GameLogic();
		gameMap.currentMap = new CurrentMap(map);
		gameMap.currentMap.level = 2;
		
		Guard guard = new Guard(3,3);
		Ogre ogre = new Ogre (2,2);
		ArrayList<Ogre> ogres = new ArrayList<Ogre>();
		ogres.add(ogre);
		
		int indiceO = 0;
		
		
		while(!clubup || !clubdown || !clubleft || !clubright) {
			ogre.x = 3;
			ogre.y = 3;
			
			gameLogic.moveNPC(gameMap, guard, ogres);
			
			if(ogre.clubX == ogre.x && ogre.clubY == ogre.y - 1)
				clubup = true;
			else if(ogre.clubX == ogre.x && ogre.clubY == ogre.y + 1)
				clubdown = true;
			else if(ogre.clubX == ogre.x - 1 && ogre.clubY == ogre.y)
				clubleft = true;
			else if (ogre.clubX == ogre.x + 1 && ogre.clubY == ogre.y)
				clubright = true;
			else
				fail("Club behaviour failed");
		}
	}
	
	@Test(timeout=1000)
	public void testClubOnKey() {
		boolean clubup = false, clubdown = false, clubleft = false, clubright = false;

		Map gameMap = new Map();
		GameLogic gameLogic = new GameLogic();
		gameMap.currentMap = new CurrentMap(map);
		gameMap.currentMap.level = 2;
		
		Guard guard = new Guard(6,6);
		Ogre ogre = new Ogre (3,3);
		ArrayList<Ogre> ogres = new ArrayList<Ogre>();
		ogres.add(ogre);
		
		gameMap.currentMap.keySymbol = 'k';
		gameMap.currentMap.keyX = 3;
		gameMap.currentMap.keyY = 2;
		
		
		while(!clubup || !clubdown || !clubleft || !clubright) {
			
			ogre.addClub(gameMap);
			
			if(ogre.clubX == ogre.x && ogre.clubY == ogre.y - 1 && ogre.clubSymbol == '$') {
				clubup = true;
				gameMap.currentMap.keyX = 4;
				gameMap.currentMap.keyY = 3;
			}
			else if(ogre.clubX == ogre.x && ogre.clubY == ogre.y + 1 && ogre.clubSymbol == '$') {
				clubdown = true;
				gameMap.currentMap.keyX = 2;
				gameMap.currentMap.keyY = 3;
			}
			else if(ogre.clubX == ogre.x - 1 && ogre.clubY == ogre.y && ogre.clubSymbol == '$') {
				clubleft = true;
			}
			else if (ogre.clubX == ogre.x + 1 && ogre.clubY == ogre.y && ogre.clubSymbol == '$') {
				clubright = true;
				gameMap.currentMap.keyX = 3;
				gameMap.currentMap.keyY = 4;
			}
		}
	}
	
	
}
