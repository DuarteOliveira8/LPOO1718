package dkeep.logic;

import dkeep.logic.Character;

public class Hero extends Character{
	
	public void initialize() {
		x = 1;
		y = 1;
		symbol = 'H';
	}
	
	@Override
	public void moveLeftSpecific(Map map) {
		if (map.currentMap.map[y][x-1] == 'I' && map.currentMap.map[y][x] == 'K' && map.currentMap.lever == 1) {
			map.currentMap.map[y][x-1] = 'S';
			map.currentMap.map[y][x] = 'H';
		}
		else if (map.currentMap.map[y][x-1] == 'k') {
			map.currentMap.map[y][x] = ' ';
			if (map.currentMap.level == 1) {
				map.currentMap.lever = 1;
				map.currentMap.map[y][x-1] = 'H';
				map.currentMap.map[5][0] = 'S';
				map.currentMap.map[6][0] = 'S';
			}
			else if (map.currentMap.level == 2) {
				map.currentMap.lever = 1;
				map.currentMap.map[y][x-1] = 'K';
			}	
			x--;
		}
		else if (map.currentMap.map[y][x-1] == 'S' && map.currentMap.map[y][x] == 'H') {
			map.currentMap.map[y][x] = ' ';
			map.currentMap.map[y][x-1] = symbol;
		}
	}
	
	@Override
	public void moveRightSpecific(Map map) {
		if (map.currentMap.map[y][x+1] == 'I' && map.currentMap.lever == 1 && map.currentMap.map[y][x] == 'K') {
			map.currentMap.map[y][x+1] = 'S';
			map.currentMap.map[y][x] = 'H';
		}
		else if (map.currentMap.map[y][x+1] == 'k') {
			map.currentMap.map[y][x] = ' ';
			if (map.currentMap.level == 1) {
				map.currentMap.lever = 1;
				map.currentMap.map[y][x+1] = 'H';
				map.currentMap.map[5][0] = 'S';
				map.currentMap.map[6][0] = 'S';
			}
			else if (map.currentMap.level == 2) {
				map.currentMap.lever = 1;
				map.currentMap.map[y][x+1] = 'K';
			}
			x++;
		}
		else if (map.currentMap.map[y][x+1] == 'S' && map.currentMap.map[y][x] == 'H') {
			map.currentMap.map[y][x] = ' ';
			map.currentMap.map[y][x+1] = symbol;
		}
		
	}
	
	@Override
	public void moveDownSpecific(Map map) {
		if (map.currentMap.map[y+1][x] == 'I' && map.currentMap.lever == 1 && map.currentMap.map[y][x] == 'K') {
			map.currentMap.map[y+1][x] = 'S';
			map.currentMap.map[y][x] = 'H';
		}
		else if (map.currentMap.map[y+1][x] == 'k') {
			map.currentMap.map[y][x] = ' ';
			if (map.currentMap.level == 1) {
				map.currentMap.lever = 1;
				map.currentMap.map[y+1][x] = 'H';
				map.currentMap.map[5][0] = 'S';
				map.currentMap.map[6][0] = 'S';
			}
			else if (map.currentMap.level == 2) {
				map.currentMap.lever = 1;
				map.currentMap.map[y+1][x] = 'K';
			}
			y++;
		}
		else if (map.currentMap.map[y+1][x] == 'S' && map.currentMap.map[y][x] == 'H') {
			map.currentMap.map[y][x] = ' ';
			map.currentMap.map[y+1][x] = symbol;
		}
	}
	
	@Override
	public void moveUpSpecific(Map map) {
		if (map.currentMap.map[y-1][x] == 'I' && map.currentMap.lever == 1 && map.currentMap.map[y][x] == 'K') {
			map.currentMap.map[y-1][x] = 'S';
			map.currentMap.map[y][x] = 'H';
		}
		else if (map.currentMap.map[y-1][x] == 'k') {
			map.currentMap.map[y][x] = ' ';
			if (map.currentMap.level == 1) {
				map.currentMap.lever = 1;
				map.currentMap.map[y-1][x] = 'H';
				map.currentMap.map[5][0] = 'S';
				map.currentMap.map[6][0] = 'S';
			}
			else if (map.currentMap.level == 2) {
				map.currentMap.lever = 1;
				map.currentMap.map[y-1][x] = 'K';
			}
			y--;
		}
		else if (map.currentMap.map[y-1][x] == 'S' && map.currentMap.map[y][x] == 'H') {
			map.currentMap.map[y][x] = ' ';
			map.currentMap.map[y-1][x] = symbol;
		}
	}
}
