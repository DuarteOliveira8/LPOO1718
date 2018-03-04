package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;

import dkeep.logic.Character;

public class Ogre extends Character{
	char ogre[] = {'a', 's', 'd', 'w'};
	int club = 0;
	
	public void initialize() {
		x = 4;
		y = 1;
		symbol = 'O';
	}
	
	public void removeClub(CurrentMap currentMap) {
		if(club == 1 && currentMap.map[y-1][x] != 'X'  && currentMap.map[y-1][x] != 'I') { // CIMA
			if(currentMap.map[y-1][x] == '$')
				currentMap.map[y-1][x] = 'k';
			else
				currentMap.map[y-1][x] = ' ';
		}
		else if(club == 2 && currentMap.map[y][x+1] != 'X'  && currentMap.map[y][x+1] != 'I') { // DIREITA
			if(currentMap.map[y][x+1] == '$')
				currentMap.map[y][x+1] = 'k';
			else
				currentMap.map[y][x+1] = ' ';
		}
		else if(club == 3 && currentMap.map[y+1][x] != 'X'  && currentMap.map[y+1][x] != 'I') { // BAIXO
			if(currentMap.map[y+1][x] == '$')
				currentMap.map[y+1][x] = 'k';
			else
				currentMap.map[y+1][x] = ' ';
		}
		else if(club == 4 && currentMap.map[y][x-1] != 'X'  && currentMap.map[y][x-1] != 'I') { // ESQUERDA
			if(currentMap.map[y][x-1] == '$')
				currentMap.map[y][x-1] = 'k';
			else
				currentMap.map[y][x-1] = ' ';
		}
	}
	
	public void addClub(CurrentMap currentMap) {
		club = ThreadLocalRandom.current().nextInt(1, 5);
		
		if(club == 1 && currentMap.map[y-1][x] != 'X'  && currentMap.map[y-1][x] != 'I') { // CIMA
			if(currentMap.map[y-1][x] == 'k')
				currentMap.map[y-1][x] = '$';
			else
				currentMap.map[y-1][x] = '*';
		}
		else if(club == 2 && currentMap.map[y][x+1] != 'X'  && currentMap.map[y][x+1] != 'I') { // DIREITA
			if(currentMap.map[y][x+1] == 'k')
				currentMap.map[y][x+1] = '$';
			else
				currentMap.map[y][x+1] = '*';
		}
		else if(club == 3 && currentMap.map[y+1][x] != 'X'  && currentMap.map[y+1][x] != 'I') { // BAIXO
			if(currentMap.map[y+1][x] == 'k')
				currentMap.map[y+1][x] = '$';
			else
				currentMap.map[y+1][x] = '*';
		}
		else if(club == 4 && currentMap.map[y][x-1] != 'X'  && currentMap.map[y][x-1] != 'I') { // ESQUERDA
			if(currentMap.map[y][x-1] == 'k')
				currentMap.map[y][x-1] = '$';
			else
				currentMap.map[y][x-1] = '*';
		}
	}
	
	@Override
	public void moveLeftSpecific(Map map) {
		
	}
	
	@Override
	public void moveRightSpecific(Map map) {
		
	}
	
	@Override
	public void moveDownSpecific(Map map) {
		
	}
	
	@Override
	public void moveUpSpecific(Map map) {
		
	}
}