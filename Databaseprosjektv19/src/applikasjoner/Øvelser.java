package applikasjoner;

import java.sql.*;
import java.util.List;

import modeller.�velse;

public class �velser {
	private List<�velse> liste;
	
	public �velser(Connection conn) {
		try {
			String SQL = "SELECT * FROM �velse;";
			PreparedStatement st = conn.prepareStatement(SQL);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt(1);
				liste
			}
			
		} catch (Exception e) {
			
		}
	}
}
