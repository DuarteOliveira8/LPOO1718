package dkeep.gui;

import dkeep.logic.*;
import java.awt.*;
import javax.swing.*;

public class GraphicsPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	GameData gameData;
	
	public GraphicsPanel(GameData gameData){
		this.gameData = gameData;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < gameData.gameMap.currentMap.emptymap.length; i++)
			for (int j = 0; j < gameData.gameMap.currentMap.emptymap[i].length; j++)
				if(gameData.gameMap.currentMap.emptymap[j][i] == 'X')
					g.drawImage(gameData.wallIcon, i * 50, j * 50, 50, 50, this);
				else if(gameData.gameMap.currentMap.emptymap[j][i] == ' ')
					g.drawImage(gameData.woodIcon, i * 50, j * 50, 50, 50, this);
				else if(gameData.gameMap.currentMap.emptymap[j][i] == 'I')
						g.drawImage(gameData.closedDoor, i * 50, j * 50, 50, 50, this);
				else if(gameData.gameMap.currentMap.emptymap[j][i] == 'S')
					g.drawImage(gameData.closedDoor, i * 50, j * 50, 50, 50, this);	
		
		if(gameData.gameMap.currentMap.doorsOpen)
			openDoors(g);
		
		gameData.chooseIcons();

		if (gameData.hero.lever == 0 && gameData.gameMap.currentMap.level == 1)
			g.drawImage(gameData.leverIcon, gameData.gameMap.currentMap.keyX*50, gameData.gameMap.currentMap.keyY*50, 50, 50, this);
		else if (gameData.hero.lever == 0 && gameData.gameMap.currentMap.level == 2 && !gameData.gameMap.currentMap.doorsOpen)
			g.drawImage(gameData.keyIcon, gameData.gameMap.currentMap.keyX*50, gameData.gameMap.currentMap.keyY*50, 50, 50, this);
		
		if(gameData.gameMap.currentMap.level == 1)
				g.drawImage(gameData.guardIcon, gameData.guard.x*50, gameData.guard.y*50, 50, 50, this);
		else if(gameData.gameMap.currentMap.level == 2) {
			for(Ogre ogreit : gameData.ogres)
				g.drawImage(gameData.clubIcon, ogreit.clubX*50, ogreit.clubY*50, 50, 50, this);

			for(Ogre ogreit : gameData.ogres)
				g.drawImage(gameData.ogreIcon, ogreit.x*50, ogreit.y*50, 50, 50, this);
		}
		
		
		if (!gameData.hero.armed && gameData.gameMap.currentMap.level == 2)
			g.drawImage(gameData.swordIcon, gameData.gameMap.currentMap.xClub*50, gameData.gameMap.currentMap.yClub*50, 50, 50, this);
		
		//if(!gameData.hero.armed && gameData.gameMap.currentMap.level == 1)
			g.drawImage(gameData.heroIcon, gameData.hero.x*50, gameData.hero.y*50, 50, 50, this);
		
	}
	
	public void openDoors(Graphics g) {
		for (int i = 0; i < gameData.gameMap.currentMap.emptymap.length; i++) {
			if(gameData.gameMap.currentMap.emptymap[i][0] == 'I')
				g.drawImage(gameData.openDoor, 0*50, i*50, 50, 50, this);	
		}
		
		for (int i = 0; i < gameData.gameMap.currentMap.emptymap.length; i++) {
			if(gameData.gameMap.currentMap.emptymap[i][gameData.gameMap.currentMap.emptymap[0].length-1] == 'I')
				g.drawImage(gameData.openDoor, (gameData.gameMap.currentMap.emptymap[0].length-1)*50, i*50, 50, 50, this);	
		}
		
		for (int i = 0; i < gameData.gameMap.currentMap.emptymap[0].length; i++) {
			if(gameData.gameMap.currentMap.emptymap[0][i] == 'I')
				g.drawImage(gameData.openDoor, i*50, 0*50, 50, 50, this);	
		}
		
		for (int i = 0; i < gameData.gameMap.currentMap.emptymap[gameData.gameMap.currentMap.emptymap.length-1].length; i++) {
			if(gameData.gameMap.currentMap.emptymap[gameData.gameMap.currentMap.emptymap.length-1][i] == 'I')
				g.drawImage(gameData.openDoor, i*50, (gameData.gameMap.currentMap.emptymap.length-1)*50, 50, 50, this);	
		}
	}
}
