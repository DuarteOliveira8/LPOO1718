package dkeep.gui;

import java.io.IOException;
import javax.swing.JFrame;
import java.awt.EventQueue;


public class dkeep {
	
	private Game game;
	private Settings settings;
	private GameData gameData;

	private JFrame frame;

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
		//gamePanel.requestFocusInWindow();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws IOException {
		gameData = new GameData();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 675);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		game = new Game(frame, gameData);
		game.setBounds(0, 0, 750, 650);
		frame.getContentPane().add(game);
		gameData.game = game;
		
		settings = new Settings(frame, gameData);
		settings.setBounds(0, 0, 750, 650);
		frame.getContentPane().add(settings);
		gameData.settings = settings;
		
		gameData.settings.setVisible(false);
		gameData.game.setVisible(true);
	}
}
