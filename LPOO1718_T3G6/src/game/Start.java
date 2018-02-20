package game;
import game.Map;
import java.util.Scanner;


public class Start {
	public static void main(String[] args) {
		Map gameMap = new Map();
		int gameState = 0;
		char input;
		Scanner scanner = new Scanner(System.in);
		gameMap.printMap();
		char patrol[] = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w','w'};
		Integer heroX = 1;
		Integer heroY = 1;
		Integer guardX = 1;
		Integer guardY = 1;
		int indiceG = 0;
		
		while (gameState != -1) {
			input = scanner.next().charAt(0);
			if(input == 'w')
				gameState = gameMap.moveUp('H', heroX, heroY);
			else if(input == 'a')
				gameState = gameMap.moveLeft('H', heroX, heroY);
			else if(input == 's')
				gameState = gameMap.moveDown('H', heroX, heroY);
			else if(input == 'd')
				gameState = gameMap.moveRight('H', heroX, heroY);
			
			System.out.println("You fsvds!!");
			if(patrol[indiceG] == 'w')
				gameMap.moveUp('H', guardX, guardY);
			else if(input == 'a')
				gameMap.moveLeft('H', guardX, guardY);
			else if(patrol[indiceG] == 's')
				gameMap.moveDown('H', guardX, guardY);
			else if(patrol[indiceG] == 'd')
				gameMap.moveRight('H', guardX, guardY);
			
			System.out.println("You dsfdsgd!!");
			indiceG++;
			if (indiceG == patrol.length)
				indiceG = 0;
			
			gameMap.printMap();
			System.out.println("You hgjygj!!");
			
			if (gameState == 1) {
				System.out.println("You win!!");
				return;
			}
		}
		
		System.out.println("You Lost!!");
	}
}
