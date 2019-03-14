package modeller;

import java.sql.*;

public class �velse implements ActiveDomainObject {
	
	private int �velse_id;
	private String navn;
	private String beskrivelse;
	private boolean fastmontert;
	
	// Konstrukt�r
	public �velse(int id) {
		this.�velse_id = id;
	}
	
	public �velse(int id, String navn, String beskrivelse, boolean fastmontert) {
		this.�velse_id = id;
		this.navn = navn;
		this.beskrivelse = beskrivelse;
		this.fastmontert = fastmontert;
	}
	// Get
	public int get�velse_id() {
		return this.�velse_id;
	}
	
	public String getNavn() {
		return this.navn;
	}
	
	public String getBeskrivelse() {
		return this.beskrivelse;
	}
	
	public boolean getFastmontert() {
		return this.fastmontert;
	}
	
	// Set
	public void setNavn(String navn) {
		this.navn = navn;
	}
	
	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}
	public void setFastmontert(boolean value) {
		this.fastmontert = value;
	}
	
	// Databasebehandlingsfunksjoner
	@Override
	public void initialize(Connection conn) {
		// bruker prepared statements, les om det p� tutorialspoint
		try {
			String SQL = "select �velse_id, navn, beskrivelse, fastmonter from �velse where �velse_id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			// fyller inn for ?
// HVA ER DETTE???
			st.setInt(1, this.�velse_id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				this.navn = rs.getString("navn");
				this.beskrivelse = rs.getString("beskrivelse");
				this.fastmontert = rs.getBoolean("fastmontert");
			}
			
		} catch (Exception e) {
			System.out.println("db error during select of �velse: "+e.getMessage());	
		}
	}
	
	@Override
	public void refresh(Connection conn) {
		initialize(conn);
	}
	@Override
	public void save(Connection conn) {
		try {
			String SQL = "update �velse set navn=?, where �ving_id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setString(1, this.navn);
			st.setString(2, this.beskrivelse);
			st.setBoolean(3, this.fastmontert);
			st.execute();
			
		} catch (SQLException e) {
			System.out.println("db error during update of �velse: " + e.getMessage() );
			
		}
	}
	public void add(Connection conn) {
		try {
			String SQL = "insert into �velse (�velse_id, navn, beskrivelse, fastmontert) values (?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1, this.�velse_id);
			st.setString(2, this.navn);
			st.setString(3, this.beskrivelse);
			st.setBoolean(4, fastmontert);
			st.execute();
			
		} catch (SQLException e) {
			System.out.println("db eror during insertion to �velse: " + e.getMessage());
		}
	}
}


//-- �velse
//CREATE TABLE �velse (
//    �velse_id INT UNSIGNED NOT NULL,
//    navn VARCHAR(32),
//    beskrivelse TEXT,
//    fastmonter BOOLEAN,
//    PRIMARY KEY (�velse_id)
//);

