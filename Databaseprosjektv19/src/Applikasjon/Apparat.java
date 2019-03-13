package Applikasjon;

import java.sql.*;

public class Apparat extends Dbcon implements ActiveDomainObject {
	private int apparat_id;
	private String navn;
	private String brukerinstruks;
	
	//konstruktør - gjør at det ikke er mulig å opprette et apparat uten en id
	public Apparat(int apparat_id) {
		this.apparat_id = apparat_id;
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
			String SQL = "update apparat set navn=?, brukerinstruks=?, where apparat_id=?";
			PreparedStatement st = conn.prepareStatement(SQL);
			st.setString(1, this.navn);
			st.setString(2, this.brukerinstruks);
			st.setInt(3, this.apparat_id);
			st.executeUpdate();
		} catch (SQLException e) { 
			System.out.println("db error during update of apparat: " + e.getMessage());
		}		
	}
	
	public static void main(String[] args) {
        System.out.print("hei\n");
		Apparat app1 = new Apparat(1);
		app1.connect();
		
		app1.initialize(app1.conn);
		System.out.println("ID: " + app1.getApparatId() +" Navn: "+ app1.getNavn() + " Brukerinstuks: " + app1.getBrukerInstruks());

		try {
			Statement statement = app1.conn.createStatement();
			ResultSet rs = statement.executeQuery("show tables");
			while (rs.next()) {
				//System.out.println(rs.getString(1));
			}
		} catch(SQLException e) {
			System.out.println("feil igjen " +e.getMessage());
		}
	
		app1.disconnect();
	
		System.out.println("test av objektene mine");
		System.out.println(app1.getApparatId());
		System.out.println(app1.getNavn());
		
		Apparat app2 = new Apparat(2);
		app2.connect();
		app2.setNavn("Mølle");
		app2.setBrukerInstruks("LØP");
		System.out.println("heihei - dette funker ikke");
		app2.save(app2.conn);
		System.out.println("heiheihei");
		app2.disconnect();
		
		
		System.out.println("Printe alle forekomster i tabellen");
		Apparat test = new Apparat(22);
		test.connect();
		try {
			Statement statement = test.conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from apparat");
			System.out.println("Dette funker");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
			
		} catch(SQLException e){
			System.out.println(e.getMessage());
		} finally {
			test.disconnect();
		}
	}
}
