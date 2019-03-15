package applikasjoner;

import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;
import modeller.Apparat;
import modeller.Dbcon;

public class ApparatController{
	private List<Apparat> apparater = new ArrayList<>();

	Dbcon connection = new Dbcon();

	public void getDatabase() {
		connection.connect();
		Connection connect = connection.getConnection();
		apparater = Apparat.listApparater(connect);
	}
	
	public void addApparat(String navn, String brukerinstruks) {
		connection.connect();
		Connection connect = connection.getConnection();		
		int apparat_id = apparater.size() +1;	
		Apparat nytt = new Apparat(apparat_id, navn, brukerinstruks);
		//legger til i databasen
		nytt.add(connect);
		//legger til i listen over apparater
		apparater.add(nytt);
	}
	
	public Apparat getApparat(int apparat_id) {
		return apparater.get(apparat_id+1);
	}
}

