package dkeep.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dkeep.cli.*;
import dkeep.logic.*;

public class TestDungeonGameLogic {
	
	
	char[][] map = {{'X','X','X','X','X'},
					{'X',' ',' ',' ','X'},
					{'I',' ',' ',' ','X'},
					{'I',' ',' ',' ','X'},
					{'X','X','X','X','X'}};
	

	@Test
	public void testMoveHeroIntoToFreeCell() {
		Map gameMap = new Map();
		gameMap.currentMap = new CurrentMap(map);
		Hero hero = new Hero(1,1);
		assertEquals(1, hero.getX());
		assertEquals(1, hero.getY());
		hero.moveDown(gameMap);
		assertEquals(1, hero.getX());
		assertEquals(2, hero.getY());
	}

	@Test
	public void testHeroIsCapturedByGuard() {
		Map gameMap = new Map();
		GameLogic gameLogic = new GameLogic();
		gameMap.currentMap = new CurrentMap(map);
		Hero hero = new Hero(1,1);
		Guard guard = new Guard(3,1);
		ArrayList<Ogre> ogres = new ArrayList<Ogre>();
		assertEquals(0, gameLogic.verifyGameState(hero, guard, ogres, gameMap));
		hero.moveRight(gameMap);
		assertEquals(-1, gameLogic.verifyGameState(hero, guard, ogres, gameMap));
	}
	
	@Test
	public void testMoveHeroIntoToWall() {
		Map gameMap = new Map();
		gameMap.currentMap = new CurrentMap(map);
		Hero hero = new Hero(1,1);
		assertEquals(1, hero.getX());
		assertEquals(1, hero.getY());
		hero.moveLeft(gameMap);
		assertEquals(1, hero.getX());
		assertEquals(1, hero.getY());
	}
	
	@Test
	public void testExitDoorFail() {
		Map gameMap = new Map();
		gameMap.currentMap = new CurrentMap(map);
		Hero hero = new Hero(1,1);
		hero.moveDown(gameMap);
		hero.moveLeft(gameMap);
		assertEquals(0,hero.lever);
		assertEquals(1, gameMap.currentMap.level);
	}
	
	@Test
	public void testOpenDoors() {
		Map gameMap = new Map();
		gameMap.currentMap = new CurrentMap(map);
		Hero hero = new Hero(1,1);
		gameMap.currentMap.keySymbol = 'k';
		gameMap.currentMap.keyX = 1;
		gameMap.currentMap.keyY = 3;
		hero.moveDown(gameMap);
		hero.moveDown(gameMap);
		assertEquals(1,hero.lever);
		assertTrue(gameMap.currentMap.doorsOpen);
	}
	
	@Test
	public void testNextLevel() {
		Map gameMap = new Map();
		gameMap.currentMap = new CurrentMap(map);
		Hero hero = new Hero(1,1);
		gameMap.currentMap.keySymbol = 'k';
		gameMap.currentMap.keyX = 1;
		gameMap.currentMap.keyY = 3;
		hero.moveDown(gameMap);
		hero.moveDown(gameMap);
		hero.moveLeft(gameMap);
		assertEquals(2, gameMap.currentMap.level);
	}
	
	@Test
	public void testOnLever() {
		Map gameMap = new Map();
		gameMap.currentMap = new CurrentMap(map);
		Hero hero = new Hero(1,1);
		gameMap.currentMap.keySymbol = 'k';
		gameMap.currentMap.keyX = 2;
		gameMap.currentMap.keyY = 2;
		hero.moveRight(gameMap);
		hero.moveDown(gameMap);
		assertEquals(1,hero.lever);
		hero.moveUp(gameMap);
		assertEquals(0,hero.lever);
		hero.moveDown(gameMap);
		assertEquals(1,hero.lever);
		hero.moveRight(gameMap);
		assertEquals(0,hero.lever);
		hero.moveLeft(gameMap);
		assertEquals(1,hero.lever);
		hero.moveDown(gameMap);
		assertEquals(0,hero.lever);
		hero.moveUp(gameMap);
		assertEquals(1,hero.lever);
		hero.moveLeft(gameMap);
		assertEquals(0,hero.lever);
		hero.moveRight(gameMap);
		assertEquals(1,hero.lever);
	}

	@Test
	public void testPrintMap() { //este teste é visual
		Map gameMap = new Map();
		gameMap.currentMap = new CurrentMap(map);
		Hero hero = new Hero(1,1);
		Guard guard = new Guard(3,1);
		ArrayList<Ogre> ogres = new ArrayList<Ogre>();
		gameMap.currentMap.keySymbol = 'k';
		gameMap.currentMap.keyX = 1;
		gameMap.currentMap.keyY = 3;
		gameMap.currentMap.printMap(hero, guard, ogres);
		hero.moveDown(gameMap);
		hero.moveDown(gameMap);
		gameMap.currentMap.printMap(hero, guard, ogres);
	}

}
