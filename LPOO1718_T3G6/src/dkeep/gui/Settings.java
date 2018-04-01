package dkeep.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Settings extends JPanel{
	
	private static final long serialVersionUID = 1L;
	GameData gameData;

	/**
	 * Create the panel.
	 */
	public Settings(JFrame frame, GameData gameData) {
		initialize();
		this.gameData = gameData;
	}
	
	private void initialize() {
		setLayout(null);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(22, 40, 108, 16);
		add(lblNumberOfOgres);
		
		JTextField textField = new JTextField();
		textField.setBounds(157, 35, 54, 26);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard personality");
		lblGuardPersonality.setBounds(22, 82, 111, 16);
		add(lblGuardPersonality);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		comboBox.setBounds(155, 78, 124, 27);
		add(comboBox);
		
		JLabel lblStatus = new JLabel("Choose your settings.");
		lblStatus.setBounds(22, 612, 422, 16);
		add(lblStatus);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				gameData.noOgres = Integer.parseInt(textField.getText());
				
				if(gameData.noOgres > 5 || gameData.noOgres < 1) {
					lblStatus.setText("The number of ogres must be between 1 and 5"); //TODO if noOgres is null
					return;
				}
				
				if(comboBox.getSelectedItem() == "Drunken")
					gameData.personality = -1;
				else if(comboBox.getSelectedItem() == "Suspicious")
					gameData.personality = 1;	

				gameData.settings.setVisible(false);
				gameData.game.setVisible(true);
			}
		});
		
		btnSave.setBounds(16, 127, 100, 30);
		add(btnSave);
	}

}
