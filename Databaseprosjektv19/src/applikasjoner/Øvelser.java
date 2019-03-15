package applikasjoner;

import java.sql.*;
import java.util.List;

import modeller.Øvelse;

public class Øvelser {
	private List<Øvelse> liste;
	
	public Øvelser(Connection conn) {
		try {
			String SQL = "SELECT * FROM øvelse;";
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
