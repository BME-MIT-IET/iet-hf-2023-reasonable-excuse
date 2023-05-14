package com.operativ_tarsulat.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Prototype {
	/*
	 * Input stream used for inputs
	 * can be console or file input stream 
	 */
	private static InputStream is;
	private static Random rand = new Random();
	
	/*
	 * Gets the next command either from console or from file depending 
	 */
	private static String readLine() {
		String parser = "";
		int ch;
		try {
			boolean hadChar = false;
			while((ch = is.read()) != -1) {
				hadChar = true;
				if(ch=='\n')
					break;
				parser += (char)ch;
			}
			if(!hadChar) {
				System.exit(0);
			}
		} catch (IOException e) {
			System.out.println("Hiba t�rt�nt olvas�s sor�n");
			e.printStackTrace();
			System.exit(2);
		}
		return parser.trim();
	}
	
	/*
	 * prompts the user for a choice
	 * user can only choose an option from 
	 */
	private static int choose(String question, String[] options) {
		Integer input = null;
		while(input==null) {
			System.out.println(question+" (1-"+(options.length)+"): ");
			int i = 1;
			for(String option : options)
				System.out.println("\t" + (i++) + ". " + option);
			try {
				Integer tmp = Integer.parseInt(readLine());
				if(tmp>0&&tmp<=options.length) {
					input = tmp;
				}else {
					System.out.println("K�rem a megadott intervalloumb�l v�lasszon");
				}				
			}catch(NumberFormatException e) {
				System.out.println("K�rem sz�mot adjon meg");
			}
		}
		return input-1;
	}
	
	/*
	 * Main for the interactive prototype
	 */
	public static void main(String[] args) {		
		if(args.length==0) 
		{
			// Reading inputs from stdin
			is = System.in;
		}else {
			// Reading inputs from file
			File inputFile = new File(args[0]);
			//try to open file
			try {
				FileInputStream fis = new FileInputStream(inputFile);
				is = fis;
				// Store file stream
			}catch(FileNotFoundException e) {
				// File doesn't exists
				System.out.println("A megadoot bemeneti file nem l�tezik");
				// Exit because of fatal error
				System.exit(1);
			}
		}
		runGame();
	}
	
	/*
	 * returns true, if the input is a valid filename
	 */
	private static boolean validFilename(String fileName) {
		if (fileName == null || fileName.isEmpty() || fileName.length() > 255) {
	        return false;
	    }
		return Arrays.stream(new Character[] {'"', '*', ':', '<', '>', '?', '\\', '|', 0x7F,'\000'}).noneMatch(ch->fileName.contains(ch.toString()));
	}
	
	/*
	 * runs the startGame command
	 * asks for world name, seed, and character names, then generates a world based on these inputs
	 */
	public static void startGame() {
		System.out.println("K�rem adja meg a j�t�k file nev�t");
		String saveFile = readLine();
		while(!validFilename(saveFile)) {
			System.out.println("K�rem val�s f�jlnevet adjon meg");
		}
		Integer seed = null;
		while(seed==null) {
			System.out.println("K�rem adja meg a j�t�k seedj�t (hagy �resen random�rt)");
			try {
				String input = readLine();
				if(input.equals("")) {
					seed = rand.nextInt();
				}else {
					seed = Integer.parseInt(input);
				}
			}catch (NumberFormatException e) {
				System.out.println("K�rem eg�sz sz�mot adjon meg");
			}
		}
		String players = null;
		while(players == null) {
			System.out.println("K�rem adja meg a j�t�kosok neveit space-el elv�lasztva");
			String tempPlayers = readLine();
			if(tempPlayers.contains(" ")) {
				players = tempPlayers;
			}else {
				System.out.println("K�rem legal�bb k�t nevet adjon meg space-el elv�lasztva");
			}
		}		
		Game.GetInstance().StartGame(saveFile, seed, players.split(" "));		
	}
	
	/*
	 * runs the loadGame command
	 * reads the filename from input, than loads game from that file
	 */
	public static boolean loadGame() {
		System.out.println("K�rem adja meg a j�t�k file nev�t");
		String saveFile = readLine();
		while(!validFilename(saveFile)) {
			System.out.println("K�rem val�s f�jlnevet adjon meg");
		}
		Game.LoadGame(saveFile);
		// TODO check if loading was successful
		return true;
	}
	
	/*
	 * runs the saveGame command
	 * saves the current game
	 */
	public static void saveGame() {
		Game.GetInstance().SaveGame();
	}
	
	/*
	 * runs the exitGame command
	 * asks the user, if the game should be saved, then exits the application
	 */
	public static void exitGame() {
		System.out.println("Szeretn� elmenteni a j�t�kot kil�p�s el�tt?");
		String in = "";
		boolean done = false;
		while(!done) {
			printCommands("igen","nem");
			in = readLine();
			if(in.equals("igen")||in.equals("nem")) {
				done = true;
			}			
		}
		if(in.equals("igen"))
			Game.GetInstance().SaveGame();
		Game.GetInstance().ExitGame();
	}
	
	/*
	 * prints all accepted commands listed in the commands argument
	 */
	public static void printCommands(String... commands) {
		System.out.println("Elfogadhat� parancsok:");
		for(String command : commands)
			System.out.println("\t"+command);
	}
	
	/*
	 * runs the playerStepsCommand
	 * asks the user where should the virologist move
	 */
	public static void playerSteps() {
		Virologist player = Game.GetInstance().getCurrentVirologist();
		List<Field> neighbours = player.GetField().GetNeighbours();
		int fieldIndex = choose("Melyik mez�re menjen a virol�gus?",neighbours.stream().map(x->x.toString()).toArray(String[]::new));
		
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
	private static void stealGear() {
		// Currently playing virologist
		Virologist player = Game.GetInstance().getCurrentVirologist();
		// Possible targets
		List<Virologist> avalibleVirologists = new LinkedList<Virologist>(Arrays.asList(player.GetField().GetVirologists()));
		// remove the player, because cannot steal from themself
		avalibleVirologists.remove(player);
		// make the user choose a target
		int virologistIndex = choose("K�rem v�lasszon c�lpontot",avalibleVirologists.stream().map(x->x.getName()).toArray(String[]::new));
		// gears that can be stolen
		List<Gear> gears = avalibleVirologists.get(virologistIndex).getGears();
		if(gears.size()==0) {
			System.out.println("Nincs a virol�gusn�l semmilyen felszerel�s");
			return;
		}
		// make the user choose a target
		int gearIndex = choose("K�rem v�lasszon c�lpontot", gears.stream().map(x->x.toString()).toArray(String[]::new));
		player.Steal(avalibleVirologists.get(virologistIndex),gears.get(gearIndex));
	}

	/*
	 * runs the stealMaterial command
	 * asks the user who they should steal from
	 */
	private static void stealMaterial() {
		// Currently playing virologist
		Virologist player = Game.GetInstance().getCurrentVirologist();
		// Possible targets
		List<Virologist> avalibleVirologists = Arrays.asList(player.GetField().GetVirologists());
		// remove the player, because they cannot steal from themself
		avalibleVirologists.remove(player);
		// make the user choose a target
		int virologistIndex = choose("K�rem v�lasszon c�lpontot",avalibleVirologists.stream().map(x->x.getName()).toArray(String[]::new));
		player.StealMaterials(avalibleVirologists.get(virologistIndex));
	}

	/*
	 * runs the useAgent command
	 * asks the user, what agent should be used and who should the target be
	 */
	private static void useAgent() {
		// Currently playing virologist
		Virologist player = Game.GetInstance().getCurrentVirologist();
		// Possible targets
		Virologist[] avalibleVirologists = player.GetField().GetVirologists();
		// make the user choose a target
		int virologistIndex = choose("K�rem v�lasszon c�lpontot",Arrays.asList(avalibleVirologists).stream().map(x->x.getName()).toArray(String[]::new));
		// agent possibilities
		List<Agent> agents = player.getAgentInventory();
		// check if there is any agent to be used
		if(agents.size()==0)
			return;
		int agentIndex = choose("Melyik �genst haszn�lja?",agents.stream().map(x->x.toString()).toArray(String[]::new));
		player.UseAgent(avalibleVirologists[virologistIndex], agents.get(agentIndex));
	}

	/*
	 * runs the createAgent command
	 * asks the user, genetic code to use for creation
	 */
	private static void createAgent() {
		// Currently playing virologist
		Virologist player = Game.GetInstance().getCurrentVirologist();
		List<GeneticCode> geneticCodes = player.getGeneticCodes();
		if(geneticCodes.size()==0) {
			System.out.println("Nincs haszn�lhat� genetikai k�d");
			return;
		}
		int codeIndex = choose("Melyik genetikai k�dot haszn�lja?",geneticCodes.stream().map(x->x.toString()).toArray(String[]::new));
		player.CreateAgent(geneticCodes.get(codeIndex));
	}
	
	/*
	 * runs the game
	 * starts in the menu, then progresses into gameplay
	 */
	public static void runGame() {
		// store if we got out of menu
		boolean outOfMenu = false;
		// repeat until we get out of the menu
		while(!outOfMenu) {
			printCommands("startGame","loadGame");
			// get the next command
			String input = readLine();
			// evaluate the command
			switch(input){
			case "startGame":
				startGame();
				outOfMenu = true;
				break;
			case "loadGame":
				if(loadGame())
					outOfMenu = true;
				break;
			default:
				break;
			}			
		}
		// Got out of menu, gameplay starts
		while(true) {
			System.out.println("Jelenlegi j�t�kos: "+Game.GetInstance().getCurrentVirologist().getName());
			printCommands("saveGame","exitGame","playerSteps","interact","createAgent","useAgent","stealMaterial","stealGear","endTurn");
			// get the next command
			String input = readLine();
			// evaluate the command
			switch(input){
			case "saveGame":
				saveGame();
				break;
			case "exitGame":
				exitGame();
				break;
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
			case "endTurn":
				endTurn();
				break;
			default:
				break;
			}	
		}
	}
}
