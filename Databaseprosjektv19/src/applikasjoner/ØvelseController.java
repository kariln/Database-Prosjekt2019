package applikasjoner;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import modeller.Øvelse;
import modeller.Apparat;
import modeller.Dbcon;

public class ØvelseController {
	List<Øvelse> øvelse = new ArrayList<>();
		
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
	
}