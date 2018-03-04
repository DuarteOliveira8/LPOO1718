package dkeep.logic;

import dkeep.logic.Character;

public class Guard extends Character{
	char patrol[] = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w','w'};
	
	public void initialize() {
		x = 8;
		y = 1;
		symbol = 'G';
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
