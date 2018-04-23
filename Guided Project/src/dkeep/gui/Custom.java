package dkeep.gui;

import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

public class Custom extends JPanel implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	JFrame frame;
	GameData gameData;
	private JTextField textField;
	private JTextField textField_1;
	BufferedImage iconOnMouse;
	char charSelected;
	int x = 0, y = 0;
	int width, height;
	boolean[][] visited;
	JLabel label;
	
	public Custom(JFrame frame, GameData gameData) {
		addMouseListener(this);
		addMouseMotionListener(this);
		this.frame = frame;
		this.gameData = gameData;
		iconOnMouse = gameData.wallIcon;
		charSelected = 'X';
		height = 10;
		width = 10;
		initialize();
	}
	
	private void initialize() {
		setLayout(null);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(verifyCustomMap()) {
					gameData.settings.setVisible(false);
					gameData.game.setVisible(true);
					gameData.custom.setVisible(false);
				}
				else
					label.setText("The custom map must be playable!");
			}
		});
		
		btnSave.setBounds(599, 620, 100, 30);
		add(btnSave);
		
		JLabel lblCustomizeTheKeep = new JLabel("Height");
		lblCustomizeTheKeep.setBounds(20, 6, 54, 47);
		add(lblCustomizeTheKeep);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(86, 16, 54, 26);
		add(textField);
		
		label = new JLabel("Customize the keep level using the buttons on the right");
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
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(textField.getText()) >= 7 && Integer.parseInt(textField.getText()) <= 10 && Integer.parseInt(textField_1.getText()) >= 7 && Integer.parseInt(textField_1.getText()) <= 10) {
					height = Integer.parseInt(textField.getText());
					width = Integer.parseInt(textField_1.getText());
					label.setText("Customize the keep level using the buttons on the right");
					gameData.customMap = createNewMap(height, width);
					repaint();
				}
				else
					label.setText("The height and width must be between 7 and 10");
			}
		});
		btnOk.setBounds(152, 25, 54, 30);
		add(btnOk);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < gameData.customMap.length; i++)
			for (int j = 0; j < gameData.customMap[i].length; j++)
				if(gameData.customMap[i][j] == 'X')
					g.drawImage(gameData.wallIcon, j * 50 + 20, i * 50 + 96, 50, 50, this);
				else if(gameData.customMap[i][j] == ' ')
					g.drawImage(gameData.woodIcon, j * 50 + 20, i * 50 + 96, 50, 50, this);
				else if(gameData.customMap[i][j] == 'I')
					g.drawImage(gameData.closedDoor, j * 50 + 20, i * 50 + 96, 50, 50, this);
		
		g.drawImage(gameData.wallIcon, 551, 96, 50, 50, this);
		g.drawImage(gameData.woodIcon, 551, 170, 50, 50, this);
		g.drawImage(gameData.closedDoor, 551, 245, 50, 50, this);
		g.drawImage(gameData.heroNotArmed, 551, 320, 50, 50, this);
		g.drawImage(gameData.normalOgre, 551, 396, 50, 50, this);
		g.drawImage(gameData.keyIcon, 551, 471, 50, 50, this);
		g.drawImage(gameData.swordIcon, 551, 546, 50, 50, this);

		g.drawImage(gameData.heroNotArmed, gameData.heroX2*50 + 20, gameData.heroY2*50 + 96, 50, 50, this);
		g.drawImage(gameData.normalOgre, gameData.ogreX2*50 + 20, gameData.ogreY2*50 + 96, 50, 50, this);
		g.drawImage(gameData.keyIcon, gameData.keyX2*50 + 20, gameData.keyY2*50 + 96, 50, 50, this);
		g.drawImage(gameData.swordIcon, gameData.clubX2*50 + 20, gameData.clubY2*50 + 96, 50, 50, this);
		
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
		x = e.getX();
		y = e.getY();
		changeMap(Math.floor((x-20)/50), Math.floor((y-96)/50));
		repaint();
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
	
	public void changeMap(double x, double y) {
		
		if (charSelected == 'X' && x >= 0 && x <= 9 && y >= 0 && y <= 9) {
			gameData.customMap[(int) y][(int) x] = charSelected;
		}
		else if (charSelected == 'I' && (x == 0 || x == 9 || y == 0 || y == 9)) {
			deleteDoors();
			gameData.customMap[(int) y][(int) x] = charSelected;
		}
		else if (x >= 1 && x <= 8 && y >= 1 && y <= 8) {
			if (gameData.gameMap.map2[(int)y][(int)x] != 'X' || gameData.gameMap.map2[(int)y][(int)x] != 'I' ) {
				if (charSelected == 'H') {
					gameData.heroX2 = (int) x;
					gameData.heroY2 = (int) y;
				} else if (charSelected == 'O') {
					gameData.ogreX2 = (int) x;
					gameData.ogreY2 = (int) y;
				} else if (charSelected == 'k') {
					gameData.keyX2 = (int) x;
					gameData.keyY2 = (int) y;
				} else if (charSelected == '+') {
					gameData.clubX2 = (int) x;
					gameData.clubY2 = (int) y;
				} else if (charSelected == ' ') {
					gameData.customMap[(int) y][(int) x] = charSelected;
				}
			} 
		} 
	}
	
	public char[][] createNewMap(int height, int width) {
		char[][] newMap = new char[height][width];
		
		for(int y = 0; y < height; y++)
			for(int x = 0; x < width; x++) {
				if(y == 0 || x == 0 || y == height - 1 || x == width - 1)
					newMap[y][x] = 'X';
				else
					newMap[y][x] = ' ';
			}
		
		newMap[1][0] = 'I';
		
		gameData.heroX2 = 1;
		gameData.heroY2 = height - 2;
		gameData.clubX2 = 2;
		gameData.clubY2 = height - 2;
		gameData.ogreX2 = 3;
		gameData.ogreY2 = 1;
		gameData.keyX2 = width - 2;
		gameData.keyY2 = 1;
	
		return newMap;
	}
	
	void deleteDoors() {
		for (int i = 0; i < gameData.customMap.length; i++)
			for (int j = 0; j < gameData.customMap[i].length; j++)
				if (gameData.customMap[i][j] == 'I')
					gameData.customMap[i][j] = 'X';
	}
	
	boolean verifyCustomMap() {
		initializeVisited(gameData.heroX2, gameData.heroY2);
		if(findGoal(gameData.heroX2, gameData.heroY2, 'k')) {
			initializeVisited(gameData.keyX2, gameData.keyY2);
			return findGoal(gameData.keyX2, gameData.keyY2, 'I');
		}
		else
			return false;
	}
	
	boolean findGoal(int x, int y, char c) {
		if (c == 'I') {
			if(gameData.customMap[y][x+1] == c || gameData.customMap[y][x-1] == c || gameData.customMap[y+1][x] == c || gameData.customMap[y-1][x] == c)
				return true;
		}
		else if (c == 'k') {
			if((y+1 == gameData.keyY2 && x == gameData.keyX2) || (y-1 == gameData.keyY2 && x == gameData.keyX2) || (y == gameData.keyY2 && x+1 == gameData.keyX2) || (y == gameData.keyY2 && x-1 == gameData.keyX2))
				return true;
		}
				
		
		if(visited[y][x+1] == false && gameData.customMap[y][x+1] == ' '){
			visited[y][x+1] = true;
			if(findGoal(x+1,y,c) == true)
				return true;
		}
		
		if(visited[y][x-1] == false && gameData.customMap[y][x-1] == ' '){
			visited[y][x-1] = true;
			if(findGoal(x-1,y,c) == true)
				return true;
		}
		
		if(visited[y+1][x] == false && gameData.customMap[y+1][x] == ' '){
			visited[y+1][x] = true;
			if(findGoal(x,y+1,c) == true)
				return true;
		}
		
		if(visited[y-1][x] == false && gameData.customMap[y-1][x] == ' '){
			visited[y-1][x] = true;
			if(findGoal(x,y-1,c) == true)
				return true;
		}
		
		return false;
	}
	
	void initializeVisited(int x, int y) {
		visited = new boolean[height][width];
		
		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++)
				visited[i][j] = false;
		
		visited[y][x] = true;
	}
}
