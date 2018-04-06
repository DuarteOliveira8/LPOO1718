package dkeep.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dkeep.cli.*;
import dkeep.logic.*;
import dkeep.logic.Character;

public class TestKeepGameLogic {
	
	
	char[][] map = {{'X','X','X','X','X'},
					{'X',' ',' ',' ','X'},
					{'I',' ',' ',' ','X'},
					{'I',' ',' ',' ','X'},
					{'X','X','X','X','X'}};

	@Test
	public void testHeroIsCapturedByOgre() {
		Map gameMap = new Map();
		GameLogic gameLogic = new GameLogic();
		gameMap.currentMap = new CurrentMap(map);
		gameMap.currentMap.level = 2;
		Hero hero = new Hero(1,1);
		Guard guard = new Guard(3,3);
		Ogre ogre = new Ogre(3,1);
		ArrayList<Ogre> ogres = new ArrayList<Ogre>();
		ogres.add(ogre);
		assertEquals(0, gameLogic.verifyGameState(hero, guard, ogres, gameMap));
		hero.moveRight(gameMap);
		assertEquals(-1, gameLogic.verifyGameState(hero, guard, ogres, gameMap));
	}
	
	@Test
	public void testHeroGrabsKey() {
		Map gameMap = new Map();
		GameLogic gameLogic = new GameLogic();
		gameMap.currentMap = new CurrentMap(map);
		gameMap.currentMap.level = 2;
		Hero hero = new Hero(1,1);
		gameMap.currentMap.keySymbol = 'k';
		gameMap.currentMap.keyX = 1;
		gameMap.currentMap.keyY = 3;
		hero.moveDown(gameMap);
		hero.moveDown(gameMap);
		assertEquals('K', hero.symbol);
	}
	
	@Test
	public void testHeroFailsOpenDoor() {
		Map gameMap = new Map();
		GameLogic gameLogic = new GameLogic();
		gameMap.currentMap = new CurrentMap(map);
		gameMap.currentMap.level = 2;
		Hero hero = new Hero(1,1);
		hero.moveDown(gameMap);
		hero.moveLeft(gameMap);
		assertFalse(gameMap.currentMap.doorsOpen);
	}
	
	@Test
	public void testHeroOpensDoor() {
		Map gameMap = new Map();
		GameLogic gameLogic = new GameLogic();
		gameMap.currentMap = new CurrentMap(map);
		gameMap.currentMap.level = 2;
		Hero hero = new Hero(1,1);
		gameMap.currentMap.keySymbol = 'k';
		gameMap.currentMap.keyX = 1;
		gameMap.currentMap.keyY = 3;
		hero.moveDown(gameMap);
		hero.moveDown(gameMap);
		hero.moveLeft(gameMap);
		assertTrue(gameMap.currentMap.doorsOpen);
	}
	
	@Test
	public void testHeroWinsGame() {
		Map gameMap = new Map();
		GameLogic gameLogic = new GameLogic();
		gameMap.currentMap = new CurrentMap(map);
		gameMap.currentMap.level = 2;
		Hero hero = new Hero(1,1);
		gameMap.currentMap.keySymbol = 'k';
		gameMap.currentMap.keyX = 1;
		gameMap.currentMap.keyY = 3;
		hero.moveDown(gameMap);
		hero.moveDown(gameMap);
		hero.moveLeft(gameMap);
		hero.moveLeft(gameMap);
		assertEquals(3, gameMap.currentMap.level);
	}
	
	@Test
	public void testHeroArmed() {
		Map gameMap = new Map();
		GameLogic gameLogic = new GameLogic();
		gameMap.currentMap = new CurrentMap(map);
		gameMap.currentMap.level = 2;
		Hero hero = new Hero(1,1);
		gameMap.currentMap.xClub = 2;
		gameMap.currentMap.yClub = 1;
		gameMap.currentMap.keySymbol = 'k';
		gameMap.currentMap.keyX = 2;
		gameMap.currentMap.keyY = 2;
		assertEquals('H',hero.symbol);
		assertFalse(hero.armed);
		hero.moveRight(gameMap);
		assertEquals('A',hero.symbol);
		assertTrue(hero.armed);
		hero.moveDown(gameMap);
		assertEquals('K',hero.symbol);
		assertTrue(hero.armed);
	}
	
