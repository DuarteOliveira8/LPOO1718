package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;

import dkeep.logic.Character;

public class Guard extends Character{
	char patrol[] = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
	char patrolreverse[] = {'s','d','w','w','w','w','d','d','d','d','d','d','w','a','a','a','a','a','a','a','s','s','s','s'};
	int personality; //-1: drunk // 0: normal // 1: suspicious
	public int characterState = 0;
	
	public Guard(int x, int y) {
		this.x = x;
		this.y = y;
		symbol = 'G';
		personality = ThreadLocalRandom.current().nextInt(-1, 2);
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
