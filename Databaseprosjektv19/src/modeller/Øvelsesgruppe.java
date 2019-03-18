package modeller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Øvelsesgruppe implements ActiveDomainObject{
	private int øvelsesgruppe_id;
	private String navn;
	private String beskrivelse;
	//driter i dette og bruker sql isteden
	//private List<Øvelse> øvelser = new ArrayList<>();
	
	
	public Øvelsesgruppe(int øvelses_id, String navn, String beskrivelse) {
		this.øvelsesgruppe_id = øvelsesgruppe_id;
		this.navn = navn;
		this.beskrivelse= beskrivelse;
	}
	public int getId() {
		return this.øvelsesgruppe_id;
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
			String SQL = "select navn, beskrivelse from notat where øvelsesgruppe_id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1, this.øvelsesgruppe_id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				this.navn = rs.getString("navn");
				this.beskrivelse = rs.getString("beskrivelse");
			}
		} catch (SQLException e) {
			System.out.println("db error during select of øvelsesgruppe: " + e.getMessage());
		}		
		
	}
	@Override
	public void refresh(Connection conn) {
		initialize(conn);
	}
	@Override
	public void save(Connection conn) {
		try {
			String SQL = "update øvelsesgruppe set navn=?, beskrivelse=? where øvelsesgruppe_id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setString(1, this.navn);
			st.setString(2, this.beskrivelse);
			st.execute();
		} catch (SQLException e) {
			System.out.println("db error during update of øvelsesgruppe: " + e.getMessage());
		}
	}
		
	@Override
	public void add(Connection conn) {
		try {
			String SQL = "insert into øvelsesgruppe (øvelsesgruppe_id, navn, beskrivelse) values (?,?,?)";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1, this.øvelsesgruppe_id);
			st.setString(2, this.navn);
			st.setString(3, this.beskrivelse);
			st.execute();
		} catch (SQLException e) {
			System.out.println("db error during insertion into øvelsesgruppe: " + e.getMessage());
		}		
	}
}
