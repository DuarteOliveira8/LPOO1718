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
	static Map gameMap = new Map();
	static int gameState = 0;
	static GameLogic gameLogic = new GameLogic();
	
	static Hero hero = new Hero(1,1);
	static Guard guard = new Guard(8,1);
	static ArrayList<Ogre> ogres = new ArrayList<Ogre>();
	static int nOgres = ThreadLocalRandom.current().nextInt(1, 6);
	

	public static void main(String[] args) {
		
		gameMap.currentMap.initializeLevels(1);
		
		for(int i = 0; i < nOgres; i++) {
			ogres.add(new Ogre(4,1));
		}
		

		while (true) {
			gameMap.printMap();
			
			userInput(gameMap.currentMap.level);
			
			gameState = gameLogic.verifyGameState(hero, guard, ogres, gameMap);
			
			if (gameState == -1) {
				gameMap.printMap();
				break;
			}
			
			for (Ogre ogre : ogres)
				ogre.verifyStun(hero.symbol, gameMap);
			
			if (gameMap.currentMap.level == 2 && gameMap.currentMap.onGame == 0) {
				System.out.println("Next Level");
				gameMap.changeMap();
				hero.changePosition(1, 8);
				gameMap.currentMap.onGame = 1;
				
			}
			
			if (gameMap.currentMap.level == 3) {
				System.out.println("You won!");
				return;
			}
			
			gameLogic.moveNPC(gameMap, guard, ogres);
			
			gameState = gameLogic.verifyGameState(hero, guard, ogres, gameMap);
			
			if (gameState == -1){
				gameMap.printMap();
				break;
			}
			
			for (Ogre ogre : ogres)
				ogre.verifyStun(hero.symbol, gameMap);
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
