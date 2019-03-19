package applikasjoner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import modeller.Apparat;
import modeller.Dbcon;
import modeller.�velse;
import modeller.Trenings�kt;
import applikasjoner.ApparatController;
import applikasjoner.Trenings�ktcontroller;

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
		System.out.print("Hva vil du gj�re?\n"); // Done
		System.out.print("[1] Registrer apparater\n"); // Done
		System.out.print("[2] Registrer �velse\n"); // Done
		System.out.print("[3] Knytt �velse til apparat\n"); // Done
		System.out.print("[4] Registrer trenings�kt\n"); // Done
		System.out.print("[5] Gi meg de n siste trenings�ktene\n"); // Done
		System.out.print("[6] Lag �velsesgruppe \n"); // Done
		System.out.print("[7] Hvis �velser i en gruppe \n"); // Done
		System.out.print("[8] Hvis total treningstid innenfor et intervall \n"); // Done
		System.out.print("[9] Hvis resultat for hver enkelt �velse \n");
		System.out.print("\n");
		
		
		int a = scanInt();
		switch (a) {
		case 1: // Reg apparat.
			regApp();
			break;
		case 2: // Reg �velse
			reg�velse();
			break;
		case 3: // Knytt �velse til apparat
			knytt�velseTilApparat();
			break;
		case 4: // RegTrenings�kt
			RegTrenings�kt();
			break;
		case 5: // Hvis N siste �kter
			NSiste�kter();
			break;
		case 6: // Lag �velsesgruppe
			lag�velsesgruppe();
			break;
		case 7: // Hvis �velse i en gruppe
			hvis�velserIGrupper();
			break;
		case 8: // Hvis total treningstid innenfor et intervall
			hvisTotalTid();
			break;
		case 9:
			hvisResultatFor�velse();
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
	public static void reg�velse() {
		System.out.print("Vi registrerer en �velse :) \n");
		�velseController app1 = new �velseController();
		
		System.out.print("Skriv inn navn: \n");
		String navn2 = scanString();
		System.out.print("Skriv inn beskrivelse: ");
		String beskrivelse = scanString();
		System.out.print("Er �velsen fastmontert? Ja -> 1, Nei -> 0 :");
		int fastmontertInt = scanInt();
		boolean fastmontertBool = false;	
		if(fastmontertInt == 1) {
			fastmontertBool = true;
		}
		app1.addNew�velse(navn2, beskrivelse, fastmontertBool);
		System.out.print("�velsen er lagt til.\n");
		
	}
	public static void knytt�velseTilApparat() {
		System.out.print("Vi knytter en �velse til et apparat.\n");
		ApparatController app = new ApparatController();
		�velseController ov = new �velseController();
		System.out.print("Liste over apparater \n------------------ \n");
		System.out.print(app.toString());
		System.out.print("Liste over �velser \n------------------ \n");
		ov.printList();
		System.out.print("Skriv inn id-en p� apparatet du vil knytte til �velse\n");
		int appId = scanInt();
		System.out.print("Skriv inn id-en p� �velsen du �nsker � knytte til apparatet.\n");
		int ovId = scanInt();
		ov.add�velseTilGruppe(appId, ovId);
	}
	public static void RegTrenings�kt() {
		System.out.print("Vi registrerer en trenings�kt.\n");
		
		Trenings�ktcontroller tre = new Trenings�ktcontroller();
		System.out.print("Skriv inn varighet: \n");
		
		int varighet = scanInt();
		tre.addTrenings�kt(varighet);
		
		System.out.print("Vil du legge til et notat til �kten? Ja -> 1, Nei -> 0\n");
		int yesno = scanInt();
		
		if( yesno == 1 ) {
			System.out.print("Skriv inn form�l: \n");
			String form�l = scanString();
			System.out.print("Skriv inn opplevelse (tall 1-10)\n");
			int opplevelse = scanInt();
			System.out.print("Skriv inn diverse\n");
			String diverse = scanString();
			System.out.print("Skriv inn form (tall 1-10)\n");
			int form = scanInt();
			System.out.print("Skriv inn prestasjon (tall 1-10)\n");
			int prestasjon = scanInt();
			
			
			int siste�kt = (tre.getTrenings�kter().get(tre.getTrenings�kter().size()-1)).get�kt_id();
			
			tre.addNotat(siste�kt, form�l, opplevelse, diverse, form, prestasjon);
			System.out.print("�kt og notat registrert \n");
		} else {
			System.out.print("�kt registrert \n");
		}
		
		System.out.print("");
	}
	public static void NSiste�kter() {
		System.out.print("Tast inn de n siste �ktene du �nsker � vise: \n");
		int n = scanInt();
		
		Trenings�ktcontroller tre = new Trenings�ktcontroller();
		
		tre.getBestemte�kter(n);
	}
	public static void lag�velsesgruppe() {
		System.out.print("Legger til �velsesgruppe. \n");
		System.out.print("Skriv et navn : \n");
		String navn = scanString();
		System.out.print("Skriv inn beskrivelse: \n");
		String beskrivelse = scanString();
		�velseController ov = new �velseController();
		ov.add�velsesgruppe(navn, beskrivelse);
		
	}
	public static void hvis�velserIGrupper() {
		�velseController app = new �velseController();
		app.printgruppeList();
		System.out.print("Skriv inn id-en til gruppen du vil vise �velser fra: \n");
		int n = scanInt();
		app.get�velserGruppe(n);
		
	}
	public static void hvisTotalTid() {
		
		Trenings�ktcontroller tre = new Trenings�ktcontroller();
		System.out.print("Oppgi fra dato p� formatet 'yyyy-MM-dd HH:mm:ss' \n");
		String fra = scanString();
		System.out.print("Oppgi til dato p� formatet 'yyyy-MM-dd HH:mm:ss' \n");
		String til = scanString();
		
		tre.findTreningsmengde(fra, til);
	}
	public static void hvisResultatFor�velse() {
		System.out.print("Resultat for �velse: \n");
		�velseController ov = new �velseController();
		ov.printList();
		System.out.print("Skriv inn �velses-id");
		int ovid = scanInt();
		System.out.print("Oppgi fra dato p� formatet 'yyyy-MM-dd HH:mm:ss' \n");
		String fra = scanString();
		System.out.print("Oppgi til dato p� formatet 'yyyy-MM-dd HH:mm:ss' \n");
		String til = scanString();
		ov.findLoggInterval(ovid, fra, til);
		
	}
}