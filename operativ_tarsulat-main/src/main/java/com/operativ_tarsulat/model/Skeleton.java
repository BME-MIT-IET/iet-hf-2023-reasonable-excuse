package com.operativ_tarsulat.model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Skeleton that runs the testing application
 * controls tests and responsible for logging
 *
 */
public class Skeleton {

	/**
	 * Asks a question and only accepts an option 
	 * @param question Question text to be printed
	 * @param options Potential answers, only one of these will be accepted
	 * @return Index of option chosen
	 */
	public static int AskQuestion(String question, String[] options) {
		System.out.println(question+"\t");
		int i = 0;
		for(String option : options) {
			System.out.println(i+". "+option+" ");
			i++;
		}
		
		while(true) {
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String input = reader.readLine();
				int num = Integer.parseInt(input);
				if(num<options.length&&num>=0) {
					return num;
				}else {
					System.out.println("Kérem a megadott opciókból válasszon");
				}

			}catch(NumberFormatException e) {
				System.out.println("Kérem a megadott opciókból válasszon");				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}		
	}
	
	/**
	 * Asks a question and only accepts yes or no as an answer
	 * @param question Question text to be printed
	 * @return true - User chose yes, false - user chose no 
	 */
	public static boolean AskYesNoQuestion(String question) {
		return (AskQuestion(question,new String[] {"Yes", "No"}) == 0);
	}
	
	/**
	 * Asks a question and accepts a number between 0 and Integer.MAX_VALUE as answer  
	 * @param question Question text to be printed
	 * @returnNumber answered by user
	 */
	public static int AskQuestion(String question) {
		return AskQuestion(question, 0, Integer.MAX_VALUE);
	}
	
	/**
	 * Asks a question and accepts a number between min and max as answer  
	 * @param question Question text to be printed
	 * @param min Minimum value accepted
	 * @param max Maximum value accepted
	 * @return Number answered by user
	 */
	public static int AskQuestion(String question, int min,int max) {
		System.out.println(question+"\t");		
		while(true) {
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String input = reader.readLine();
				int num = Integer.parseInt(input);
				if(num>=min&&num<=max) {
					return num;
				}else {
					System.out.println("Kérem a keret között adjon meg számot! ("+min+"-"+max+")");
				}
			}catch(NumberFormatException e) {
				System.out.println("Számot adjon meg");				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}		
	}
	
	/**
	 * Stores the number of indentations for logging
	 */
	private static int logTabCount = 0;
	
	/**
	 * Logs a function call with arguments
	 * @param functionName Name of the function called
	 * @param args Argumens of the call as strings
	 */
	public static void LogFunctionCall(String functionName, String... args) {
		/*for(int i = 0;i<logTabCount;i++) // Indentation
			System.out.print("\t");
		System.out.print(functionName+"(");
		if(args!=null) { // Has arguments?
			for(int i = 0; i<args.length; i++) {
				System.out.print(args[i]);
				if(i < args.length - 1)
					System.out.print(", ");
			}
		}
		System.out.println(")");
		logTabCount++;*/
	}
	
	/**
	 *  Logs a return with no return value
	 */
	public static void LogReturn() {
		LogReturn(null);
	}
	
	/**
	 * Logs a return with return value
	 * @param value value returned
	 */
	public static void LogReturn(String value) {		
		/*logTabCount--;
		for(int i = 0;i<logTabCount;i++)
			System.out.print("\t");
		System.out.print("return");
		if(value!=null)
			System.out.print(": "+value);
		System.out.println(""); //new line*/
	}
	
	/**
	 * Main function called at the start of the program
	 * @param args Command line arguments, not used currently
	 */
	public static void main(String[] args) {
		while(true) {
			int testCase = AskQuestion("Melyik tesztet szeretnéd futtatni?", new String[]{"Kilépés","Játékos lép","Genetikai kód tanulása", "Felszerelés felvétele", "Anyag felvétele", "Ágens készítése","Játék mentése", "Játék betöltése", "Játék indítása","Ágens kenése", "Anyagkészlet lopása", "Felszerelés lopása", "Kör kezdete", "Kör kezdete tánc ágensel","Kör kezdete bénító ágensel" });
			switch(testCase) {
			case 0: // Exits the program
				Game.GetInstance().ExitGame();
				break;
			case 1: // Runs virologist moves test
				VirologistMoveTest(); 
				break;
			case 2: // Runs learning genetic code test
				LearnGeneticCodeTest();
				break;
			case 3: // Runs picking up gear test
				PickUpGearTest();
				break;
			case 4: // Runs pucking up material test
				PickUpMaterialTest();
				break;
			case 5: // Runs creating agent test
				CreateAgentTest(); 
				break;
			case 6: // Runs loading the game test
				SaveGameTest(); 
				break;
			case 7: // Runs loading the game test
				LoadGameTest(); 
				break;
			case 8: // Runs starting the game test
				StartGameTest(); 
				break;
			case 9: // Runs use of agent test
				UseAgentTest();
				break;
			case 10: // Runs steal materials test
				StealMaterialTest(); 
				break;
			case 11: // Runs steal gear test
				StealGearTest(); 
				break;
			case 12: // Runs start turn test
				StartTurnTest(); 
				break;
			case 13: // Runs start turn with dance agent test
				StartTurnWithDanceAgentTest() ;
				break;
			case 14: // Runs start turn with paralyze agent test
				StartTurnWithParalyzeAgentTest(); 
				break;
			}
		}
	}
	
