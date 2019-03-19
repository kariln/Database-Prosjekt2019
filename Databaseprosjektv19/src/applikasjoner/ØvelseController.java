package applikasjoner;

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
import modeller.�velse;
import modeller.�velsesgruppe;
import modeller.Apparat;
import modeller.Dbcon;
import modeller.Logg;

public class �velseController {
	private List<�velse> �velse = new ArrayList<>();
	private List<�velsesgruppe> �velsesgrupper = new ArrayList<>();
	
	Dbcon connection = new Dbcon();
	
	private Connection connect() {
		Dbcon connection = new Dbcon();
		connection.connect();
		Connection connect = connection.getConnection();
		return connect;
	}
	private void refresh() {
		�velse.clear();
		�velse = �velse.list�velser(connect());
		�velsesgrupper = �velsesgruppe.list�velsesgrupper(connect());
	}
	
	public �velseController() {
		refresh();
	}
	
	public void printList() {
		for(�velse ele : �velse) {
			System.out.println(ele.get�velseId() + " " + ele.getNavn() + " " + ele.getBeskrivelse() + " " + ele.getFastmontert());
		}
	}
	
	public void printgruppeList() {
		for (�velsesgruppe ele: �velsesgrupper) {
			System.out.println(ele.getId() + " " + ele.getNavn() + " " +ele.getBeskrivelse());
		}
	}
	public void addNew�velse(String navn, String beskrivelse, boolean fastmontert) {
		int id = �velse.size()+1;
		�velse nytt = new �velse(id, navn, beskrivelse, fastmontert);
		// Legger til i database.
		nytt.add(connect());
		�velse.add(nytt);
		refresh();
	}
	
	public �velse get�velse(int �velse_id) {
		return �velse.get(�velse_id-1);
	}

	
	public void addLogg(int �velse_id, Timestamp logg_tidspunkt, int sett, int rep, int kilo, Connection connection) {
		//Logg.knyttloggtil�velse(logg_tidspunkt, connection);
		Logg ny_logg = new Logg(�velse_id,logg_tidspunkt,sett,rep,kilo);
		ny_logg.add(connection);
		ny_logg.refresh(connection);
		//ny_logg.save(connection);
		
	}

	// legge til en ny �velsesgruppe

	public void add�velsesgruppe(String navn, String beskrivelse) {
		int �velsesgruppe_id = �velsesgrupper.size()+1;
		�velsesgruppe ny = new �velsesgruppe(�velsesgruppe_id, navn, beskrivelse);
		ny.add(connect());
		�velsesgrupper.add(ny);
	}

	//legge til �velse i gruppe
	public void add�velseTilGruppe(int �velse_id, int gruppe_id) {
		//legger til i tabellen �velse_gruppe
		//forutsetter at gruppenummer er kjent
		get�velse(�velse_id).knytt�velseTilGruppe(gruppe_id, connect());
	}

	//finne �velser som er i samme gruppe

	public void get�velserGruppe(int �velsesgruppe_id) {
		String s = new String();
		try {
			String SQL = "select �velse.�velse_id, �velse.navn from �velse join �velse_gruppe on �velse.�velse_id = �velse_gruppe.�velse_id where �velse_gruppe.�velsesgruppe_id=? ";
			PreparedStatement st =connect().prepareStatement(SQL);
			st.setInt(1, �velsesgruppe_id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				s+= "�velser i �velsesgruppe: " +�velsesgruppe_id +'\n';
				s += "�velsesid: " + rs.getInt("�velse_id");
				s += " Navn p� �velse: " + rs.getString("navn") +'\n'; 
			}
			System.out.println(s);
		} catch(SQLException e) {
			System.out.println("db error during selection of �velsegruppe" + e.getMessage());
		}
	}	
	
	public void findLoggInterval(int �velse_id, String dato1, String dato2) {
		String s = new String();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dt1 = new java.sql.Date(sdf.parse(dato1).getTime());
			Date dt2 = new java.sql.Date(sdf.parse(dato2).getTime());				
		    Timestamp t1 = new Timestamp(dt1.getTime());
		    Timestamp t2 = new Timestamp(dt2.getTime());
			String SQL = " SELECT * FROM logg WHERE dato_tidspunkt BETWEEN ? AND ? AND �velse_id =?";
			PreparedStatement st = connect().prepareStatement(SQL);
			st.setTimestamp(1, t1);
			st.setTimestamp(2, t2);
			st.setInt(3, �velse_id);
			st.execute();
			ResultSet rs = st.executeQuery();
			s+= "Logger knyttet til �velsen i angitt tidsperiode:\n";
			while (rs.next()) {
				s+= "Logg til �velse: " +rs.getString("�velse_id") + ", sett: " + rs.getInt("sett") + ", repetisjoner: " +rs.getInt("repetisjoner")+", kilo:" +rs.getInt("kg")+'\n';
			}
			System.out.println(s);
			
		} catch(SQLException | ParseException e) {
			System.out.println("db error during selection of logg" + e.getMessage());
		}
	}
	
/*	public void findLoggIntervall(int N) {
		String s = new String();
		int i = 0;
		s = "De" +  N + "siste loggene:" + '\n';
		try {
			String SQL = "SELECT * FROM logg ORDER BY dato_tidspunkt DESC LIMIT ?";
			PreparedStatement st = connect().prepareStatement(SQL);
			st.setInt(1,N);
			st.execute();
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				i = i+1;
				s += "Den " + i + " siste loggen: ";
				s += " �velse_id: " + rs.getInt("�velse_id");
				s += " dato_tidspunkt: " + rs.getTimestamp("dato_tidspunkt");
				s += " sett: " + rs.getInt("sett");
				s += " repetisjoner: " + rs.getInt("repetisjoner");
				s += " kg: " + rs.getInt("kg") + '\n';
			}
			
			System.out.println(s);
		} catch(SQLException e) {
			System.out.println("db error during selection of logg" + e.getMessage());
		}
	}*/
	

}