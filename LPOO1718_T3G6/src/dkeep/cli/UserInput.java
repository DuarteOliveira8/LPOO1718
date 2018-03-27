package dkeep.cli;

import dkeep.logic.Map;
import dkeep.logic.GameLogic;
import dkeep.logic.Hero;
import dkeep.logic.Ogre;
import dkeep.logic.Guard;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class UserInput {
	public static Map gameMap;
	public static int gameState;
	public static GameLogic gameLogic;
	
	public static Hero hero;
	public static Guard guard;
	public static ArrayList<Ogre> ogres;
	
	
	public static void main(String[] args) {
		int nOgres = ThreadLocalRandom.current().nextInt(1, 4);
		int personality = ThreadLocalRandom.current().nextInt(-1, 2);
		initializeGame(nOgres, personality);
		newGame();
	}
	
	public static void initializeGame(int noOgres, int personality) {
		gameMap = new Map();
		gameLogic = new GameLogic();
		hero = new Hero(1,1);
		guard = new Guard(8,1);
		gameState = 0;
		ogres = new ArrayList<Ogre>();
		
		for(int i = 0; i < noOgres; i++) {
			ogres.add(new Ogre(4,1));
		}
		
		guard.personality = personality;
	}

	public static void newGame() {//-1: drunk // 0: normal // 1: suspicious

		while (true) {
			System.out.print(gameMap.currentMap.printMap(hero, guard, ogres));
			
			userInput(gameMap.currentMap.level);
			
			gameState = gameLogic.verifyGameState(hero, guard, ogres, gameMap);
			
			if (gameState == -1) {
				System.out.print(gameMap.currentMap.printMap(hero, guard, ogres));
				break;
			}
			
			for (Ogre ogre : ogres)
				ogre.verifyStun(hero, gameMap);
			
			if (gameMap.currentMap.level == 2 && gameMap.currentMap.onGame == 0) { 
				System.out.print(gameMap.currentMap.printMap(hero, guard, ogres));
				System.out.println("Next Level");
				gameMap.changeMap();
				hero.changePosition(1, 8);
				gameMap.currentMap.onGame = 1;
				
			}
			
			if (gameMap.currentMap.level == 3) {
				System.out.print(gameMap.currentMap.printMap(hero, guard, ogres));
				System.out.println("You won!");
				return;
			}
			
			gameLogic.moveNPC(gameMap, guard, ogres);
			
			gameState = gameLogic.verifyGameState(hero, guard, ogres, gameMap);
			
			if (gameState == -1){
				System.out.print(gameMap.currentMap.printMap(hero, guard, ogres));
				break;
			}
			
			for (Ogre ogre : ogres)
				ogre.verifyStun(hero, gameMap);
		}
		
		System.out.println("You lost!");
	}
	
	public static void userInput(int level) {
		Scanner scanner = new Scanner(System.in);
		char input = scanner.next().charAt(0);
		
		if(input == 'w')
			hero.moveUp(gameMap);
		else if(input == 'a')
			hero.moveLeft(gameMap);
		else if(input == 's')
			hero.moveDown(gameMap);
		else if(input == 'd')
			hero.moveRight(gameMap);
	}
}