	@Test
	public void testOgreOverKey() {
		Map gameMap = new Map();
		GameLogic gameLogic = new GameLogic();
		gameMap.currentMap = new CurrentMap(map);
		gameMap.currentMap.level = 2;
		Ogre ogre = new Ogre(1,1);
		gameMap.currentMap.keySymbol = 'k';
		gameMap.currentMap.keyX = 2;
		gameMap.currentMap.keyY = 2;
		ogre.moveRight(gameMap);
		ogre.moveDown(gameMap);
		assertEquals('$',ogre.symbol);
		assertEquals('$',gameMap.currentMap.keySymbol);
		ogre.moveUp(gameMap);
		assertEquals('O',ogre.symbol);
		assertEquals('k',gameMap.currentMap.keySymbol);
		ogre.moveDown(gameMap);
		assertEquals('$',ogre.symbol);
		assertEquals('$',gameMap.currentMap.keySymbol);
		ogre.moveRight(gameMap);
		assertEquals('O',ogre.symbol);
		assertEquals('k',gameMap.currentMap.keySymbol);
		ogre.moveLeft(gameMap);
		assertEquals('$',ogre.symbol);
		assertEquals('$',gameMap.currentMap.keySymbol);
		ogre.moveDown(gameMap);
		assertEquals('O',ogre.symbol);
		assertEquals('k',gameMap.currentMap.keySymbol);
		ogre.moveUp(gameMap);
		assertEquals('$',ogre.symbol);
		assertEquals('$',gameMap.currentMap.keySymbol);
		ogre.moveLeft(gameMap);
		assertEquals('O',ogre.symbol);
		assertEquals('k',gameMap.currentMap.keySymbol);
		ogre.moveRight(gameMap);
		assertEquals('$',ogre.symbol);
		assertEquals('$',gameMap.currentMap.keySymbol);
	}
	
	@Test
	public void testOgreStunned() {
		Map gameMap = new Map();
		GameLogic gameLogic = new GameLogic();
		gameMap.currentMap = new CurrentMap(map);
		gameMap.currentMap.level = 2;
		Hero hero = new Hero(1,1);
		Ogre ogre = new Ogre(2,2);
		ArrayList<Ogre> ogres = new ArrayList<Ogre>();
		ogres.add(ogre);
		Guard guard = new Guard(4,4);
		
		gameMap.currentMap.xClub = 2;
		gameMap.currentMap.yClub = 1;
		
		hero.moveRight(gameMap);
		assertEquals('A',hero.symbol);
		ogre.verifyStun(hero, gameMap);
		assertEquals(2,ogre.stunned);
		gameLogic.moveNPC(gameMap,guard,ogres);
		assertEquals(1,ogre.stunned);
		
		ogre.x = 2; ogre.y = 2;
		hero.moveRight(gameMap);
		hero.moveDown(gameMap);
		ogre.verifyStun(hero, gameMap);
		assertEquals(2,ogre.stunned);
		gameLogic.moveNPC(gameMap,guard,ogres);
		assertEquals(1,ogre.stunned);
		
		ogre.x = 2; ogre.y = 2;
		hero.moveDown(gameMap);
		hero.moveLeft(gameMap);
		ogre.verifyStun(hero, gameMap);
		assertEquals(2,ogre.stunned);
		gameLogic.moveNPC(gameMap,guard,ogres);
		assertEquals(1,ogre.stunned);
		
		ogre.x = 2; ogre.y = 2;
		hero.moveLeft(gameMap);
		hero.moveUp(gameMap);
		ogre.verifyStun(hero, gameMap);
		assertEquals(2,ogre.stunned);
		gameLogic.moveNPC(gameMap,guard,ogres);
		assertEquals(1,ogre.stunned);
		
	}
	
	@Test
	public void testPrintMap() { //este teste é visual
		Map gameMap = new Map();
		gameMap.currentMap = new CurrentMap(map);
		Hero hero = new Hero(1,1);
		Guard guard = new Guard(3,1);
		Ogre ogre = new Ogre(3,1);
		gameMap.currentMap.level = 2;
		gameMap.currentMap.xClub = 3;
		gameMap.currentMap.yClub = 3;
		ArrayList<Ogre> ogres = new ArrayList<Ogre>();
		ogre.addClub(gameMap);
		ogres.add(ogre);
		gameMap.currentMap.keySymbol = 'k';
		gameMap.currentMap.keyX = 1;
		gameMap.currentMap.keyY = 3;
		gameMap.currentMap.printMap(hero, guard, ogres);
		hero.moveDown(gameMap);
		hero.moveDown(gameMap);
		gameMap.currentMap.printMap(hero, guard, ogres);
	}
	
	@Test
	public void testChangeMap() {
		Map map = new Map();
		map.currentMap.initializeLevel(2, map.map2);
		assertArrayEquals(map.map2, map.currentMap.map);
	}
	
	@Test
	public void testChangePositionHero() {
		Character c = new Hero();
		c.changePosition(2, 3);
		assertEquals(2, c.getX());
		assertEquals(3, c.getY());
	}
	
	@Test
	public void testChangePositionOgre() {
		Character c = new Ogre();
		c.changePosition(2, 3);
		assertEquals(2, c.getX());
		assertEquals(3, c.getY());
	}
}