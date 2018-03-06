package dkeep.logic;

import dkeep.logic.Character;

public class Hero extends Character{
	
	public Hero() {
		x = 1;
		y = 1;
		symbol = 'H';
	}
	
	@Override
	public void moveLeftSpecific(Map map) {
		if(map.currentMap.map[y][x-1] == ' ' && lever == 1) {
			if (map.currentMap.level == 1) {
				map.currentMap.map[y][x-1] = symbol;
				map.currentMap.map[y][x] = 'k';
			}
			else if (map.currentMap.level == 2) {
				map.currentMap.map[y][x-1] = symbol;
				map.currentMap.map[y][x] = ' ';
			}
			lever = 0;
			x--;
		}
		else if (map.currentMap.map[y][x-1] == 'I' && symbol == 'K') {
			map.currentMap.map[y][x-1] = 'S';
			symbol = 'H';
			map.currentMap.map[y][x] = symbol;
		}
		else if (map.currentMap.map[y][x-1] == 'k') {
			map.currentMap.map[y][x] = ' ';
			if (map.currentMap.level == 1) {
				lever = 1;
				map.currentMap.map[y][x-1] = symbol;
				map.currentMap.map[5][0] = 'S';
				map.currentMap.map[6][0] = 'S';
			}
			else if (map.currentMap.level == 2) {
				lever = 1;
				symbol = 'K';
				map.currentMap.map[y][x-1] = symbol;
			}	
			x--;
		}
		else if (map.currentMap.map[y][x-1] == 'S') {
			map.currentMap.map[y][x] = ' ';
			map.currentMap.map[y][x-1] = symbol;
			map.currentMap.level++;
			map.currentMap.onGame = 0;
		}
	}
	
	@Override
	public void moveRightSpecific(Map map) {
		if(map.currentMap.map[y][x+1] == ' ' && lever == 1) {
			if (map.currentMap.level == 1) {
				map.currentMap.map[y][x+1] = symbol;
				map.currentMap.map[y][x] = 'k';
			}
			else if (map.currentMap.level == 2) {
				map.currentMap.map[y][x+1] = symbol;
				map.currentMap.map[y][x] = ' ';
			}
			lever = 0;
			x++;
		}
		else if (map.currentMap.map[y][x+1] == 'I' && symbol == 'K') {
			map.currentMap.map[y][x+1] = 'S';
			symbol = 'H';
			map.currentMap.map[y][x] = symbol;
		}
		else if (map.currentMap.map[y][x+1] == 'k') {
			map.currentMap.map[y][x] = ' ';
			if (map.currentMap.level == 1) {
				lever = 1;
				map.currentMap.map[y][x+1] = symbol;
				map.currentMap.map[5][0] = 'S';
				map.currentMap.map[6][0] = 'S';
			}
			else if (map.currentMap.level == 2) {
				lever = 1;
				symbol = 'K';
				map.currentMap.map[y][x+1] = symbol;
			}
			x++;
		}
		else if (map.currentMap.map[y][x+1] == 'S' && map.currentMap.map[y][x] == 'H') {
			map.currentMap.map[y][x] = ' ';
			map.currentMap.map[y][x+1] = symbol;
			map.currentMap.level++;
			map.currentMap.onGame = 0;
		}
		
	}
	
	@Override
	public void moveDownSpecific(Map map) {
		if(map.currentMap.map[y+1][x] == ' ' && lever == 1) {
			if (map.currentMap.level == 1) {
				map.currentMap.map[y+1][x] = symbol;
				map.currentMap.map[y][x] = 'k';
			}
			else if (map.currentMap.level == 2) {
				map.currentMap.map[y+1][x] = symbol;
				map.currentMap.map[y][x] = ' ';
			}
			lever = 0;
			y++;
		}
		else if (map.currentMap.map[y+1][x] == 'I' && symbol == 'K') {
			map.currentMap.map[y+1][x] = 'S';
			symbol = 'H';
			map.currentMap.map[y][x] = symbol;
		}
		else if (map.currentMap.map[y+1][x] == 'k') {
			map.currentMap.map[y][x] = ' ';
			if (map.currentMap.level == 1) {
				lever = 1;
				map.currentMap.map[y+1][x] = symbol;
				map.currentMap.map[5][0] = 'S';
				map.currentMap.map[6][0] = 'S';
			}
			else if (map.currentMap.level == 2) {
				lever = 1;
				symbol = 'K';
				map.currentMap.map[y+1][x] = symbol;
			}
			y++;
		}
		else if (map.currentMap.map[y+1][x] == 'S' && map.currentMap.map[y][x] == 'H') {
			map.currentMap.map[y][x] = ' ';
			map.currentMap.map[y+1][x] = symbol;
			map.currentMap.level++;
			map.currentMap.onGame = 0;
		}
	}
	
	@Override
	public void moveUpSpecific(Map map) {
		if(map.currentMap.map[y-1][x] == ' ' && lever == 1) {
			if (map.currentMap.level == 1) {
				map.currentMap.map[y-1][x] = symbol;
				map.currentMap.map[y][x] = 'k';
			}
			else if (map.currentMap.level == 2) {
				map.currentMap.map[y-1][x] = symbol;
				map.currentMap.map[y][x] = ' ';
			}
			lever = 0;
			y--;
		}
		else if (map.currentMap.map[y-1][x] == 'I' && symbol == 'K') {
			map.currentMap.map[y-1][x] = 'S';
			symbol = 'H';
			map.currentMap.map[y][x] = symbol;
		}
		else if (map.currentMap.map[y-1][x] == 'k') {
			map.currentMap.map[y][x] = ' ';
			if (map.currentMap.level == 1) {
				lever = 1;
				map.currentMap.map[y-1][x] = symbol;
				map.currentMap.map[5][0] = 'S';
				map.currentMap.map[6][0] = 'S';
			}
			else if (map.currentMap.level == 2) {
				lever = 1;
				symbol = 'K';
				map.currentMap.map[y-1][x] = symbol;
			}
			y--;
		}
		else if (map.currentMap.map[y-1][x] == 'S' && map.currentMap.map[y][x] == 'H') {
			map.currentMap.map[y][x] = ' ';
			map.currentMap.map[y-1][x] = symbol;
			map.currentMap.level++;
			map.currentMap.onGame = 0;
		}
	}
}
