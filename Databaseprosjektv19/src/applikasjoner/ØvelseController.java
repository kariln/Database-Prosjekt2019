package applikasjoner;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	public void addNewØvelse(String navn, String beskrivelse, boolean fastmontert) {
		int id = øvelse.size()+1;
		Øvelse nytt = new Øvelse(id, navn, beskrivelse, fastmontert);
		// Legger til i database.
		nytt.add(connect());
		øvelse.add(nytt);
		refresh();
	}
	
	//legger til logg
	public Øvelse getØvelse(int øvelse_id) {
		return øvelse.get(øvelse_id-1);
	}

	
	public void addLogg(int øvelse_id, Timestamp logg_tidspunkt, int sett, int rep, int kilo, Connection connection) {
		//Logg.knyttloggtiløvelse(logg_tidspunkt, connection);
		Logg ny_logg = new Logg(øvelse_id,logg_tidspunkt,sett,rep,kilo);
		ny_logg.add(connection);
		//ny_logg.refresh(connection);
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

	public String getØvelserGruppe(int øvelsesgruppe_id) {
		String s = new String();
		try {
			String SQL = "select øvelse.øvelse_id, øvelse.navn from øvelse join øvelse_gruppe on øvelse.øvelse_id = øvelse_gruppe.øvelse_id where øvelse_gruppe.øvelsesgruppe_id=? ";
			PreparedStatement st =connect().prepareStatement(SQL);
			st.setInt(1, øvelsesgruppe_id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				s += "Øvelsesid: " + rs.getInt("øvelse_id");
				s+= "Navn på øvelse: " + rs.getString("navn") +'\n'; 
			}

		} catch(SQLException e) {
			System.out.println("db error during selection of øvelsegruppe" + e.getMessage());
		}
		return s;
	}	
	
	
}