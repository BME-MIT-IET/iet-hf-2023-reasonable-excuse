package com.operativ_tarsulat.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Tests {

	public static void main(String[] args) {
		
		int counter = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true&&counter<10) {
			System.out.println("Adjon meg egy parancsot!");
			String command = "";
			try {
				command = br.readLine();
			}catch(IOException e)
			{
				e.printStackTrace();
			}
			switch(command) {
			case "loadGame": 
				LoadGameTest();
				break;
			case "exitGame":
				ExitGameTest();
				break;
			case "startGame":
				StartGameTest();
				break;
			case "playerSteps":
				PlayerStepsTest();
				break;
			case "learnGeneticCode":
				LearnGeneticCodeTest();
				break;
			case "dieVirologist":
				PlayerDiesTest();
				break;
			case "pickUpMaterial":
				PickUpMaterialTest();
				break;
			case "stealGear":
				StealGearTest();
				break;
			case "createAgent":
				CreateAgentTest();
				break;
			case "useAgent":
				UseAgentTest();
				break;
			case "stealMaterial":
				StealMaterialTest();
				break;
			
				
			
			default: System.out.println("Nem ismert parancs!");
			}
			counter++;
		}
		
		
	}
	
	static void LoadGameTest()
	{		
		BufferedReader lr = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Játék neve: ");
		String gameName = "";
		try {
			gameName = lr.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Game.GetInstance().LoadGame(gameName);
		
	}
	
	static void ExitGameTest()
	{
		BufferedReader lr = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("El akarod menteni a fájlt?(I/N) ");
		String answer = "";
		try {
			answer = lr.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(answer.equals("I"))Game.GetInstance().SaveGame();
		System.out.println("Sikeres kilépés!");
		Game.GetInstance().ExitGame();
		
		
	}	
	
	static void StartGameTest()
	{
		BufferedReader lr = new BufferedReader(new InputStreamReader(System.in));
		int seed =0;
		String fileName = "";
		String []players = new String [10];
		try {
			System.out.print("Fájlnév: ");
			fileName = lr.readLine();
			System.out.print("Seed: ");
			seed = Integer.parseInt(lr.readLine());
			System.out.print("Játékosok: ");
			players = lr.readLine().split(" ");
			
		} catch (IOException e) {
			System.out.println("Sikertelen pálya betöltés!");
			e.printStackTrace();
		}
		Game.GetInstance().StartGame(fileName, (seed), players);
		System.out.println("Sikeres pálya betöltés");
		
	}
	
	static void PlayerStepsTest()
	{
		BufferedReader lr = new BufferedReader(new InputStreamReader(System.in));
		String name = "";
		try {
			System.out.println("Virológus: ");
			name = lr.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Virologist v = new Virologist(name);
		Field f = new FreeField();
		Laboratory f2 = new Laboratory(new DanceGeneticCode(),true);
		Field f3 = new City();
		v.SetField(f);
		f.AddNeighbour(f2);
		f.AddNeighbour(f3);
		f2.AddNeighbour(f);
		f2.AddNeighbour(f3);
		f3.AddNeighbour(f);
		f3.AddNeighbour(f2);
		List<Field> neigh = f.GetNeighbours();
		System.out.print("Elérhetõ mezõ: ");
		int a = 1;
		for(int i =0;i<neigh.size();i++)
		{
			System.out.print("("+a+")"+neigh.get(i).toString()+",");
			a++;
			
		}
		try {
			a = Integer.parseInt(lr.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		v.Move(neigh.get(a-1));
		
		boolean atment = false;
		for(int i = 0;i<neigh.get(a-1).GetVirologists().length;i++)
		{
			Virologist[] vir = neigh.get(a-1).GetVirologists();
			if(vir[i].getName().equals(name)) atment = true;
		}
		if(atment)System.out.println(name+" sikerült elmozdulnia");
		else System.out.println(name+" nem sikerült elmozdulnia");
		v.InteractWithField();
		if(f2.getHasBearVirus())
		{
			System.out.println(name +" Megfertõzõdött medve vírussal");
		}
		else System.out.println("Nem fertõzõdött meg medvevírussal");
		
		
		
	}

	static void PickUpMaterialTest()
	{
		BufferedReader lr = new BufferedReader(new InputStreamReader(System.in));
		String name = "";
		try {
			System.out.println("Virológus: ");
			name = lr.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Virologist v = new Virologist(name);
		int regiAminoHely = v.GetVacantAmino();
		int regiNucleoHely = v.GetVacantNucleo();
		List<Building> buildings = new LinkedList<Building>();
		buildings.add(new Warehouse(10,10));
		City c = new City(buildings);
		v.SetField(c);
		v.InteractWithField();
		int ujAminoHely = v.GetVacantAmino();
		int ujNucleoHely = v.GetVacantNucleo();
		if(ujAminoHely<regiAminoHely||ujNucleoHely<regiNucleoHely)
		{
			System.out.println("Sikerült felvenni: "+((regiAminoHely-ujAminoHely)+(regiNucleoHely-ujNucleoHely))+" anyagot");
		}
		
	}
	
	static void LearnGeneticCodeTest()
	{
		BufferedReader lr = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Virológus: ");
		String name = "";
		try {
			name = lr.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Virologist v = new Virologist(name);
		Laboratory l = new Laboratory(new AmnesiaGeneticCode(),false);
		int geneticCodes = v.getGeneticCodes().size();
		v.SetField(l);
		v.InteractWithField();
		if(v.getGeneticCodes().size()>geneticCodes)
		{
			System.out.println("Sikerült megtanulni!");
			if(v.getGeneticCodes().size()==4) 
			{
				System.out.println(name+" megnyerte a játékot!");
				Game.GetInstance().EndGame();
			}	
		}
		else System.out.println("Nem sikerült megtanulni!");
		
	}
	
	static void PlayerDiesTest(){
		Scanner scan = new Scanner(System.in);
		String name;

		Axe axe = new Axe();
		Field f = new FreeField();
		System.out.println("Virológus akin a fejszét használták: ");
		name = scan.nextLine();
		Virologist v = new Virologist(name);
		v.SetField(f);

		axe.Attack(v);

		if(Arrays.asList(f.GetVirologists()).contains(v)) System.out.println(v.getName() + " nem tûnt el.");
		else if(!Arrays.asList(f.GetVirologists()).contains(v)) System.out.println(v.getName() +" meghalt");

	}
	
	static void StealGearTest(){
		Scanner scan = new Scanner(System.in);
		System.out.println("A lopast vegrehajto virologus neve: ");
		String name1 = scan.nextLine();
		
		System.out.println("A lopast elszenvedo virologus neve: ");
		String name2 = scan.nextLine();
		

		Virologist v1 = new Virologist(name1);
		Virologist v2 = new Virologist(name2);
		
		Field f = new FreeField();

		v1.SetField(f);
		v2.SetField(f);

		v2.AddAgent(new ParalyzeVirus());
		v2.GetGear(new ProtectiveCape());

		
		System.out.println("Felszereles: (1)Glove (2)Bag (3)Cape (4)Axe");
		int command = scan.nextInt();
		switch (command){
			case 1:
				v1.Steal(v2, new Gloves());
				if(v2.GetGear(new Gloves()) == null)
					System.out.println("Nem sikerult felszerelest lopni.");
				break;
			case 2:
				v1.Steal(v2,new Bag());
				if(v2.GetGear(new Bag()) == null)
					System.out.println("Nem sikerult felszerelest lopni.");
				break;
			case 3:
				v1.Steal(v2,new ProtectiveCape());
				if(v2.GetGear(new ProtectiveCape()) != null)
					System.out.println("A virologus Cape felszerelest lopott " + v2.getName() + " nevu virologustol.");
				break;
			case 4:
				v1.Steal(v2, new Axe());
				if(v2.GetGear(new Axe()) == null)
					System.out.println("Nem sikerult felszerelest lopni.");
				break;
		}
	}

	static void CreateAgentTest()
	{
		BufferedReader lr = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Virológus neve: ");
		String name = "";
		try {
			name = lr.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("(1)Amnesia agent (2)Dance agent (3)Paralyze agent (4)Protection agent");
		int valasz =0;
		try {
			valasz = Integer.parseInt(lr.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		Virologist v = new Virologist(name);
		v.SetAmino(20);
		v.SetNucleo(20);
		v.AddGeneticCode(new AmnesiaGeneticCode());
		v.AddGeneticCode(new DanceGeneticCode());
		v.AddGeneticCode(new ParalyzeGeneticCode());
		v.AddGeneticCode(new ProtectionGeneticCode());
		int regiGcDB = v.getAgentInventory().size();
		switch(valasz)
		{
		case 1:
			v.CreateAgent(v.getGeneticCodes().get(0));
			if(v.getAgentInventory().size()>regiGcDB) System.out.println("Sikerült Amnesia agent készíteni");
			else System.out.println("Nem sikerült agent készíteni");
			break;
		case 2:
			v.CreateAgent(v.getGeneticCodes().get(1));
			if(v.getAgentInventory().size()>regiGcDB) System.out.println("Sikerült Dance agent készíteni");
			else System.out.println("Nem sikerült agent készíteni");
			break;
		case 3:
			v.CreateAgent(v.getGeneticCodes().get(2));
			if(v.getAgentInventory().size()>regiGcDB) System.out.println("Sikerült Paralyze agent készíteni");
			else System.out.println("Nem sikerült agent készíteni");
			break;
		case 4:
			v.CreateAgent(v.getGeneticCodes().get(3));
			if(v.getAgentInventory().size()>regiGcDB) System.out.println("Sikerült Protection agent készíteni");
			else System.out.println("Nem sikerült agent készíteni");
			break;
		case 0: System.out.println("Nem jo valasz");
			break;
		}
		
	}

	static void UseAgentTest()
	{
		BufferedReader lr = new BufferedReader(new InputStreamReader(System.in));
		
		String vn1 = "";
		String vn2 = "";
		int valasz = 0;
		try {
			System.out.print("Ki keni: ");
			vn1 = lr.readLine();
			System.out.print("Kire keni: ");
			vn2 = lr.readLine();
			System.out.println("(1)Amnesia agent (2)Dance agent (3)Paralyze agent (4)Protection agent");
			valasz = Integer.parseInt(lr.readLine());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Virologist v= new Virologist(vn1);
		Virologist v2 = new Virologist(vn2);
		FreeField f = new FreeField();
		v.SetField(f);
		v2.SetField(f);
		
		switch(valasz)
		{
		case 1:
			v.AddGeneticCode(new AmnesiaGeneticCode());
			v.CreateAgent(v.getGeneticCodes().get(0));
			v.UseAgent(v2, v.getAgentInventory().get(0));
			System.out.println("Sikeresen használva lett az Amnesia ágens a "+vn2+" nevû virológuson");
			break;
		case 2:
			v.AddGeneticCode(new DanceGeneticCode());
			v.CreateAgent(v.getGeneticCodes().get(0));
			v.UseAgent(v2, v.getAgentInventory().get(0));
			System.out.println("Sikeresen használva lett az Dance ágens a "+vn2+" nevû virológuson");
			
			break;
		case 3:
			v.AddGeneticCode(new ParalyzeGeneticCode());
			v.CreateAgent(v.getGeneticCodes().get(0));
			v.UseAgent(v2, v.getAgentInventory().get(0));
			System.out.println("Sikeresen használva lett az Paralyze ágens a "+vn2+" nevû virológuson");
			
			break;
		case 4:
			v.AddGeneticCode(new ProtectionGeneticCode());
			v.CreateAgent(v.getGeneticCodes().get(0));
			v.UseAgent(v2, v.getAgentInventory().get(0));
			System.out.println("Sikeresen használva lett az Protection ágens a "+vn2+" nevû virológuson");
			 
			break;
		case 0: System.out.println("Nem jo valasz");
			break;
		}
	}
	
	
	static void StealMaterialTest()
	{
        Scanner scan = new Scanner(System.in);
        System.out.println("A lopast vegrehajto virologus neve: ");
        String name1 = scan.nextLine();

        System.out.println("A lopast elszenvedo virologus neve: ");
        String name2 = scan.nextLine();

        Virologist v1 = new Virologist(name1);
        Virologist v2 = new Virologist(name2);

        Field f = new FreeField();

        v1.SetField(f);
        v2.SetField(f);
        v2.AddAgent(new ParalyzeVirus());

        v1.SetAmino(0);
        v1.SetNucleo(0);

        v2.SetAmino(5);
        v2.SetNucleo(0);

        v1.StealMaterials(v2);

        if(v1.getAminoCount() + v1.getNucleoCount() == 5)
            System.out.println("A virologus " + (v1.getAminoCount() + v1.getNucleoCount()) + " anyagot lopott a " + v2.getName()+ "nevu virologustol.");

    }
	
	
	
}
