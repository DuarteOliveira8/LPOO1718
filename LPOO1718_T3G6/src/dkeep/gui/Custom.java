package dkeep.gui;

import java.awt.Graphics;
import java.io.IOException;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Custom extends JPanel implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	JFrame frame;
	GameData gameData;
	private JTextField textField;
	private JTextField textField_1;
	BufferedImage iconOnMouse;
	char charSelected;
	
	public Custom(JFrame frame, GameData gameData) {
		addMouseListener(this);
		addMouseMotionListener(this);
		this.frame = frame;
		this.gameData = gameData;
		iconOnMouse = gameData.wallIcon;
		charSelected = 'X';
		initialize();
	}
	
	private void initialize() {
		setLayout(null);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				gameData.settings.setVisible(false);
				gameData.game.setVisible(true);
				gameData.custom.setVisible(false);
			}
		});
		
		btnSave.setBounds(599, 620, 100, 30);
		add(btnSave);
		
		JLabel lblCustomizeTheKeep = new JLabel("Length");
		lblCustomizeTheKeep.setBounds(20, 6, 54, 47);
		add(lblCustomizeTheKeep);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(86, 16, 54, 26);
		add(textField);
		
		JLabel label = new JLabel("Customize the keep level using the buttons on the right");
		label.setBounds(20, 594, 397, 81);
		add(label);
		
		JLabel lblWidth = new JLabel("Width");
		lblWidth.setBounds(20, 37, 54, 47);
		add(lblWidth);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(86, 47, 54, 26);
		add(textField_1);
		
		JButton btnWall = new JButton("Wall");
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iconOnMouse = gameData.wallIcon;
				charSelected = 'X';
				repaint();
			}
		});
		btnWall.setBounds(631, 104, 100, 30);
		add(btnWall);
		
		JButton btnWood = new JButton("Floor");
		btnWood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iconOnMouse = gameData.woodIcon;
				charSelected = ' ';
				repaint();
			}
		});
		btnWood.setBounds(631, 180, 100, 30);
		add(btnWood);
		
		JButton btnDoor = new JButton("Door");
		btnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iconOnMouse = gameData.closedDoor;
				charSelected = 'I';
				repaint();
			}
		});
		btnDoor.setBounds(631, 258, 100, 30);
		add(btnDoor);
		
		JButton btnHero = new JButton("Hero");
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iconOnMouse = gameData.heroNotArmed;
				charSelected = 'H';
				repaint();
			}
		});
		btnHero.setBounds(631, 330, 100, 30);
		add(btnHero);
		
		JButton btnOgre = new JButton("Ogre");
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iconOnMouse = gameData.normalOgre;
				charSelected = 'O';
				repaint();
			}
		});
		btnOgre.setBounds(631, 406, 100, 30);
		add(btnOgre);
		
		JButton btnKey = new JButton("Key");
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iconOnMouse = gameData.keyIcon;
				charSelected = 'k';
				repaint();
			}
		});
		btnKey.setBounds(631, 483, 100, 30);
		add(btnKey);
		
		JLabel lblEditingNow = new JLabel("Icon selected:");
		lblEditingNow.setBounds(216, 16, 154, 57);
		add(lblEditingNow);
		
		JButton btnSword = new JButton("Sword");
		btnSword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iconOnMouse = gameData.swordIcon;
				charSelected = '+';
				repaint();
			}
		});
		btnSword.setBounds(631, 554, 100, 30);
		add(btnSword);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < gameData.gameMap.map2.length; i++)
			for (int j = 0; j < gameData.gameMap.map2[i].length; j++)
				if(gameData.gameMap.map2[j][i] == 'X')
					g.drawImage(gameData.wallIcon, i * 50 + 20, j * 50 + 96, 50, 50, this);
				else if(gameData.gameMap.map2[j][i] == ' ')
					g.drawImage(gameData.woodIcon, i * 50 + 20, j * 50 + 96, 50, 50, this);
				else if(gameData.gameMap.map2[j][i] == 'I')
					g.drawImage(gameData.closedDoor, i * 50 + 20, j * 50 + 96, 50, 50, this);
		
		g.drawImage(gameData.wallIcon, 551, 96, 50, 50, this);
		g.drawImage(gameData.woodIcon, 551, 170, 50, 50, this);
		g.drawImage(gameData.closedDoor, 551, 245, 50, 50, this);
		g.drawImage(gameData.heroNotArmed, 551, 320, 50, 50, this);
		g.drawImage(gameData.normalOgre, 551, 396, 50, 50, this);
		g.drawImage(gameData.keyIcon, 551, 471, 50, 50, this);
		g.drawImage(gameData.swordIcon, 551, 546, 50, 50, this);
		

		g.drawImage(gameData.heroNotArmed, gameData.gameMap.currentMap.heroX2*50 + 20, gameData.gameMap.currentMap.heroY2*50 + 96, 50, 50, this);
		g.drawImage(gameData.normalOgre, gameData.gameMap.currentMap.ogreX2*50 + 20, gameData.gameMap.currentMap.ogreY2*50 + 96, 50, 50, this);
		g.drawImage(gameData.keyIcon, gameData.gameMap.currentMap.keyX2*50 + 20, gameData.gameMap.currentMap.keyY2*50 + 96, 50, 50, this);
		g.drawImage(gameData.swordIcon, gameData.gameMap.currentMap.clubX2*50 + 20, gameData.gameMap.currentMap.clubY2*50 + 96, 50, 50, this);
		
		
		
		g.drawImage(iconOnMouse, 316, 20, 50, 50, this);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
