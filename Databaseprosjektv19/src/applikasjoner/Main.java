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

		try {
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery("show tables");
			while (rs.next()) {
//				System.out.println(rs.getString(1));

				//System.out.println(rs.getString(1));
			}
		} catch(SQLException e) {
			System.out.println("feil igjen " +e.getMessage());
		}
	
		System.out.println("test av objektene mine");
		System.out.println(app1.getApparatId());
		System.out.println(app1.getNavn());
		
		Apparat app2 = new Apparat(2);
		app2.setNavn("Mølle");
		app2.setBrukerInstruks("LØP");
		System.out.println("heihei - dette funker ikke");
		System.out.println("heiheihei");
			
		System.out.println("Printe alle forekomster i tabellen");
		Apparat test = new Apparat(22);
		try {
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery("select * from apparat");
			System.out.println("Dette funker");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
			
		} catch(SQLException e){
			System.out.println(e.getMessage());
		} finally {
			connection.disconnect();
		}
	}
}