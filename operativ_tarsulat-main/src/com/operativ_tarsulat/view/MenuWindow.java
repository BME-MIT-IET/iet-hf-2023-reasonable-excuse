package com.operativ_tarsulat.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.operativ_tarsulat.model.Game;

public class MenuWindow extends JFrame {
	
	JTextField loadGameName,seed,nameOfgameField;
	JTextArea playersArea;
	JButton loadGameButton,startGameButton;
	JPanel seedPanel,playersPanel,nameOfgamePanel,newGamePanel,loadGamePanel;
	JScrollPane playerScroll;

	/**
	 * Main function of the class
	 * @param args
	 */
	public static void main(String[] args) {
		MenuWindow menu = new MenuWindow();
		menu.setVisible(true);
	}

	/**
	 * Constructor for the menu window
	 */
	public MenuWindow() {
		super("Menü");
		this.setSize(600,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container content = getContentPane();		
		content.setLayout(null);
		
		JLabel title = new JLabel("Vak Virologusok");
		title.setFont(new Font("Monospaced",Font.ITALIC,30));
		title.setBounds(150,0,300,50);
		content.add(title);
		
		JLabel newGameLabel = new JLabel("Options for new Game");
		newGameLabel.setFont(new Font("Serif",Font.PLAIN,20));
		newGameLabel.setBounds(320,50,200,50);
		content.add(newGameLabel);
		
		JLabel seedLabel = new JLabel("Seed:");
		seedLabel.setBounds(300,100,100,25);
		seed = new JTextField();
		seed.setBounds(350,105,150,20);
		content.add(seedLabel);
		content.add(seed);
		
		
		JLabel playersLabel = new JLabel("Players:");
		playersArea = new JTextArea(10,10);
		playersLabel.setBounds(300,150,100,25);
		playersArea.setBounds(350,150,150,100);
		content.add(playersLabel);
		content.add(playersArea);
		
		JLabel gameLabel = new JLabel("Game:");
		nameOfgameField = new JTextField(10);
		gameLabel.setBounds(300,300,100,25);
		nameOfgameField = new JTextField();
		nameOfgameField.setBounds(350,305,150,20);
		content.add(gameLabel);
		content.add(nameOfgameField);
		
		startGameButton = new JButton("Start new game");
		startGameButton.setBounds(325,350,200,50);
		content.add(startGameButton);
		
		
		JLabel loadGameLabel = new JLabel("Load previous save");
		loadGameLabel.setFont(new Font("Serif",Font.PLAIN,20));
		loadGameLabel.setBounds(50,50,300,50);
		JLabel fileNameLabel = new JLabel("FileName:");
		fileNameLabel.setBounds(30,100,200,20);
		loadGameName = new JTextField();
		loadGameName.setBounds(100,100,150,20);
		loadGameButton = new JButton("LoadGame");
		loadGameButton.setBounds(70,130,170,50);
		content.add(loadGameLabel);
		content.add(fileNameLabel);
		content.add(loadGameName);
		content.add(loadGameButton);
	
		startGameButton.addActionListener((e)->{
			try {
				Integer.parseInt(seed.getText());
				if(!playersArea.getText().isEmpty()&&!nameOfgameField.getText().isEmpty()&&!seed.getText().isEmpty())
				{
					String s = playersArea.getText();
					String [] names = s.split("\n");
					Game.GetInstance().StartGame(nameOfgameField.getText(),Integer.parseInt(seed.getText()),names);
				}
				else Game.GetInstance().StartGame("game",1,new String[]{"Player1","Player2"});
					
				
				MainWindow.getInstance().setVisible(true);
				setVisible(false);
			}catch(NumberFormatException ex) {
				ex.printStackTrace();
			}
		});
		
		loadGameButton.addActionListener((e)->{
			if(!loadGameName.getText().isEmpty())
			{
				Game.GetInstance().LoadGame(loadGameName.getText());
			}
				
			
			MainWindow.getInstance().setVisible(true);
			setVisible(false);
		});
	}
}