	/**
	 * Új kör bénító ágensel tesztelése
	 */
	private static void StartTurnWithParalyzeAgentTest() {
		Virologist v1 = new Virologist();
		Virologist v2 = new Virologist();
		
		Game.Clear();
		Game g = Game.GetInstance();
		
		g.AddVirologist(v1);
		g.AddVirologist(v2);
				
		v2.AddAgent(new ParalyzeVirus());
		
		v1.EndTurn();		
	}

	/**
	 * Új kör táncóló ágensel tesztelése
	 */
	private static void StartTurnWithDanceAgentTest() {
		Virologist v1 = new Virologist();
		Virologist v2 = new Virologist();
		
		Game.Clear();
		Game g = Game.GetInstance();
		
		g.AddVirologist(v1);
		g.AddVirologist(v2);

				
		FreeField f1 = new FreeField();
		FreeField f2 = new FreeField();
		FreeField f3 = new FreeField();
		FreeField f4 = new FreeField();

		// f1 is in the middle and every other field is a neighbour
		f1.AddNeighbour(f2); // add neighbours of f1
		f1.AddNeighbour(f3);
		f1.AddNeighbour(f4);
		
		f2.AddNeighbour(f1); // add f1 as neighbour to neighbours
		f3.AddNeighbour(f1);
		f4.AddNeighbour(f1);
		
		v2.SetField(f1); // set the current field of the upcoming virologist
		v2.AddAgent(new DanceVirus(5, v2)); // add dance virus to the virologis
		
		v1.EndTurn();
		
	}

	/**
	 * Új kör indításának tesztelése
	 */
	private static void StartTurnTest() {
		Virologist v1 = new Virologist();
		Virologist v2 = new Virologist();
		
		Game.Clear();
		Game g = Game.GetInstance();
		
		g.AddVirologist(v1);
		g.AddVirologist(v2);
				
		if(AskYesNoQuestion("Hat a soron következõ virológusra amnézia vírus?")) {
			int length = AskQuestion("Hány körig hat még a vírus?",1,5);
			AmnesiaVirus a = new AmnesiaVirus();
			a.SetDuration(length);
			v2.AddAgent(a);
		}
		
		if(AskYesNoQuestion("Hat a soron következõ virológusra védelmi vakcina?")) {
			int length = AskQuestion("Hány körig hat még a vakcina?",1,5);
			ProtectionVaccine a = new ProtectionVaccine();
			a.SetDuration(length);
			v2.AddAgent(a);
		}
		
		v1.EndTurn();
	}

	/**
	 * Felszerelés lopásának tesztelése
	 */
	private static void StealGearTest() {
		Virologist v1 = new Virologist();
		Virologist v2 = new Virologist();
		
		FreeField f = new FreeField();
		
		v1.SetField(f);
		v2.SetField(f);
		
		if(AskYesNoQuestion("Le van bénulva a lopó virológus?")) {
			v1.AddAgent(new ParalyzeVirus());
		}
		if(AskYesNoQuestion("Le van bénulva az \"áldozat\" virológus?")) {
			v2.AddAgent(new ParalyzeVirus());	
		}
		int gearType =AskQuestion("Milyen tárgyat lopjon a virológus?",new String[] {"Kesztyû","Zsák","Köpeny"});
		Gear gear =  (new Gear[] {new Gloves(),new Bag(),new ProtectiveCape()})[gearType];
		v2.GetGear(gear);
		if(AskYesNoQuestion("Van már ilyen felszerelés a lopó virológusnál?")) {
			v1.GetGear((new Gear[] {new Gloves(),new Bag(),new ProtectiveCape()})[gearType]);
		}
		v1.Steal(v2, gear);
		
	}
	
