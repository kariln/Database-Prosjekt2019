package Applikasjon;

import java.sql.*;
import java.util.*;


public class Main {
	public static void main(String[] args) {
		Testapplikasjon test = new Testapplikasjon();
//		test.password = "root";
//		test.username = "root";
		test.connect();
		// printer alle tables i databasen
		try {
		Statement statement = test.conn.createStatement();
		ResultSet rs = statement.executeQuery("SHOW tables");
		
		while(rs.next()) {
			System.out.println(rs.getString(1));
		}
		test.conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage());
		}
	}
}
