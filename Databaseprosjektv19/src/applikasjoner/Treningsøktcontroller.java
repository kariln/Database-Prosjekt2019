package applikasjoner;
import modeller.Resultater;
import modeller.Trenings�kt;
import java.util.List;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList; 
import modeller.Dbcon;

public class Trenings�ktcontroller {
	private List<Trenings�kt> trenings�kter = new ArrayList<>();
	private List<Resultater> resultater = new ArrayList<>();
	
	Dbcon connection = new Dbcon();
	
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
		Trenings�kt ny = new Trenings�kt(�velse_id, tid, varighet);
		ny.add(connect);
		trenings�kter.add(ny);
		//m� ogs� ha funksjonalitet for � legge til tilh�rende ting, typ �velser
	}
	
	public void addResultat(Trenings�kt �kt, int form, int prestasjon) {
		connection.connect();
		Connection connect = connection.getConnection();
		Resultater ny = new Resultater(�kt, form, prestasjon);
		resultater.add(ny);
		ny.add(connect);	
	}
	
	public void addNotat() {
		connection.connect();
		Connection connect = connection.getConnection();
	}
	
	public Trenings�kt getTrenings�kt(int �velse_id) {
		return trenings�kter.get(�velse_id-1);
	}
	
	public void refresh() {
		connection.connect();
		Connection connect = connection.getConnection();
		for (Trenings�kt �kt: trenings�kter) {
			�kt.refresh(connect);
		}
	}
	// f� opp n sist gjennomf�rte trenings�kter med notater, der n spesifiseres av bruker
}