	/**
	 * Anyag lopásának tesztelsée
	 */
	private static void StealMaterialTest() {
		Virologist v1 = new Virologist();
		Virologist v2 = new Virologist();
		
		FreeField f = new FreeField();
		
		v1.SetField(f);
		v2.SetField(f);
		
		if(AskYesNoQuestion("Le van bénulva a lopó virológus?")) {
			v1.AddAgent(new ParalyzeVirus());
		}
		if(AskYesNoQuestion("Le van bénulva az \"áldozat\" virológus?")) {
			v2.AddAgent(new ParalyzeVirus());	
		}
		if(AskYesNoQuestion("Rendelkezik zsákkal a lopó virológus?")) {
			v1.GetGear(new Bag());
		}
		int aminoVir = AskQuestion("Mennyi amino található a lopó virológusnál?"); 
		int nucleoVir = AskQuestion("Mennyi nucleo található a lopó virológusnál?");	
		
		v1.SetAmino(aminoVir);
		v1.SetNucleo(nucleoVir);
		
		int aminoVir2 = AskQuestion("Mennyi amino található az \"áldozat\" virológusnál?"); 
		int nucleoVir2 = AskQuestion("Mennyi nucleo található az \\\"áldozat\\\" virológusnál?");	
		
		v2.SetAmino(aminoVir2);
		v2.SetNucleo(nucleoVir2);
		
		v1.StealMaterials(v2);
	}

	/**
	 * Ágens kenésének tesztelése
	 */
	private static void UseAgentTest() {
		Virologist v1= new Virologist();
		Virologist v2 = new Virologist();
		FreeField f = new FreeField();
		v1.SetField(f);
		v2.SetField(f);
		int agent = AskQuestion("Milyen ágenst kenjen a virológus?",new String[] {"Bénító","Amnézia","Védelmi","Táncoló"});
		if(AskYesNoQuestion("Le van bénulva a virológus?")) {
			v1.AddAgent(new ParalyzeVirus());			
		}
		if(AskYesNoQuestion("A másik virólógusra hat védelmi vakcina?")) {
			v2.AddAgent(new ProtectionVaccine());			
		}
		if(AskYesNoQuestion("A másik virólóguson van védelmi köpeny?")) {
			v2.GetGear(new ProtectiveCape());			
		}
		if(AskYesNoQuestion("A másik virólóguson van kesztyû?")) {
			v2.GetGear(new Gloves());				
		}
		Agent[] agents = new Agent[] {new ParalyzeVirus(),new AmnesiaVirus(), new ProtectionVaccine(), new DanceVirus()};
		v1.AddAgentToInventory(agents[agent]);
		v1.UseAgent(v2,agents[agent]);
	}
	
	/**
	 * Játék indításának tesztelése
	 */
	private static void StartGameTest() {
		Game.GetInstance().StartGame("sampleGame", 100,new String[] {"Virológus 1","Virológus2","Virológus3"});
	}

	/**
	 * Játék betöltésének tesztelése	
	 */
	private static void SaveGameTest() {
		Game.Clear();
		Game g = Game.GetInstance();
		g.SetSaveFile("sampleGame");
		g.AddVirologist(new Virologist("v1"));
		g.AddVirologist(new Virologist("v2"));
		g.AddField(new Laboratory(new ParalyzeGeneticCode(), false));
		g.AddField(new Laboratory(new AmnesiaGeneticCode(), false));
		g.AddField(new FreeField());		
		g.SaveGame();		
	}
	
	/**
	 * Játék betöltésének tesztelése	
	 */
	private static void LoadGameTest() {
		Game.GetInstance().LoadGame("sampleGame");		
	}

	/**
	 * Ágens létrehozásának tesztelése
	 */
	private static void CreateAgentTest() {
		Virologist v = new Virologist();
		if(AskYesNoQuestion("Le van bénulva a virológus?")) {
			v.AddAgent(new ParalyzeVirus());
		}
		if(AskYesNoQuestion("Hat a virológusra amnézia vírus?")) {
			v.AddAgent(new AmnesiaVirus());
		}
		int codeToUse = AskQuestion("Melyik ágenst hozza létre a virológus?",new String[] {"Bénító","Amnézia","Védelmi","Táncoló"});
		
		GeneticCode c = new GeneticCode[] {new ParalyzeGeneticCode(),new AmnesiaGeneticCode(),new ProtectionGeneticCode(),new DanceGeneticCode()}[codeToUse];
		v.AddGeneticCode(c);
		
		int aminoVir = AskQuestion("Mennyi amino található a virológusnál?"); 
		int nucleoVir = AskQuestion("Mennyi nucleo található a virológusnál?");	
		
		v.SetAmino(aminoVir);
		v.SetNucleo(nucleoVir);
		
		v.CreateAgent(c);
	}

