package applikasjoner;
import modeller.Treningsøkt;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import modeller.Dbcon;
import modeller.Notat;

public class Treningsøktcontroller {
	private List<Treningsøkt> treningsøkter = new ArrayList<>();
	
	Dbcon connection = new Dbcon();
	
	Treningsøktcontroller() {
		getDatabase();
	}
	
	public List<Treningsøkt> getTreningsøkter() {
		return this.treningsøkter;
	}
	
	//init
	public void getDatabase() {
		connection.connect();
		Connection connect = connection.getConnection();
		treningsøkter = Treningsøkt.listTreningsøkter(connect);
	}
	//legge til treningsøkt med tilhørende data
	public void addTreningsøkt(int varighet) {
		connection.connect();
		Connection connect = connection.getConnection();
		int øvelse_id = treningsøkter.size()+1;
		Treningsøkt ny = new Treningsøkt(øvelse_id, varighet);
		ny.add(connect);
		treningsøkter.add(ny);
	}
	
	//tilhørende data til en treningsøkt
	//uferdig
	public void addNotat(int treningsøkt_id, String formål, int opplevelse, String diverse, int form, int prestasjon) {
		connection.connect();
		Connection connect = connection.getConnection();
		Treningsøkt temp = getTreningsøkt(treningsøkt_id);
		Notat ny = new Notat(temp, formål, opplevelse, diverse, form, prestasjon);
		ny.add(connect);
	}
	
	//getter for treningsøkt, henter på øvelsesid
	public Treningsøkt getTreningsøkt(int øvelse_id) {
		return treningsøkter.get(øvelse_id-1);
	}
	
	//oppdaterer objekter ihht databasen
	public void refresh() {
		connection.connect();
		Connection connect = connection.getConnection();
		for (Treningsøkt økt: treningsøkter) {
			økt.refresh(connect);
		}
	}
	// få opp n sist gjennomførte treningsøkter med notater, der n spesifiseres av bruker
	
	public void getBestemteØkter(int n) {
		connection.connect();
		Connection connect = connection.getConnection();
		String output = new String();
		try {
			String SQL = "Select treningsøkt.økt_id, dato_tidspunkt, varighet, formål, opplevelse, diverse, form, prestasjon from treningsøkt join notat on treningsøkt.økt_id = notat.økt_id order by dato_tidspunkt desc LIMIT ?";
			PreparedStatement st = connect.prepareStatement(SQL);
			st.setInt(1, n);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				output += "øktid:" + rs.getInt("økt_id");
				output += ", gjennomført: " + rs.getTimestamp("dato_tidspunkt");
				output += ", varighet i minutter: " +rs.getInt("varighet");
				output += ", formål: " + rs.getString("formål");
				output += ", opplevelse: " + rs.getInt("opplevelse");
				output += ", diverse: " + rs.getString("diverse");
				output += ", form: " + rs.getInt("form");
				output += ", prestasjon: " +rs.getInt("prestasjon") + "\n";
			}
			
			System.out.println(output);
		} catch (SQLException e) {
			System.out.println("db error during select from join" + e.getMessage());
		}
	}

	public void findTreningsmengde(String dato1, String dato2) {
		connection.connect();
		Connection connect = connection.getConnection();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dt1 = null;
			Date dt2 = null;
			try {
				dt1 = new java.sql.Date(sdf.parse(dato1).getTime());
				dt2 = new java.sql.Date(sdf.parse(dato2).getTime());				
			} catch (ParseException e) {
				System.out.println(e);
			}
		    Timestamp t1 = new Timestamp(dt1.getTime());
		    Timestamp t2 = new Timestamp(dt2.getTime());
			String SQL = " SELECT SUM(varighet) AS total_varighet FROM treningsøkt WHERE dato_tidspunkt BETWEEN ? AND ?";
			PreparedStatement st = connect.prepareStatement(SQL);
			st.setTimestamp(1, t1);
			st.setTimestamp(2, t2);
			ResultSet rs = st.executeQuery();
			if (rs.next()){
				System.out.println("Antall minutter trent: " + rs.getInt("total_varighet"));
			} else {
				System.out.println("Ingen treningsøkter registrert i perioden");
				}
			} catch(SQLException e) {
			System.out.println("db error during selection of logg: " + e.getMessage());
		}
	}
	
}
