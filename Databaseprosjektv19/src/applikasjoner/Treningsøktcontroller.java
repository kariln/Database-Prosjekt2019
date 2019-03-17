package applikasjoner;
import modeller.Resultater;
import modeller.Treningsøkt;
import java.util.List;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList; 
import modeller.Dbcon;

public class Treningsøktcontroller {
	private List<Treningsøkt> treningsøkter = new ArrayList<>();
	private List<Resultater> resultater = new ArrayList<>();
	
	Dbcon connection = new Dbcon();
	
	public void getDatabase() {
		connection.connect();
		Connection connect = connection.getConnection();
		treningsøkter = Treningsøkt.listTreningsøkter(connect);
	}
	//legge til treningsøkt med tilhørende data
	public void addTreningsøkt(Timestamp tid, int varighet) {
		connection.connect();
		Connection connect = connection.getConnection();
		int øvelse_id = treningsøkter.size()+1;
		Treningsøkt ny = new Treningsøkt(øvelse_id, tid, varighet);
		ny.add(connect);
		treningsøkter.add(ny);
		//må også ha funksjonalitet for å legge til tilhørende ting, typ øvelser
	}
	
	public void addResultat(Treningsøkt økt, int form, int prestasjon) {
		connection.connect();
		Connection connect = connection.getConnection();
		Resultater ny = new Resultater(økt, form, prestasjon);
		resultater.add(ny);
		ny.add(connect);	
	}
	
	public void addNotat() {
		connection.connect();
		Connection connect = connection.getConnection();
	}
	
	public Treningsøkt getTreningsøkt(int øvelse_id) {
		return treningsøkter.get(øvelse_id-1);
	}
	
	public void refresh() {
		connection.connect();
		Connection connect = connection.getConnection();
		for (Treningsøkt økt: treningsøkter) {
			økt.refresh(connect);
		}
	}
	// få opp n sist gjennomførte treningsøkter med notater, der n spesifiseres av bruker
}
