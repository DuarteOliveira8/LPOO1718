package dkeep.cli;

import dkeep.logic.Map;
import dkeep.logic.GameLogic;
import java.util.Scanner;

public class UserInput {
	static Map gameMap = new Map();
	static int gameState = 0;
	static GameLogic gameLogic = new GameLogic();
	
	public static void main(String[] args) {
		gameMap.printMap();

		int level = 1;
		
		while (gameState != -1) {

			gameLogic.moveNPC(level, gameMap);
			
			gameState = userInput(level);

			if (gameState == 1) {
				System.out.println("Next Level!!");
				gameMap.changeMap();
				gameState = 0;
				level++;
			}
			gameMap.printMap();
			if (level == 3) {
				System.out.println("You win!!");
				return;
			}
		}
		
		System.out.println("You Lost!!");
	}
	
	public static int userInput(int level) {
		Scanner scanner = new Scanner(System.in);
		char input = scanner.next().charAt(0);
		
		if(input == 'w')
			return gameMap.moveUp('H', level);
		else if(input == 'a')
			return gameMap.moveLeft('H', level);
		else if(input == 's')
			return gameMap.moveDown('H', level);
		else if(input == 'd')
			return gameMap.moveRight('H', level);
		
		return 0;
	}
}
