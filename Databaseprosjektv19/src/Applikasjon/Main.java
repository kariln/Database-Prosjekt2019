package Applikasjon;

import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {
	public static void main(String[] args) {
		Testapplikasjon test = new Testapplikasjon();
//		test.password = "root";
//		test.username = "root";
		test.connect();
	}
}
