package dkeep.cli;

import dkeep.logic.Map;
import dkeep.logic.GameLogic;
import dkeep.logic.Hero;
import dkeep.logic.Ogre;
import dkeep.logic.Guard;

import java.util.Scanner;

public class UserInput {
	static Map gameMap = new Map();
	static int gameState = 0;
	static GameLogic gameLogic = new GameLogic();
	
	static Hero hero = new Hero();
	static Guard guard = new Guard();
	static Ogre ogre = new Ogre();
	
	
	public static void main(String[] args) {
		gameMap.printMap();

		while (true) {

			gameLogic.moveNPC(gameMap, guard, ogre);
			
			gameState = gameLogic.verifyGameState(hero, guard, ogre, gameMap);
			
			if (gameState == -1){
				gameMap.printMap();
				break;
			}
			
			userInput(gameMap.currentMap.level);
			
			gameState = gameLogic.verifyGameState(hero, guard, ogre, gameMap);
			
			if (gameState == -1) {
				gameMap.printMap();
				break;
			}

			if (gameMap.currentMap.level == 2 && gameMap.currentMap.onGame == 0) {
				System.out.println("Next Level!!");
				gameMap.changeMap();
				hero.x = 1;
				hero.y = 8;
				gameMap.currentMap.onGame = 1;
			}
			gameMap.printMap();
			if (gameMap.currentMap.level == 3) {
				System.out.println("You win!!");
				return;
			}
		}
		
		System.out.println("You Lost!!");
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
