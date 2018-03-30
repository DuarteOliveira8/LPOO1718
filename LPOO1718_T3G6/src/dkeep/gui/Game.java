package dkeep.gui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import dkeep.logic.Ogre;

public class Game extends JPanel implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	JButton btnUp;
	JButton btnRight;
	JButton btnDown;
	JButton btnLeft;
	JLabel lblStatus;
	static private GraphicsPanel gamePanel;
	JFrame frame;
	GameData gameData;

	/**
	 * Create the panel.
	 */
	public Game(JFrame frame, GameData gameData) throws IOException {
		addKeyListener(this);
		initialize();
		this.frame = frame;
		this.gameData = gameData;
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameData.game.setVisible(false);
				gameData.settings.setVisible(true);
			}
		});
		btnSettings.setBounds(155, 28, 109, 29);
		add(btnSettings);
		requestFocusInWindow();

	}
	
	public void afterEventHandler() {
		gameData.gameState = gameData.gameLogic.verifyGameState(gameData.hero, gameData.guard, gameData.ogres, gameData.gameMap);
		
		if (gameData.gameState == -1) {
			gamePanel.repaint();
			lblStatus.setText("You lost!");
			btnStatus(false);
			return;
		}
		
		for (Ogre ogre : gameData.ogres)
			ogre.verifyStun(gameData.hero, gameData.gameMap);
		
		if (gameData.gameMap.currentMap.level == 2 && gameData.gameMap.currentMap.onGame == 0) { 
			gamePanel.repaint();
			lblStatus.setText("You're on Level 2.");
			gameData.gameMap.changeMap();
			gameData.hero.changePosition(1, 8);
			gameData.gameMap.currentMap.onGame = 1;
			
		}
		
		if (gameData.gameMap.currentMap.level == 3) {
			gamePanel.repaint();
			lblStatus.setText("You won!");
			btnStatus(false);
			return;
		}
		
		gameData.gameLogic.moveNPC(gameData.gameMap, gameData.guard, gameData.ogres);
		
		gameData.gameState = gameData.gameLogic.verifyGameState(gameData.hero, gameData.guard, gameData.ogres, gameData.gameMap);
		
		if (gameData.gameState == -1){
			gamePanel.repaint();
			lblStatus.setText("You lost!");
			btnStatus(false);
			return;
		}
		
		for (Ogre ogre : gameData.ogres)
			ogre.verifyStun(gameData.hero, gameData.gameMap);
		
		gamePanel.repaint();
	}

	private void initialize() throws IOException {
		setLayout(null);
		
		btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameData.hero.moveUp(gameData.gameMap);
				afterEventHandler();
				requestFocusInWindow();
			}
		});
		btnUp.setBounds(600, 280, 75, 29);
		add(btnUp);
		
		btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameData.hero.moveDown(gameData.gameMap);
				afterEventHandler();
				requestFocusInWindow();
			}
		});
		btnDown.setBounds(600, 391, 80, 29);
		add(btnDown);
		
		btnLeft = new JButton("Left");
		btnLeft.setEnabled(false);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameData.hero.moveLeft(gameData.gameMap);
				afterEventHandler();
				requestFocusInWindow();
			}
		});
		btnLeft.setBounds(532, 335, 75, 29);
		add(btnLeft);
		
		btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameData.hero.moveRight(gameData.gameMap);
				afterEventHandler();
				requestFocusInWindow();
			}
		});
		btnRight.setBounds(667, 335, 77, 29);
		add(btnRight);
		
		JButton btnNewButton = new JButton("New game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				btnStatus(true);
				
				lblStatus.setText("You're on Level 1.");
				
				gameData.initializeGame(gameData.noOgres, gameData.personality);
		
				gamePanel = new GraphicsPanel(gameData);
				gamePanel.setBounds(20, 96, 500, 500);
				add(gamePanel);
				gamePanel.repaint();
				requestFocusInWindow();
				
			}
		});
		
		JButton btnExitGame = new JButton("Exit game");
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExitGame.setBounds(620, 28, 106, 29);
		add(btnExitGame);
		btnNewButton.setBounds(22, 28, 109, 29);
		add(btnNewButton);
		
		lblStatus = new JLabel("You can start a new game.");
		lblStatus.setBounds(22, 612, 165, 16);
		add(lblStatus);
	}
	
	public void btnStatus(boolean status) {
		btnUp.setEnabled(status);
		btnRight.setEnabled(status);
		btnDown.setEnabled(status);
		btnLeft.setEnabled(status);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (gameData.gameState != -1) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				gameData.hero.moveUp(gameData.gameMap);
				afterEventHandler();
				requestFocusInWindow();
				break;
			case KeyEvent.VK_LEFT:
				gameData.hero.moveLeft(gameData.gameMap);
				afterEventHandler();
				requestFocusInWindow();
				break;
			case KeyEvent.VK_DOWN:
				gameData.hero.moveDown(gameData.gameMap);
				afterEventHandler();
				requestFocusInWindow();
				break;
			case KeyEvent.VK_RIGHT:
				gameData.hero.moveRight(gameData.gameMap);
				afterEventHandler();
				requestFocusInWindow();
				break;
			default:
				break;
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 10; j++)
				if(i == 0 || j == 0 || i == 9 || j == 9 )
					g.drawImage(gameData.wallIcon, i * 50 + 20, j * 50 + 96, 50, 50, this);
				else
					g.drawImage(gameData.woodIcon, i * 50 + 20, j * 50 + 96, 50, 50, this);
	}
}
