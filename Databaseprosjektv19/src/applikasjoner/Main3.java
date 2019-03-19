package applikasjoner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import modeller.Apparat;
import modeller.Dbcon;
import modeller.Øvelse;
import applikasjoner.ApparatController;

//Scanner in = new Scanner(System.in); 
//String s = in.nextLine(); 
//System.out.println("You entered string "+s); 


public class Main3 {
	
	private static String scanString() {
		Scanner reader = new Scanner(System.in);
//		reader.nextLine();
		String scannedString = reader.nextLine();
//		scanClear();
		return scannedString;
		
	}
	private static int scanInt() {
		Scanner reader = new Scanner(System.in);
		int scannedInt = reader.nextInt();
		//reader.close();
//		scanClear();
		return scannedInt;
	}
	private static void scanClear() {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			scanner.next();
		}
	}
	
	public static void main(String[] args) {
		
		System.out.print("hei2\n");
		System.out.print("Velkommet til \nTrenigsdagbok\n");
		
//		System.out.print(scanString());
		while(true) {
			start();
		}
		
	}
	
	public static void start() {
		System.out.print("Hva vil du gjøre?\n"); // Done
		System.out.print("[1] Registrer apparater\n"); // Done
		System.out.print("[2] Registrer øvelse\n"); // Done
		System.out.print("[3] Knytt øvelse til apparat\n"); // TODO
		System.out.print("[4] Registrer treningsøkt\\n"); // TODO
		System.out.print("[5] Registrer \n");
		System.out.print("[6] Registrer \n");
		System.out.print("\n");
		
		
		int a = scanInt();
		switch (a) {
		case 1: // Reg apparat.
			regApp();
			break;
		case 2: // Reg øvelse
			regØvelse();
			break;
		case 3: // Knytt øvelse til apparat
			knyttØvelseTilApparat();
			break;
		case 4: // RegTreningsøkt
			RegTreningsøkt();
			break;
		default:
			System.out.print("Ugyldig valg.");
			break;
		}
	}
	
	public static void regApp() { // Apparat
		System.out.print("Vi registrere et apparat.\n");
		ApparatController app1 = new ApparatController();
		System.out.print("Skriv inn navn: \n");
		String navn = scanString();
		System.out.print("Skriv inn brukerinstruks: \n");
		String brukerinstruks = scanString();
		app1.addApparat(navn, brukerinstruks);
		

	}
	public static void regØvelse() {
		System.out.print("Vi registrerer en øvelse :) \n");
		ØvelseController app1 = new ØvelseController();
		
		System.out.print("Skriv inn navn: \n");
		String navn2 = scanString();
		System.out.print("Skriv inn beskrivelse: ");
		String beskrivelse = scanString();
		System.out.print("Er øvelsen fastmontert? Ja -> 1, Nei -> 0 :");
		int fastmontertInt = scanInt();
		boolean fastmontertBool = false;	
		if(fastmontertInt == 1) {
			fastmontertBool = true;
		}
		app1.addNewØvelse(navn2, beskrivelse, fastmontertBool);
		System.out.print("Øvelsen er lagt til.\n");
		
	}
	public static void knyttØvelseTilApparat() {
		System.out.print("Vi knytter en øvelse til et apparat.\n");
		ApparatController app = new ApparatController();
		ØvelseController ov = new ØvelseController();
		System.out.print("Liste over apparater \n------------------ \n");
		System.out.print(app.toString());
		System.out.print("Liste over øvelser \n------------------ \n");
		ov.printList();
		System.out.print("Skriv inn id-en på apparatet du vil knytte til øvelse\n");
		int appId = scanInt();
		System.out.print("Skriv inn id-en på øvelsen du ønsker å knytte til apparatet.\n");
		int ovId = scanInt();
		ov.addØvelseTilGruppe(appId, ovId);
	}
	public static void RegTreningsøkt() {
		System.out.print("Vi registrerer en treningsøkt.\n");
		
		Treningsøktcontroller tre = new Treningsøktcontroller();
		System.out.print("Skriv inn varighet: \n");
		
		tre.addTreningsøkt(tid, varighet);
		
		
		System.out.print("");
	}

		
////		Apparat app1 = new Apparat(1);
//		ØvelseController app1 = new ØvelseController();
////		app1.printList();
//		app1.addØvelseTilGruppe(2, 1013);
//		app1.getØvelse(1).knyttØvelseTilApparat(1);
		
		
		//app1.listØvelser(connect);
//		System.out.println("ID: " + app1.getØvelseId() +" Navn: "+ app1.getNavn() + " Beskrivelse: " + app1.getBeskrivelse() + " Fastmontert? " + app1.getFastmontert());

//
//	
//		System.out.println("test av objektene mine");
//		System.out.println(app1.getØvelseId());
//		System.out.println(app1.getNavn());
//		System.out.println(app1.getBeskrivelse());
//		System.out.println(app1.getFastmontert());
//		
//		
//		
//		Apparat app2 = new Apparat(2);
//		app2.setNavn("Mølle");
//		app2.setBrukerInstruks("LØP");
//		System.out.println("Printe alle forekomster i tabellen");
//		Apparat test = new Apparat(22);
//		try {
//			Statement statement = connect.createStatement();
//			ResultSet rs = statement.executeQuery("select * from apparat");
//			System.out.println("Dette funker");
//			while (rs.next()) {
//				System.out.println(rs.getString(1));
//			}
//			
//		} catch(SQLException e){
//			System.out.println(e.getMessage());
//		} finally {
//			connection.disconnect();
//		}
}