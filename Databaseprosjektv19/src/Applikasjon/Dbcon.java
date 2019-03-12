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
			
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SHOW tables");
			
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
			conn.close();
		}
		catch (SQLException ex) {
			System.out.println("SQLException " + ex.getMessage());
		}
	}
	
	/*
    public Connection conn = null;
    public Dbcon () {
    }
    public void connect() {
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            // Properties for user and password. Here the user and password are both 'paulr'
            Properties p = new Properties();
            p.put("user", "root");
            p.put("password", "root");           
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/treningsdagbok?autoReconnect=true&useSSL=false",p);
        } catch (Exception e)
    	{
            throw new RuntimeException("Unable to connect", e);
    	}
    }
    */
}

