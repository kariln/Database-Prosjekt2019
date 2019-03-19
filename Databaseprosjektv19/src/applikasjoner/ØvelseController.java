package applikasjoner;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import modeller.Øvelse;
import modeller.Apparat;
import modeller.Dbcon;
import modeller.Logg;

public class ØvelseController {
	List<Øvelse> øvelse = new ArrayList<>();
	
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
		ny_logg.refresh(connection);
		ny_logg.save(connection);
		
	}
	 
}