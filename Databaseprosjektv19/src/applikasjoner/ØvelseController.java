package applikasjoner;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import modeller.�velse;
import modeller.Apparat;
import modeller.Dbcon;

public class �velseController {
	List<�velse> �velse = new ArrayList<>();
		
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
	
}