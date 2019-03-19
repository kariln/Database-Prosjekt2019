package modeller;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class �velse implements ActiveDomainObject {
	
	private int �velse_id;
	private String navn;
	private String beskrivelse;
	private boolean fastmontert;
	private Apparat apparat;
	
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
	public int get�velseId() {
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
	private Connection connect() {
		Dbcon connection = new Dbcon();
		connection.connect();
		Connection connect = connection.getConnection();
		return connect;
	}
	
	
	@Override
	public void initialize(Connection conn) {
		// bruker prepared statements, les om det p� tutorialspoint
		try {
			String SQL = "select �velse_id, navn, beskrivelse, fastmontert from �velse where �velse_id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			// fyller inn for ?
// HVA ER DETTE???
			st.setInt(1, this.�velse_id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				this.navn = rs.getString("navn");
				this.beskrivelse = rs.getString("beskrivelse");
				this.fastmontert = rs.getBoolean("fastmontert");
				// Apparat ID
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
			String SQL = "update �velse set navn=?, beskrivelse=?, fastmontert=?, where �ving_id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setString(1, this.navn);
			st.setString(2, this.beskrivelse);
			st.setBoolean(3, this.fastmontert);
			st.setInt(4, this.�velse_id);
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
	
	// databasebehandlingsfunksjoner
	public static void print�velser(Connection conn) {
		// Bruker prepared statements
		try {
			String SQL = "SELECT * FROM �velse;";
			PreparedStatement st = conn.prepareStatement(SQL);
			ResultSet rs = st.executeQuery();
			
			// rs = result
			while(rs.next()) {
				System.out.print(rs.getInt(1) + " ");
				System.out.print(rs.getString(2) + "\t ");
				System.out.print(rs.getString(3) + "\t ");
				System.out.println(rs.getBoolean(4));
			}
		} catch (Exception e) {
			
		}
	}
		
	public static List<�velse> list�velser(Connection conn) {
		try {
			String SQL = "SELECT * FROM �velse;";
			PreparedStatement st = conn.prepareStatement(SQL);
			ResultSet rs = st.executeQuery();
			List<�velse> �velser = new ArrayList<>();
			
			while (rs.next()) {
				int �velse_id = rs.getInt(1);
				
				// Bruker konstrukt�ren til �velse.
				�velser.add(new �velse(�velse_id));
			}
			// Henter navn++ fra database.
			for(�velse ele : �velser) {
				ele.initialize(conn);
			}
			return �velser;
			
		} catch (SQLException e) {
			// Catch me if you can.
			System.out.println("db error during select from �velse: " + e.getMessage());
		}
		return null;
	}
	public void knytt�velseTilApparat(int appId) {
		try {
			String SQL = "INSERT INTO �velse_apparat VALUES (?, ?);";
			PreparedStatement st = connect().prepareStatement(SQL);
			st.setInt(1, appId);
			st.setInt(2, this.�velse_id);
			st.execute();
		} catch (SQLException e) {
			System.out.println("db error during insert to �velse_apparat." + e.getMessage());
		}
	}
	
	public void knytt�velseTilGruppe(int gruppe_id, Connection connect) {
		try {
			String SQL = "INSERT INTO �velse_gruppe VALUES (?,?);";
			PreparedStatement st = connect.prepareStatement(SQL);
			st.setInt(1, this.�velse_id);
			st.setInt(2, gruppe_id);
			st.execute();
			
		} catch(SQLException e) {
			System.out.println("db error during insertion to �velse_gruppe" + e.getMessage());
		}
	}
}