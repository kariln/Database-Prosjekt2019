package applikasjoner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import modeller.Apparat;
import modeller.Dbcon;
import modeller.Øvelse;

public class Main3 {
	private static String scanString() {
		Scanner reader = new Scanner(System.in);
		String scannedString = reader.next();
		reader.close();
		return scannedString;
	}
	private static int scanInt() {
		Scanner reader = new Scanner(System.in);
		int scannedInt = reader.nextInt();
		reader.close();
		return scannedInt;
	}
	
	public static void main(String[] args) {
		
		System.out.print("hei2\n");
		System.out.print("Velkommet til \nTrenigsdagbok\n");
		
//		System.out.print(scanString());
		start();
		
	}
	public static void start() {
		System.out.print("Hva vil du gjøre?\n"); // TODO
		System.out.print("[1] Registrer apparater\n"); // TODO
		System.out.print("[2] Registrer øvelse\n"); // TODO
		System.out.print("[3] Registrer treningsøkt\n"); // TODO
		System.out.print("[4] Registrer \n"); // TODO
		System.out.print("[5] Registrer \n");
		System.out.print("[6] Registrer \n");
		System.out.print("\n");
		
		
		int a = scanInt();
		switch (a) {
		case 1: a = 1;
			regApp();
			break;
		case 2: a = 2;
			break;
		default:
			System.out.print("Ugyldig valg.");
			start();
			break;
		}
	}
	public static void regApp() {
		System.out.print("Vi registrerer apparat");
		
		
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