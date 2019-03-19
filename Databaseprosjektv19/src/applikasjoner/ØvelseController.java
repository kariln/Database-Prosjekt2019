package applikasjoner;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import modeller.�velse;
import modeller.Apparat;
import modeller.Dbcon;
import modeller.Logg;

public class �velseController {
	List<�velse> �velse = new ArrayList<>();
	
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
	}
	
	public �velseController() {
		refresh();
	}
	
	public void printList() {
		for(�velse ele : �velse) {
			System.out.println(ele.get�velseId() + " " + ele.getNavn() + " " + ele.getBeskrivelse() + " " + ele.getFastmontert());
		}
	}
	public void addNew�velse(String navn, String beskrivelse, boolean fastmontert) {
		int id = �velse.size()+1;
		�velse nytt = new �velse(id, navn, beskrivelse, fastmontert);
		// Legger til i database.
		nytt.add(connect());
		refresh();
	}
	
	//legger til logg
	public �velse get�velse(int �velse_id) {
		return �velse.get(�velse_id-1);
	}

	
	public void addLogg(int �velse_id, Timestamp logg_tidspunkt, int sett, int rep, int kilo, Connection connection) {
		//Logg.knyttloggtil�velse(logg_tidspunkt, connection);
		Logg ny_logg = new Logg(�velse_id,logg_tidspunkt,sett,rep,kilo);
		ny_logg.add(connection);
		ny_logg.refresh(connection);
		ny_logg.save(connection);
		
	}
	 
}