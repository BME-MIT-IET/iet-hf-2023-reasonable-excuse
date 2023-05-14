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
					System.out.println("K�rem a megadott opci�kb�l v�lasszon");
				}

			}catch(NumberFormatException e) {
				System.out.println("K�rem a megadott opci�kb�l v�lasszon");				
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
					System.out.println("K�rem a keret k�z�tt adjon meg sz�mot! ("+min+"-"+max+")");
				}
			}catch(NumberFormatException e) {
				System.out.println("Sz�mot adjon meg");				
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
			int testCase = AskQuestion("Melyik tesztet szeretn�d futtatni?", new String[]{"Kil�p�s","J�t�kos l�p","Genetikai k�d tanul�sa", "Felszerel�s felv�tele", "Anyag felv�tele", "�gens k�sz�t�se","J�t�k ment�se", "J�t�k bet�lt�se", "J�t�k ind�t�sa","�gens ken�se", "Anyagk�szlet lop�sa", "Felszerel�s lop�sa", "K�r kezdete", "K�r kezdete t�nc �gensel","K�r kezdete b�n�t� �gensel" });
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
			default:
				break;
			}
		}
	}
	
	/**
	 * �j k�r b�n�t� �gensel tesztel�se
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
	 * �j k�r t�nc�l� �gensel tesztel�se
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
	 * �j k�r ind�t�s�nak tesztel�se
	 */
	private static void StartTurnTest() {
		Virologist v1 = new Virologist();
		Virologist v2 = new Virologist();
		
		Game.Clear();
		Game g = Game.GetInstance();
		
		g.AddVirologist(v1);
		g.AddVirologist(v2);
				
		if(AskYesNoQuestion("Hat a soron k�vetkez� virol�gusra amn�zia v�rus?")) {
			int length = AskQuestion("H�ny k�rig hat m�g a v�rus?",1,5);
			AmnesiaVirus a = new AmnesiaVirus();
			a.SetDuration(length);
			v2.AddAgent(a);
		}
		
		if(AskYesNoQuestion("Hat a soron k�vetkez� virol�gusra v�delmi vakcina?")) {
			int length = AskQuestion("H�ny k�rig hat m�g a vakcina?",1,5);
			ProtectionVaccine a = new ProtectionVaccine();
			a.SetDuration(length);
			v2.AddAgent(a);
		}
		
		v1.EndTurn();
	}

	/**
	 * Felszerel�s lop�s�nak tesztel�se
	 */
	private static void StealGearTest() {
		Virologist v1 = new Virologist();
		Virologist v2 = new Virologist();
		
		FreeField f = new FreeField();
		
		v1.SetField(f);
		v2.SetField(f);
		
		if(AskYesNoQuestion("Le van b�nulva a lop� virol�gus?")) {
			v1.AddAgent(new ParalyzeVirus());
		}
		if(AskYesNoQuestion("Le van b�nulva az \"�ldozat\" virol�gus?")) {
			v2.AddAgent(new ParalyzeVirus());	
		}
		int gearType =AskQuestion("Milyen t�rgyat lopjon a virol�gus?",new String[] {"Keszty�","Zs�k","K�peny"});
		Gear gear =  (new Gear[] {new Gloves(),new Bag(),new ProtectiveCape()})[gearType];
		v2.GetGear(gear);
		if(AskYesNoQuestion("Van m�r ilyen felszerel�s a lop� virol�gusn�l?")) {
			v1.GetGear((new Gear[] {new Gloves(),new Bag(),new ProtectiveCape()})[gearType]);
		}
		v1.Steal(v2, gear);
		
	}
	
	/**
	 * Anyag lop�s�nak tesztels�e
	 */
	private static void StealMaterialTest() {
		Virologist v1 = new Virologist();
		Virologist v2 = new Virologist();
		
		FreeField f = new FreeField();
		
		v1.SetField(f);
		v2.SetField(f);
		
		if(AskYesNoQuestion("Le van b�nulva a lop� virol�gus?")) {
			v1.AddAgent(new ParalyzeVirus());
		}
		if(AskYesNoQuestion("Le van b�nulva az \"�ldozat\" virol�gus?")) {
			v2.AddAgent(new ParalyzeVirus());	
		}
		if(AskYesNoQuestion("Rendelkezik zs�kkal a lop� virol�gus?")) {
			v1.GetGear(new Bag());
		}
		int aminoVir = AskQuestion("Mennyi amino tal�lhat� a lop� virol�gusn�l?"); 
		int nucleoVir = AskQuestion("Mennyi nucleo tal�lhat� a lop� virol�gusn�l?");	
		
		v1.SetAmino(aminoVir);
		v1.SetNucleo(nucleoVir);
		
		int aminoVir2 = AskQuestion("Mennyi amino tal�lhat� az \"�ldozat\" virol�gusn�l?"); 
		int nucleoVir2 = AskQuestion("Mennyi nucleo tal�lhat� az \\\"�ldozat\\\" virol�gusn�l?");	
		
		v2.SetAmino(aminoVir2);
		v2.SetNucleo(nucleoVir2);
		
		v1.StealMaterials(v2);
	}

	/**
	 * �gens ken�s�nek tesztel�se
	 */
	private static void UseAgentTest() {
		Virologist v1= new Virologist();
		Virologist v2 = new Virologist();
		FreeField f = new FreeField();
		v1.SetField(f);
		v2.SetField(f);
		int agent = AskQuestion("Milyen �genst kenjen a virol�gus?",new String[] {"B�n�t�","Amn�zia","V�delmi","T�ncol�"});
		if(AskYesNoQuestion("Le van b�nulva a virol�gus?")) {
			v1.AddAgent(new ParalyzeVirus());			
		}
		if(AskYesNoQuestion("A m�sik vir�l�gusra hat v�delmi vakcina?")) {
			v2.AddAgent(new ProtectionVaccine());			
		}
		if(AskYesNoQuestion("A m�sik vir�l�guson van v�delmi k�peny?")) {
			v2.GetGear(new ProtectiveCape());			
		}
		if(AskYesNoQuestion("A m�sik vir�l�guson van keszty�?")) {
			v2.GetGear(new Gloves());				
		}
		Agent[] agents = new Agent[] {new ParalyzeVirus(),new AmnesiaVirus(), new ProtectionVaccine(), new DanceVirus()};
		v1.AddAgentToInventory(agents[agent]);
		v1.UseAgent(v2,agents[agent]);
	}
	
	/**
	 * J�t�k ind�t�s�nak tesztel�se
	 */
	private static void StartGameTest() {
		Game.GetInstance().StartGame("sampleGame", 100,new String[] {"Virol�gus 1","Virol�gus2","Virol�gus3"});
	}

	/**
	 * J�t�k bet�lt�s�nek tesztel�se	
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
	 * J�t�k bet�lt�s�nek tesztel�se	
	 */
	private static void LoadGameTest() {
		Game.GetInstance().LoadGame("sampleGame");		
	}

	/**
	 * �gens l�trehoz�s�nak tesztel�se
	 */
	private static void CreateAgentTest() {
		Virologist v = new Virologist();
		if(AskYesNoQuestion("Le van b�nulva a virol�gus?")) {
			v.AddAgent(new ParalyzeVirus());
		}
		if(AskYesNoQuestion("Hat a virol�gusra amn�zia v�rus?")) {
			v.AddAgent(new AmnesiaVirus());
		}
		int codeToUse = AskQuestion("Melyik �genst hozza l�tre a virol�gus?",new String[] {"B�n�t�","Amn�zia","V�delmi","T�ncol�"});
		
		GeneticCode c = new GeneticCode[] {new ParalyzeGeneticCode(),new AmnesiaGeneticCode(),new ProtectionGeneticCode(),new DanceGeneticCode()}[codeToUse];
		v.AddGeneticCode(c);
		
		int aminoVir = AskQuestion("Mennyi amino tal�lhat� a virol�gusn�l?"); 
		int nucleoVir = AskQuestion("Mennyi nucleo tal�lhat� a virol�gusn�l?");	
		
		v.SetAmino(aminoVir);
		v.SetNucleo(nucleoVir);
		
		v.CreateAgent(c);
	}

	///
	///	Anyag felv�tel�nek tesztel�se
	///
	private static void PickUpMaterialTest() {
		Virologist v = new Virologist();
		City c = new City();
		if(AskYesNoQuestion("Le van b�nulva a virol�gus?")) {
			v.AddAgent(new ParalyzeVirus());
		}	
		int amino = AskQuestion("Mennyi amino tal�lhat� a rakt�rban?"); 
		int nucleo = AskQuestion("Mennyi nucleo tal�lhat� a rakt�rban?");	
		
		int aminoVir = AskQuestion("Mennyi amino tal�lhat� a virol�gusn�l?"); 
		int nucleoVir = AskQuestion("Mennyi nucleo tal�lhat� a virol�gusn�l?");	
		
		v.SetAmino(aminoVir);
		v.SetNucleo(nucleoVir);
		
		if(AskYesNoQuestion("Van a virol�gusn�l zs�k?")) {
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
	/// J�t�kos l�p�s�nek tesztel�se
	///
	public static void VirologistMoveTest() {
		Virologist v = new Virologist("v");
		Field f = new FreeField();
		Field f2 = new FreeField();

		v.SetField(f);
		if(AskYesNoQuestion("Legyen b�n�t� �gens a var�zsl�n?")) {
			ParalyzeVirus p = new ParalyzeVirus();
			v.AddAgent(p);
		}
		f.AddNeighbour(f2);
		f2.AddNeighbour(f);
		v.Move(f2);		
	}
	
	///
	/// Genetikai k�d tanul�s�nak tesztel�se
	///
	private static void LearnGeneticCodeTest() {
		Virologist v = new Virologist("tanul�");
		Laboratory l = new Laboratory();
		Game.GetInstance().Clear();
		Game.GetInstance().AddVirologist(v);
		v.SetField(l);
		if(AskYesNoQuestion("Le van b�nulva a virol�gus?")) {
			v.AddAgent(new ParalyzeVirus());
		}	
		int codeToLearn = AskQuestion("Melyik genetikai k�dot tanulja meg a virol�gus?",new String[] {"B�n�t�","Amn�zia","V�delmi","T�ncol�"});
		l.SetGeneticCode(new GeneticCode[] {new ParalyzeGeneticCode(),new AmnesiaGeneticCode(),new ProtectionGeneticCode(),new DanceGeneticCode()}[codeToLearn]);
		int i = 0;
		if(codeToLearn!=0&&AskYesNoQuestion("A virol�gus m�r megtanulta a b�n�t� genetikai k�dot")) {
			ParalyzeGeneticCode p = new ParalyzeGeneticCode();
			v.AddGeneticCode(p);
			i++;
		}
		if(codeToLearn!=1&&AskYesNoQuestion("A virol�gus m�r megtanulta az amn�zia genetikai k�dot")) {
			AmnesiaGeneticCode p = new AmnesiaGeneticCode();
			v.AddGeneticCode(p);
			i++;
		}
		if(codeToLearn!=2&&AskYesNoQuestion("A virol�gus m�r megtanulta a v�delmi genetikai k�dot")) {
			ProtectionGeneticCode p = new ProtectionGeneticCode();
			v.AddGeneticCode(p);
			i++;
		}
		if(codeToLearn!=3&&i!=3&&AskYesNoQuestion("A virol�gus m�r megtanulta a t�ncol� genetikai k�dot")) {
			DanceGeneticCode p = new DanceGeneticCode();
			v.AddGeneticCode(p);
		}
		v.InteractWithField();
	}

	///
	///	V�d� felszerel�s felv�tel�nek tesztel�se
	///
	private static void PickUpGearTest() {
		Virologist v = new Virologist();
		City c = new City();
		Gear g = null;
		if(AskYesNoQuestion("Le van b�nulva a virol�gus?")) {
			v.AddAgent(new ParalyzeVirus());
		}	
		int localGear = AskQuestion("Milyen t�rgyat vegyen fel a virol�gus?",new String[] {"Keszty�","Zs�k","K�peny"});
		
		Gear[] gears = new Gear[] {new Gloves(),new Bag(),new ProtectiveCape()};
		g = gears[localGear];
		if(AskYesNoQuestion("Van m�r a virol�gusn�l ugyan ilyen felszerel�s?")) {
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
