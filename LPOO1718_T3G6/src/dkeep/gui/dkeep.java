package dkeep.gui;

import dkeep.cli.*;
import dkeep.logic.*;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;

public class dkeep {

	private JFrame frame;
	private JTextField textField;
	private GraphicsPanel gamePanel;
	JLabel lblStatus;
	JButton btnUp;
	JButton btnRight;
	JButton btnDown;
	JButton btnLeft;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dkeep window = new dkeep();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public dkeep()  throws IOException{
		initialize();
	}
	
	static UserInput userInput;
	
	public void afterEventHandler() {
		
		userInput.gameState = userInput.gameLogic.verifyGameState(userInput.hero, userInput.guard, userInput.ogres, userInput.gameMap);
		
		if (userInput.gameState == -1) {
			//textArea.setText(userInput.gameMap.currentMap.printMap(userInput.hero, userInput.guard, userInput.ogres));
			gamePanel.repaint();
			lblStatus.setText("You lost!");
			btnStatus(false);
			return;
		}
		
		for (Ogre ogre : userInput.ogres)
			ogre.verifyStun(userInput.hero, userInput.gameMap);
		
		if (userInput.gameMap.currentMap.level == 2 && userInput.gameMap.currentMap.onGame == 0) { 
			//textArea.setText(userInput.gameMap.currentMap.printMap(userInput.hero, userInput.guard, userInput.ogres));
			gamePanel.repaint();
			lblStatus.setText("You're on Level 2.");
			userInput.gameMap.changeMap();
			userInput.hero.changePosition(1, 8);
			userInput.gameMap.currentMap.onGame = 1;
			
		}
		
		if (userInput.gameMap.currentMap.level == 3) {
			//textArea.setText(userInput.gameMap.currentMap.printMap(userInput.hero, userInput.guard, userInput.ogres));
			gamePanel.repaint();
			lblStatus.setText("You won!");
			btnStatus(false);
			return;
		}
		
		userInput.gameLogic.moveNPC(userInput.gameMap, userInput.guard, userInput.ogres);
		
		userInput.gameState = userInput.gameLogic.verifyGameState(userInput.hero, userInput.guard, userInput.ogres, userInput.gameMap);
		
		if (userInput.gameState == -1){
			//textArea.setText(userInput.gameMap.currentMap.printMap(userInput.hero, userInput.guard, userInput.ogres));
			gamePanel.repaint();
			lblStatus.setText("You lost!");
			btnStatus(false);
			return;
		}
		
		for (Ogre ogre : userInput.ogres)
			ogre.verifyStun(userInput.hero, userInput.gameMap);
		
		//textArea.setText(userInput.gameMap.currentMap.printMap(userInput.hero, userInput.guard, userInput.ogres));
		gamePanel.repaint();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws IOException {
		
		userInput = new UserInput();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(20, 20, 108, 16);
		frame.getContentPane().add(lblNumberOfOgres);
		
		textField = new JTextField();
		textField.setBounds(160, 13, 51, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard personality");
		lblGuardPersonality.setBounds(20, 51, 129, 16);
		frame.getContentPane().add(lblGuardPersonality);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		comboBox.setBounds(159, 47, 175, 27);
		frame.getContentPane().add(comboBox);
		
		JButton btnExitGame = new JButton("Exit game");
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExitGame.setBounds(488, 297, 117, 29);
		frame.getContentPane().add(btnExitGame);
		
		btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userInput.hero.moveUp(userInput.gameMap);
				afterEventHandler();
			}
		});
		btnUp.setBounds(502, 155, 82, 29);
		frame.getContentPane().add(btnUp);
		
		btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userInput.hero.moveDown(userInput.gameMap);
				afterEventHandler();
			}
		});
		btnDown.setBounds(502, 236, 82, 29);
		frame.getContentPane().add(btnDown);
		
		btnLeft = new JButton("Left");
		btnLeft.setEnabled(false);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userInput.hero.moveLeft(userInput.gameMap);
				afterEventHandler();
			}
		});
		btnLeft.setBounds(439, 195, 82, 29);
		frame.getContentPane().add(btnLeft);
		
		btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userInput.hero.moveRight(userInput.gameMap);
				afterEventHandler();
			}
		});
		btnRight.setBounds(570, 196, 82, 29);
		frame.getContentPane().add(btnRight);
		
		gamePanel = new GraphicsPanel(userInput);
		gamePanel.loadImages();
		
		JButton btnNewButton = new JButton("New game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				int noOgres = Integer.parseInt(textField.getText());
				
				if(noOgres > 5 || noOgres < 1) {
					lblStatus.setText("The number of ogres must be between 1 and 5");
					return;
				}
				
				btnStatus(true);
				int personality = 0;
				
				if(comboBox.getSelectedItem() == "Drunken")
					personality = -1;
				else if(comboBox.getSelectedItem() == "Suspicious")
					personality = 1;
				
				lblStatus.setText("You're on Level 1.");

				userInput.initializeGame(noOgres, personality);
				//textArea.setText(userInput.gameMap.currentMap.printMap(userInput.hero, userInput.guard, userInput.ogres));
				gamePanel.setBounds(20, 96, 250, 250);
				frame.getContentPane().add(gamePanel);
				gamePanel.repaint();
				
			}
		});
		btnNewButton.setBounds(488, 91, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		lblStatus = new JLabel("You can start a new game.");
		lblStatus.setBounds(20, 389, 376, 16);
		frame.getContentPane().add(lblStatus);
	}
	
	public void btnStatus(boolean status) {
		btnUp.setEnabled(status);
		btnRight.setEnabled(status);
		btnDown.setEnabled(status);
		btnLeft.setEnabled(status);
	}
}
