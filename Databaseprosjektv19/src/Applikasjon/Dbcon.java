package Applikasjon;

import java.sql.*;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Dbcon {
	
	Connection conn = null;
	
	public void connect() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/treningsdagbok?serverTimezone=UTC", "root", "root");

		}
		catch (SQLException ex) {
			System.out.println("SQLException " + ex.getMessage());
		}
	}
}
