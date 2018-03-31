package dkeep.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import dkeep.logic.GameLogic;
import dkeep.logic.Guard;
import dkeep.logic.Hero;
import dkeep.logic.Map;
import dkeep.logic.Ogre;

public class GameData {
	int noOgres;
	int personality;
	
	public Map gameMap;
	public int gameState;
	public GameLogic gameLogic;
	
	public Hero hero;
	public Guard guard;
	public ArrayList<Ogre> ogres;
	
	BufferedImage heroIcon;
	BufferedImage guardIcon;
	BufferedImage ogreIcon;
	BufferedImage clubIcon;
	BufferedImage swordIcon;
	BufferedImage leverIcon;
	BufferedImage wallIcon;
	BufferedImage woodIcon;
	BufferedImage keyIcon;
	
	
	BufferedImage heroNotArmed;
	BufferedImage heroArmed;
	BufferedImage closedDoor;
	BufferedImage openDoor;
	BufferedImage normalOgre;
	BufferedImage guardAwake;
	BufferedImage guardSleep;
	BufferedImage normalClub;
	BufferedImage clubAndKey;
	BufferedImage ogreAndKey;
	BufferedImage ogreStunned;
	BufferedImage ogreStunnedAndKey;
	BufferedImage heroWithKey;
	BufferedImage heroArmedWithKey;
	
	JPanel game;
	JPanel settings;
	JPanel custom;
	
	public GameData() throws IOException {
		noOgres = 1;
		personality = 0;
		loadImages();
		gameMap = new Map();
	}
	
	public void loadImages() throws IOException{
		heroNotArmed = ImageIO.read(new File("images/hero.png"));
		heroArmed = ImageIO.read(new File("images/heroarmed.png"));
		closedDoor = ImageIO.read(new File("images/closeddoor.png"));
		openDoor = ImageIO.read(new File("images/opendoor.png"));
		wallIcon = ImageIO.read(new File("images/wall.png"));
		woodIcon = ImageIO.read(new File("images/wood.png"));
		normalOgre = ImageIO.read(new File("images/ogre.png"));
		keyIcon = ImageIO.read(new File("images/key.png"));
		guardAwake = ImageIO.read(new File("images/guard.png"));
		guardSleep = ImageIO.read(new File("images/guardsleep.png"));
		normalClub = ImageIO.read(new File("images/club.png"));
		swordIcon = ImageIO.read(new File("images/sword.png"));
		clubAndKey = ImageIO.read(new File("images/clubandkey.png"));
		ogreAndKey = ImageIO.read(new File("images/ogreandkey.png"));
		leverIcon = ImageIO.read(new File("images/leveron.png"));
		ogreStunned = ImageIO.read(new File("images/ogrestunned.png"));
		ogreStunnedAndKey = ImageIO.read(new File("images/ogrestunnedandkey.png"));
		heroWithKey = ImageIO.read(new File("images/herowithkey.png"));
		heroArmedWithKey = ImageIO.read(new File("images/heroarmedwithkey.png"));
	}
	
	public void initializeGame(int noOgres, int personality) {
		gameMap = new Map();
		gameLogic = new GameLogic();
		hero = new Hero(1,1);
		guard = new Guard(8,1);
		gameState = 0;
		ogres = new ArrayList<Ogre>();
		
		for(int i = 0; i < noOgres; i++) {
			ogres.add(new Ogre(gameMap.currentMap.ogreX2,gameMap.currentMap.ogreY2));
		}
		
		guard.personality = personality;
	}
	
	public void chooseIcons() {
		
		if(gameMap.currentMap.level == 1)
			if(guard.characterState == 1 && guard.personality == -1)
				guardIcon = guardSleep;
			else
				guardIcon = guardAwake;
		else if(gameMap.currentMap.level == 2) {
			for(Ogre ogreit : ogres) {
				if (ogreit.clubY != -1 && ogreit.clubX != -1 && ogreit.clubSymbol == '*')
					clubIcon = normalClub;
				else if (ogreit.clubSymbol == '$')
					clubIcon = clubAndKey;
			}
			for(Ogre ogreit : ogres) {
				if(ogreit.symbol == 'O' && ogreit.stunned == 0)
					ogreIcon = normalOgre;
				else if(ogreit.symbol == '$' && ogreit.stunned == 0)
					ogreIcon = ogreAndKey;
				else if(ogreit.symbol == 'O' && ogreit.stunned > 0)
					ogreIcon = ogreStunned;
				else if(ogreit.symbol == '$' && ogreit.stunned > 0)
					ogreIcon = ogreStunnedAndKey; 
			}
		}
		
		if(!hero.armed && gameMap.currentMap.level == 1)
			heroIcon = heroNotArmed;
		else if(!hero.armed && gameMap.currentMap.level == 2 && hero.lever == 0)
			heroIcon = heroNotArmed;
		else if(!hero.armed && gameMap.currentMap.level == 2 && hero.lever == 1)
			heroIcon = heroWithKey;
		else if(hero.armed && gameMap.currentMap.level == 2 && hero.lever == 1)
			heroIcon = heroArmedWithKey;
		else if(hero.armed && gameMap.currentMap.level == 2 && hero.lever == 0)
			heroIcon = heroArmed;
	}

}
