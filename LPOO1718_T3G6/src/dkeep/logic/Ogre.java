package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;

import dkeep.logic.Character;

public class Ogre extends Character{
	char movement[] = {'a', 's', 'd', 'w'};
	char movement2[] = {'d', 'd', 'd', 'd', 's', 's', 's'};
	int club = 0;
	int clubX = 0;
	int clubY = 0;
	char clubSymbol = '*';
	public int stunned = 0;
	
	public Ogre(int x, int y) {
		this.x = x;
		this.y = y;
		symbol = 'O';
	}
	
	public void addClub(Map map) {
		club = ThreadLocalRandom.current().nextInt(1, 5);
		
		if(club == 1 && map.currentMap.emptymap[y-1][x] != 'X' && map.currentMap.emptymap[y-1][x] != 'I' && stunned == 0 && !(map.currentMap.xClub == x && map.currentMap.yClub == y-1)) { // CIMA
			if(map.currentMap.keyX == x && map.currentMap.keyY == y-1)
				clubSymbol = '$';
			else
				clubSymbol = '*';
			
			clubX = x;
			clubY = y-1;
		}
		else if(club == 2 && map.currentMap.emptymap[y][x+1] != 'X'  && map.currentMap.emptymap[y][x+1] != 'I' && stunned == 0 && !(map.currentMap.xClub == x+1 && map.currentMap.yClub == y)) { // DIREITA
			if(map.currentMap.keyX == x+1 && map.currentMap.keyY == y)
				clubSymbol = '$';
			else
				clubSymbol = '*';
			
			clubX = x+1;
			clubY = y;
		}
		else if(club == 3 && map.currentMap.emptymap[y+1][x] != 'X'  && map.currentMap.emptymap[y+1][x] != 'I' && stunned == 0 && !(map.currentMap.xClub == x && map.currentMap.yClub == y+1)) { // BAIXO
			if(map.currentMap.keyX == x && map.currentMap.keyY == y+1)
				clubSymbol = '$';
			else
				clubSymbol = '*';
			
			clubX = x;
			clubY = y+1;
		}
		else if(club == 4 && map.currentMap.emptymap[y][x-1] != 'X'  && map.currentMap.emptymap[y][x-1] != 'I' && stunned == 0 && !(map.currentMap.xClub == x-1 && map.currentMap.yClub == y)) { // ESQUERDA
			if(map.currentMap.keyX == x-1 && map.currentMap.keyY == y)
				clubSymbol = '$';
			else
				clubSymbol = '*';
			
			clubX = x-1;
			clubY = y;
		}
		else {
			clubX = -1;
			clubY = -1;
		}
	}
	
	@Override
	public void moveLeftSpecific(Map map) {
		if(map.currentMap.emptymap[y][x-1] == ' ' && lever == 1) {
			symbol = 'O';
			map.currentMap.keySymbol = 'k';
			lever = 0;
			x--;
		}
		else if (map.currentMap.keyX == x-1 && map.currentMap.keyY == y) {
			map.currentMap.keySymbol = '$';
			lever = 1;
			x--;
		}
	}
	
	@Override
	public void moveRightSpecific(Map map) {
		if(map.currentMap.emptymap[y][x+1] == ' ' && lever == 1) {
			symbol = 'O';
			map.currentMap.keySymbol = 'k';
			lever = 0;
			x++;
		}
		else if (map.currentMap.keyX == x+1 && map.currentMap.keyY == y) {
			map.currentMap.keySymbol = '$';
			lever = 1;
			x++;
		}
	}
	
	@Override
	public void moveDownSpecific(Map map) {
		if(map.currentMap.emptymap[y+1][x] == ' ' && lever == 1) {
			symbol = 'O';
			map.currentMap.keySymbol = 'k';
			lever = 0;
			y++;
		}
		else if (map.currentMap.keyX == x && map.currentMap.keyY == y+1) {
			map.currentMap.keySymbol = '$';
			lever = 1;
			y++;
		}
	}
	
	@Override
	public void moveUpSpecific(Map map) {
		if(map.currentMap.emptymap[y-1][x] == ' ' && lever == 1) {
			symbol = 'O';
			map.currentMap.keySymbol = 'k';
			lever = 0;
			y--;
		}
		else if (map.currentMap.keyX == x && map.currentMap.keyY == y-1) {
			map.currentMap.keySymbol = '$';
			lever = 1;
			y--;
		}
	}
	
	public void verifyStun(Hero hero, Map gameMap) {
		if(gameMap.currentMap.level == 2 &&
				((hero.x == x+1 && hero.y == y) ||
				(hero.x == x-1 && hero.y == y) ||
				(hero.x == x && hero.y == y+1) ||
				(hero.x == x && hero.y == y-1))) {
			stunned = 2;
		}
	}
}