package applikasjoner;

import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;
import modeller.Apparat;
import modeller.Dbcon;

public class ApparatController{
	private List<Apparat> apparater = new ArrayList<>();

	Dbcon connection = new Dbcon();
	
	ApparatController() {
		getDatabase();
	}

	public void getDatabase() {
		connection.connect();
		Connection connect = connection.getConnection();
		apparater = Apparat.listApparater(connect);
	}
	
	public String toString() {
		String s = new String();
		for (int i =0; i <= apparater.size()-1; i++){
			s += apparater.get(i).toString() + '\n';
		}
		return s;
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
		return apparater.get(apparat_id-1);
	}
	
	public void refresh() {
		connection.connect();
		Connection connect = connection.getConnection();
		for (Apparat a: apparater) {
			a.refresh(connect);
		}
	}
}

