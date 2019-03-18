package applikasjoner;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {
	//Scanner scanner = new Scanner(System.in);
	
	//switch();
	
	//test av treningsøktcontroller og mål 1
	
	public static void main(String[] args) {
		//String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		Treningsøktcontroller controller = new Treningsøktcontroller();
		//controller.addTreningsøkt(new Timestamp(System.currentTimeMillis()), 45);
		controller.getDatabase();
		//controller.addNotat(1, "kondisjon", 5, "spinningtime", 4, 5);
		//controller.addTreningsøkt(new Timestamp(System.currentTimeMillis()), 30);
		//controller.addNotat(2, "noe", 10, "kult", 3, 1);
		controller.getBestemteØkter(1);
	}
	
	/*public static void main(String[] args) {
		Dbcon connection = new Dbcon();
		
		System.out.print("hei\n");
		Apparat app1 = new Apparat(1);
		connection.connect();
		Connection connect = connection.getConnection();
		
		app1.initialize(connect);
		System.out.println("ID: " + app1.getApparatId() +" Navn: "+ app1.getNavn() + " Brukerinstuks: " + app1.getBrukerInstruks());

	
		System.out.println("test av objektene mine");
		System.out.println(app1.getApparatId());
		System.out.println(app1.getNavn());
		app1.setNavn("Kari");
		app1.add(connect);
		
		Apparat app2 = new Apparat(2);
		app2.setNavn("Mølle");
		app2.setBrukerInstruks("LØP");
		app2.add(connect);
		System.out.println("Printe alle forekomster i tabellen");
		Apparat test = new Apparat(22);
		try {
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery("select * from apparat");
			System.out.println("Dette funker");
			while (rs.next()) {
				System.out.println(rs.getString("navn"));
			}
			
		} catch(SQLException e){
			System.out.println(e.getMessage());
		} finally {
//			connection.disconnect();
		}
	}*/
}