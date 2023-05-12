package com.operativ_tarsulat.view;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import com.operativ_tarsulat.controller.Menu;
import com.operativ_tarsulat.model.Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener{
	
	private static MainWindow instance = null;

	/**
	 * Returns the singleton instance of the MainWindow class.
	 * @return the singleton instance
	 */
	public static MainWindow getInstance() {
		if(instance == null)
			instance = new MainWindow();
		return instance;
	}
	JMenuBar menuBar;
	MapPanel mapPanel;
	JList listView;
	JMenuItem saveItem;
	JMenuItem exitItem;
	/**
	 * JLabels for showing icons in the main window
	 */
	JLabel danceLabel,amnesiaLabel,paralyzeLabel,protectionLabel,actualPlayer, nucleoLabel, aminoLabel,gotProtectionLabel,gotParalyzeLabel,gotAmnesiaLabel,gotDanceLabel,gotBearLabel,gotBagLabel,gotProtectiveCapeLabel,gotGlovesLabel,gotAxeLabel,activeParalyzeLabel,activeDanceLabel,activeAmnesiaLabel,activeBearLabel,activeProtectionLabel;

	/**
	 * MainWindow constructor that creates the main window
	 */
	private MainWindow() {
		super("Vak virológusok földje...");
		setSize(1000,630);
		this.setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		mapPanel = new MapPanel();
		mapPanel.setBounds(0,0,1000,300);
		getContentPane().add(mapPanel);
		listView = new JList();
		ImageIcon protectionEmblem = new ImageIcon("protectionEmblem.png");
		ImageIcon amnesiaEmblem = new ImageIcon("amnesiaEmblem.png");
		ImageIcon bearEmblem = new ImageIcon("bearEmblem.png");
		ImageIcon danceEmblem = new ImageIcon("danceEmblem.png");
		ImageIcon paralyzeEmblem = new ImageIcon("paralyzeEmblem.png");
		
		JScrollPane menuScrollPane = new JScrollPane(listView);
		menuScrollPane.setBounds(400,410,370,160);
		getContentPane().add(menuScrollPane);
		
		
		Menu m = new Menu(listView);
		
		JLabel lb=new JLabel("AvailableAction:");
		lb.setBounds(300,405,200,30);
		getContentPane().add(lb);
		
		
		JLabel actionKeret = new JLabel();
		Border actionKeretBorder = BorderFactory.createLineBorder(Color.black,1);
		actionKeret.setBounds(280,410,490,200);
		actionKeret.setBorder(actionKeretBorder);
		getContentPane().add(actionKeret);
		
		
		
		JLabel aktJatekos = new JLabel("Actual player: ");
		aktJatekos.setBounds(300,365,200,30);
		getContentPane().add(aktJatekos);

		actualPlayer = new JLabel("");
		actualPlayer.setBounds(400,365,200,30);
		getContentPane().add(actualPlayer);
		
		JLabel Keret2 = new JLabel();
		Border Keret2Border = BorderFactory.createLineBorder(Color.black,1);
		Keret2.setBounds(280,350,490,60);
		Keret2.setBorder(Keret2Border);
		getContentPane().add(Keret2);
		
		JLabel Keret3 = new JLabel();
		Border Keret3Border = BorderFactory.createLineBorder(Color.black,1);
		Keret3.setBounds(0,350,280,240);
		Keret3.setBorder(Keret3Border);
		getContentPane().add(Keret3);
		
		JLabel Keret4 = new JLabel();
		Border Keret4Border = BorderFactory.createLineBorder(Color.black,1);
		Keret4.setBounds(770,350,230,240);
		Keret4.setBorder(Keret4Border);
		getContentPane().add(Keret4);
		
		
		JLabel cLabel = new JLabel("Codes:");
		cLabel.setBounds(10,370,100,20);
		getContentPane().add(cLabel);

		danceLabel = new JLabel("");
		danceLabel.setBounds(60,345,70,70);
		getContentPane().add(danceLabel);
		
		amnesiaLabel = new JLabel("");
		amnesiaLabel.setBounds(110,345,70,70);
		getContentPane().add(amnesiaLabel);
		
		paralyzeLabel = new JLabel("");
		paralyzeLabel.setBounds(160,345,70,70);
		getContentPane().add(paralyzeLabel);
		
		protectionLabel = new JLabel("");
		protectionLabel.setBounds(210,345,70,70);
		getContentPane().add(protectionLabel);
		
		
		JLabel mLabel = new JLabel("Materials:");
		mLabel.setBounds(10,415,70,20);
		getContentPane().add(mLabel);
		JLabel aLabel = new JLabel("Amino:");
		aLabel.setBounds(90,415,70,20);
		getContentPane().add(aLabel);
		JLabel nLabel = new JLabel("Nucleo:");
		nLabel.setBounds(190,415,70,20);
		getContentPane().add(nLabel);
		
		aminoLabel = new JLabel("");
		aminoLabel.setBounds(140,415,60,20);
		getContentPane().add(aminoLabel);
		
		nucleoLabel = new JLabel("");
		nucleoLabel.setBounds(240,415,60,20);
		getContentPane().add(nucleoLabel);
		
		JLabel vLabel = new JLabel("Inventory:");
		vLabel.setBounds(10,455,70,20);
		getContentPane().add(vLabel);

		gotProtectionLabel = new JLabel("");
		gotProtectionLabel.setBounds(80,455,25,25);
		getContentPane().add(gotProtectionLabel);
		
		gotAmnesiaLabel = new JLabel("");
		gotAmnesiaLabel.setBounds(114,455,25,25);
		getContentPane().add(gotAmnesiaLabel);
		
		gotParalyzeLabel = new JLabel("");
		gotParalyzeLabel.setBounds(149,455,25,25);
		getContentPane().add(gotParalyzeLabel);
		
		gotDanceLabel = new JLabel("");
		gotDanceLabel.setBounds(184,455,25,25);
		getContentPane().add(gotDanceLabel);
		
		gotBearLabel = new JLabel("");
		gotBearLabel.setBounds(219,455,25,25);
		getContentPane().add(gotBearLabel);
		
		JLabel vPanel = new JLabel("Active:");
		vPanel.setBounds(10,520,70,20);
		getContentPane().add(vPanel);

		activeParalyzeLabel = new JLabel("");
		activeParalyzeLabel.setBounds(80,520,25,25);
		getContentPane().add(activeParalyzeLabel);

		activeDanceLabel = new JLabel("");
		activeDanceLabel.setBounds(114,520,25,25);
		getContentPane().add(activeDanceLabel);

		activeBearLabel = new JLabel("");
		activeBearLabel.setBounds(149,520,25,25);
		getContentPane().add(activeBearLabel);

		activeAmnesiaLabel = new JLabel("");
		activeAmnesiaLabel.setBounds(184,520,25,25);
		getContentPane().add(activeAmnesiaLabel);
		
		activeProtectionLabel = new JLabel("");
		activeProtectionLabel.setBounds(219,520,25,25);
		getContentPane().add(activeProtectionLabel);
		
		JLabel eqLabel = new JLabel("Equipment:");
		eqLabel.setBounds(780,370,100,25);
		getContentPane().add(eqLabel);
		
		JLabel ckLabel = new JLabel("Cape:");
		ckLabel.setBounds(780,420,100,25);
		getContentPane().add(ckLabel);
		gotProtectiveCapeLabel = new JLabel("");
		gotProtectiveCapeLabel.setBounds(820,420,40,40);
		getContentPane().add(gotProtectiveCapeLabel);
		
		JLabel gLabel = new JLabel("Gloves:");
		gLabel.setBounds(885,420,100,25);
		getContentPane().add(gLabel);
		gotGlovesLabel = new JLabel("");
		gotGlovesLabel.setBounds(930,420,40,40);
		getContentPane().add(gotGlovesLabel);
		
		JLabel axLabel = new JLabel("Axe:");
		axLabel.setBounds(885,500,100,25);
		getContentPane().add(axLabel);
		gotAxeLabel = new JLabel("");
		gotAxeLabel.setBounds(930,500,40,40);
		getContentPane().add(gotAxeLabel);
		
		JLabel baLabel = new JLabel("Bag:");
		baLabel.setBounds(780,500,100,25);
		getContentPane().add(baLabel);
		gotBagLabel = new JLabel("");
		gotBagLabel.setBounds(820,500,40,40);
		getContentPane().add(gotBagLabel);
		
		
		/*
		JLabel hatter = new JLabel();
		hatter.setBounds(0,350,1000,250);
		hatter.setBackground(new Color(117,198,239));
		hatter.setOpaque(true);
		getContentPane().add(hatter);
		
		*/
		
		menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		saveItem = new JMenuItem("Save");
		saveItem.addActionListener(this);
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(this);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);
		this.setJMenuBar(menuBar);
		
		this.setResizable(false);
	}

	/**
	 * Getter setter functions return or set the value of the variables
	 */
	public JLabel getDanceLabel ()
	{
		return danceLabel;
	}
	public void setDanceLabel (JLabel label)
	{
		 danceLabel = label;
	}
	
	public JLabel getAmnesiaLabel ()
	{
		return amnesiaLabel;
	}
	public void setAmnesiaLabel (JLabel label)
	{
		 amnesiaLabel = label;
	}
	
	public JLabel getParalyzeLabel ()
	{
		return paralyzeLabel;
	}
	public void setParalyzeLabel (JLabel label)
	{
		 paralyzeLabel = label;
	}
	
	public JLabel getProtectionLabel ()
	{
		return protectionLabel;
	}
	public void setProtectionLabel (JLabel label)
	{
		protectionLabel = label;
	}
	
	public JLabel getActualPlayerLabel ()
	{
		return actualPlayer;
	}
	public void setActualPlayerLabel (JLabel label)
	{
		actualPlayer = label;
	}
	
	public JLabel getNucleoLabel ()
	{
		return nucleoLabel;
	}
	public void setNucleoLabel (JLabel label)
	{
		nucleoLabel = label;
	}
	
	public JLabel getAminoLabel ()
	{
		return aminoLabel;
	}
	public void setAminoLabel (JLabel label)
	{
		aminoLabel = label;
	}
	
	public JLabel getGotProtectionLabel ()
	{
		return gotProtectionLabel;
	}
	public void setGotProtectionLabel (JLabel label)
	{
		gotProtectionLabel = label;
	}
	
	public JLabel getGotParalyzeLabel ()
	{
		return gotParalyzeLabel;
	}
	public void setGotParalyzeLabel (JLabel label)
	{
		gotParalyzeLabel = label;
	}
	
	public JLabel getGotAmnesiaLabel ()
	{
		return gotAmnesiaLabel;
	}
	public void setGotAmnesiaLabel (JLabel label)
	{
		gotAmnesiaLabel = label;
	}
	
	public JLabel getGotDanceLabel ()
	{
		return gotDanceLabel;
	}
	public void setGotDanceLabel (JLabel label)
	{
		gotDanceLabel = label;
	}
	
	public JLabel getGotBearLabel ()
	{
		return gotBearLabel;
	}
	public void setGotBearLabel (JLabel label)
	{
		gotBearLabel = label;
	}
	
	public JLabel getGotBagLabel ()
	{
		return gotBagLabel;
	}
	public void setGotBagLabel (JLabel label)
	{
		gotBagLabel = label;
	}
	
	public JLabel getGotProtectiveCapeLabel ()
	{
		return gotProtectiveCapeLabel;
	}
	public void setGotProtectiveCapeLabel (JLabel label)
	{
		gotProtectiveCapeLabel = label;
	}
	
	public JLabel getGotGlovesLabel ()
	{
		return gotGlovesLabel;
	}
	public void setGotGlovesLabel (JLabel label)
	{
		gotGlovesLabel = label;
	}
	public JLabel getActiveDanceLabel()
	{
		return activeDanceLabel;
	}
	public JLabel getActiveProtectionLabel()
	{
		return activeProtectionLabel;
	}
	public JLabel getActiveAmnesiaLabel()
	{
		return activeAmnesiaLabel;
	}
	public JLabel getActiveBearLabel()
	{
		return activeBearLabel;
	}
	public JLabel getActiveParalyzeLabel()
	{
		return activeParalyzeLabel;
	}
	
	public JLabel getGotAxeLabel ()
	{
		return gotAxeLabel;
	}
	public void setGotAxeLabel (JLabel label)
	{
		gotAxeLabel = label;
	}
	
	
	public MapPanel getMapPanel(){
		return mapPanel;
	}


	/**
	 * Handles the events of the menu items
	 * @param e the event that has been fired
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==saveItem)
		{
			 Game.GetInstance().SaveGame();
		}
		if(e.getSource()==exitItem)
		{
			 Game.GetInstance().ExitGame();
		}
		
	}
}
