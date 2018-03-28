package dkeep.gui;

import dkeep.cli.*;
import dkeep.logic.*;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	BufferedImage heronotarmed;
	BufferedImage heroarmed;
	BufferedImage closeddoor;
	BufferedImage opendoor;
	BufferedImage wall;
	BufferedImage wood;
	BufferedImage ogre;
	BufferedImage key;
	BufferedImage guardawake;
	BufferedImage guardsleep;
	BufferedImage club;
	BufferedImage sword;
	UserInput userinput;
	
	
	public GraphicsPanel(UserInput userinput){
		this.userinput = userinput;	
	}
	
	public void loadImages() throws IOException{
		heronotarmed = ImageIO.read(new File("images/hero.png"));
		heroarmed = ImageIO.read(new File("images/heroarmed.png"));
		closeddoor = ImageIO.read(new File("images/closeddoor.png"));
		opendoor = ImageIO.read(new File("images/opendoor.png"));
		wall = ImageIO.read(new File("images/wall.png"));
		wood = ImageIO.read(new File("images/wood.png"));
		ogre = ImageIO.read(new File("images/ogre.png"));
		key = ImageIO.read(new File("images/key.png"));
		guardawake = ImageIO.read(new File("images/guard.png"));
		guardsleep = ImageIO.read(new File("images/guardsleep.png"));
		club = ImageIO.read(new File("images/club.png"));
		sword = ImageIO.read(new File("images/sword.png"));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
			
		
		for (int i = 0; i < userinput.gameMap.currentMap.emptymap.length; i++)
			for (int j = 0; j < userinput.gameMap.currentMap.emptymap[i].length; j++)
				if(userinput.gameMap.currentMap.emptymap[j][i] == 'X')
					g.drawImage(wall, i * 25, j * 25, 25, 25, this);
				else if(userinput.gameMap.currentMap.emptymap[j][i] == ' ')
					g.drawImage(wood, i * 25, j * 25, 25, 25, this);
				else if(userinput.gameMap.currentMap.emptymap[j][i] == 'I')
						g.drawImage(closeddoor, i * 25, j * 25, 25, 25, this);
				else if(userinput.gameMap.currentMap.emptymap[j][i] == 'S')
					g.drawImage(closeddoor, i * 25, j * 25, 25, 25, this);	
		
		if(userinput.gameMap.currentMap.doorsOpen)
			openDoors(g);

		
		if(userinput.gameMap.currentMap.level == 1)
			if(userinput.guard.characterState == 1 && userinput.guard.personality == -1)
				g.drawImage(guardawake, userinput.guard.x*25, userinput.guard.y*25, 25, 25, this);
			else
				g.drawImage(guardsleep, userinput.guard.x*25, userinput.guard.y*25, 25, 25, this);
		else if(userinput.gameMap.currentMap.level == 2) {
			for(Ogre ogreit : userinput.ogres) {
				if (ogreit.clubY != -1 && ogreit.clubX != -1)
					g.drawImage(club, ogreit.clubX*25, ogreit.clubY*25, 25, 25, this);
			}
			for(Ogre ogreit : userinput.ogres) {
				g.drawImage(ogre, ogreit.x*25, ogreit.y*25, 25, 25, this);
			}
		}
		
		if (userinput.hero.lever == 0)
			g.drawImage(key, userinput.gameMap.currentMap.keyX*25, userinput.gameMap.currentMap.keyY*25, 25, 25, this);
		
		if (!userinput.hero.armed && userinput.gameMap.currentMap.level == 2)
			g.drawImage(sword, userinput.gameMap.currentMap.xClub*25, userinput.gameMap.currentMap.yClub*25, 25, 25, this);
		
		if(!userinput.hero.armed)
			g.drawImage(heronotarmed, userinput.hero.x*25, userinput.hero.y*25, 25, 25, this);
		else
			g.drawImage(heroarmed, userinput.hero.x*25, userinput.hero.y*25, 25, 25, this);		
	}
	
	public void openDoors(Graphics g) {
		for (int i = 0; i < userinput.gameMap.currentMap.emptymap.length; i++) {
			if(userinput.gameMap.currentMap.emptymap[i][0] == 'I')
				g.drawImage(opendoor, 0*25, i*25, 25, 25, this);	
		}
		
		for (int i = 0; i < userinput.gameMap.currentMap.emptymap.length; i++) {
			if(userinput.gameMap.currentMap.emptymap[i][userinput.gameMap.currentMap.emptymap[0].length-1] == 'I')
				g.drawImage(opendoor, (userinput.gameMap.currentMap.emptymap[0].length-1)*25, i*25, 25, 25, this);	
		}
		
		for (int i = 0; i < userinput.gameMap.currentMap.emptymap[0].length; i++) {
			if(userinput.gameMap.currentMap.emptymap[0][i] == 'I')
				g.drawImage(opendoor, i*25, 0*25, 25, 25, this);	
		}
		
		for (int i = 0; i < userinput.gameMap.currentMap.emptymap[userinput.gameMap.currentMap.emptymap.length-1].length; i++) {
			if(userinput.gameMap.currentMap.emptymap[userinput.gameMap.currentMap.emptymap.length-1][i] == 'I')
				g.drawImage(opendoor, i*25, (userinput.gameMap.currentMap.emptymap.length-1)*25, 25, 25, this);	
		}
	}
	
}
