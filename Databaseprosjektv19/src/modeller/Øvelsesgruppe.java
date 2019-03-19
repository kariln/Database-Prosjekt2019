package modeller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class �velsesgruppe implements ActiveDomainObject{
	private int �velsesgruppe_id;
	private String navn;
	private String beskrivelse;
	
	public �velsesgruppe(int �velsesgruppe_id, String navn, String beskrivelse) {
		this.�velsesgruppe_id = �velsesgruppe_id;
		this.navn = navn;
		this.beskrivelse= beskrivelse;
	}
	public int getId() {
		return this.�velsesgruppe_id;
	}
	public String getNavn() {
		return this.navn;
	}
	
	public String getBeskrivelse() {
		return this.beskrivelse;
	}
	@Override
	public void initialize(Connection conn) {
		try {
			String SQL = "select navn, beskrivelse from notat where �velsesgruppe_id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1, this.�velsesgruppe_id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				this.navn = rs.getString("navn");
				this.beskrivelse = rs.getString("beskrivelse");
			}
		} catch (SQLException e) {
			System.out.println("db error during select of �velsesgruppe: " + e.getMessage());
		}		
		
	}
	@Override
	public void refresh(Connection conn) {
		initialize(conn);
	}
	@Override
	public void save(Connection conn) {
		try {
			String SQL = "update �velsesgruppe set navn=?, beskrivelse=? where �velsesgruppe_id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setString(1, this.navn);
			st.setString(2, this.beskrivelse);
			st.execute();
		} catch (SQLException e) {
			System.out.println("db error during update of �velsesgruppe: " + e.getMessage());
		}
	}
		
	@Override
	public void add(Connection conn) {
		try {
			String SQL = "insert into �velsesgruppe (�velsesgruppe_id, navn, beskrivelse) values (?,?,?)";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1, this.�velsesgruppe_id);
			st.setString(2, this.navn);
			st.setString(3, this.beskrivelse);
			st.execute();
		} catch (SQLException e) {
			System.out.println("db error during insertion into �velsesgruppe: " + e.getMessage());
		}		
	}
	
	public static List<�velsesgruppe> list�velsesgrupper(Connection conn) {
		try {
			String SQL = "SELECT * FROM �velsesgruppe;";
			PreparedStatement st = conn.prepareStatement(SQL);
			ResultSet rs = st.executeQuery();
			List<�velsesgruppe> �velsesgrupper = new ArrayList<>();
			
			while (rs.next()) {
				int �velsesgruppe_id = rs.getInt("�velsesgruppe_id");
				String navn = rs.getString("navn");
				String beskrivelse = rs.getString("beskrivelse");
				�velsesgrupper.add(new �velsesgruppe(�velsesgruppe_id, navn, beskrivelse));
			}
			return �velsesgrupper;
			
		} catch (SQLException e) {
			// Catch me if you can.
			System.out.println("db error during select from �velsesgrupper: " + e.getMessage());
		}
		return null;
	}
}
