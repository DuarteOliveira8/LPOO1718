package game;
import game.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class Start {
	public static void main(String[] args) {
		Map gameMap = new Map();
		int gameState = 0;
		int level = 1;
		char input;
		Scanner scanner = new Scanner(System.in);
		gameMap.printMap();
		char patrol[] = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w','w'};
		char ogre[] = {'a', 's', 'd', 'w'};
		int indiceG = 0;
		int indiceO = 0;
		
		while (gameState != -1) {
			if (level == 1) {
				if (patrol[indiceG] == 'w')
					gameMap.moveUp('G');
				else if (patrol[indiceG] == 'a')
					gameMap.moveLeft('G');
				else if (patrol[indiceG] == 's')
					gameMap.moveDown('G');
				else if (patrol[indiceG] == 'd')
					gameMap.moveRight('G');
			}
			else if(level == 2) {
				indiceO = ThreadLocalRandom.current().nextInt(0, 4);
				if (ogre[indiceO] == 'w')
					gameMap.moveUp('O');
				else if (ogre[indiceO] == 'a')
					gameMap.moveLeft('O');
				else if (ogre[indiceO] == 's')
					gameMap.moveDown('O');
				else if (ogre[indiceO] == 'd')
					gameMap.moveRight('O');
				
				System.out.println(indiceO);
			}
			input = scanner.next().charAt(0);
			
			if(input == 'w')
				gameState = gameMap.moveUp('H');
			else if(input == 'a')
				gameState = gameMap.moveLeft('H');
			else if(input == 's')
				gameState = gameMap.moveDown('H');
			else if(input == 'd')
				gameState = gameMap.moveRight('H');

			
			indiceG++;
			if (indiceG == patrol.length)
				indiceG = 0;
			
			if (indiceO == ogre.length)
				indiceO = 0;
			
			
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
}
