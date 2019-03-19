package modeller;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class Øvelse implements ActiveDomainObject {
	
	private int øvelse_id;
	private String navn;
	private String beskrivelse;
	private boolean fastmontert;
	private Apparat apparat;
	
	// Konstruktør
	public Øvelse(int id) {
		this.øvelse_id = id;
	}
	
	public Øvelse(int id, String navn, String beskrivelse, boolean fastmontert) {
		this.øvelse_id = id;
		this.navn = navn;
		this.beskrivelse = beskrivelse;
		this.fastmontert = fastmontert;
	}
	// Get
	public int getØvelseId() {
		return this.øvelse_id;
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
		// bruker prepared statements, les om det på tutorialspoint
		try {
			String SQL = "select øvelse_id, navn, beskrivelse, fastmontert from øvelse where øvelse_id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			// fyller inn for ?
// HVA ER DETTE???
			st.setInt(1, this.øvelse_id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				this.navn = rs.getString("navn");
				this.beskrivelse = rs.getString("beskrivelse");
				this.fastmontert = rs.getBoolean("fastmontert");
				// Apparat ID
			}
			
		} catch (Exception e) {
			System.out.println("db error during select of øvelse: "+e.getMessage());	
		}
	}
	
	@Override
	public void refresh(Connection conn) {
		initialize(conn);
	}
	@Override
	public void save(Connection conn) {
		try {
			String SQL = "update øvelse set navn=?, beskrivelse=?, fastmontert=?, where øving_id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setString(1, this.navn);
			st.setString(2, this.beskrivelse);
			st.setBoolean(3, this.fastmontert);
			st.setInt(4, this.øvelse_id);
			st.execute();
			
		} catch (SQLException e) {
			System.out.println("db error during update of øvelse: " + e.getMessage() );
			
		}
	}
	public void add(Connection conn) {
		try {
			String SQL = "insert into øvelse (øvelse_id, navn, beskrivelse, fastmontert) values (?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1, this.øvelse_id);
			st.setString(2, this.navn);
			st.setString(3, this.beskrivelse);
			st.setBoolean(4, fastmontert);
			st.execute();
			
		} catch (SQLException e) {
			System.out.println("db eror during insertion to øvelse: " + e.getMessage());
		}
	}
	
	// databasebehandlingsfunksjoner
	public static void printØvelser(Connection conn) {
		// Bruker prepared statements
		try {
			String SQL = "SELECT * FROM øvelse;";
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
		
	public static List<Øvelse> listØvelser(Connection conn) {
		try {
			String SQL = "SELECT * FROM øvelse;";
			PreparedStatement st = conn.prepareStatement(SQL);
			ResultSet rs = st.executeQuery();
			List<Øvelse> øvelser = new ArrayList<>();
			
			while (rs.next()) {
				int øvelse_id = rs.getInt(1);
				
				// Bruker konstruktøren til øvelse.
				øvelser.add(new Øvelse(øvelse_id));
			}
			// Henter navn++ fra database.
			for(Øvelse ele : øvelser) {
				ele.initialize(conn);
			}
			return øvelser;
			
		} catch (SQLException e) {
			// Catch me if you can.
			System.out.println("db error during select from øvelse: " + e.getMessage());
		}
		return null;
	}
	public void knyttØvelseTilApparat(int appId) {
		try {
			String SQL = "INSERT INTO øvelse_apparat VALUES (?, ?);";
			PreparedStatement st = connect().prepareStatement(SQL);
			st.setInt(1, appId);
			st.setInt(2, this.øvelse_id);
			st.execute();
		} catch (SQLException e) {
			System.out.println("db error during insert to øvelse_apparat." + e.getMessage());
		}
	}
	
	public void knyttØvelseTilGruppe(int gruppe_id, Connection connect) {
		try {
			String SQL = "INSERT INTO øvelse_gruppe VALUES (?,?);";
			PreparedStatement st = connect.prepareStatement(SQL);
			st.setInt(1, this.øvelse_id);
			st.setInt(2, gruppe_id);
			st.execute();
			
		} catch(SQLException e) {
			System.out.println("db error during insertion to øvelse_gruppe" + e.getMessage());
		}
	}
}