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
import modeller.Øvelse;
import modeller.Øvelsesgruppe;
import modeller.Apparat;
import modeller.Dbcon;
import modeller.Logg;

public class ØvelseController {
	private List<Øvelse> øvelse = new ArrayList<>();
	private List<Øvelsesgruppe> øvelsesgrupper = new ArrayList<>();
	
	Dbcon connection = new Dbcon();
	
	private Connection connect() {
		Dbcon connection = new Dbcon();
		connection.connect();
		Connection connect = connection.getConnection();
		return connect;
	}
	private void refresh() {
		øvelse.clear();
		øvelse = Øvelse.listØvelser(connect());
		øvelsesgrupper = Øvelsesgruppe.listØvelsesgrupper(connect());
	}
	
	public ØvelseController() {
		refresh();
	}
	
	public void printList() {
		for(Øvelse ele : øvelse) {
			System.out.println(ele.getØvelseId() + " " + ele.getNavn() + " " + ele.getBeskrivelse() + " " + ele.getFastmontert());
		}
	}
	
	public void printgruppeList() {
		for (Øvelsesgruppe ele: øvelsesgrupper) {
			System.out.println(ele.getId() + " " + ele.getNavn() + " " +ele.getBeskrivelse());
		}
	}
	public void addNewØvelse(String navn, String beskrivelse, boolean fastmontert) {
		int id = øvelse.size()+1;
		Øvelse nytt = new Øvelse(id, navn, beskrivelse, fastmontert);
		// Legger til i database.
		nytt.add(connect());
		øvelse.add(nytt);
		refresh();
	}
	
	public Øvelse getØvelse(int øvelse_id) {
		return øvelse.get(øvelse_id-1);
	}

	
	public void addLogg(int øvelse_id, Timestamp logg_tidspunkt, int sett, int rep, int kilo, Connection connection) {
		//Logg.knyttloggtiløvelse(logg_tidspunkt, connection);
		Logg ny_logg = new Logg(øvelse_id,logg_tidspunkt,sett,rep,kilo);
		ny_logg.add(connection);
		ny_logg.refresh(connection);
		//ny_logg.save(connection);
		
	}

	// legge til en ny øvelsesgruppe

	public void addØvelsesgruppe(String navn, String beskrivelse) {
		int øvelsesgruppe_id = øvelsesgrupper.size()+1;
		Øvelsesgruppe ny = new Øvelsesgruppe(øvelsesgruppe_id, navn, beskrivelse);
		ny.add(connect());
		øvelsesgrupper.add(ny);
	}

	//legge til øvelse i gruppe
	public void addØvelseTilGruppe(int øvelse_id, int gruppe_id) {
		//legger til i tabellen øvelse_gruppe
		//forutsetter at gruppenummer er kjent
		getØvelse(øvelse_id).knyttØvelseTilGruppe(gruppe_id, connect());
	}

	//finne øvelser som er i samme gruppe

	public void getØvelserGruppe(int øvelsesgruppe_id) {
		String s = new String();
		try {
			String SQL = "select øvelse.øvelse_id, øvelse.navn from øvelse join øvelse_gruppe on øvelse.øvelse_id = øvelse_gruppe.øvelse_id where øvelse_gruppe.øvelsesgruppe_id=? ";
			PreparedStatement st =connect().prepareStatement(SQL);
			st.setInt(1, øvelsesgruppe_id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				s+= "Øvelser i øvelsesgruppe: " +øvelsesgruppe_id +'\n';
				s += "Øvelsesid: " + rs.getInt("øvelse_id");
				s += " Navn på øvelse: " + rs.getString("navn") +'\n'; 
			}
			System.out.println(s);
		} catch(SQLException e) {
			System.out.println("db error during selection of øvelsegruppe" + e.getMessage());
		}
	}	
	
	public void findLoggInterval(int øvelse_id, String dato1, String dato2) {
		String s = new String();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dt1 = new java.sql.Date(sdf.parse(dato1).getTime());
			Date dt2 = new java.sql.Date(sdf.parse(dato2).getTime());				
		    Timestamp t1 = new Timestamp(dt1.getTime());
		    Timestamp t2 = new Timestamp(dt2.getTime());
			String SQL = " SELECT * FROM logg WHERE dato_tidspunkt BETWEEN ? AND ? AND øvelse_id =?";
			PreparedStatement st = connect().prepareStatement(SQL);
			st.setTimestamp(1, t1);
			st.setTimestamp(2, t2);
			st.setInt(3, øvelse_id);
			st.execute();
			ResultSet rs = st.executeQuery();
			s+= "Logger knyttet til øvelsen i angitt tidsperiode:\n";
			while (rs.next()) {
				s+= "Logg til øvelse: " +rs.getString("øvelse_id") + ", sett: " + rs.getInt("sett") + ", repetisjoner: " +rs.getInt("repetisjoner")+", kilo:" +rs.getInt("kg")+'\n';
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
				s += " øvelse_id: " + rs.getInt("øvelse_id");
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