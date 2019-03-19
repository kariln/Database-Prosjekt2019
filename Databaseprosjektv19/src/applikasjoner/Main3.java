package applikasjoner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import modeller.Apparat;
import modeller.Dbcon;
import modeller.Øvelse;
import modeller.Treningsøkt;
import applikasjoner.ApparatController;
import applikasjoner.Treningsøktcontroller;

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
		System.out.print("[3] Knytt øvelse til apparat\n"); // Done
		System.out.print("[4] Registrer treningsøkt\n"); // Done
		System.out.print("[5] Gi meg de n siste treningsøktene\n"); // Done
		System.out.print("[6] Lag øvelsesgruppe \n"); // Done
		System.out.print("[7] Hvis øvelser i en gruppe \n"); // Done
		System.out.print("[8] Hvis total treningstid innenfor et intervall \n"); // Done
		System.out.print("[9] Hvis resultat for hver enkelt øvelse \n");
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
		case 5: // Hvis N siste økter
			NSisteØkter();
			break;
		case 6: // Lag øvelsesgruppe
			lagØvelsesgruppe();
			break;
		case 7: // Hvis øvelse i en gruppe
			hvisØvelserIGrupper();
			break;
		case 8: // Hvis total treningstid innenfor et intervall
			hvisTotalTid();
			break;
		case 9:
			hvisResultatForØvelse();
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
		
		int varighet = scanInt();
		tre.addTreningsøkt(varighet);
		
		System.out.print("Vil du legge til et notat til økten? Ja -> 1, Nei -> 0\n");
		int yesno = scanInt();
		
		if( yesno == 1 ) {
			System.out.print("Skriv inn formål: \n");
			String formål = scanString();
			System.out.print("Skriv inn opplevelse (tall 1-10)\n");
			int opplevelse = scanInt();
			System.out.print("Skriv inn diverse\n");
			String diverse = scanString();
			System.out.print("Skriv inn form (tall 1-10)\n");
			int form = scanInt();
			System.out.print("Skriv inn prestasjon (tall 1-10)\n");
			int prestasjon = scanInt();
			
			
			int sisteØkt = (tre.getTreningsøkter().get(tre.getTreningsøkter().size()-1)).getØkt_id();
			
			tre.addNotat(sisteØkt, formål, opplevelse, diverse, form, prestasjon);
			System.out.print("Økt og notat registrert \n");
		} else {
			System.out.print("Økt registrert \n");
		}
		
		System.out.print("");
	}
	public static void NSisteØkter() {
		System.out.print("Tast inn de n siste øktene du ønsker å vise: \n");
		int n = scanInt();
		
		Treningsøktcontroller tre = new Treningsøktcontroller();
		
		tre.getBestemteØkter(n);
	}
	public static void lagØvelsesgruppe() {
		System.out.print("Legger til øvelsesgruppe. \n");
		System.out.print("Skriv et navn : \n");
		String navn = scanString();
		System.out.print("Skriv inn beskrivelse: \n");
		String beskrivelse = scanString();
		ØvelseController ov = new ØvelseController();
		ov.addØvelsesgruppe(navn, beskrivelse);
		
	}
	public static void hvisØvelserIGrupper() {
		ØvelseController app = new ØvelseController();
		app.printgruppeList();
		System.out.print("Skriv inn id-en til gruppen du vil vise øvelser fra: \n");
		int n = scanInt();
		app.getØvelserGruppe(n);
		
	}
	public static void hvisTotalTid() {
		
		Treningsøktcontroller tre = new Treningsøktcontroller();
		System.out.print("Oppgi fra dato på formatet 'yyyy-MM-dd HH:mm:ss' \n");
		String fra = scanString();
		System.out.print("Oppgi til dato på formatet 'yyyy-MM-dd HH:mm:ss' \n");
		String til = scanString();
		
		tre.findTreningsmengde(fra, til);
	}
	public static void hvisResultatForØvelse() {
		System.out.print("Resultat for øvelse: \n");
		ØvelseController ov = new ØvelseController();
		ov.printList();
		System.out.print("Skriv inn øvelses-id");
		int ovid = scanInt();
		System.out.print("Oppgi fra dato på formatet 'yyyy-MM-dd HH:mm:ss' \n");
		String fra = scanString();
		System.out.print("Oppgi til dato på formatet 'yyyy-MM-dd HH:mm:ss' \n");
		String til = scanString();
		ov.findLoggInterval(ovid, fra, til);
		
	}
}