package modeller;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class Apparat implements ActiveDomainObject {
	private int apparat_id;
	private String navn;
	private String brukerinstruks;
	
	//konstruktør - gjør at det ikke er mulig å opprette et apparat uten en id
	public Apparat(int apparat_id) {
		this.apparat_id = apparat_id;
	}
	
	public Apparat(int apparat_id, String navn, String brukerinstruks) {
		this.apparat_id = apparat_id;
		this.navn = navn;
		this.brukerinstruks = brukerinstruks;
	}
	
	//gettere og settere
	public int getApparatId() {
		return this.apparat_id;
	}
	public String getNavn() {
		return this.navn;
	}
	public String getBrukerInstruks() {
		return this.brukerinstruks;
	}
	public void setNavn(String navn) {
		this.navn = navn;
	}
	public void setBrukerInstruks(String brukerinstruks) {
		this.brukerinstruks = brukerinstruks;
	}
	
	//databasebehandlingsfunksjoner
	@Override
	public void initialize(Connection conn) {
		// TODO Auto-generated method stub

		//bruker prepared statements, les om det på tutorialspoint
        try {
		    String SQL = "select navn, brukerinstruks from apparat where apparat_id=?";
		    PreparedStatement st = conn.prepareStatement(SQL);
		    //fyller inn for ? 
		    st.setInt(1, this.apparat_id);
            ResultSet rs = st.executeQuery();
            System.out.println(rs); // ??????
            while (rs.next()) {
                this.navn =  rs.getString("navn");
                this.brukerinstruks = rs.getString("brukerinstruks");
            }
        } catch (Exception e) {
            System.out.println("db error during select of apparat: "+e.getMessage());
        }
	}

	@Override
	public void refresh(Connection conn) {
		// TODO Auto-generated method stub	
		initialize(conn);	
	}

	@Override
	public void save(Connection conn) {
		// TODO Auto-generated method stub
		try {
			String SQL = "update apparat set navn=?, brukerinstruks=? where apparat_id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setString(1, this.navn);
			st.setString(2, this.brukerinstruks);
			st.setInt(3, this.apparat_id);
			st.execute();
		} catch (SQLException e) { 
			System.out.println("db error during update of apparat: " + e.getMessage());
		}		
	}
	
	public void add(Connection conn) {
		try {
			String SQL = "insert into apparat (apparat_id, navn, brukerinstruks) values (?,?,?)";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setInt(1,this.apparat_id);
			st.setString(2, this.navn);
			st.setString(3, this.brukerinstruks);
			st.execute();
		} catch (SQLException e) {
			System.out.println("db error during insertion to apparat: " + e.getMessage());
		}
	}
	
	public String toString() {
		return "Apparat: " + this.apparat_id + ", navn: " + this.navn + ", funksjonsbeskrivelse: " + this.brukerinstruks;
	}
	
	public static List<Apparat> listApparater(Connection conn){
		try {
			String SQL = "Select * from øvelse;";
			PreparedStatement st = conn.prepareStatement(SQL);
			ResultSet rs = st.executeQuery();
			
			List<Apparat> apparater = new ArrayList<>();
			
			while (rs.next()) {
				int apparat_id = rs.getInt("apparat_id");
				String navn = rs.getString("navn");
				String funksjonsbeskrivelse = rs.getString("funksjonsbeskrivelse");
				
				apparater.add(new Apparat(apparat_id, navn, funksjonsbeskrivelse));
			}
			return apparater;
		} catch (SQLException e) {
			System.out.println("db error during select from apparat: " + e.getMessage());
		}
		return null; 
	}
}
