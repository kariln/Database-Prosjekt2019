package applikasjoner;
import modeller.Trenings�kt;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList; 
import modeller.Dbcon;
import modeller.Notat;

public class Trenings�ktcontroller {
	private List<Trenings�kt> trenings�kter = new ArrayList<>();
	
	Dbcon connection = new Dbcon();
	
	//init
	public void getDatabase() {
		connection.connect();
		Connection connect = connection.getConnection();
		trenings�kter = Trenings�kt.listTrenings�kter(connect);
	}
	//legge til trenings�kt med tilh�rende data
	public void addTrenings�kt(Timestamp tid, int varighet) {
		connection.connect();
		Connection connect = connection.getConnection();
		int �velse_id = trenings�kter.size()+1;
		Trenings�kt ny = new Trenings�kt(�velse_id, varighet);
		ny.add(connect);
		trenings�kter.add(ny);
	}
	
	//tilh�rende data til en trenings�kt
	//uferdig
	public void addNotat(int trenings�kt_id, String form�l, int opplevelse, String diverse, int form, int prestasjon) {
		connection.connect();
		Connection connect = connection.getConnection();
		Trenings�kt temp = getTrenings�kt(trenings�kt_id);
		Notat ny = new Notat(temp, form�l, opplevelse, diverse, form, prestasjon);
		ny.add(connect);
	}
	
	//getter for trenings�kt, henter p� �velsesid
	public Trenings�kt getTrenings�kt(int �velse_id) {
		return trenings�kter.get(�velse_id-1);
	}
	
	//oppdaterer objekter ihht databasen
	public void refresh() {
		connection.connect();
		Connection connect = connection.getConnection();
		for (Trenings�kt �kt: trenings�kter) {
			�kt.refresh(connect);
		}
	}
	// f� opp n sist gjennomf�rte trenings�kter med notater, der n spesifiseres av bruker
	
	public void getBestemte�kter(int n) {
		connection.connect();
		Connection connect = connection.getConnection();
		String output = new String();
		try {
			String SQL = "Select trenings�kt.�kt_id, dato_tidspunkt, varighet, form�l, opplevelse, diverse, form, prestasjon from trenings�kt join notat on trenings�kt.�kt_id = notat.�kt_id order by dato_tidspunkt desc LIMIT ?";
			PreparedStatement st = connect.prepareStatement(SQL);
			st.setInt(1, n);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				output += "�ktid:" + rs.getInt("�kt_id");
				output += ", gjennomf�rt: " + rs.getTimestamp("dato_tidspunkt");
				output += ", varighet i minutter: " +rs.getInt("varighet");
				output += ", form�l: " + rs.getString("form�l");
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

}