	///
	///	Anyag felvételének tesztelése
	///
	private static void PickUpMaterialTest() {
		Virologist v = new Virologist();
		City c = new City();
		if(AskYesNoQuestion("Le van bénulva a virológus?")) {
			v.AddAgent(new ParalyzeVirus());
		}	
		int amino = AskQuestion("Mennyi amino található a raktárban?"); 
		int nucleo = AskQuestion("Mennyi nucleo található a raktárban?");	
		
		int aminoVir = AskQuestion("Mennyi amino található a virológusnál?"); 
		int nucleoVir = AskQuestion("Mennyi nucleo található a virológusnál?");	
		
		v.SetAmino(aminoVir);
		v.SetNucleo(nucleoVir);
		
		if(AskYesNoQuestion("Van a virológusnál zsák?")) {
			v.GetGear(new Bag());
		}
		
		Warehouse s = new Warehouse();
		s.SetAmino(amino);
		s.SetNucleo(nucleo);
		c.AddBuilding(s);
		v.SetField(c);
		v.InteractWithField();
		
	}

	///
	/// Játékos lépésének tesztelése
	///
	public static void VirologistMoveTest() {
		Virologist v = new Virologist("v");
		Field f = new FreeField();
		Field f2 = new FreeField();

		v.SetField(f);
		if(AskYesNoQuestion("Legyen bénító ágens a varázslón?")) {
			ParalyzeVirus p = new ParalyzeVirus();
			v.AddAgent(p);
		}
		f.AddNeighbour(f2);
		f2.AddNeighbour(f);
		v.Move(f2);		
	}
	
	///
	/// Genetikai kód tanulásának tesztelése
	///
	private static void LearnGeneticCodeTest() {
		Virologist v = new Virologist("tanuló");
		Laboratory l = new Laboratory();
		Game.GetInstance().Clear();
		Game.GetInstance().AddVirologist(v);
		v.SetField(l);
		if(AskYesNoQuestion("Le van bénulva a virológus?")) {
			v.AddAgent(new ParalyzeVirus());
		}	
		int codeToLearn = AskQuestion("Melyik genetikai kódot tanulja meg a virológus?",new String[] {"Bénító","Amnézia","Védelmi","Táncoló"});
		l.SetGeneticCode(new GeneticCode[] {new ParalyzeGeneticCode(),new AmnesiaGeneticCode(),new ProtectionGeneticCode(),new DanceGeneticCode()}[codeToLearn]);
		int i = 0;
		if(codeToLearn!=0&&AskYesNoQuestion("A virológus már megtanulta a bénító genetikai kódot")) {
			ParalyzeGeneticCode p = new ParalyzeGeneticCode();
			v.AddGeneticCode(p);
			i++;
		}
		if(codeToLearn!=1&&AskYesNoQuestion("A virológus már megtanulta az amnézia genetikai kódot")) {
			AmnesiaGeneticCode p = new AmnesiaGeneticCode();
			v.AddGeneticCode(p);
			i++;
		}
		if(codeToLearn!=2&&AskYesNoQuestion("A virológus már megtanulta a védelmi genetikai kódot")) {
			ProtectionGeneticCode p = new ProtectionGeneticCode();
			v.AddGeneticCode(p);
			i++;
		}
		if(codeToLearn!=3&&i!=3&&AskYesNoQuestion("A virológus már megtanulta a táncoló genetikai kódot")) {
			DanceGeneticCode p = new DanceGeneticCode();
			v.AddGeneticCode(p);
		}
		v.InteractWithField();
	}

	///
	///	Védõ felszerelés felvételének tesztelése
	///
	private static void PickUpGearTest() {
		Virologist v = new Virologist();
		City c = new City();
		Gear g = null;
		if(AskYesNoQuestion("Le van bénulva a virológus?")) {
			v.AddAgent(new ParalyzeVirus());
		}	
		int localGear = AskQuestion("Milyen tárgyat vegyen fel a virológus?",new String[] {"Kesztyû","Zsák","Köpeny"});
		
		Gear[] gears = new Gear[] {new Gloves(),new Bag(),new ProtectiveCape()};
		g = gears[localGear];
		if(AskYesNoQuestion("Van már a virológusnál ugyan ilyen felszerelés?")) {
			Gear[] gears2 = new Gear[] {new Gloves(),new Bag(),new ProtectiveCape()};
			v.GetGear(gears2[localGear]);
		}
		Shelter s = new Shelter();
		s.SetGear(g);
		c.AddBuilding(s);
		v.SetField(c);
		v.InteractWithField();
	}

}
