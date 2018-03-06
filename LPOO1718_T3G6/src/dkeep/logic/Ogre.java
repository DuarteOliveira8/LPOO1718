package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;

import dkeep.logic.Character;

public class Ogre extends Character{
	char movement[] = {'a', 's', 'd', 'w'};
	char movement2[] = {'d', 'd', 'd', 'd', 's', 's', 's'};
	int club = 0;
	int clubX = 0;
	int clubY = 0;
	public int stunned = 0;
	
	public Ogre() {
		x = 4;
		y = 1;
		symbol = 'O';
	}
	
	public void removeClub(Map map) {
		if(club == 1 && map.currentMap.map[y-1][x] != 'X'  && map.currentMap.map[y-1][x] != 'I' && map.currentMap.map[y-1][x] != 'O') { // CIMA
			if(map.currentMap.map[y-1][x] == '$')
				map.currentMap.map[y-1][x] = 'k';
			else
				map.currentMap.map[y-1][x] = ' ';
		}
		else if(club == 2 && map.currentMap.map[y][x+1] != 'X'  && map.currentMap.map[y][x+1] != 'I' && map.currentMap.map[y][x+1] != 'O') { // DIREITA
			if(map.currentMap.map[y][x+1] == '$')
				map.currentMap.map[y][x+1] = 'k';
			else
				map.currentMap.map[y][x+1] = ' ';
		}
		else if(club == 3 && map.currentMap.map[y+1][x] != 'X'  && map.currentMap.map[y+1][x] != 'I' && map.currentMap.map[y+1][x] != 'O') { // BAIXO
			if(map.currentMap.map[y+1][x] == '$')
				map.currentMap.map[y+1][x] = 'k';
			else
				map.currentMap.map[y+1][x] = ' ';
		}
		else if(club == 4 && map.currentMap.map[y][x-1] != 'X'  && map.currentMap.map[y][x-1] != 'I' && map.currentMap.map[y][x-1] != 'O') { // ESQUERDA
			if(map.currentMap.map[y][x-1] == '$')
				map.currentMap.map[y][x-1] = 'k';
			else
				map.currentMap.map[y][x-1] = ' ';
		}
	}
	
	public void addClub(Map map) {
		club = ThreadLocalRandom.current().nextInt(1, 5);
		
		if(club == 1 && map.currentMap.map[y-1][x] != 'X'  && map.currentMap.map[y-1][x] != 'I' && map.currentMap.map[y-1][x] != 'O') { // CIMA
			if(map.currentMap.map[y-1][x] == 'k')
				map.currentMap.map[y-1][x] = '$';
			else
				map.currentMap.map[y-1][x] = '*';
			
			clubX = x;
			clubY = y-1;
		}
		else if(club == 2 && map.currentMap.map[y][x+1] != 'X'  && map.currentMap.map[y][x+1] != 'I' && map.currentMap.map[y][x+1] != 'O') { // DIREITA
			if(map.currentMap.map[y][x+1] == 'k')
				map.currentMap.map[y][x+1] = '$';
			else
				map.currentMap.map[y][x+1] = '*';
			
			clubX = x+1;
			clubY = y;
		}
		else if(club == 3 && map.currentMap.map[y+1][x] != 'X'  && map.currentMap.map[y+1][x] != 'I' && map.currentMap.map[y+1][x] != 'O') { // BAIXO
			if(map.currentMap.map[y+1][x] == 'k')
				map.currentMap.map[y+1][x] = '$';
			else
				map.currentMap.map[y+1][x] = '*';
			
			clubX = x;
			clubY = y+1;
		}
		else if(club == 4 && map.currentMap.map[y][x-1] != 'X'  && map.currentMap.map[y][x-1] != 'I' && map.currentMap.map[y][x-1] != 'O') { // ESQUERDA
			if(map.currentMap.map[y][x-1] == 'k')
				map.currentMap.map[y][x-1] = '$';
			else
				map.currentMap.map[y][x-1] = '*';
			
			clubX = x-1;
			clubY = y;
		}
	}
	
	@Override
	public void moveLeftSpecific(Map map) {
		if(map.currentMap.map[y][x-1] == ' ' && lever == 1) {
			symbol = 'O';
			map.currentMap.map[y][x-1] = symbol;
			map.currentMap.map[y][x] = 'k';
			lever = 0;
			x--;
		}
		else if (map.currentMap.map[y][x-1] == 'k') {
			map.currentMap.map[y][x] = ' ';
			symbol = '$';
			map.currentMap.map[y][x-1] = symbol;
			lever = 1;
			x--;
		}
	}
	
	@Override
	public void moveRightSpecific(Map map) {
		if(map.currentMap.map[y][x+1] == ' ' && lever == 1) {
			symbol = 'O';
			map.currentMap.map[y][x+1] = symbol;
			map.currentMap.map[y][x] = 'k';
			lever = 0;
			x++;
		}
		else if (map.currentMap.map[y][x+1] == 'k') {
			map.currentMap.map[y][x] = ' ';
			symbol = '$';
			map.currentMap.map[y][x+1] = symbol;
			lever = 1;
			x++;
		}
	}
	
	@Override
	public void moveDownSpecific(Map map) {
		if(map.currentMap.map[y+1][x] == ' ' && lever == 1) {
			symbol = 'O';
			map.currentMap.map[y+1][x] = symbol;
			map.currentMap.map[y][x] = 'k';
			lever = 0;
			y++;
		}
		else if (map.currentMap.map[y+1][x] == 'k') {
			map.currentMap.map[y][x] = ' ';
			symbol = '$';
			map.currentMap.map[y+1][x] = symbol;
			lever = 1;
			y++;
		}
	}
	
	@Override
	public void moveUpSpecific(Map map) {
		if(map.currentMap.map[y-1][x] == ' ' && lever == 1) {
			symbol = 'O';
			map.currentMap.map[y-1][x] = symbol;
			map.currentMap.map[y][x] = 'k';
			lever = 0;
			y--;
		}
		else if (map.currentMap.map[y-1][x] == 'k') {
			map.currentMap.map[y][x] = ' ';
			symbol = '$';
			map.currentMap.map[y-1][x] = symbol;
			lever = 1;
			y--;
		}
	}
	
	public void verifyStun(char heroSymbol, Map gameMap) {
		if(gameMap.currentMap.level == 2 &&
				(gameMap.currentMap.map[y][x+1] == heroSymbol ||
				gameMap.currentMap.map[y][x-1] == heroSymbol ||
				gameMap.currentMap.map[y-1][x] == heroSymbol ||
				gameMap.currentMap.map[y+1][x] == heroSymbol)) {
			stunned = 2;
		}
	}
}