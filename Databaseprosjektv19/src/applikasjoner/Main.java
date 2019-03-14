package applikasjoner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeller.Apparat;
import modeller.Dbcon;

public class Main {
	public static void main(String[] args) {
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
		app2.setNavn("M�lle");
		app2.setBrukerInstruks("L�P");
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
	}
}