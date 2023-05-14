package com.operativ_tarsulat.controller;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.SwingUtilities;

import java.awt.event.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Handler;

import com.operativ_tarsulat.model.Agent;
import com.operativ_tarsulat.model.Field;
import com.operativ_tarsulat.model.Game;
import com.operativ_tarsulat.model.Gear;
import com.operativ_tarsulat.model.GearSlot;
import com.operativ_tarsulat.model.GeneticCode;
import com.operativ_tarsulat.model.Virologist;
import com.operativ_tarsulat.model.Weapon;

public class Menu {
	private class MenuItem<T>{
		private T command;
		private String display;
		public MenuItem(T command, String display) {
			this.command = command;
			this.display = display;
		}
		public T GetCommand() {
			return command;
		}
		public String GetDisplay() {
			return display;
		}
		@Override
		public String toString() {
			return GetDisplay();
		}
	} 
	private class SelectedMenuItemContainer{
		private MenuItem selected = null;
		public void SetMenuItem(MenuItem item) {
			selected = item;
		}
		public MenuItem GetMenuItem() {
			return selected;
		}
	}
	
	SelectedMenuItemContainer selectedItem = new SelectedMenuItemContainer();
	DefaultListModel<MenuItem> menuItems;
	private JList listView;
	
	public Menu(JList listview) {
		this.listView = listview;
		listView.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 2) { // Double click detected
		        	synchronized (selectedItem) {
		        		selectedItem.SetMenuItem((MenuItem)listView.getSelectedValue());
		        		selectedItem.notify();
					}
		        }
		    }
		});
		menuItems = new DefaultListModel<>();
		listView.setModel(menuItems);
		Thread thread = new Thread(()->{menuThread();}) ;
		thread.start();
	}
	
	private int waitInput() {
		MenuItem selected;
		synchronized (selectedItem) {
			while((selected = selectedItem.GetMenuItem())==null) {
				try {
					selectedItem.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			selectedItem.SetMenuItem(null);
		}
		System.out.println("Menu item selected: "+menuItems.indexOf(selected)+ " with text: "+selected.GetDisplay());
		return menuItems.indexOf(selected);
	}
	
	/*
	 * runs the playerStepsCommand
	 * asks the user where should the virologist move
	 */
	public void playerSteps() {
		Virologist player = Game.GetInstance().getCurrentVirologist();
		List<Field> neighbours = player.GetField().GetNeighbours();
		SwingUtilities.invokeLater(()->{
			menuItems.clear();
			for(Field neighbour : neighbours)
				menuItems.addElement(new MenuItem(neighbour,neighbour.toString()));
			menuItems.addElement(new MenuItem(null,"M�gse"));
		});
		int fieldIndex = waitInput();
		if(fieldIndex == menuItems.getSize()-1)
			return; // cancel called 		
		player.Move(neighbours.get(fieldIndex));
	}
	
	/*
	 * runs the interract command
	 * makes the current virologist interact with the field it is on
	 */
	private static void interact() {
		Game.GetInstance().getCurrentVirologist().InteractWithField();		
	}

	/*
	 * runs the endTurn command
	 * ends the current turn, so the next one can begin
	 */
	private static void endTurn() {
		Game.GetInstance().NextTurn();		
	}
	
	/*
	 * runs the stealGear command
	 * asks the user who they should steal from and what gear
	 */
	private void stealGear() {
		// Currently playing virologist
		Virologist player = Game.GetInstance().getCurrentVirologist();
		// Possible targets
		List<Virologist> avalibleVirologists = new LinkedList<Virologist>(Arrays.asList(player.GetField().GetVirologists()));
		// remove the player, because cannot steal from themself
		avalibleVirologists.remove(player);
		// make the user choose a target
		SwingUtilities.invokeLater(()->{
		menuItems.clear();
		for(Virologist v : avalibleVirologists)
			menuItems.addElement(new MenuItem(v,v.getName()));
		menuItems.addElement(new MenuItem(null,"M�gse"));
		});
		int virologistIndex = waitInput();
		if(virologistIndex == menuItems.getSize()-1)
			return; // cancel called 		
		// gears that can be stolen
		List<Gear> gears = avalibleVirologists.get(virologistIndex).getGears();
		if(gears.size()==0) {
			System.out.println("Nincs a virol�gusn�l semmilyen felszerel�s");
			return;
		}
		// make the user choose a target
		SwingUtilities.invokeLater(()->{
		menuItems.clear();
		for(Gear gear : gears)
			menuItems.addElement(new MenuItem(gear,gear.toString()));
		menuItems.addElement(new MenuItem(null,"M�gse"));
		});
		int gearIndex = waitInput();
		if(gearIndex == menuItems.getSize()-1)
			return; // cancel called 
		player.Steal(avalibleVirologists.get(virologistIndex),gears.get(gearIndex));
	}
	/*
	 * runs the stealMaterial command
	 * asks the user who they should steal from
	 */
	private void stealMaterial() {
		// Currently playing virologist
		Virologist player = Game.GetInstance().getCurrentVirologist();
		// Possible targets
		List<Virologist> avalibleVirologists1 = Arrays.asList(player.GetField().GetVirologists());
		final List<Virologist> avalibleVirologists = new LinkedList<Virologist>(avalibleVirologists1);
		// remove the player, because they cannot steal from themself
		avalibleVirologists.remove(player);
		// make the user choose a target
		SwingUtilities.invokeLater(()->{
		menuItems.clear();
			for(Virologist v : avalibleVirologists)
				menuItems.addElement(new MenuItem(v,v.getName()));
			menuItems.addElement(new MenuItem(null,"M�gse"));
		});
		int virologistIndex = waitInput();
		if(virologistIndex == menuItems.getSize()-1)
			return; // cancel called 
		player.StealMaterials(avalibleVirologists.get(virologistIndex));
	}
	
	/*
	 * runs the useAgent command
	 * asks the user, what agent should be used and who should the target be
	 */
	private void useAgent() {
		// Currently playing virologist
		Virologist player = Game.GetInstance().getCurrentVirologist();
		// Possible targets
		Virologist[] avalibleVirologists = player.GetField().GetVirologists();
		// make the user choose a target
		SwingUtilities.invokeLater(()->{
			menuItems.clear();
			for(Virologist v : avalibleVirologists)
				menuItems.addElement(new MenuItem(v,v.getName()));
			menuItems.addElement(new MenuItem(null,"M�gse"));
		});
		int virologistIndex = waitInput();
		if(virologistIndex == menuItems.getSize()-1)
			return; // cancel called 
		// agent possibilities
		List<Agent> agents = player.getAgentInventory();
		// check if there is any agent to be used
		if(agents.size()==0)
			return;
		SwingUtilities.invokeLater(()->{
			menuItems.clear();
			for(Agent agent : agents)
				menuItems.addElement(new MenuItem(agent,agent.toString())); 
			menuItems.addElement(new MenuItem(null,"M�gse"));
		});
		int agentIndex = waitInput();
		if(agentIndex == menuItems.getSize()-1)
			return; // cancel called 
		player.UseAgent(avalibleVirologists[virologistIndex], agents.get(agentIndex));
	}
	
	/*
	 * runs the createAgent command
	 * asks the user, genetic code to use for creation
	 */
	private void createAgent() {
		// Currently playing virologist
		Virologist player = Game.GetInstance().getCurrentVirologist();
		List<GeneticCode> geneticCodes = player.getGeneticCodes();
		if(geneticCodes.size()==0) {
			System.out.println("Nincs haszn�lhat� genetikai k�d");
			return;
		}

		SwingUtilities.invokeLater(()->{
			menuItems.clear();
			for(GeneticCode gc : geneticCodes)
				menuItems.addElement(new MenuItem(gc,gc.toString())); 
			menuItems.addElement(new MenuItem(null,"M�gse"));
		});
		int codeIndex = waitInput();
		if(codeIndex == menuItems.getSize()-1)
			return; // cancel called 
		player.CreateAgent(geneticCodes.get(codeIndex));
	}
	
	/*
	 * runs the attacj command
	 * asks the user, for a target to attack
	 */
	private void attack() {
		// Currently playing virologist
		Virologist player = Game.GetInstance().getCurrentVirologist();
		// Possible targets
		List<Virologist> avalibleVirologists = new LinkedList<Virologist>(Arrays.asList(player.GetField().GetVirologists()));
		// remove the player, because cannot attack themself
		avalibleVirologists.remove(player);
		// make the user choose a target
		SwingUtilities.invokeLater(()->{
			menuItems.clear();
			for(Virologist v : avalibleVirologists)
				menuItems.addElement(new MenuItem(v,v.getName()));
			menuItems.addElement(new MenuItem(null,"M�gse"));
		});
		int virologistIndex = waitInput();
		if(virologistIndex == menuItems.getSize()-1)
			return; // cancel called
		List<Gear> gears = player.getGears();
		for(Gear g : gears) {
			if(g.GetSlot() == GearSlot.Weapon)
				((Weapon)g).Attack(avalibleVirologists.get(virologistIndex));
		}
		
	}
	
	/*
	 * main function of the thread that is responsible for menu interactions
	 * in an infinite loop checks for inputs and handles that input
	 */
	private void menuThread() {
		while(true) {
			showCommands(new MenuItem("playerSteps","J�t�kos l�ptet�se"),new MenuItem("interact","Interakci� a mez�vel"), new MenuItem("createAgent","�gens l�trehoz�sa"),new MenuItem("useAgent","�gens haszn�lata"),new MenuItem("stealMaterial","Anyag lop�sa"),new MenuItem("stealGear","Felszerel�s lop�sa"),new MenuItem("attack","T�mad�s"),new MenuItem("endTurn","K�r befejez�se"));
			// get the next command
			int input = waitInput();
			// evaluate the command
			switch((String)menuItems.elementAt(input).GetCommand()){
			case "playerSteps":
				playerSteps();
				break;
			case "interact":
				interact();
				break;
			case "createAgent":
				createAgent();
				break;
			case "useAgent":
				useAgent();
				break;
			case "stealMaterial":
				stealMaterial();
				break;
			case "stealGear":
				stealGear();
				break;
			case "attack":
				attack();
				break;
			case "endTurn":
				endTurn();
				break;
			default:
				break;
			}	
		}
	}
	private void showCommands(MenuItem... commands) {
		//System.out.println("Showing main menu commands: "+ commands.length);	
		SwingUtilities.invokeLater(()->{
			menuItems.clear();		
			for(int i = 0; i<commands.length;i++)
				menuItems.addElement(commands[i]);		
		});
	}
	
	
}
