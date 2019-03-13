package modeller;

import java.sql.*;

import java.util.Properties;

import com.mysql.cj.xdevapi.SqlUpdateResult;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbcon {
	
	private Connection conn = null;
	
	public Connection getConnection() {
		return this.conn;
	}
	
	public void connect() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/treningsdagbok?serverTimezone=UTC", "root", "root");

		}
		catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage());
		}
	}
	public void disconnect() {
		if (conn != null) {
			try {
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException" + e.getMessage());
			}	
		}
	}
}
